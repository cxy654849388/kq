package com.chm.kd.kq.command;

import com.chm.kd.kq.event.GroupMsg;

/**
 * @author caihongming
 * @version v1.0
 * @title SetPromptCommand
 * @package com.chm.kd.kq.command
 * @since 2019-07-26
 * description 设置提示语命令
 **/
public interface SetPromptCommand {

  /**
   * 设置提示
   *
   * @param groupMsg
   */
  void setGroupPrompt(GroupMsg groupMsg);

  /**
   * 查看提示
   *
   * @param groupMsg
   */
  void setGroupPromptView(GroupMsg groupMsg);

  /**
   * 删除提示
   *
   * @param groupMsg
   */
  void setGroupPromptDelete(GroupMsg groupMsg);

}
