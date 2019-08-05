package com.chm.kd.kq.annotations;

import java.lang.annotation.*;

/**
 * @author caihongming
 * @version v1.0
 * @title Switch
 * @package com.chm.kd.kq.annotations
 * @since 2019-07-19
 * description 开关控制注解
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Switch {

  /**
   * 需求排除的方法名
   *
   * @return
   */
  String[] excludeMethod() default "";

  /**
   * 开关类别
   *
   * @return
   */
  String[] types() default "";
}
