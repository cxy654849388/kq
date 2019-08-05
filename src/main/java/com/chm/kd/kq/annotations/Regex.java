package com.chm.kd.kq.annotations;

import java.lang.annotation.*;

/**
 * @author caihongming
 * @version v1.0
 * @title Regex
 * @package com.chm.kd.kq.annotations
 * @since 2019-07-17
 * description 正则表达式配置
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Regex {

  /**
   * 匹配其中任意一个
   *
   * @return
   */
  String[] anyMatchRegexps() default "";

  /**
   * 匹配全部
   *
   * @return
   */
  String[] allMatchRegexps() default "";

  /**
   * 其中有任意一个不匹配
   *
   * @return
   */
  String[] anyNotMatchRegexps() default "";

  /**
   * 全部都不匹配
   *
   * @return
   */
  String[] allNotMatchRegexps() default "";
}
