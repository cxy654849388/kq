package com.chm.kd.kq.constants;

/**
 * @author caihongming
 * @version v1.0
 * @title SetGroupAdminCommandRegex
 * @package com.chm.kd.kq.constants
 * @since 2019-07-26
 * description
 **/
public interface SetGroupAdminCommandRegex {

  /**
   * 查看群管理员
   */
  String SET_GROUP_ADMIN_VIEW = "^设置群管理员 查看";

  /**
   * 增加群管理员
   */
  String SET_GROUP_ADMIN_ADD = "^设置群管理员 增加 ([\\d，]+)$";

  /**
   * 删除群管理员
   */
  String SET_GROUP_ADMIN_DELETE = "^设置群管理员 删除 ([\\d，]+)$";

  /**
   * 禁止群管理员
   */
  String SET_GROUP_ADMIN_BAN = "^设置群管理员 禁止 ([\\d，]+)$";

  /**
   * 清空群管理员
   */
  String SET_GROUP_ADMIN_EMPTY = "^设置群管理员 清空$";

  /**
   * 设置群管理命令
   */
  String SET_GROUP_ADMIN_COMMAND = "设置群管理员 ([^ ]+) ?.*";
}
