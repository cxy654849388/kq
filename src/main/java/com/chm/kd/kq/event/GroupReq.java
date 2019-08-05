package com.chm.kd.kq.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupReq
 * @package com.chm.kd.kq.event
 * @since 2019-07-11
 * description 加群请求／邀请 请求类
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupReq extends EventReq {

  /**
   * add、invite	请求子类型，分别表示加群请求、邀请登录号入群
   */
  @JSONField(name = "sub_type")
  private String sub_type;

  /**
   * 群号
   */
  @JSONField(name = "group_id")
  private Long group_id;

  /**
   * 发送请求的 QQ 号
   */
  @JSONField(name = "user_id")
  private Long user_id;

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
