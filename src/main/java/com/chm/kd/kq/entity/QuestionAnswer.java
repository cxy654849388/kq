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
 * @title QuestionAnswer
 * @package com.chm.kd.kq.entity
 * @since 2019-07-18
 * description Q群问答表
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_question_answer")
public class QuestionAnswer extends Model<QuestionAnswer> {

  private static final long serialVersionUID = 1L;

  @TableId(value = "ID", type = IdType.AUTO)
  private Long id;

  /**
   * 问题id
   */
  @TableField("QUESTION_ID")
  private Integer questionId;

  /**
   * 群号
   */
  @TableField("GROUP_ID")
  private Long groupId;

  /**
   * 问题
   */
  @TableField("QUESTION")
  private String question;

  /**
   * 答案
   */
  @TableField("ANSWER")
  private String answer;

  /**
   * 问答类型 1 精确问 2 模糊问
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
