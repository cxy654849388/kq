package com.chm.kd.kq.annotations;

import java.lang.annotation.*;

/**
 * @author caihongming
 * @version v1.0
 * @title End
 * @package com.chm.kd.kq.annotations
 * @since 2019-07-18
 * description 结束流程
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface End {
}
