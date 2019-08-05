package com.chm.kd.kq.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author caihongming
 * @version v1.0
 * @title Anonymous
 * @package com.chm.kd.kq.event
 * @since 2019-07-11
 * description 匿名消息类
 **/
@Data
public class Anonymous implements Serializable {

  /**
   * 匿名用户 ID
   */
  @JSONField(name = "id")
  private Long id;

  /**
   * 匿名用户名称
   */
  @JSONField(name = "name")
  private String name;

  /**
   * 匿名用户 flag，在调用禁言 API 时需要传入
   */
  @JSONField(name = "flag")
  private String flag;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
