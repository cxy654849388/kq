package com.chm.kd.kq.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupMsgEvent
 * @package com.chm.kd.kq.event
 * @since 2019-07-10
 * description 群聊事件消息类
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMsg extends EventMsg {

  /**
   * normal、anonymous、notice	消息子类型，正常消息是 normal，匿名消息是 anonymous，系统提示（如「管理员已禁止群内匿名聊天」）是 notice
   */
  @JSONField(name = "sub_type")
  private String subType;

  /**
   * 群号
   */
  @JSONField(name = "group_id")
  private Long groupId;

  /**
   * 匿名信息，如果不是匿名消息则为 null
   */
  @JSONField(name = "anonymous")
  private Anonymous anonymous;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
