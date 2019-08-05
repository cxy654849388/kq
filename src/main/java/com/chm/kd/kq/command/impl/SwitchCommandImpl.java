package com.chm.kd.kq.command.impl;

import com.chm.kd.kq.annotations.End;
import com.chm.kd.kq.annotations.Regex;
import com.chm.kd.kq.annotations.Role;
import com.chm.kd.kq.annotations.Switch;
import com.chm.kd.kq.api.MsgApi;
import com.chm.kd.kq.command.SwitchCommand;
import com.chm.kd.kq.config.ApplicationConfig;
import com.chm.kd.kq.constants.SetPromptCommandRegex;
import com.chm.kd.kq.constants.SwitchCommandRegex;
import com.chm.kd.kq.event.GroupMsg;
import com.chm.kd.kq.exception.EndException;
import com.chm.kd.kq.service.GroupSwitchService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author caihongming
 * @version v1.0
 * @title SwitchCommandImpl
 * @package com.chm.kd.kq.command.impl
 * @since 2019-07-19
 * description
 **/
@Service
public class SwitchCommandImpl implements SwitchCommand {

  @Autowired
  private ApplicationConfig applicationConfig;

  @Autowired
  private MsgApi msgApi;

  @Autowired
  private GroupSwitchService groupSwitchService;

  @Override
  @Role(roles = {"owner", "admin"})
  @Regex(anyMatchRegexps = {SwitchCommandRegex.SWITCH_COMMAND})
  @Switch
  public void groupSwitchControl(GroupMsg groupMsg) {
    Long groupId = groupMsg.getGroupId();
    String readMessage = groupMsg.getRawMessage();
    String operate = StringUtils.replacePattern(readMessage, SwitchCommandRegex.SWITCH_COMMAND, "$1");
    String switchTypeName = StringUtils.replacePattern(readMessage, SwitchCommandRegex.SWITCH_COMMAND, "$2");
    String switchType = MapUtils.getString(applicationConfig.getSwitchTypes(), switchTypeName);
    boolean aSwitch = StringUtils.equals("开启", operate);
    if (null != switchType && groupSwitchService.groupSwitchControl(groupId, switchType, aSwitch)) {
      msgApi.sendGroupMsg(groupId, String.format("操作成功，已%s本群的%s功能！", aSwitch ? "开启" : "关闭", switchTypeName));
      throw EndException.getInstance();
    }
  }
}
