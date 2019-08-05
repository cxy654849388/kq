package com.chm.kd.kq.command.impl;

import com.chm.kd.kq.annotations.End;
import com.chm.kd.kq.annotations.Regex;
import com.chm.kd.kq.annotations.Role;
import com.chm.kd.kq.api.GroupApi;
import com.chm.kd.kq.api.MsgApi;
import com.chm.kd.kq.command.SetPromptCommand;
import com.chm.kd.kq.config.ApplicationConfig;
import com.chm.kd.kq.constants.SetPromptCommandRegex;
import com.chm.kd.kq.entity.GroupPrompt;
import com.chm.kd.kq.event.GroupMsg;
import com.chm.kd.kq.exception.EndException;
import com.chm.kd.kq.service.GroupPromptService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author caihongming
 * @version v1.0
 * @title SetPromptCommandImpl
 * @package com.chm.kd.kq.command.impl
 * @since 2019-07-26
 * description
 **/
@Service
public class SetPromptCommandImpl implements SetPromptCommand {

  @Autowired
  private ApplicationConfig applicationConfig;

  @Autowired
  private MsgApi msgApi;

  @Autowired
  private GroupApi groupApi;

  @Autowired
  private GroupPromptService groupPromptService;

  @Override
  @Role(roles = {"owner", "admin"})
  @Regex(anyMatchRegexps = {SetPromptCommandRegex.SET_GROUP_PROMPT})
  public void setGroupPrompt(GroupMsg groupMsg) {
    String readMessage = groupMsg.getRawMessage();
    Long groupId = groupMsg.getGroupId();
    String typeName = StringUtils.replacePattern(readMessage, SetPromptCommandRegex.SET_GROUP_PROMPT, "$1");
    String content = StringUtils.replacePattern(readMessage, SetPromptCommandRegex.SET_GROUP_PROMPT, "$2");
    String type = MapUtils.getString(applicationConfig.getPrompts(), typeName);
    if (null == type) {
      return;
    }
    if (groupPromptService.setByGroupIdAndTypeAndContent(groupId, type, content)) {
      msgApi.sendGroupMsg(groupId, String.format("%s提示设置成功", typeName));
    }
    throw EndException.getInstance();
  }

  @Override
  @Role(roles = {"owner", "admin"})
  @Regex(anyMatchRegexps = {SetPromptCommandRegex.SET_GROUP_PROMPT_VIEW})
  public void setGroupPromptView(GroupMsg groupMsg) {
    String readMessage = groupMsg.getRawMessage();
    Long groupId = groupMsg.getGroupId();
    String typeName = StringUtils.replacePattern(readMessage, SetPromptCommandRegex.SET_GROUP_PROMPT_VIEW, "$1");
    String type = MapUtils.getString(applicationConfig.getPrompts(), typeName);
    if (null == type) {
      return;
    }
    String content = groupPromptService.getContentByGroupIdAndType(groupId, type);
    groupApi.sendPromptView(groupId, content);
    throw EndException.getInstance();
  }

  @Override
  @Role(roles = {"owner", "admin"})
  @Regex(anyMatchRegexps = {SetPromptCommandRegex.SET_GROUP_PROMPT_DELETE})
  public void setGroupPromptDelete(GroupMsg groupMsg) {
    String readMessage = groupMsg.getRawMessage();
    Long groupId = groupMsg.getGroupId();
    String typeName = StringUtils.replacePattern(readMessage, SetPromptCommandRegex.SET_GROUP_PROMPT_DELETE, "$1");
    String type = MapUtils.getString(applicationConfig.getPrompts(), typeName);
    if (null == type) {
      return;
    }
    GroupPrompt groupPrompt = groupPromptService.getByGroupIdAndType(groupId, type);
    if (null != groupPrompt.getId() && groupPromptService.removeById(groupPrompt.getId())) {
      msgApi.sendGroupMsg(groupId, String.format("%s提示删除成功", typeName));
    }
    throw EndException.getInstance();
  }
}
