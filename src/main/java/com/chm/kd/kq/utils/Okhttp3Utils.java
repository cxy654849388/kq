package com.chm.kd.kq.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import okhttp3.*;
import org.apache.commons.collections.MapUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author caihongming
 * @version v1.0
 * @title Okhttp3Utils
 * @package com.chm.kd.kq.utils
 * @since 2019-07-12
 * description  okhttp3工具类
 **/
public class Okhttp3Utils {
  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
  public static final MediaType XML = MediaType.parse("application/xml; charset=utf-8");

  public Okhttp3Utils() {
  }

  private static OkHttpClient getHttpClient(long timeout) {
    return (new OkHttpClient()).newBuilder().connectTimeout(timeout, TimeUnit.SECONDS).readTimeout(timeout, TimeUnit.SECONDS).writeTimeout(timeout, TimeUnit.SECONDS).build();
  }

  public static String httpClientGetReturnAsString(String url, Map<String, Object> paramMap, long timeout) throws Exception {
    OkHttpClient client = getHttpClient(timeout);
    Request request = (new Request.Builder()).url(buildGetUrlEndConnetorParam(url, paramMap)).build();
    Response response = client.newCall(request).execute();
    return response.body().string();
  }

  public static Map<String, Object> httpClientGetReturnAsMap(String url, Map<String, Object> paramMap, long timeout) throws Exception {
    OkHttpClient client = getHttpClient(timeout);
    Request request = (new Request.Builder()).url(buildGetUrlEndConnetorParam(url, paramMap)).build();
    Response response = client.newCall(request).execute();
    String respTxt = response.body().string();
    return JSONObject.parseObject(respTxt);
  }

  public static String httpClientJsonPostReturnAsString(String url, String jsonString, long timeout) throws Exception {
    OkHttpClient client = getHttpClient(timeout);
    RequestBody body = RequestBody.create(JSON, jsonString);
    Request request = (new Request.Builder()).url(url).post(body).build();
    Response response = client.newCall(request).execute();
    return response.body().string();
  }

  public static String httpClientJsonPostReturnAsString(String url, Map<String, Object> paramMap, long timeout) throws Exception {
    OkHttpClient client = getHttpClient(timeout);
    RequestBody body = RequestBody.create(JSON, JSONObject.toJSONString(paramMap));
    Request request = (new Request.Builder()).url(url).post(body).build();
    Response response = client.newCall(request).execute();
    return response.body().string();
  }

  public Map<String, Object> httpClientJsonPostReturnAsMap(String url, String jsonString, long timeout) throws Exception {
    OkHttpClient client = getHttpClient(timeout);
    RequestBody body = RequestBody.create(JSON, jsonString);
    Request request = (new Request.Builder()).url(url).post(body).build();
    Response response = client.newCall(request).execute();
    String respTxt = response.body().string();
    return JSONObject.parseObject(respTxt);
  }

  public Map<String, Object> httpClientJsonPostReturnAsMap(String url, Map<String, Object> paramMap, long timeout) throws Exception {
    OkHttpClient client = getHttpClient(timeout);
    RequestBody body = RequestBody.create(JSON, JSONObject.toJSONString(paramMap));
    Request request = (new Request.Builder()).url(url).post(body).build();
    Response response = client.newCall(request).execute();
    String respTxt = response.body().string();
    return JSONObject.parseObject(respTxt);
  }

  public static String httpClientFormPostReturnAsString(String url, Map<String, Object> paramMap, long timeout) throws Exception {
    OkHttpClient client = getHttpClient(timeout);
    FormBody body = buildFormParams(paramMap);
    Request request = (new Request.Builder()).url(url).post(body).build();
    Response response = client.newCall(request).execute();
    return response.body().string();
  }

  public static Map<String, Object> httpClientFormPostReturnAsMap(String url, Map<String, Object> paramMap, long timeout) throws Exception {
    OkHttpClient client = getHttpClient(timeout);
    FormBody body = buildFormParams(paramMap);
    Request request = (new Request.Builder()).url(url).post(body).build();
    Response response = client.newCall(request).execute();
    String respTxt = response.body().string();
    return JSONObject.parseObject(respTxt);
  }

  private static FormBody buildFormParams(Map<String, Object> paramMap) {
    okhttp3.FormBody.Builder builder = new okhttp3.FormBody.Builder();
    if (MapUtils.isNotEmpty(paramMap)) {
      paramMap.entrySet().stream().filter(e -> null != e.getKey() && null != e.getValue()).collect(Collectors.toMap(
              Map.Entry::getKey,
              Map.Entry::getValue
      )).forEach((k, v) -> builder.add(k, v.toString()));
    }
    return builder.build();
  }

  private static HttpUrl buildGetUrlEndConnetorParam(String url, Map<String, Object> paramMap) {
    HttpUrl queryUrl = HttpUrl.parse(url);
    if (MapUtils.isNotEmpty(paramMap)) {
      TreeMap treeMap = new TreeMap(paramMap);
      String key = (String) treeMap.firstKey();
      Object value = treeMap.get(key);

      for (queryUrl = getQueryHttpUrl(key, value, queryUrl); (key = (String) treeMap.higherKey(key)) != null; queryUrl = getQueryHttpUrl(key, value, queryUrl)) {
        value = treeMap.get(key);
      }
    }

    return queryUrl;
  }

  private static HttpUrl getQueryHttpUrl(String key, Object value, HttpUrl httpUrl) {
    if (value != null) {
      if (value instanceof String) {
        httpUrl = httpUrl.newBuilder().addEncodedQueryParameter(key, (String) value).build();
      } else {
        httpUrl = httpUrl.newBuilder().addEncodedQueryParameter(key, String.valueOf(value)).build();
      }
    }

    return httpUrl;
  }

  public static void main(String[] args) {
    String url = "http://172.30.0.13:8080/ktb/login";
    Map<String, Object> hmap = Maps.newHashMap();
    hmap.put("systemName", "zx14");
    hmap.put("systemPW", "E10ADC3949BA59ABBE56E057F20F883E");
    hmap.put("systemType", "1");

    try {
      String result = httpClientFormPostReturnAsString(url, hmap, 1000);
      System.out.println("result: =" + result);
    } catch (Exception var4) {
      var4.printStackTrace();
    }

  }
}

