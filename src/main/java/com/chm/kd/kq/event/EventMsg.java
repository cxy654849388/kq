package com.chm.kd.kq.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author caihongming
 * @version v1.0
 * @title EventMsg
 * @package com.chm.kd.kq.event
 * @since 2019-07-11
 * description
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class EventMsg extends Event {

  /**
   * 消息类型
   * private 私聊消息
   * group 群消息
   * discuss 讨论组消息
   */
  @JSONField(name = "message_type")
  private String messageType;

  /**
   * 消息 ID
   */
  @JSONField(name = "message_id")
  private Integer messageId;

  /**
   * 发送者 QQ 号
   */
  @JSONField(name = "userId")
  private Long userId;

  /**
   * 消息内容
   */
  @JSONField(name = "message")
  private String message;

  /**
   * 原始消息内容
   */
  @JSONField(name = "raw_message")
  private String rawMessage;

  /**
   * 字体
   */
  @JSONField(name = "font")
  private Integer font;

  /**
   * 发送人信息
   */
  @JSONField(name = "sender")
  private Sender sender;
  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
