package com.example.newsfeeds.utils;

import android.text.TextUtils;
import android.util.Log;
import com.example.newsfeeds.BuildConfig;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * print log
 * Created by chenyang.coder@gmail.com on 13-8-18 下午3:07.
 * Finished on 13-8-19 上午1:58.
 */
public final class L {
	static final int STACK_DEPTH = 4;
	static final String TAG = "newsfeed";
	private L(){}

	public static void v(final String msg, final Object... args){
		log(Log.VERBOSE, null, msg, args);
	}

	public static void d(final String msg, final Object... args){
		log(Log.DEBUG, null, msg, args);
	}

	public static void i(final String msg, final Object... args){
		log(Log.INFO, null, msg, args);
	}

	public static void w(final String msg, final Object... args){
		log(Log.WARN, null, msg, args);
	}

	public static void e(final String msg, final Object... args){
		log(Log.ERROR, null, msg, args);
	}

	public static void exception(final Throwable throwable, final String msg, final Object... args){
		log(Log.WARN, throwable, msg, args);
	}

	public static void exception(final Throwable throwable){
		log(Log.WARN, throwable, "");
	}

	private static void log(int level, Throwable throwable, String msg, Object... arg){
		if (!BuildConfig.DEBUG){
			return;
		}
		final StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		final StackTraceElement stack = stacks[STACK_DEPTH];
		final StringBuilder strBuffer = new StringBuilder();
		String tag = "";
		String info = "";
		boolean isShownTrace = false;
		boolean isEnable = true;
		try {
			final ArrayList<Class> classes = new ArrayList<Class>();
			String className = stack.getClassName();

			classes.add(Class.forName(stack.getClassName()));
			while (className.contains("$")){
				try {
					className = className.substring(0, className.lastIndexOf("$"));
					classes.add(Class.forName(className));
				}catch (Throwable ignored){}
			}
			for (Class clazz : classes){
				LogInfo methodLogInfo = null;
				for (Method method : clazz.getMethods()){
					if (method.getName().equals(stack.getMethodName())){
						if (methodLogInfo == null)
							methodLogInfo = method.getAnnotation(LogInfo.class);
						if (methodLogInfo != null && methodLogInfo.showTrace()){
							isShownTrace = true;
							break;
						}
						if (methodLogInfo != null){
							isEnable &= methodLogInfo.isEnable();
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (TextUtils.isEmpty(tag)){
			tag = TAG;
		}
		if (throwable == null){
			if (isShownTrace){
				appendInfo(strBuffer, info)
						.append("Stack Trace:\n");
				for (int i = STACK_DEPTH; i < stacks.length; ++i){
					strBuffer.append("\t")
							.append(getThreadInfo(stacks[i]));
				}
			}
		}
		appendInfo(strBuffer, info).append(String.format(msg, arg));
		if (throwable == null){
			Log.println(level, tag, strBuffer.toString());
		}else {
			Log.w(tag, strBuffer.toString(), throwable);
		}
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
		return String.format("at %s.%s(%s:%d)\n",
				stack.getClassName(),
				stack.getMethodName(),
				stack.getFileName(),
				stack.getLineNumber());
	}

}
