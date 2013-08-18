package com.example.newsfeeds.utils;

import android.text.TextUtils;
import android.util.Log;
import com.example.newsfeeds.BuildConfig;

import java.lang.reflect.Method;

/**
 * print log
 * Created by chenyang.coder@gmail.com on 13-8-18 下午3:07.
 */
public final class L {
	static final int STACK_DEPTH = 4;
	static final String TAG = "newsfeed";
	private L(){}

	public static void v(final String msg, final Object... args){
		if (!BuildConfig.DEBUG){
			return;
		}
		log(Log.VERBOSE, msg, args);
	}

	private static void log(int level, final String msg, final Object... args){
		final StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		final StackTraceElement stack = stacks[STACK_DEPTH];
		final StringBuilder strBuffer = new StringBuilder();
		String tag = "";
		String info = "";
		try {
			final Class clazz = Class.forName(stack.getClassName());
			final LogInfo classLogInfo = (LogInfo) clazz.getAnnotation(LogInfo.class);
			if (classLogInfo != null){
				tag = classLogInfo.tag();
				info = classLogInfo.info();
			}
			LogInfo methodLogInfo = null;
			for (Method method : clazz.getMethods()){
				if (method.getName().equals(stack.getMethodName())){
					methodLogInfo = method.getAnnotation(LogInfo.class);
					break;
				}
			}
			if (methodLogInfo != null){
				if (!TextUtils.isEmpty(methodLogInfo.tag())){
					tag = methodLogInfo.tag();
				}

				if (!TextUtils.isEmpty(methodLogInfo.info())){
					info = methodLogInfo.info();
				}

				if (methodLogInfo.showTrace()){
					strBuffer.append("Stack Trace:\n");
					for (int i = STACK_DEPTH; i < stacks.length; ++i){
						strBuffer.append("\t")
								.append(getThreadInfo(stacks[i]));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (TextUtils.isEmpty(tag)){
			tag = TAG;
		}
		if (!TextUtils.isEmpty(info)){
			strBuffer.append("[")
					.append(info)
					.append("] ");
		}
		strBuffer.append(String.format(msg, args));
		Log.println(level, tag, strBuffer.toString());
	}

	private static String getThreadInfo(StackTraceElement stack){
		final String fileName = stack.getFileName();
		return String.format("%s.%s(%d)\n",
				fileName.substring(0, fileName.indexOf(".")),
				stack.getMethodName(),
				stack.getLineNumber());
	}

}
