package com.chm.kd.kq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chm.kd.kq.entity.QuestionAnswer;

import java.util.List;

/**
 * @author caihongming
 * @version v1.0
 * @title QuestionAnswerService
 * @package com.chm.kd.kq.service
 * @since 2019-07-12
 * description Q群问答表 服务类
 **/
public interface QuestionAnswerService extends IService<QuestionAnswer> {

  /**
   * 获取精确问答信息
   *
   * @param groupId  QQ群号
   * @param question 问题
   * @return
   */
  QuestionAnswer getByGroupIdAndQuestion(Long groupId, String question);

  /**
   * 查问
   *
   * @param groupId  QQ群号
   * @param question 问题
   * @return
   */
  List<QuestionAnswer> findQuestion(Long groupId, String question);

  /**
   * 保存问题
   *
   * @param groupId  QQ群号
   * @param question 问题
   * @param answer   回答
   * @param type     消息类型
   * @return
   */
  Long saveQuestion(Long groupId, List<String> question, String answer, String type);

  /**
   * 删除问答
   *
   * @param groupId
   * @param questionsIdList
   * @return
   */
  Long deleteQuestion(Long groupId, List<Integer> questionsIdList);

  /**
   * 获取所有questionsId为空的问答对象
   *
   * @return
   */
  List<QuestionAnswer> getEmptyQuestionsIdList();

  /**
   * 回答问题
   *
   * @param groupId
   * @param question
   * @return
   */
  List<QuestionAnswer> answers(Long groupId, String question);

  /**
   * 设置问题id
   *
   * @param questionAnswer
   * @return
   */
  boolean setQuestionId(QuestionAnswer questionAnswer);

  /**
   * 复制问答
   * @param srcGroupId
   * @param destGroupId
   * @return
   */
  Long copyQuestion(Long srcGroupId, Long destGroupId);
}
