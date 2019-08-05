package com.chm.kd.kq.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author caihongming
 * @version v1.0
 * @title FriendAddNotice
 * @package com.chm.kd.kq.event
 * @since 2019-07-11
 * description
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class FriendAddNotice extends EventNotice {

  /**
   * 新添加好友 QQ 号
   */
  @JSONField(name = "user_id")
  private Long userId;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
