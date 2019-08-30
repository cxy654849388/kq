package com.chm.kd.kq.listener;


import com.chm.kd.kq.api.GroupApi;
import com.chm.kd.kq.api.MsgApi;
import com.chm.kd.kq.command.GroupCommand;
import com.chm.kd.kq.command.SetPromptCommand;
import com.chm.kd.kq.command.SwitchCommand;
import com.chm.kd.kq.config.ApplicationConfig;
import com.chm.kd.kq.config.GroupSwitchConfig;
import com.chm.kd.kq.constants.SetPromptCommandRegex;
import com.chm.kd.kq.constants.SwitchCommandRegex;
import com.chm.kd.kq.enums.CqCode;
import com.chm.kd.kq.enums.SubType;
import com.chm.kd.kq.event.*;
import com.chm.kd.kq.exception.EndException;
import com.chm.kd.kq.info.GroupMemberInfo;
import com.chm.kd.kq.utils.MapSetUtils;
import com.chm.kd.kq.utils.RegexUtils;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * @author caihongming
 * @version v1.0
 * @title KqMsgAdapter
 * @package com.chm.kd.kq.listener
 * @since 2019-07-09
 * description
 **/
@Slf4j
@Component
public class KqMsgAdapter implements KqMsgListener {

  @Autowired
  private ApplicationConfig applicationConfig;

  @Autowired
  private MsgApi msgApi;

  @Autowired
  private GroupApi groupApi;

  @Autowired
  private GroupSwitchConfig groupSwitchConfig;

  @Autowired
  private GroupCommand groupCommand;

  @Autowired
  private SwitchCommand switchCommand;

  @Autowired
  private SetPromptCommand setPromptCommand;

  @Override
  @Transactional(rollbackFor = Exception.class, noRollbackFor = EndException.class)
  public void rePrivateMsg(PrivateMsg privateMsg) {
    log.info("接收到私聊消息 {}({}):{}", privateMsg.getSender().getNickName(), privateMsg.getUserId(), privateMsg.getRawMessage());
    String readMessage = privateMsg.getRawMessage();
    String regex = "发送群消息 ([\\d]+) ([\\s\\S]+)";
    if (RegexUtils.matchRegex(regex, readMessage) && privateMsg.getUserId().equals(applicationConfig.getMaster())) {
      Long groupId = NumberUtils.createLong(StringUtils.replacePattern(readMessage, regex, "$1"));
      String message = StringUtils.replacePattern(readMessage, regex, "$2");
      msgApi.sendGroupMsg(groupId, message);
    }
  }

  @Override
  @Transactional(rollbackFor = Exception.class, noRollbackFor = EndException.class)
  public void reGroupMsg(GroupMsg groupMsg) {
    log.info("接收到QQ群消息 {}({})---{}({}):{}", groupMsg.getGroupId(), groupMsg.getGroupId(), groupMsg.getSender().getNickName(), groupMsg.getUserId(), groupMsg.getRawMessage());
    String readMessage = groupMsg.getRawMessage();
    Long groupId = groupMsg.getGroupId();
    Long userId = groupMsg.getUserId();

    if (NumberUtils.compare(815926454L, groupMsg.getGroupId()) == 0) {

    }

    if (groupSwitchConfig.getOpen().equals(readMessage)) {
      groupCommand.open(groupMsg);
      return;
    }
    if (groupSwitchConfig.getClose().equals(readMessage)) {
      groupCommand.close(groupMsg);
      return;
    }
    // 命令操作
    command(groupMsg);

    if (NumberUtils.compare(208763383L, groupId) == 0 || NumberUtils.compare(595135112L, groupId) == 0) {
      Map<Long, GroupMemberInfo> groupMemberInfoMap = MapSetUtils.GROUP_MEMBER_INFO_LIST_MAP.get(groupId);
      GroupMemberInfo groupMemberInfo = groupMemberInfoMap.get(userId);
      if (RegexUtils.notMatchRegex(RegexUtils.BUSINESS_CARD, StringUtils.defaultIfEmpty(groupMemberInfo.getCard(), groupMemberInfo.getNickname()))) {
        groupMemberInfo = groupApi.getGroupMemberInfo(groupId, userId);
        if (RegexUtils.notMatchRegex(RegexUtils.BUSINESS_CARD, StringUtils.defaultIfEmpty(groupMemberInfo.getCard(), groupMemberInfo.getNickname()))) {
          msgApi.sendGroupMsg(groupMsg.getGroupId(), String.format("%s 请正确修改群名片哦！ 格式：年级-专业或地区-昵称", CqCode.at.getCqCode(groupMsg.getUserId().toString())));
        }
      }
    }

    // 发送提醒
    groupApi.sendRemind(groupId, userId);

    if (StringUtils.containsAny(readMessage, CqCode.at.getCqCode(groupMsg.getSelfId().toString()), CqCode.at.getCqCode(applicationConfig.getMaster().toString()))
            && !Sets.newHashSet(applicationConfig.getMaster(), groupMsg.getSelfId()).contains(groupMsg.getUserId())) {
      msgApi.sendGroupMsg(groupMsg.getGroupId(), "[CQ:image,file=29960DC87C4D408413C8B01D7651AFA7.jpg]");
    }

    groupCommand.answers(groupMsg);
  }

  private void command(GroupMsg groupMsg) {
    String readMessage = groupMsg.getRawMessage();
    if (RegexUtils.matchRegex(SetPromptCommandRegex.SET_PROMPT_COMMAND, readMessage)) {
      setPromptCommand(groupMsg);
    } else if (RegexUtils.matchRegex(RegexUtils.COMMAND, readMessage)) {
      String command = StringUtils.replacePattern(readMessage, RegexUtils.COMMAND, "$1");
      switch (command) {
        case "精确问":
        case "模糊问":
          groupCommand.setQuestions(groupMsg);
          break;

        case "复制问答":
          groupCommand.copyQuestions(groupMsg);
          break;

        case "查问":
          groupCommand.findQuestions(groupMsg);
          break;

        case "删答":
          groupCommand.deleteQuestions(groupMsg);
          break;

        case "定时发送":
          groupCommand.timingSend(groupMsg);
          break;

        case "重复发送":
          groupCommand.repeatSend(groupMsg);
          break;

        case "删重复发送":
          groupCommand.deleteRepeatSend(groupMsg);
          break;

        case "设置群管理员":
          groupCommand.setGroupAdmin(groupMsg);
          break;

        default:
          break;
      }
    } else if (RegexUtils.matchRegex(SwitchCommandRegex.SWITCH_COMMAND, readMessage)) {
      switchCommand.groupSwitchControl(groupMsg);
    }
  }


  private void setPromptCommand(GroupMsg groupMsg) {
    String readMessage = groupMsg.getRawMessage();
    String operate = StringUtils.replacePattern(readMessage, SetPromptCommandRegex.SET_PROMPT_COMMAND, "$2");
    switch (operate) {
      case "查看":
        setPromptCommand.setGroupPromptView(groupMsg);
        break;

      case "删除":
        setPromptCommand.setGroupPromptDelete(groupMsg);
        break;

      default:
        setPromptCommand.setGroupPrompt(groupMsg);
        break;
    }
  }

  @Override
  public void reDiscussMsg(DiscussMsg discussMsg) {

  }

  @Override
  public void groupFileUploadNotice(GroupFileUploadNotice groupFileUploadNotice) {

  }

  @Override
  public void groupAdminChangeNotice(GroupAdminChangeNotice groupAdminChangeNotice) {
    Map<Long, String> groupAdminsMap = MapSetUtils.GROUP_ADMINS_MAP.get(groupAdminChangeNotice.getGroupId());
    if (SubType.set.getCode().equals(groupAdminChangeNotice.getSubType())) {
      groupAdminsMap.put(groupAdminChangeNotice.getUserId(), "admin");
    } else if (SubType.unset.getCode().equals(groupAdminChangeNotice.getSubType())) {
      groupAdminsMap.remove(groupAdminChangeNotice.getUserId());
    }
  }

  @Override
  public void groupMemberDecreaseNotice(GroupMemberDecreaseNotice groupMemberDecreaseNotice) {
    Long groupId = groupMemberDecreaseNotice.getGroupId();
    Long userId = groupMemberDecreaseNotice.getUserId();
    Map<Long, GroupMemberInfo> groupMemberInfoMap = MapSetUtils.GROUP_MEMBER_INFO_LIST_MAP.get(groupId);
    groupMemberInfoMap.remove(userId);
    groupApi.sendQuitGroupPrompt(groupId, userId);
  }

  @Override
  public void groupMemberIncreaseNotice(GroupMemberIncreaseNotice groupMemberIncreaseNotice) {
    Long groupId = groupMemberIncreaseNotice.getGroupId();
    Long userId = groupMemberIncreaseNotice.getUserId();
    Map<Long, GroupMemberInfo> groupMemberInfoMap = MapSetUtils.GROUP_MEMBER_INFO_LIST_MAP.get(groupId);
    GroupMemberInfo userInfo = groupApi.getGroupMemberInfo(groupId, userId);
    groupMemberInfoMap.put(userId, userInfo);
    groupApi.sendJoinGroupPrompt(groupId, userId);
  }

  @Override
  public void friendAddNotice(FriendAddNotice friendAddNotice) {

  }

  @Override
  public void friendReq(FriendReq friendReq) {

  }

  @Override
  public void groupReq(GroupReq groupReq) {

  }
}
