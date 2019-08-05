package com.chm.kd.kq.listener;

import com.chm.kd.kq.event.*;

/**
 * @author caihongming
 * @version v1.0
 * @title KqMsgListener
 * @package com.chm.kd.kq.listener
 * @since 2019-07-09
 * description 事件列表
 **/
public interface KqMsgListener {

  /**
   * 私聊消息事件
   *
   * @param privateMsg
   */
  void rePrivateMsg(PrivateMsg privateMsg);

  /**
   * 群消息事件
   *
   * @param groupMsg
   */
  void reGroupMsg(GroupMsg groupMsg);

  /**
   * 讨论组消息事件
   *
   * @param discussMsg
   */
  void reDiscussMsg(DiscussMsg discussMsg);

  /**
   * 群文件上传事件
   *
   * @param groupFileUploadNotice
   */
  void groupFileUploadNotice(GroupFileUploadNotice groupFileUploadNotice);

  /**
   * 群管理员变动事件
   *
   * @param groupAdminChangeNotice
   */
  void groupAdminChangeNotice(GroupAdminChangeNotice groupAdminChangeNotice);

  /**
   * 群成员减少
   *
   * @param groupMemberDecreaseNotice
   */
  void groupMemberDecreaseNotice(GroupMemberDecreaseNotice groupMemberDecreaseNotice);

  /**
   * 群成员增加
   *
   * @param groupMemberIncreaseNotice
   */
  void groupMemberIncreaseNotice(GroupMemberIncreaseNotice groupMemberIncreaseNotice);

  /**
   * 好友添加通知
   *
   * @param friendAddNotice
   */
  void friendAddNotice(FriendAddNotice friendAddNotice);

  /**
   * 加好友请求
   *
   * @param friendReq
   */
  void friendReq(FriendReq friendReq);

  /**
   * 加群请求／邀请
   *
   * @param groupReq
   */
  void groupReq(GroupReq groupReq);
}
