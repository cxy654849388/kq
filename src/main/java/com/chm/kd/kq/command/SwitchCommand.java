package com.chm.kd.kq.command;

import com.chm.kd.kq.event.GroupMsg;

/**
 * @author caihongming
 * @version v1.0
 * @title SwitchCommand
 * @package com.chm.kd.kq.command
 * @since 2019-07-19
 * description 开关命令接口
 **/
public interface SwitchCommand {

  /**
   * 打开问答
   *
   * @param groupMsg
   */
  void groupSwitchControl(GroupMsg groupMsg);
}
