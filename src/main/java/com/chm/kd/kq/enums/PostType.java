package com.chm.kd.kq.enums;

/**
 * @author caihongming
 * @version v1.0
 * @title PostType
 * @package com.chm.kd.kq.enums
 * @since 2019-07-11
 * description 上报类型
 **/
public enum PostType {

  /**
   * 收到消息
   */
  message("message", "收到消息"),

  /**
   * 群、讨论组变动等通知类事件
   */
  notice("notice", "群、讨论组变动等通知类事件"),

  /**
   * 加好友请求、加群请求／邀请
   */
  request("request", "加好友请求、加群请求／邀请"),

  /**
   * 默认
   */
  default_("default", "默认");

  private String code;

  private String description;

  PostType(String code, String description) {
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
  public static PostType getByCode(String code) {
    for (PostType postType : values()) {
      if (postType.getCode().equals(code)) {
        return postType;
      }
    }
    return default_;
  }
}
