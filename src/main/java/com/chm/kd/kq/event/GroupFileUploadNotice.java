package com.chm.kd.kq.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupFileUploadEventMsg
 * @package com.chm.kd.kq.event
 * @since 2019-07-11
 * description 群文件上传消息类
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupFileUploadNotice extends EventNotice {

  /**
   * 群号
   */
  @JSONField(name = "group_id")
  private Long groupId;
  /**
   * 发送者 QQ 号
   */
  @JSONField(name = "user_id")
  private Long userId;
  /**
   * 文件信息
   */
  @JSONField(name = "file")
  private File file;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
