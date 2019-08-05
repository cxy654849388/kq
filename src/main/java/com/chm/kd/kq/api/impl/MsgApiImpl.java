package com.chm.kd.kq.api.impl;

import com.chm.kd.kq.annotations.Switch;
import com.chm.kd.kq.api.MsgApi;
import com.chm.kd.kq.utils.Okhttp3Utils;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author caihongming
 * @version v1.0
 * @title MsgApiImpl
 * @package com.chm.kd.kq.api.impl
 * @since 2019-07-12
 * description
 **/
@Slf4j
@Service
@Data
public class MsgApiImpl implements MsgApi {

  @Value("${cq.http.host}")
  private String cqHttpHost;

  @Value("${cq.http.port}")
  private String cqHttpPort;

  @Override
  public void sendPrivateMsg(Long userId, String message) {
    Map<String, Object> paramsMap = Maps.newHashMap();
    paramsMap.put("user_id", userId);
    paramsMap.put("message", message);
    String url = String.format("http://%s:%s/send_private_msg_rate_limited", cqHttpHost, cqHttpPort);
    try {
      Okhttp3Utils.httpClientFormPostReturnAsMap(url, paramsMap, 600L);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  @Override
  public void sendGroupMsg(Long groupId, String message) {
    Map<String, Object> paramsMap = Maps.newHashMap();
    paramsMap.put("group_id", groupId);
    paramsMap.put("message", message);
    String url = String.format("http://%s:%s/send_group_msg_rate_limited", cqHttpHost, cqHttpPort);
    try {
      Okhttp3Utils.httpClientFormPostReturnAsMap(url, paramsMap, 600L);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  @Override
  public void sendDiscussMsg(Long discussId, String message) {
  }
}
