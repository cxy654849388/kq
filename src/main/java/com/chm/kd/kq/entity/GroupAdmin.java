package com.chm.kd.kq.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupAdmin
 * @package com.chm.kd.kq.entity
 * @since 2019-07-18
 * description 群管理控制表
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_group_admin")
public class GroupAdmin extends Model<GroupAdmin> {

  private static final long serialVersionUID = 1L;

  /**
   * id主键
   */
  @TableId(value = "ID", type = IdType.AUTO)
  private Long id;

  /**
   * 群号
   */
  @TableField("GROUP_ID")
  private Long groupId;

  /**
   * 管理员QQ
   */
  @TableField("USER_ID")
  private Long userId;

  /**
   * 控制类型 1 设置为管理员 2禁止为管理员
   */
  @TableField("TYPE")
  private String type;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }
}
