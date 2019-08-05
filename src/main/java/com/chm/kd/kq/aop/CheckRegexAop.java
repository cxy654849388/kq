package com.chm.kd.kq.aop;

import com.chm.kd.kq.annotations.Regex;
import com.chm.kd.kq.event.GroupMsg;
import com.chm.kd.kq.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * @author caihongming
 * @version v1.0
 * @title CheckRegex
 * @package com.chm.kd.kq.aop
 * @since 2019-07-17
 * description 正则表达式统一校验
 **/
@Aspect
@Component
@Slf4j
@Order(2)
public class CheckRegexAop {

  @Around(value = "@annotation(regex)&&args(groupMsg)")
  public Object regex(ProceedingJoinPoint pjp, Regex regex, GroupMsg groupMsg) throws Throwable {
    String rawMessage = groupMsg.getRawMessage();
    if (Stream.of(regex.anyMatchRegexps()).anyMatch(s -> RegexUtils.matchRegex(s, rawMessage))) {
      // 符合正则表达式，可执行方法
      pjp.proceed();
    } else if (Stream.of(regex.allMatchRegexps()).allMatch(s -> RegexUtils.matchRegex(s, rawMessage))) {
      // 符合正则表达式，可执行方法
      pjp.proceed();
    } else if (Stream.of(regex.anyNotMatchRegexps()).anyMatch(s -> RegexUtils.notMatchRegex(s, rawMessage))) {
      // 符合正则表达式，可执行方法
      pjp.proceed();
    } else if (Stream.of(regex.allNotMatchRegexps()).allMatch(s -> RegexUtils.notMatchRegex(s, rawMessage))) {
      // 符合正则表达式，可执行方法
      pjp.proceed();
    }
    return ObjectUtils.NULL;
  }
}
