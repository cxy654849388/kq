package com.chm.kd.kq.enums;

/**
 * @author caihongming
 * @version v1.0
 * @title SubType
 * @package com.chm.kd.kq.enums
 * @since 2019-07-11
 * description 消息子类型
 **/
public enum SubType {

  /**
   * 好友消息
   */
  friend("friend", "好友消息"),

  /**
   * 群消息
   */
  group("group", "群消息"),

  /**
   * 讨论组消息
   */
  discuss("discuss", "讨论组消息"),

  /**
   * 其他消息
   */
  other("other", "其他消息"),

  /**
   * 正常消息
   */
  normal("normal", "正常消息"),

  /**
   * 匿名消息
   */
  anonymous("anonymous", "匿名消息"),

  /**
   * 系统提示（如「管理员已禁止群内匿名聊天」）
   */
  notice("notice", "系统提示（如「管理员已禁止群内匿名聊天」）"),

  /**
   * 设置管理员
   */
  set("set", "设置管理员"),

  /**
   * 取消管理员
   */
  unset("unset", "取消管理员"),

  /**
   * 管理员已同意入群
   */
  approve("approve", "管理员已同意入群"),

  /**
   * 管理员邀请入群/邀请登录号入群
   */
  invite("invite", "管理员邀请入群/邀请登录号入群"),

  /**
   * 主动退群
   */
  leave("leave", "主动退群"),

  /**
   * 成员被踢
   */
  kick("kick", "成员被踢"),

  /**
   * 登录号被踢
   */
  kick_me("kick_me", "登录号被踢"),

  /**
   * 加群请求
   */
  add("add", "加群请求"),

  /**
   * 默认
   */
  default_("default", "默认");


  private String code;

  private String description;

  SubType(String code, String description) {
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
  public static SubType getByCode(String code) {
    for (SubType subType : values()) {
      if (subType.getCode().equals(code)) {
        return subType;
      }
    }
    return default_;
  }
}
