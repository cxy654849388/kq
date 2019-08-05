package com.chm.kd.kq.config;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author caihongming
 * @version v1.0
 * @title ApplicationConfig
 * @package com.chm.kd.kq.config
 * @since 2019-07-26
 * description
 **/
@Component
@ConfigurationProperties(prefix = "custom")
@Data
public class ApplicationConfig {

  private Long master;

  private List<Map<String, Object>> promptArrays;

  private Map<String, Object> prompts;

  private List<Map<String, Object>> switchTypeArrays;

  private Map<String, Object> switchTypes;

  @PostConstruct
  public void init() {
    this.prompts = promptArrays.stream().collect(Collectors.toMap(map -> MapUtils.getString(map, "name"), map -> MapUtils.getString(map, "type")));
    this.switchTypes = switchTypeArrays.stream().collect(Collectors.toMap(map -> MapUtils.getString(map, "name"), map -> MapUtils.getString(map, "field")));
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
