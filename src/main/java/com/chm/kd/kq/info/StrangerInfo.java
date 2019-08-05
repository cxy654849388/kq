package com.chm.kd.kq.info;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author caihongming
 * @version v1.0
 * @title StrangerInfo
 * @package com.chm.kd.kq.info
 * @since 2019-07-12
 * description 陌生人信息
 **/
@Data
public class StrangerInfo implements Serializable {

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
   * 性别，male 或 female 或 unknown
   */
  @JSONField(name = "sex")
  private String sex;

  /**
   * 年龄
   */
  @JSONField(name = "age")
  private Integer age;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
