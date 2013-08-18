package com.example.newsfeeds.utils;

import java.lang.annotation.*;

/**
 * Created by chenyang.coder@gmail.com on 13-8-18 下午3:14.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LogInfo {
	String tag() default "";
	String info() default "";
	boolean showTrace() default false;
}
