package com.chm.kd.kq.info;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupInfo
 * @package com.chm.kd.kq.info
 * @since 2019-07-12
 * description 群信息
 **/
@Data
public class GroupInfo implements Serializable {

  /**
   * 群号
   */
  @JSONField(name = "group_id")
  private Long groupId;
  /**
   * 群名称
   */
  @JSONField(name = "group_name")
  private String groupName;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
