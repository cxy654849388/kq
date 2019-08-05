package com.chm.kd.kq.enums;

/**
 * @author caihongming
 * @version v1.0
 * @title MessageType
 * @package com.chm.kd.kq.enums
 * @since 2019-07-11
 * description 消息类型
 **/
public enum MessageType {

  /**
   * 私聊消息
   */
  private_("private", "私聊消息"),

  /**
   * 群消息
   */
  group("group", "群消息"),

  /**
   * 讨论组消息
   */
  discuss("discuss", "讨论组消息"),

  /**
   * 默认
   */
  default_("default", "默认");

  private String code;

  private String description;

  MessageType(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * 根据value返回枚举类型,主要在switch中使用
   *
   * @param code
   * @return
   */
  public static MessageType getByCode(String code) {
    for (MessageType messageType : values()) {
      if (messageType.getCode().equals(code)) {
        return messageType;
      }
    }
    return default_;
  }
}
