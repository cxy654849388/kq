package com.chm.kd.kq.config;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupSwitch
 * @package com.chm.kd.kq.config
 * @since 2019-07-15
 * description
 **/
@Component
@ConfigurationProperties(prefix = "group.switch")
@Data
public class GroupSwitchConfig implements Serializable {

  /**
   * 打开命令
   */
  private String open;

  /**
   * 关闭命令
   */
  private String close;

  /**
   * 打开命令回复
   */
  private String openStr;

  /**
   * 再次打开命令回复
   */
  private String openAgainStr;

  /**
   * 关闭命令回复
   */
  private String closeStr;

  /**
   * 再次关闭命令回复
   */
  private String closeAgainStr;


  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
