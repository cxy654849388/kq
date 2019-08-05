package com.chm.kd.kq.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author caihongming
 * @version v1.0
 * @title EventNotice
 * @package com.chm.kd.kq.event
 * @since 2019-07-11
 * description
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class EventNotice extends Event {

  /**
   * 事件名
   * group_upload 群文件上传
   * group_admin 群管理员变动
   * group_decrease 群成员减少
   * group_increase 群成员增加
   * friend_add 好友添加
   */
  @JSONField(name = "notice_type")
  private String noticeType;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
