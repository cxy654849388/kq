package com.chm.kd.kq.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupMemberDecrNotice
 * @package com.chm.kd.kq.event
 * @since 2019-07-11
 * description 群成员减少消息类
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMemberDecreaseNotice extends EventNotice {

  /**
   * leave、kick、kick_me	事件子类型，分别表示主动退群、成员被踢、登录号被踢
   */
  @JSONField(name = "sub_type")
  private String subType;

  /**
   * 群号
   */
  @JSONField(name = "group_id")
  private Long groupId;

  /**
   * 操作者 QQ 号（如果是主动退群，则和 user_id 相同）
   */
  @JSONField(name = "operator_id")
  private Long operatorId;

  /**
   * 离开者 QQ 号
   */
  @JSONField(name = "user_id")
  private Long userId;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
