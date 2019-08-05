package com.chm.kd.kq.api;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author caihongming
 * @version v1.0
 * @title KqResponse
 * @package com.chm.kd.kq.api
 * @since 2019-07-12
 * description api结果返回类
 **/
@Data
public class KqResponse implements Serializable {

  private String status;

  private Integer retcode;

  private Object data;

  public boolean isOk() {
    return "ok".equals(status);
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
