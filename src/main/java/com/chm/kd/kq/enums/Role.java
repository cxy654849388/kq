package com.chm.kd.kq.enums;

/**
 * @author caihongming
 * @version v1.0
 * @title Role
 * @package com.chm.kd.kq.enums
 * @since 2019-07-11
 * description 角色
 **/
public enum Role {

  /**
   * 群主
   */
  owner("owner", "群主"),

  /**
   *
   */
  admin("admin", "管理员"),

  /**
   * 普通成员
   */
  member("member", "普通成员"),

  /**
   * 默认
   */
  default_("default", "默认");

  private String code;

  private String description;

  Role(String code, String description) {
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
  public static Role getByCode(String code) {
    for (Role role : values()) {
      if (role.getCode().equals(code)) {
        return role;
      }
    }
    return default_;
  }

  public static boolean isAdmin(String role) {
    return owner.getCode().equals(role) || admin.getCode().equals(role);
  }
}
