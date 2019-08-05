package com.chm.kd.kq.aop;

import com.chm.kd.kq.annotations.Switch;
import com.chm.kd.kq.event.GroupMemberDecreaseNotice;
import com.chm.kd.kq.event.GroupMemberIncreaseNotice;
import com.chm.kd.kq.event.GroupMsg;
import com.chm.kd.kq.service.GroupSwitchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author caihongming
 * @version v1.0
 * @title SwitchAop
 * @package com.chm.kd.kq.aop
 * @since 2019-07-19
 * description
 **/
@Aspect
@Component
@Slf4j
@Order(3)
public class SwitchAop {

  @Autowired
  private GroupSwitchService groupSwitchService;

  @Around(value = "@within(aSwitch) && args(groupMsg)")
  public Object within(ProceedingJoinPoint pjp, Switch aSwitch, GroupMsg groupMsg) throws Throwable {
    String methodName = pjp.getSignature().getName();
    if (checkSwitch(aSwitch, methodName, groupMsg.getGroupId())) {
      return pjp.proceed();
    }
    return ObjectUtils.NULL;
  }

  @Around(value = "@annotation(aSwitch) && args(groupMsg)")
  public Object annotation(ProceedingJoinPoint pjp, Switch aSwitch, GroupMsg groupMsg) throws Throwable {
    String methodName = pjp.getSignature().getName();
    if (checkSwitch(aSwitch, methodName, groupMsg.getGroupId())) {
      return pjp.proceed();
    }
    return ObjectUtils.NULL;
  }

  @Around("@annotation(aSwitch) && args(groupMemberIncreaseNotice)")
  public Object groupMemberIncreaseNotice(ProceedingJoinPoint pjp, Switch aSwitch, GroupMemberIncreaseNotice groupMemberIncreaseNotice) throws Throwable {
    String methodName = pjp.getSignature().getName();
    if (checkSwitch(aSwitch, methodName, groupMemberIncreaseNotice.getGroupId())) {
      return pjp.proceed();
    }
    return ObjectUtils.NULL;
  }

  @Around("@annotation(aSwitch) && args(groupMemberDecreaseNotice)")
  public Object groupMemberDecreaseNotice(ProceedingJoinPoint pjp, Switch aSwitch, GroupMemberDecreaseNotice groupMemberDecreaseNotice) throws Throwable {
    String methodName = pjp.getSignature().getName();
    if (checkSwitch(aSwitch, methodName, groupMemberDecreaseNotice.getGroupId())) {
      return pjp.proceed();
    }
    return ObjectUtils.NULL;
  }

  @Around("@annotation(aSwitch) && args(groupId,userId)")
  public Object args(ProceedingJoinPoint pjp, Switch aSwitch, Long groupId, Long userId) throws Throwable {
    String methodName = pjp.getSignature().getName();
    if (checkSwitch(aSwitch, methodName, groupId)) {
      return pjp.proceed();
    }
    return ObjectUtils.NULL;
  }

  private boolean checkSwitch(Switch aSwitch, String methodName, Long groupId) {
    Set<String> switchTypeList = Stream.of(aSwitch.types()).filter(StringUtils::isNotBlank).collect(Collectors.toSet());
    switchTypeList.add("MAIN_SWITCH");
    return Stream.of(aSwitch.excludeMethod()).anyMatch(s -> StringUtils.equals(s, methodName)) || groupSwitchService.checkSwitchTypeList(groupId, switchTypeList);
  }
}
