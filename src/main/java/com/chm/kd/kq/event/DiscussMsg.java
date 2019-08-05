package com.chm.kd.kq.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author caihongming
 * @version v1.0
 * @title DiscussEventMsg
 * @package com.chm.kd.kq.event
 * @since 2019-07-11
 * description 讨论组消息类
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class DiscussMsg extends EventMsg {

  /**
   * 讨论组 ID
   */
  @JSONField(name = "discuss_id")
  private Long discussId;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
