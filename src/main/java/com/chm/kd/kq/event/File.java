package com.chm.kd.kq.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author caihongming
 * @version v1.0
 * @title File
 * @package com.chm.kd.kq.event
 * @since 2019-07-11
 * description 文件信息类
 **/
@Data
public class File implements Serializable {

  /**
   * 文件 ID
   */
  @JSONField(name = "id")
  private String id;

  /**
   * 文件名
   */
  @JSONField(name = "name")
  private String name;

  /**
   * 文件大小（字节数）
   */
  @JSONField(name = "size")
  private Long size;

  /**
   * busid（目前不清楚有什么作用）
   */
  @JSONField(name = "busid")
  private Long busid;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
