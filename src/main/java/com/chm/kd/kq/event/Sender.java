package com.chm.kd.kq.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author caihongming
 * @version v1.0
 * @title Sender
 * @package com.chm.kd.kq.event
 * @since 2019-07-10
 * description 发送人信息类
 **/
@Data
public class Sender implements Serializable {

  /**
   * 发送者 QQ 号
   */
  @JSONField(name = "user_id")
  private Long userId;

  /**
   * 昵称
   */
  @JSONField(name = "nickname")
  private String nickName;

  /**
   * 群名片／备注
   */
  @JSONField(name = "card")
  private String card;

  /**
   * 性别，male 或 female 或 unknown
   */
  @JSONField(name = "sex")
  private String sex;

  /**
   * 年龄
   */
  @JSONField(name = "age")
  private Integer age;

  /**
   * 地区
   */
  @JSONField(name = "area")
  private String area;

  /**
   * 成员等级
   */
  @JSONField(name = "level")
  private String level;

  /**
   * 角色，owner 或 admin 或 member
   */
  @JSONField(name = "role")
  private String role;

  /**
   * 专属头衔
   */
  @JSONField(name = "title")
  private String title;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
