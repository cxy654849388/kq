package com.chm.kd.kq.command.impl;

import com.chm.kd.kq.annotations.End;
import com.chm.kd.kq.annotations.Regex;
import com.chm.kd.kq.annotations.Role;
import com.chm.kd.kq.annotations.Switch;
import com.chm.kd.kq.api.MsgApi;
import com.chm.kd.kq.command.GroupCommand;
import com.chm.kd.kq.command.SetGroupAdminCommand;
import com.chm.kd.kq.config.ApplicationConfig;
import com.chm.kd.kq.config.GroupSwitchConfig;
import com.chm.kd.kq.constants.GroupCommandRegex;
import com.chm.kd.kq.constants.SetGroupAdminCommandRegex;
import com.chm.kd.kq.entity.QuestionAnswer;
import com.chm.kd.kq.event.GroupMsg;
import com.chm.kd.kq.service.GroupSwitchService;
import com.chm.kd.kq.service.QuestionAnswerService;
import com.chm.kd.kq.utils.MapSetUtils;
import com.chm.kd.kq.utils.RegexUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupCommandImpl
 * @package com.chm.kd.kq.command.impl
 * @since 2019-07-16
 * description
 **/
@Service
@Switch(excludeMethod = {"open", "close"})
public class GroupCommandImpl implements GroupCommand {

  @Autowired
  private GroupSwitchConfig groupSwitchConfig;

  @Autowired
  private ApplicationConfig applicationConfig;

  @Autowired
  private MsgApi msgApi;

  @Autowired
  private GroupSwitchService groupSwitchService;

  @Autowired
  private QuestionAnswerService questionAnswerService;

  @Resource(name = "scheduledExecutorService")
  private ScheduledExecutorService scheduledExecutorService;

  @Autowired
  private SetGroupAdminCommand setGroupAdminCommand;

  @Override
  @End
  @Role(roles = {"owner", "admin"})
  public void open(GroupMsg groupMsg) {
    Long groupId = groupMsg.getGroupId();
    boolean mainSwitch = groupSwitchService.checkMainSwitch(groupId);
    String message = mainSwitch ? groupSwitchConfig.getOpenAgainStr() : groupSwitchConfig.getOpenStr();
    if (groupSwitchService.groupMainSwitchControl(groupId, true)) {
      msgApi.sendGroupMsg(groupId, message);
    }
  }

  @Override
  @End
  @Role(roles = {"owner", "admin"})
  public void close(GroupMsg groupMsg) {
    Long groupId = groupMsg.getGroupId();
    boolean mainSwitch = groupSwitchService.checkMainSwitch(groupId);
    String message = mainSwitch ? groupSwitchConfig.getCloseStr() : groupSwitchConfig.getCloseAgainStr();
    if (groupSwitchService.groupMainSwitchControl(groupId, false)) {
      msgApi.sendGroupMsg(groupId, message);
    }
  }

  @Override
  @End
  @Role(roles = {"owner", "admin"})
  @Switch(types = {"MAIN_SWITCH", "QUESTION_SWITCH"})
  @Regex(anyMatchRegexps = {GroupCommandRegex.EXACT_QUESTION, GroupCommandRegex.FUZZY_QUESTION})
  public void setQuestions(GroupMsg groupMsg) {
    Long groupId = groupMsg.getGroupId();
    String readMessage = groupMsg.getRawMessage();
    String question = RegexUtils.extractQuestion(readMessage);
    List<String> questionList = Lists.newArrayList(StringUtils.split(question, "，")).stream().map(StringUtils::trim).filter(StringUtils::isNotBlank)
            .collect(Collectors.toList());
    String answer = RegexUtils.extractAnswer(readMessage);
    if (questionAnswerService.saveQuestion(groupId, questionList, answer, RegexUtils.matchRegex(GroupCommandRegex.EXACT_QUESTION, readMessage) ? "1" : "2") > 0) {
      msgApi.sendGroupMsg(groupId, "添加问题回复成功，你可以这么问我：" + StringUtils.join(questionList, "或者"));
    }
  }

  @Override
  @End
  @Role(roles = {"owner", "admin"})
  @Switch(types = {"MAIN_SWITCH", "QUESTION_SWITCH"})
  @Regex(anyMatchRegexps = {GroupCommandRegex.COPY_QUESTION})
  public void copyQuestions(GroupMsg groupMsg) {
    Long groupId = groupMsg.getGroupId();
    String readMessage = groupMsg.getRawMessage();
    Long srcGroupId = NumberUtils.createLong(readMessage.replaceAll(GroupCommandRegex.COPY_QUESTION, "$1"));
    Long count = questionAnswerService.copyQuestion(srcGroupId, groupId);
    String replyMessage;
    if (count == 0) {
      replyMessage = "您要复制的群不存在问答数据！";
    } else {
      replyMessage = String.format("复制完成，本次共复制%d条问答数据", count);
    }
    msgApi.sendGroupMsg(groupId, replyMessage);
  }

  @Override
  @End
  @Role(roles = {"owner", "admin"})
  @Switch(types = {"MAIN_SWITCH", "QUESTION_SWITCH"})
  @Regex(anyMatchRegexps = GroupCommandRegex.FIND_QUESTION)
  public void findQuestions(GroupMsg groupMsg) {
    Long groupId = groupMsg.getGroupId();
    String readMessage = groupMsg.getRawMessage();
    String question = readMessage.replaceAll(GroupCommandRegex.FIND_QUESTION, "$1");
    List<QuestionAnswer> questionAnswerList = questionAnswerService.findQuestion(groupId, question);
    msgApi.sendGroupMsg(groupId, findQuestionStr(questionAnswerList));
  }

  private String findQuestionStr(List<QuestionAnswer> questionAnswerList) {
    if (CollectionUtils.isEmpty(questionAnswerList)) {
      return "没有查询到相关数据";
    }
    StringBuffer stringBuffer = new StringBuffer("共查询到").append(questionAnswerList.size()).append("条相关数据，详情如下所示：\n").append("--------------------\n");
    questionAnswerList.forEach(questionAnswer -> stringBuffer.append("编号：").append(questionAnswer.getQuestionId()).append("\n")
            .append("问题：").append(questionAnswer.getQuestion()).append("\n")
            .append("答案：").append(questionAnswer.getAnswer()).append("\n")
            .append("类型：群内回复\n")
            .append("--------------------\n"));
    stringBuffer.append("发送“删答 编号”删答！\n").append("例如：删答 ").append(questionAnswerList.get(0).getQuestionId()).append("\n");
    if (questionAnswerList.size() > 1) {
      stringBuffer.append("例如：删答 ").append(StringUtils.join(questionAnswerList.stream().map(QuestionAnswer::getQuestionId).collect(Collectors.toList()), "，"));
    }
    return stringBuffer.toString();
  }

  @Override
  @End
  @Role(roles = {"owner", "admin"})
  @Switch(types = {"MAIN_SWITCH", "QUESTION_SWITCH"})
  @Regex(anyMatchRegexps = GroupCommandRegex.DELETE_QUESTION)
  public void deleteQuestions(GroupMsg groupMsg) {
    Long groupId = groupMsg.getGroupId();
    String readMessage = groupMsg.getRawMessage();
    String questions = readMessage.replaceAll(GroupCommandRegex.DELETE_QUESTION, "$1");
    List<Integer> questionsIdList = Stream.of(StringUtils.split(questions, "，")).map(NumberUtils::createInteger).collect(Collectors.toList());
    long count = questionAnswerService.deleteQuestion(groupId, questionsIdList);
    msgApi.sendGroupMsg(groupId, String.format("操作成功，本次共删除问答数据%d条！", count));
  }

  @Override
  @End
  @Role(roles = {"owner", "admin"})
  @Regex(anyMatchRegexps = RegexUtils.TIMING_SEND)
  public void timingSend(GroupMsg groupMsg) {
    Long groupId = groupMsg.getGroupId();
    String rawMessage = groupMsg.getRawMessage();
    String sendMessage = StringUtils.replacePattern(rawMessage, RegexUtils.TIMING_SEND, "$1");
    String time = StringUtils.replacePattern(rawMessage, RegexUtils.TIMING_SEND, "$2");
    LocalDateTime localDateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), localDateTime);
    String replyMessage;
    if (seconds >= 0) {
      scheduledExecutorService.schedule(() -> msgApi.sendGroupMsg(groupId, sendMessage), seconds, TimeUnit.SECONDS);
      replyMessage = String.format("成功设置定时发送，将于%d秒后发送，内容为：%s", seconds, sendMessage);
    } else {
      replyMessage = "设置时间必须大于当前时间";
    }
    msgApi.sendGroupMsg(groupId, replyMessage);
  }

  @Override
  @End
  @Role(roles = {"owner", "admin"})
  @Regex(anyMatchRegexps = RegexUtils.REPEAT_SEND)
  public void repeatSend(GroupMsg groupMsg) {
    Long groupId = groupMsg.getGroupId();
    String rawMessage = groupMsg.getRawMessage();
    String sendMessage = StringUtils.replacePattern(rawMessage, RegexUtils.REPEAT_SEND, "$1");
    Integer plan = NumberUtils.createInteger(StringUtils.replacePattern(rawMessage, RegexUtils.REPEAT_SEND, "$2"));
    String timeStr = StringUtils.replacePattern(rawMessage, RegexUtils.REPEAT_SEND, "$3");
    String replyMessage;
    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    try {
      String hourStr = StringUtils.replacePattern(rawMessage, RegexUtils.REPEAT_SEND, "$6");
      Integer hour = NumberUtils.createInteger(StringUtils.isBlank(hourStr) ? "0" : hourStr);
      String minuteStr = StringUtils.replacePattern(rawMessage, RegexUtils.REPEAT_SEND, "$8");
      Integer minute = NumberUtils.createInteger(StringUtils.isBlank(minuteStr) ? "0" : minuteStr);
      String secondStr = StringUtils.replacePattern(rawMessage, RegexUtils.REPEAT_SEND, "$10");
      Integer second = NumberUtils.createInteger(StringUtils.isBlank(secondStr) ? "0" : secondStr);
      long seconds = LocalTime.of(hour, minute, second).toSecondOfDay();
      if (seconds <= 0) {
        replyMessage = "时间格式不正确";
        msgApi.sendGroupMsg(groupId, replyMessage);
        return;
      }
      Map<String, Object> repeatSendMap = Maps.newConcurrentMap();
      MapSetUtils.REPEAT_SEND_MAP.put(uuid, repeatSendMap);
      repeatSendMap.put("plan", plan);
      repeatSendMap.put("executed", 0);
      repeatSendMap.put("groupId", groupId);
      repeatSendMap.put("sendMessage", sendMessage);
      repeatSendMap.put("seconds", seconds);
      scheduledExecutorService.schedule(() -> repeatSend(uuid), seconds, TimeUnit.SECONDS);
      replyMessage = String.format("成功设置重复发送，重复间隔：%s，重复次数为：%d，内容为：%s", timeStr, plan, sendMessage);
      msgApi.sendPrivateMsg(applicationConfig.getMaster(), String.format("成功设置重复发送，uuid：%s，重复间隔：%s，重复次数为：%d，内容为：%s", uuid, timeStr, plan, sendMessage));
    } catch (Exception e) {
      replyMessage = "时间格式不正确";
    }
    msgApi.sendGroupMsg(groupId, replyMessage);
  }

  private void repeatSend(String uuid) {
    Map<String, Object> repeatSendMap = MapSetUtils.REPEAT_SEND_MAP.get(uuid);
    if (MapUtils.isEmpty(repeatSendMap)) {
      return;
    }
    Integer plan = MapUtils.getInteger(repeatSendMap, "plan");
    Integer executed = MapUtils.getInteger(repeatSendMap, "executed");
    Long groupId = MapUtils.getLong(repeatSendMap, "groupId");
    String sendMessage = MapUtils.getString(repeatSendMap, "sendMessage");
    Long seconds = MapUtils.getLong(repeatSendMap, "seconds");
    if (plan <= executed) {
      MapSetUtils.REPEAT_SEND_MAP.remove(uuid);
      return;
    }
    msgApi.sendGroupMsg(groupId, sendMessage);
    repeatSendMap.put("executed", executed + 1);
    scheduledExecutorService.schedule(() -> repeatSend(uuid), seconds, TimeUnit.SECONDS);
  }

  @Override
  @End
  @Role
  @Regex(anyMatchRegexps = RegexUtils.DELETE_REPEAT_SEND)
  public void deleteRepeatSend(GroupMsg groupMsg) {
    Long groupId = groupMsg.getGroupId();
    String rawMessage = groupMsg.getRawMessage();
    String uuid = StringUtils.replacePattern(rawMessage, RegexUtils.DELETE_REPEAT_SEND, "$1");
    AtomicReference<Integer> num = new AtomicReference<>(0);
    MapSetUtils.REPEAT_SEND_MAP.keySet().removeIf(k -> {
      if (StringUtils.startsWith(k, uuid)) {
        num.getAndSet(num.get() + 1);
        return true;
      }
      return false;
    });
    msgApi.sendGroupMsg(groupId, String.format("操作成功，删除%d条重复发送", num.get()));
  }

  @Override
  public void setGroupAdmin(GroupMsg groupMsg) {
    String readMessage = groupMsg.getRawMessage();
    String command = StringUtils.replacePattern(readMessage, SetGroupAdminCommandRegex.SET_GROUP_ADMIN_COMMAND, "$1");
    switch (command) {
      case "查看":
        setGroupAdminCommand.view(groupMsg);
        break;

      case "增加":
        setGroupAdminCommand.add(groupMsg);
        break;

      case "删除":
        setGroupAdminCommand.delete(groupMsg);
        break;

      case "禁止":
        setGroupAdminCommand.ban(groupMsg);
        break;

      case "清空":
        setGroupAdminCommand.empty(groupMsg);
        break;

      default:
        break;
    }
  }

  @Override
  @End
  @Switch(types = {"MAIN_SWITCH", "QUESTION_SWITCH"})
  public void answers(GroupMsg groupMsg) {
    List<QuestionAnswer> answers = questionAnswerService.answers(groupMsg.getGroupId(), groupMsg.getRawMessage());
    if (CollectionUtils.isNotEmpty(answers)) {
      answers.stream().filter(questionAnswer -> StringUtils.isNotBlank(questionAnswer.getAnswer())).forEach(questionAnswer -> msgApi.sendGroupMsg(groupMsg.getGroupId(), questionAnswer.getAnswer()));
    }
  }

}
