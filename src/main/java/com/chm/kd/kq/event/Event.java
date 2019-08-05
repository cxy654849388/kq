package com.chm.kd.kq.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author caihongming
 * @version v1.0
 * @title EventMsg
 * @package com.chm.kd.kq.event
 * @since 2019-07-10
 * description 事件消息类
 **/
@Data
public class Event implements Serializable {

  /**
   * 上报类型	说明
   * message	收到消息
   * notice	群、讨论组变动等通知类事件
   * request	加好友请求、加群请求／邀请
   */
  @JSONField(name = "post_type")
  protected String postType;

  /**
   * 事件发生的时间戳
   */
  @JSONField(name = "time")
  protected Long time;

  /**
   * 收到消息的机器人 QQ 号
   */
  @JSONField(name = "self_id")
  protected Long selfId;

  /**
   * true 表示拦截事件（不再让后面的插件处理），否则表示忽略（不拦截）
   */
  @JSONField(name = "block")
  protected Boolean block;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
