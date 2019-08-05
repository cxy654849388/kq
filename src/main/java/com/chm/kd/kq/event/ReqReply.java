package com.chm.kd.kq.event;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author caihongming
 * @version v1.0
 * @title ReqReply
 * @package com.chm.kd.kq.event
 * @since 2019-07-11
 * description 请求回复类
 **/
@Data
public class ReqReply implements Serializable {

  /**
   * 是否同意请求	默认 不处理
   */
  @JSONField(name = "approve")
  private String approve;

  /**
   * 添加后的好友备注（仅在同意时有效） 默认 无备注
   */
  @JSONField(name = "remark")
  private String remark;

  /**
   * 拒绝理由（仅在拒绝时有效） 默认 无理由
   */
  @JSONField(name = "reason")
  private String reason;
}
