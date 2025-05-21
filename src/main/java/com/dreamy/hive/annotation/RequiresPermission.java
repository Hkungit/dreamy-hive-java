package com.dreamy.hive.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解，用于标记需要特定权限才能访问的方法
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermission {
    /**
     * 需要的权限编码
     */
    String value();
    
    /**
     * 是否需要同时具有多个权限，默认为false，即只需要任一权限即可
     */
    boolean logical() default false;
} 