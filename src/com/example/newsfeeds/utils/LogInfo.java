package com.example.newsfeeds.utils;

import java.lang.annotation.*;

/**
 * 设置Log属性的注解
 * ${tag} Log的TAG, 默认是L类里的TAG
 * ${info} 设置之后，Log的样式为：[info] msg....
 * ${showTrace} 是否列出栈信息，只对设置在方法上的注解有效
 * 用法：
 * 设置在类名上，可以控制这个类里任何一个处L类的调用时候的信息
 * 设置在方法上，可以忽略类名的注解，假如有相同的方法名，会找第一个有这个注解的。
 * 注意：
 * 任意一个同名方法设置了showTrace，其余同名方法调用L也会打出来trace（这个算bug，懒得改了。。。）
 * 注解在类名上和方法上覆盖原则：对于tag和info来说，优先使用方法上的注解，假如方法上没有，会查看类名上的
 * 栈的打印和普通异常打印的格式是一样的，在IDE下可点击
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
