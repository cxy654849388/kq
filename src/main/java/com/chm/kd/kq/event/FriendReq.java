package com.chm.kd.kq.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author caihongming
 * @version v1.0
 * @title AddFriendReq
 * @package com.chm.kd.kq.event
 * @since 2019-07-11
 * description 加好友请求类
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class FriendReq extends EventReq {

  /**
   * 发送请求的 QQ 号
   */
  @JSONField(name = "user_id")
  private Long userId;

  /**
   * 验证信息
   */
  @JSONField(name = "comment")
  private String comment;

  /**
   * 请求 flag，在调用处理请求的 API 时需要传入
   */
  @JSONField(name = "flag")
  private String flag;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
