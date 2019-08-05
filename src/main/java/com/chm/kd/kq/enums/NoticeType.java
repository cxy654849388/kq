package com.chm.kd.kq.enums;

/**
 * @author caihongming
 * @version v1.0
 * @title NoticeType
 * @package com.chm.kd.kq.enums
 * @since 2019-07-11
 * description 事件名
 **/
public enum NoticeType {

  /**
   * 群文件上传
   */
  group_upload("group_upload", "群文件上传"),

  /**
   * 群管理员变动
   */
  group_admin("group_admin", "群管理员变动"),

  /**
   * 群成员减少
   */
  group_decrease("group_decrease", "群成员减少"),

  /**
   * 群成员增加
   */
  group_increase("group_increase", "群成员增加"),

  /**
   * 好友添加
   */
  friend_add("friend_add", "好友添加"),

  /**
   * 默认
   */
  default_("default", "默认");

  private String code;

  private String description;

  NoticeType(String code, String description) {
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
  public static NoticeType getByCode(String code) {
    for (NoticeType noticeType : values()) {
      if (noticeType.getCode().equals(code)) {
        return noticeType;
      }
    }
    return default_;
  }
}
