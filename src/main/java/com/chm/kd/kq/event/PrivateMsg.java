package com.chm.kd.kq.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author caihongming
 * @version v1.0
 * @title PrivateMsgEvent
 * @package com.chm.kd.kq.event
 * @since 2019-07-10
 * description 私聊事件消息类
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class PrivateMsg extends EventMsg {

  /**
   * friend、group、discuss、other	消息子类型，如果是好友则是 friend，如果从群或讨论组来的临时会话则分别是 group、discuss
   */
  @JSONField(name = "sub_type")
  private String subType;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
