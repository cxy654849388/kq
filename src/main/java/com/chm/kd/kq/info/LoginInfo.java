package com.chm.kd.kq.info;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author caihongming
 * @version v1.0
 * @title LoginInfo
 * @package com.chm.kd.kq.info
 * @since 2019-07-12
 * description 登录号信息
 **/
@Data
public class LoginInfo implements Serializable {

  /**
   * QQ 号
   */
  @JSONField(name = "user_id")
  private Long userId;

  /**
   * QQ 昵称
   */
  @JSONField(name = "nickname")
  private String nickname;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
