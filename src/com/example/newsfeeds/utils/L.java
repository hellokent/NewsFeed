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
		log(Log.VERBOSE, msg, args);
	}

	public static void d(final String msg, final Object... args){
		log(Log.DEBUG, msg, args);
	}

	public static void i(final String msg, final Object... args){
		log(Log.INFO, msg, args);
	}

	public static void w(final String msg, final Object... args){
		log(Log.WARN, msg, args);
	}

	public static void e(final String msg, final Object... args){
		log(Log.ERROR, msg, args);
	}


	private static void log(int level, final String msg, final Object... args){
		if (!BuildConfig.DEBUG){
			return;
		}
		final StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		final StackTraceElement stack = stacks[STACK_DEPTH];
		final StringBuilder strBuffer = new StringBuilder();
		String tag = "";
		String info = "";
		boolean isShownTrace = false;
		try {
			final Class clazz = Class.forName(stack.getClassName());
			LogInfo methodLogInfo = null;
			for (Method method : clazz.getMethods()){
				if (method.getName().equals(stack.getMethodName())){
					if (methodLogInfo == null)
						methodLogInfo = method.getAnnotation(LogInfo.class);
					if (methodLogInfo != null && methodLogInfo.showTrace()){
						isShownTrace = true;
						break;
					}
				}
			}
			if (methodLogInfo != null){
				tag = methodLogInfo.tag();
				info = methodLogInfo.info();
			}
			final LogInfo classLogInfo = (LogInfo) clazz.getAnnotation(LogInfo.class);
			if (classLogInfo != null){
				if (TextUtils.isEmpty(tag))
					tag = classLogInfo.tag();
				if (TextUtils.isEmpty(info))
					info = classLogInfo.info();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (TextUtils.isEmpty(tag)){
			tag = TAG;
		}
		if (isShownTrace){
			appendInfo(strBuffer, info)
					.append("Stack Trace:\n");
			for (int i = STACK_DEPTH; i < stacks.length; ++i){
				strBuffer.append("\t")
						.append(getThreadInfo(stacks[i]));
			}
		}
		appendInfo(strBuffer, info);
		strBuffer.append(String.format(msg, args));
		Log.println(level, tag, strBuffer.toString());
	}

	private static StringBuilder appendInfo(final StringBuilder strBuf, final String info){
		if (TextUtils.isEmpty(info)){
			return strBuf;
		}
		strBuf.append("[")
				.append(info)
				.append("] ");
		return strBuf;
	}

	private static String getThreadInfo(StackTraceElement stack){
		final String fileName = stack.getFileName();
		return String.format("%s.%s(%d)\n",
				fileName.substring(0, fileName.indexOf(".")),
				stack.getMethodName(),
				stack.getLineNumber());
	}

}
