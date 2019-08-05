package com.chm.kd.kq.aop;

import com.chm.kd.kq.exception.EndException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author caihongming
 * @version v1.0
 * @title EndAop
 * @package com.chm.kd.kq.aop
 * @since 2019-07-18
 * description 结束流程
 **/
@Aspect
@Component
@Slf4j
@Order
public class EndAop {

  @Around(value = "@annotation(com.chm.kd.kq.annotations.End)")
  public Object end(ProceedingJoinPoint pjp) throws Throwable {
    pjp.proceed();
    throw EndException.getInstance();
  }
}
