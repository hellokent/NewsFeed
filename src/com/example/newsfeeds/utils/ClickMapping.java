package com.example.newsfeeds.utils;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

import static android.view.View.OnClickListener;

/**
 * 用于设置一个类中View的点击事件，可以映射。使用方法如下：
 * 1.将点击事件的方法上加上注解{@link ClickMapping}，在注解里设置对应View的id，可以设置多个Id，多个Id表示这些View共享一个点击事件
 * 2.调用map方法，
 * map可以传入Activity，这时会用{@code activity.findViewById(id)} 去找到id对应的View。
 * 也可以传入View，会调用{@code view.findViewById(id)}去找id对应的View
 * <p/>
 * User: yang-chen@renren-inc.com
 * Date: 2/17/13 1:41 PM
 */
public final class ClickMapping {

	private static final Class[] HANDLER_INTERFACE_CLASS = new Class[]{OnClickListener.class};

	public static <T> void map(Object o, T t, FindViewAbility<T> findViewAbility) {
		assert o != null;
		Class<?> clazz = o.getClass();
		final ClickHandler clickHandler = new ClickHandler(o, findViewAbility);
		final HashMap<Integer, Method> idMethodMap = clickHandler.kIdMethodMap;
		final OnClickListener onClickListener = (OnClickListener) Proxy.newProxyInstance(
				clazz.getClassLoader(),
				HANDLER_INTERFACE_CLASS,
				clickHandler);
		while (clazz != null &&
				!Activity.class.equals(clazz) &&
				!FragmentActivity.class.equals(clazz) &&
				!View.class.equals(clazz) &&
				!ViewGroup.class.equals(clazz)) {
			final Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				final OnClick click = method.getAnnotation(OnClick.class);
				if (click == null) {
					continue;
				}

				for (int i = 0; i < click.value().length; ++i) {
					final int viewId = click.value()[i];
					final View v = findViewAbility.findViewById(t, viewId);
					if (v == null) {
						continue;
					}
					if (idMethodMap.containsKey(viewId)) {
						continue;
					}
					idMethodMap.put(viewId, method);
					v.setOnClickListener(onClickListener);
				}
			}
			clazz = clazz.getSuperclass();
		}
	}

	public static void map(Activity activity) {
		map(activity, activity, ACTIVITY_FIND_VIEW);
	}

	public static void map(View view, Object object) {
		map(object, view, ROOTVIEW_FIND_VIEW);
	}

	static class ClickHandler<T> implements InvocationHandler {
		T kType;
		FindViewAbility<T> kFindView;

		ClickHandler(final T t, final FindViewAbility<T> findViewAbility) {
			kType = t;
			kFindView = findViewAbility;
		}

		final HashMap<Integer, Method> kIdMethodMap = new HashMap<Integer, Method>();

		@Override
		public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
			View view = (View) args[0];
			int viewId = view.getId();
			if (!kIdMethodMap.containsKey(view.getId())) {
				return null;
			}
			Method $method = kIdMethodMap.get(viewId);
			$method.setAccessible(true);
			if ($method.getParameterTypes().length == 1)
				$method.invoke(kType, args);
			else
				$method.invoke(kType);
			return null;
		}
	}

	/**
	 * 获取View的接口，假如不用Activity或者根View，就需要实现这个接口了
	 *
	 * @param <T>
	 */
	public static interface FindViewAbility<T> {
		View findViewById(T t, int id);
	}

	static final FindViewAbility<Activity> ACTIVITY_FIND_VIEW = new FindViewAbility<Activity>() {
		@Override
		public View findViewById(final Activity activity, final int id) {
			return activity.findViewById(id);
		}
	};

	static final FindViewAbility<View> ROOTVIEW_FIND_VIEW = new FindViewAbility<View>() {
		@Override
		public View findViewById(final View view, final int id) {
			return view.findViewById(id);
		}
	};
}
