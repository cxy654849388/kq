package com.chm.kd.kq.info;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupMemberInfo
 * @package com.chm.kd.kq.info
 * @since 2019-07-12
 * description 群成员信息
 **/
@Data
public class GroupMemberInfo implements Serializable {

  /**
   * 群号
   */
  @JSONField(name = "group_id")
  private Long groupId;

  /**
   * QQ 号
   */
  @JSONField(name = "user_id")
  private Long userId;

  /**
   * 昵称
   */
  @JSONField(name = "nickname")
  private String nickname;

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
   * 加群时间戳
   */
  @JSONField(name = "join_time")
  private Integer joinTime;

  /**
   * 最后发言时间戳
   */
  @JSONField(name = "last_sent_time")
  private Integer lastSentTime;

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
   * 是否不良记录成员
   */
  @JSONField(name = "unfriendly")
  private Boolean unfriendly;

  /**
   * 专属头衔
   */
  @JSONField(name = "title")
  private String title;

  /**
   * 专属头衔过期时间戳
   */
  @JSONField(name = "title_expire_time")
  private Integer titleExpireTime;

  /**
   * 是否允许修改群名片
   */
  @JSONField(name = "card_changeable")
  private Boolean cardChangeable;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
