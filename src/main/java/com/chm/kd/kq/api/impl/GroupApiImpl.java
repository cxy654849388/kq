package com.chm.kd.kq.api.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chm.kd.kq.annotations.Switch;
import com.chm.kd.kq.api.GroupApi;
import com.chm.kd.kq.api.KqResponse;
import com.chm.kd.kq.api.MsgApi;
import com.chm.kd.kq.entity.GroupRemind;
import com.chm.kd.kq.enums.CqCode;
import com.chm.kd.kq.event.Anonymous;
import com.chm.kd.kq.exception.EndException;
import com.chm.kd.kq.info.GroupInfo;
import com.chm.kd.kq.info.GroupMemberInfo;
import com.chm.kd.kq.service.GroupPromptService;
import com.chm.kd.kq.service.GroupRemindService;
import com.chm.kd.kq.utils.MapSetUtils;
import com.chm.kd.kq.utils.Okhttp3Utils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupApiImpl
 * @package com.chm.kd.kq.api.impl
 * @since 2019-07-12
 * description
 **/
@Slf4j
@Service
public class GroupApiImpl implements GroupApi {

  @Value("${cq.http.host}")
  private String cqHttpHost;

  @Value("${cq.http.port}")
  private String cqHttpPort;

  @Autowired
  private MsgApi msgApi;

  @Autowired
  private GroupPromptService groupPromptService;

  @Autowired
  private GroupRemindService groupRemindService;

  @Override
  public void setGroupKick(Long groupId, Long userId) {

  }

  @Override
  public void setGroupBan(Long groupId, Long userId, Integer duration) {

  }

  @Override
  public void setGroupAnonymousBan(Long groupId, Anonymous anonymous, Integer duration) {

  }

  @Override
  public void setGroupAnonymousBan(Long groupId, String flag, Integer duration) {

  }

  @Override
  public void setGroupWholeBan(Long groupId, Boolean enable) {

  }

  @Override
  public void setGroupWholeBan(Long groupId, Long userId, Boolean enable) {

  }

  @Override
  public void setGroupAnonymous(Long groupId, Boolean enable) {

  }

  @Override
  public void setGroupCard(Long groupId, Long userId, String card) {

  }

  @Override
  public void setGroupLeave(Long groupId, Boolean isDismiss) {

  }

  @Override
  public void setGroupSpecialTitle(Long groupId, Long userId, String specialTitle) {

  }

  @Override
  public List<GroupInfo> getGroupList() {
    String url = String.format("http://%s:%s/get_group_list", cqHttpHost, cqHttpPort);
    try {
      String responseStr = Okhttp3Utils.httpClientFormPostReturnAsString(url, Maps.newHashMap(), 600L);
      KqResponse kqResponse = JSON.parseObject(responseStr, KqResponse.class);
      return kqResponse.isOk() ? JSON.parseArray(JSON.toJSONString(kqResponse.getData()), GroupInfo.class) : Lists.newArrayList();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return Lists.newArrayList();
  }

  @Override
  public GroupMemberInfo getGroupMemberInfo(Long groupId, Long userId) {
    String url = String.format("http://%s:%s/get_group_member_info", cqHttpHost, cqHttpPort);
    Map<String, Object> paramsMap = Maps.newHashMap();
    paramsMap.put("group_id", groupId);
    paramsMap.put("user_id", userId);
    paramsMap.put("no_cache", true);
    try {
      Thread.sleep(10L);
      String responseStr = Okhttp3Utils.httpClientFormPostReturnAsString(url, paramsMap, 600L);
      KqResponse kqResponse = JSON.parseObject(responseStr, KqResponse.class);
      return kqResponse.isOk() ? JSON.parseObject(JSON.toJSONString(kqResponse.getData()), GroupMemberInfo.class) : new GroupMemberInfo();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return null;
  }

  @Override
  public List<GroupMemberInfo> getGroupMemberList(Long groupId) {
    String url = String.format("http://%s:%s/get_group_member_list", cqHttpHost, cqHttpPort);
    Map<String, Object> paramsMap = Maps.newHashMap();
    paramsMap.put("group_id", groupId);
    try {
      String responseStr = Okhttp3Utils.httpClientFormPostReturnAsString(url, paramsMap, 600L);
      KqResponse kqResponse = JSON.parseObject(responseStr, KqResponse.class);
      return kqResponse.isOk() ? JSON.parseArray(JSON.toJSONString(kqResponse.getData()), GroupMemberInfo.class) : Lists.newArrayList();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return Lists.newArrayList();
  }

  @Override
  @Switch(types = {"JOIN_SWITCH"})
  public void sendJoinGroupPrompt(Long groupId, Long userId) {
    String groupJoinPrompt = groupPromptService.getGroupJoinPrompt(groupId);
    groupJoinPrompt = StringUtils.replace(groupJoinPrompt, "&#91;@QQ&#93;", CqCode.at.getCqCode(userId.toString()));
    if (StringUtils.isNotEmpty(groupJoinPrompt)) {
      msgApi.sendGroupMsg(groupId, groupJoinPrompt);
    }
  }

  @Override
  @Switch(types = {"QUIT_SWITCH"})
  public void sendQuitGroupPrompt(Long groupId, Long userId) {

  }

  @Override
  @Switch(types = {"MODIFY_CARD_SWITCH"})
  public void sendModifyCardPrompt(Long groupId, Long userId) {
    Map<Long, GroupMemberInfo> groupMemberInfoMap = MapSetUtils.GROUP_MEMBER_INFO_LIST_MAP.get(groupId);
    GroupMemberInfo oldUserInfo = groupMemberInfoMap.get(userId);
    GroupMemberInfo newUserInfo = this.getGroupMemberInfo(groupId, userId);
    String groupModifyCardPrompt = groupPromptService.getGroupModifyCardPrompt(groupId);
    groupModifyCardPrompt = StringUtils.replace(groupModifyCardPrompt, "&#91;QQ&#93;", userId.toString());
    groupModifyCardPrompt = StringUtils.replace(groupModifyCardPrompt, "&#91;旧名片&#93;", StringUtils.isEmpty(oldUserInfo.getCard()) ? oldUserInfo.getNickname() : oldUserInfo.getCard());
    groupModifyCardPrompt = StringUtils.replace(groupModifyCardPrompt, "&#91;新名片&#93;", StringUtils.isEmpty(newUserInfo.getCard()) ? newUserInfo.getNickname() : newUserInfo.getCard());
    groupModifyCardPrompt = StringUtils.replace(groupModifyCardPrompt, "&#91;时间&#93;", DATE_TIME_FORMATTER.format(LocalDateTime.now()));
    if (StringUtils.isNotEmpty(groupModifyCardPrompt)) {
      msgApi.sendGroupMsg(groupId, groupModifyCardPrompt);
    }
    groupMemberInfoMap.put(userId, newUserInfo);
  }

  @Override
  public void sendPromptView(Long groupId, String prompt) {
    StringBuilder stringBuilder = new StringBuilder("提示语如下：\n").append("------------------------\n").append(StringUtils.defaultString(prompt, "")).append("\n------------------------");
    if (StringUtils.isNotEmpty(stringBuilder)) {
      msgApi.sendGroupMsg(groupId, stringBuilder.toString());
    }
  }

  @Override
  @Switch(types = {"REMIND_SWITCH"})
  public void sendRemind(Long groupId, Long userId) {
    GroupRemind groupRemind = groupRemindService.getByGroupIdAndUserId(groupId, userId);
    if (null != groupRemind) {
      msgApi.sendGroupMsg(groupId, groupRemind.getContent());
      groupRemindService.update(Wrappers.<GroupRemind>lambdaUpdate().set(GroupRemind::getLastRemindTime, LocalDateTime.now()).eq(GroupRemind::getId, groupRemind.getId()));
      // 当有提示语时结束程序流程
      throw EndException.getInstance();
    }
  }

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
