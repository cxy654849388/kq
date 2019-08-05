package com.chm.kd.kq.enums;

/**
 * @author caihongming
 * @version v1.0
 * @title Sex
 * @package com.chm.kd.kq.enums
 * @since 2019-07-11
 * description 性别
 **/
public enum Sex {

  /**
   * 男
   */
  male("male", "男"),

  /**
   * 女
   */
  female("female", "女"),

  /**
   * 未知
   */
  unknown("unknown", "未知"),

  /**
   * 默认
   */
  default_("default", "默认");

  private String code;

  private String description;

  Sex(String code, String description) {
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
  public static Sex getByCode(String code) {
    for (Sex sex : values()) {
      if (sex.getCode().equals(code)) {
        return sex;
      }
    }
    return default_;
  }
}
