package com.chm.kd.kq.api;

/**
 * @author caihongming
 * @version v1.0
 * @title MsgApi
 * @package com.chm.kd.kq.api
 * @since 2019-07-12
 * description 消息api
 **/
public interface MsgApi {

  /**
   * 发送私聊消息
   *
   * @param userId
   * @param message
   */
  void sendPrivateMsg(Long userId, String message);

  /**
   * 发送群聊消息
   *
   * @param groupId
   * @param message
   */
  void sendGroupMsg(Long groupId, String message);

  /**
   * 发送讨论组消息
   *
   * @param discussId
   * @param message
   */
  void sendDiscussMsg(Long discussId, String message);
}
