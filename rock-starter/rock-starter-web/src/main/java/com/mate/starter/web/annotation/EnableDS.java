package com.mate.starter.web.annotation;

import java.lang.annotation.*;

/**
 * 启用数据范围注解
 * <p>样例：@EnableDS</p>
 *
 * @author kevin
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableDS {
}
