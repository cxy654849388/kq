package com.chm.kd.kq.controller;

import com.chm.kd.kq.api.GroupApi;
import com.chm.kd.kq.mapper.HealthCheckMapper;
import com.chm.kd.kq.utils.RegexUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author caihongming
 * @version v1.0
 * @title BasicController
 * @package com.chm.kd.kq.controller
 * @since 2019-07-24
 * description 基础控制器
 **/
@RestController
@Slf4j
public class BasicController {

  @Autowired
  private HealthCheckMapper healthCheckMapper;

  @Autowired
  private GroupApi groupApi;

  private static final String MODIFY_NAME_REGEX = "^\\[QQ:群名片变更,QQ=([0-9]+),新名片=[\\s\\S]+\\]$";

  @PostMapping(value = "/test")
  public Object test(@RequestBody Map<String, Object> params) {
    log.debug("test,params:{}", params);
    String typeCode = MapUtils.getString(params, "TypeCode");
    if (StringUtils.equals("GetNewMsg", typeCode)) {
      Long groupId = MapUtils.getLong(params, "Fromgroup");
      String message = MapUtils.getString(params, "Message");
      if (RegexUtils.matchRegex(MODIFY_NAME_REGEX, message)) {
        Long userId = NumberUtils.createLong(StringUtils.replacePattern(message, MODIFY_NAME_REGEX, "$1"));
        log.debug("触发改名，groupId:{},userId:{}", groupId, userId);
        groupApi.sendModifyCardPrompt(groupId, userId);
      }
    }
    return null;
  }

  @GetMapping(value = "/healthCheck")
  public Object healthCheck() {
    Integer result = healthCheckMapper.healthCheck();
    Map<String, Object> map = Maps.newHashMap();
    map.put("resultCode", 1 == result ? "0" : "1");
    return map;
  }

}
