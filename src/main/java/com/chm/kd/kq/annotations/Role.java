package com.chm.kd.kq.annotations;

import java.lang.annotation.*;

/**
 * @author caihongming
 * @version v1.0
 * @title Role
 * @package com.chm.kd.kq.annotations
 * @since 2019-07-17
 * description
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Role {
  String[] roles() default "master";
}
