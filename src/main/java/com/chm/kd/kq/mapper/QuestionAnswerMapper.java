package com.chm.kd.kq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chm.kd.kq.entity.QuestionAnswer;
import org.apache.ibatis.annotations.Param;

/**
 * @author caihongming
 * @version v1.0
 * @title QuestionAnswerMapper
 * @package com.chm.kd.kq.mapper
 * @since 2019-07-12
 * description Q群问答表 Mapper 接口
 **/
public interface QuestionAnswerMapper extends BaseMapper<QuestionAnswer> {

  /**
   * 获取最大问题id
   *
   * @param groupId
   * @return
   */
  Long getMaxQuestionId(@Param("groupId") Long groupId);
}
