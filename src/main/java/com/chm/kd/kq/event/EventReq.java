package com.chm.kd.kq.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author caihongming
 * @version v1.0
 * @title EventReq
 * @package com.chm.kd.kq.event
 * @since 2019-07-11
 * description 请求消息类
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class EventReq extends Event {

  /**
   * 请求类型
   * friend 加好友请求
   * group 加群请求/邀请
   */
  @JSONField(name = "request_type")
  private String requestType;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
