package com.chm.kd.kq.client;

import com.alibaba.fastjson.JSON;
import com.chm.kd.kq.enums.MessageType;
import com.chm.kd.kq.enums.NoticeType;
import com.chm.kd.kq.enums.PostType;
import com.chm.kd.kq.enums.RequestType;
import com.chm.kd.kq.event.*;
import com.chm.kd.kq.exception.EndException;
import com.chm.kd.kq.listener.KqMsgListener;
import com.chm.kd.kq.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.*;

/**
 * @author caihongming
 * @version v1.0
 * @title KqWebSocketServer
 * @package com.chm.kd.kq.client
 * @since 2019-07-10
 * description
 **/
@ClientEndpoint
@Slf4j
public class KqWebSocketClient {

  private Session session;

  @Autowired
  private KqMsgListener kqMsgListener = SpringUtils.getBean(KqMsgListener.class);

  /**
   * 当客户端打开连接
   */
  @OnOpen
  public void onOpen(Session session) {
    this.session = session;
    log.info("服务器连接成功");
  }

  /**
   * 当客户端发送消息
   * <p>
   * PS: 这里约定传递的消息为JSON字符串 方便传递更多参数！
   */
  @OnMessage
  @SuppressWarnings("all")
  public void onMessage(String jsonStr) {
    try {
      log.info(jsonStr);
      Event event = JSON.parseObject(jsonStr, Event.class);
      switch (PostType.getByCode(event.getPostType())) {
        case message:
          EventMsg eventMsg = JSON.parseObject(jsonStr, EventMsg.class);
          switch (MessageType.getByCode(eventMsg.getMessageType())) {
            case group:
              GroupMsg groupMsg = JSON.parseObject(jsonStr, GroupMsg.class);
              kqMsgListener.reGroupMsg(groupMsg);
              break;

            case private_:
              PrivateMsg privateMs = JSON.parseObject(jsonStr, PrivateMsg.class);
              kqMsgListener.rePrivateMsg(privateMs);
              break;

            case discuss:
              DiscussMsg discussMsg = JSON.parseObject(jsonStr, DiscussMsg.class);
              kqMsgListener.reDiscussMsg(discussMsg);
              break;

            default:
              break;
          }
          break;

        case notice:
          EventNotice eventNotice = JSON.parseObject(jsonStr, EventNotice.class);
          switch (NoticeType.getByCode(eventNotice.getNoticeType())) {
            case group_upload:
              GroupFileUploadNotice groupFileUploadNotice = JSON.parseObject(jsonStr, GroupFileUploadNotice.class);
              kqMsgListener.groupFileUploadNotice(groupFileUploadNotice);
              break;

            case group_admin:
              GroupAdminChangeNotice groupAdminChangeNotice = JSON.parseObject(jsonStr, GroupAdminChangeNotice.class);
              kqMsgListener.groupAdminChangeNotice(groupAdminChangeNotice);
              break;

            case group_decrease:
              GroupMemberDecreaseNotice groupMemberDecreaseNotice = JSON.parseObject(jsonStr, GroupMemberDecreaseNotice.class);
              kqMsgListener.groupMemberDecreaseNotice(groupMemberDecreaseNotice);
              break;

            case group_increase:
              GroupMemberIncreaseNotice groupMemberIncreaseNotice = JSON.parseObject(jsonStr, GroupMemberIncreaseNotice.class);
              kqMsgListener.groupMemberIncreaseNotice(groupMemberIncreaseNotice);
              break;

            case friend_add:
              FriendAddNotice friendAddNotice = JSON.parseObject(jsonStr, FriendAddNotice.class);
              kqMsgListener.friendAddNotice(friendAddNotice);
              break;

            default:
              break;
          }
          break;

        case request:
          EventReq eventReq = JSON.parseObject(jsonStr, EventReq.class);
          switch (RequestType.getByCode(eventReq.getRequestType())) {
            case friend:
              FriendReq friendReq = JSON.parseObject(jsonStr, FriendReq.class);
              kqMsgListener.friendReq(friendReq);
              break;

            case group:
              GroupReq groupReq = JSON.parseObject(jsonStr, GroupReq.class);
              kqMsgListener.groupReq(groupReq);
              break;

            default:
              break;
          }
          break;

        default:
          break;
      }
    } catch (EndException e) {
    } catch (Exception e) {
      log.error("系统异常", e);
    }
  }

  /**
   * 当关闭连接
   */
  @OnClose
  public void onClose() {
    this.session = null;
    log.info("关闭连接");
  }

  /**
   * 当通信发生异常：打印错误日志
   */
  @OnError
  public void onError(Throwable error) {
    log.error(error.getMessage(), error);
  }
}
