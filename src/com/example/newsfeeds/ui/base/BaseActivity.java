package com.example.newsfeeds.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Activity的基础类，每个Activity需要注册一个Manager，在这个类的注解里声明即可
 * Created by chenyang.coder@gmail.com on 13-8-17 下午10:37.
 */
public abstract class BaseActivity<T extends BaseManager> extends Activity {
	static final Class[] MANAGER_CONSTRUCTOR_CLASSES = {Handler.class};

	T mManager = null;
	Handler mManagerHandler = new Handler(Looper.getMainLooper()){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			BaseActivity.this.handleMessage(msg);
		}
	};


	public BaseActivity(){
		super();
		ManagerInject managerInject = getClass().getAnnotation(ManagerInject.class);
		if (managerInject != null){
			try {
				mManager = (T) managerInject.value().getConstructor(MANAGER_CONSTRUCTOR_CLASSES).newInstance(mManagerHandler);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mManager.onInit(getIntent());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mManager.onDestory();
	}

	public abstract void handleMessage(final Message message);

	public T getManager(){
		return mManager;
	}
}
