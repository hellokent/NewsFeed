package com.example.newsfeeds.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 * @说明 视图和ID映射关系工具
 * @说明 解除了ID映射间的重复代码和类型装换, 这样可以把重心转移到业务逻辑 在继承上不侵入任何类
 * @使用 1.为了提高效率将需要映射的View控件可见域置为public 2.对于需要映射的对象中的属性导入:
 */
public final class ViewMapUtil {
	/**
	 * @param object   要映射对象
	 * @param rootView 要映射对象所要查询的根控件
	 * @author dingwei.chen
	 */
	public static void viewMapping(Object object, View rootView) {
		Class<?> clazz = object.getClass();
		while (clazz != null &&
				!clazz.equals(Activity.class) &&
				!clazz.equals(Fragment.class) &&
				!clazz.equals(FragmentActivity.class) &&
				!clazz.equals(Object.class)) {
			Field[] fields = clazz.getDeclaredFields();// 必须是public
			for (Field f : fields) {
				ViewMapping mapping = f.getAnnotation(ViewMapping.class);
				int id = 0;
				if (mapping == null) {
					continue;
				}
				try {
					id = mapping.value();
					f.setAccessible(true);
					View childView = rootView.findViewById(id);
					if (childView == null) {
						continue;
					}
					f.set(object, childView);
				} catch (Exception e) {
					System.err.println(String.format(
							"view map error = %h, clazz:%s, field:%s",
							id,
							clazz.getSimpleName(),
							f.getName()));
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
			clazz = clazz.getSuperclass();
		}
	}

	public static View viewMapping(Object object, Context context) {
		final View rootView = View.inflate(context, getViewMapping(object.getClass()).value(), null);
		viewMapping(object, rootView);
		return rootView;
	}

	/**
	 * 根据ViewHolder的Class对象，新建一个ViewHolder类和对应Layout的View对象
	 *
	 * @return Pair.first是对应的ViewHolder，Pair.second是ViewHolder注解里面的Layout对应的View
	 * @author yang-chen
	 */
	public static <T> Pair<T, View> viewMapping(Class<T> clazz, LayoutInflater inflater, ViewGroup root) {
		Pair<T, View> pair = null;
		T object;
		try {
			object = clazz.getConstructor(new Class[]{}).newInstance();
			View rootView = inflater.inflate(
					getViewMapping(clazz).value(), root, false);
			pair = new Pair<T, View>(object, rootView);
			viewMapping(object, rootView);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pair;
	}

	public static <T> Pair<T, View> viewMapping(Class<T> clazz, LayoutInflater inflater) {
		return viewMapping(clazz, inflater, null);
	}

	public static <T> Pair<T, View> viewMapForConvert(Class<T> clazz, View convertView, Context context) {
		final T $t;
		if (convertView != null) {
			$t = (T) convertView.getTag();
		} else {
			Pair<T, View> pair = viewMapping(clazz, (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
			$t = pair.first;
			convertView = pair.second;
			convertView.setTag($t);
		}
		return new Pair<T, View>($t, convertView);
	}

	static ViewMapping getViewMapping(Class<?> clazz) {
		ViewMapping mapping = null;

		while (!Activity.class.equals(clazz)
				|| !Fragment.class.equals(clazz)
				|| !FragmentActivity.class.equals(clazz)) {
			mapping = clazz.getAnnotation(ViewMapping.class);
			if (mapping != null) {
				break;
			} else {
				clazz = clazz.getSuperclass();
			}
		}
		return mapping;
	}
}
