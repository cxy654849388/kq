package com.chm.kd.kq.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chm.kd.kq.entity.QuestionAnswer;
import com.chm.kd.kq.mapper.QuestionAnswerMapper;
import com.chm.kd.kq.service.QuestionAnswerService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author caihongming
 * @version v1.0
 * @title QuestionAnswerServiceImpl
 * @package com.chm.kd.kq.service.impl
 * @since 2019-07-12
 * description Q群问答表 服务实现类
 **/
@Service
public class QuestionAnswerServiceImpl extends ServiceImpl<QuestionAnswerMapper, QuestionAnswer> implements QuestionAnswerService {

  @Override
  public QuestionAnswer getByGroupIdAndQuestion(Long groupId, String question) {
    QuestionAnswer questionAnswer = this.getOne(Wrappers.<QuestionAnswer>lambdaQuery().eq(QuestionAnswer::getGroupId, groupId).eq(QuestionAnswer::getQuestion, question), false);
    return null != questionAnswer ? questionAnswer : new QuestionAnswer();
  }

  @Override
  public List<QuestionAnswer> findQuestion(Long groupId, String question) {
    return this.list(Wrappers.<QuestionAnswer>lambdaQuery().isNotNull(QuestionAnswer::getQuestionId).eq(QuestionAnswer::getGroupId, groupId)
            .and(i -> i.like(QuestionAnswer::getQuestion, question).or().like(QuestionAnswer::getAnswer, question)));
  }

  @Override
  public Long saveQuestion(Long groupId, List<String> questionList, String answer, String type) {
    return questionList.stream().filter(question -> {
      QuestionAnswer questionAnswer = this.getByGroupIdAndQuestion(groupId, question);
      questionAnswer.setGroupId(groupId);
      questionAnswer.setQuestion(question);
      questionAnswer.setAnswer(answer);
      questionAnswer.setType(type);
      return this.saveOrUpdate(questionAnswer);
    }).count();
  }

  @Override
  public Long deleteQuestion(Long groupId, List<Integer> questionsIdList) {
    return questionsIdList.stream().filter(questionsId -> this.remove(Wrappers.<QuestionAnswer>lambdaQuery().eq(QuestionAnswer::getGroupId, groupId).eq(QuestionAnswer::getQuestionId, questionsId))).count();
  }

  @Override
  public List<QuestionAnswer> getEmptyQuestionsIdList() {
    return this.list(Wrappers.<QuestionAnswer>lambdaQuery().isNull(QuestionAnswer::getQuestionId));
  }

  @Override
  public List<QuestionAnswer> answers(Long groupId, String question) {
    return this.list(Wrappers.<QuestionAnswer>lambdaQuery().eq(QuestionAnswer::getGroupId, groupId).and(
            i -> i.and(b -> b.eq(QuestionAnswer::getQuestion, question).eq(QuestionAnswer::getType, "1"))
                    .or(b -> b.apply("{0} LIKE CONCAT('%', QUESTION, '%')", question).eq(QuestionAnswer::getType, "2"))));
  }

  @Override
  public boolean setQuestionId(QuestionAnswer questionAnswer) {
    Long questionId = baseMapper.getMaxQuestionId(questionAnswer.getGroupId());
    return this.update(Wrappers.<QuestionAnswer>lambdaUpdate().set(QuestionAnswer::getQuestionId, Optional.ofNullable(questionId).orElse(0L) + 1L)
            .eq(QuestionAnswer::getId, questionAnswer.getId()));
  }

  @Override
  public Long copyQuestion(Long srcGroupId, Long destGroupId) {
    AtomicReference<Long> count = new AtomicReference<>(0L);
    List<QuestionAnswer> srcQuestionAnswerList = this.list(Wrappers.<QuestionAnswer>lambdaQuery().eq(QuestionAnswer::getGroupId, srcGroupId));
    srcQuestionAnswerList.forEach(questionAnswer -> count.updateAndGet(v -> v + this.saveQuestion(destGroupId, Lists.newArrayList(questionAnswer.getQuestion()), questionAnswer.getAnswer(), questionAnswer.getType())));
    return count.get();
  }
}
