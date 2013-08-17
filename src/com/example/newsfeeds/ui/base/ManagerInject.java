package com.example.newsfeeds.ui.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Manager注解，负责注入Manager对象
 * Created by chenyang.coder@gmail.com on 13-8-17 下午10:39.
 */
@Target(ElementType.TYPE)
public @interface ManagerInject {
	Class<? extends BaseManager> value();
}
