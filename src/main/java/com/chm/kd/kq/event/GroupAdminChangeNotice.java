package com.chm.kd.kq.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupAdminChangeEventMsg
 * @package com.chm.kd.kq.event
 * @since 2019-07-11
 * description 群管理员变动消息类
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupAdminChangeNotice extends EventNotice {

  /**
   * set、unset	事件子类型，分别表示设置和取消管理员
   */
  @JSONField(name = "sub_type")
  private String subType;

  /**
   * 群号
   */
  @JSONField(name = "group_id")
  private Long groupId;

  /**
   * 管理员 QQ 号
   */
  @JSONField(name = "user_id")
  private Long userId;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
