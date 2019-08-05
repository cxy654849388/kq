package com.chm.kd.kq.entity;

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
 * @title GroupPrompt
 * @package com.chm.kd.kq.entity
 * @since 2019-07-25
 * description 提示语表
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_group_prompt")
public class GroupPrompt extends Model<GroupPrompt> {

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
   * 具体内容
   */
  @TableField("CONTENT")
  private String content;

  /**
   * 提示语类型：
   * 1-加群提示
   * 2-改名提示
   */
  @TableField("TYPE")
  private String type;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }

}
