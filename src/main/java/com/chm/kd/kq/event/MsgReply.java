package com.chm.kd.kq.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author caihongming
 * @version v1.0
 * @title MsgReply
 * @package com.chm.kd.kq.event
 * @since 2019-07-11
 * description 消息回复类
 **/
@Data
public class MsgReply implements Serializable {

  /**
   * 要回复的内容 默认 不回复
   */
  @JSONField(name = "reply")
  private String reply;

  /**
   * 消息内容是否作为纯文本发送（即不解析 CQ 码），只在 reply 字段是字符串时有效 默认	不转义
   */
  @JSONField(name = "auto_escape")
  private Boolean autoEscape;

  /**
   * 是否要在回复开头 at 发送者（自动添加），发送者是匿名用户时无效  默认	at 发送者
   */
  @JSONField(name = "at_sender")
  private Boolean atSender;

  /**
   * 撤回该条消息	默认 不撤回
   */
  @JSONField(name = "delete")
  private Boolean delete;

  /**
   * 把发送者踢出群组（需要登录号权限足够），不拒绝此人后续加群请求，发送者是匿名用户时无效 默认 不踢
   */
  @JSONField(name = "kick")
  private Boolean kick;

  /**
   * 把发送者禁言 ban_duration 指定时长，对匿名用户也有效 默认 不禁言
   */
  @JSONField(name = "ban")
  private Boolean ban;

  /**
   * 禁言时长  默认	30 分钟
   */
  @JSONField(name = "ban_duration")
  private Integer banDuration;


  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
