package com.chm.kd.kq.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupSwitch
 * @package com.chm.kd.kq.entity
 * @since 2019-07-18
 * description 群组配置表
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_group_switch")
public class GroupSwitch extends Model<GroupSwitch> {

  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  @TableId(value = "ID", type = IdType.AUTO)
  private Long id;

  /**
   * 群号
   */
  @TableField("GROUP_ID")
  private Long groupId;

  /**
   * 总开关 0=关 1=开
   */
  @TableField("MAIN_SWITCH")
  private Boolean mainSwitch;

  /**
   * 问答开关 0=关 1=开
   */
  @TableField("QUESTION_SWITCH")
  private Boolean questionSwitch;

  /**
   * 加群提示开关 0=关 1=开
   */
  @TableField("JOIN_SWITCH")
  private Boolean joinSwitch;

  /**
   * 退群提示开关 0=关 1=开
   */
  @TableField("QUIT_SWITCH")
  private Boolean quitSwitch;

  /**
   * 改名提示开关 0=关 1=开
   */
  @TableField("MODIFY_CARD_SWITCH")
  private Boolean modifyCardSwitch;

  /**
   * 改名提示开关 0=关 1=开
   */
  @TableField("REMIND_SWITCH")
  private Boolean remindSwitch;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }

}
