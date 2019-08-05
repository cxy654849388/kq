package com.chm.kd.kq.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupMemberIncreaseNotice
 * @package com.chm.kd.kq.event
 * @since 2019-07-11
 * description 群成员增加消息类
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMemberIncreaseNotice extends EventNotice {

  /**
   * approve、invite 事件子类型，分别表示管理员已同意入群、管理员邀请入群
   */
  @JSONField(name = "sub_type")
  private String subType;

  /**
   * 群号
   */
  @JSONField(name = "group_id")
  private Long groupId;

  /**
   * 操作者 QQ 号
   */
  @JSONField(name = "operator_id")
  private Long operatorId;

  /**
   * 加入者 QQ 号
   */
  @JSONField(name = "user_id")
  private Long userId;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
