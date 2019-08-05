package com.chm.kd.kq.command;

import com.chm.kd.kq.event.GroupMsg;

/**
 * @author caihongming
 * @version v1.0
 * @title SetGroupAdminCommand
 * @package com.chm.kd.kq.command
 * @since 2019-07-18
 * description 设置群管理员命令接口
 **/
public interface SetGroupAdminCommand {

  /**
   * 查看群管理员
   *
   * @param groupMsg
   */
  void view(GroupMsg groupMsg);

  /**
   * 增加群管理员
   *
   * @param groupMsg
   */
  void add(GroupMsg groupMsg);

  /**
   * 删除群管理员
   *
   * @param groupMsg
   */
  void delete(GroupMsg groupMsg);

  /**
   * 禁止群管理员
   *
   * @param groupMsg
   */
  void ban(GroupMsg groupMsg);

  /**
   * 清空群管理员
   *
   * @param groupMsg
   */
  void empty(GroupMsg groupMsg);
}
