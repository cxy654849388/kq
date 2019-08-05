package com.chm.kd.kq.enums;

/**
 * @author caihongming
 * @version v1.0
 * @title RequestType
 * @package com.chm.kd.kq.enums
 * @since 2019-07-11
 * description 请求类型
 **/
public enum RequestType {

  /**
   * 加好友请求
   */
  friend("friend", "加好友请求"),

  /**
   * 加群请求／邀请
   */
  group("group", "加群请求／邀请"),

  /**
   * 默认
   */
  default_("default", "默认");

  private String code;

  private String description;

  RequestType(String code, String description) {
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
  public static RequestType getByCode(String code) {
    for (RequestType requestType : values()) {
      if (requestType.getCode().equals(code)) {
        return requestType;
      }
    }
    return default_;
  }
}
