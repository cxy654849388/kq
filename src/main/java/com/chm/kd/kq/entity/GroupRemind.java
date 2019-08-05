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
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupRemind
 * @package com.chm.kd.kq.entity
 * @since 2019-07-30
 * description 群提醒配置表
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_group_remind")
public class GroupRemind extends Model<GroupRemind> {

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
   * 提醒对象qq号
   */
  @TableField("USER_ID")
  private Long userId;

  /**
   * 开始时间
   */
  @TableField("START_TIME")
  private LocalTime startTime;

  /**
   * 结束时间
   */
  @TableField("END_TIME")
  private LocalTime endTime;

  /**
   * 上次提醒时间
   */
  @TableField("LAST_REMIND_TIME")
  private LocalDateTime lastRemindTime;

  /**
   * 时间间隔
   */
  @TableField("`INTERVAL`")
  private LocalTime interval;

  /**
   * 提醒内容
   */
  @TableField("CONTENT")
  private String content;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + JSON.toJSONString(this);
  }

}
