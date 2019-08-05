package com.chm.kd.kq.scheduled;

import com.chm.kd.kq.api.GroupApi;
import com.chm.kd.kq.entity.QuestionAnswer;
import com.chm.kd.kq.info.GroupInfo;
import com.chm.kd.kq.info.GroupMemberInfo;
import com.chm.kd.kq.service.QuestionAnswerService;
import com.chm.kd.kq.utils.MapSetUtils;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author caihongming
 * @version v1.0
 * @title ScheduleTask
 * @package com.chm.kd.kq.scheduled
 * @since 2019-07-26
 * description
 **/
@Component
@Configuration
@EnableScheduling
public class ScheduleTask {

  @Autowired
  private GroupApi groupApi;

  @Autowired
  private QuestionAnswerService questionAnswerService;

  /**
   * 定时更新成员信息
   */
  @Scheduled(fixedRate = 600000L)
  private void updateGroupMemberInfo() {
    List<GroupInfo> groupInfoList = groupApi.getGroupList();
    groupInfoList.forEach(groupInfo -> {
      Long groupId = groupInfo.getGroupId();
      Map<Long, GroupMemberInfo> groupMemberInfoMap = MapSetUtils.GROUP_MEMBER_INFO_LIST_MAP.getOrDefault(groupId, Maps.newConcurrentMap());
      groupApi.getGroupMemberList(groupId).stream().filter(groupMemberInfo -> !groupMemberInfoMap.containsKey(groupMemberInfo.getUserId())).
              forEach(groupMemberInfo -> groupMemberInfoMap.put(groupMemberInfo.getUserId(), groupMemberInfo));
      MapSetUtils.GROUP_MEMBER_INFO_LIST_MAP.put(groupId, groupMemberInfoMap);
    });
  }

  /**
   * 定时初始化问题id
   */
  @Scheduled(fixedRate = 10000L)
  private void initQuestionsId() {
    List<QuestionAnswer> questionAnswerList = questionAnswerService.getEmptyQuestionsIdList();
    questionAnswerList.forEach(questionAnswer -> questionAnswerService.setQuestionId(questionAnswer));
  }
}
