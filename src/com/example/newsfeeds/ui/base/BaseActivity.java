package com.example.newsfeeds.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.example.newsfeeds.utils.L;

/**
 * Activity的基础类，每个Activity需要注册一个Manager，在这个类的注解里声明即可
 * Created by chenyang.coder@gmail.com on 13-8-17 下午10:37.
 */
public abstract class BaseActivity<T extends BaseManager> extends Activity {
	static final Class[] EMPTY_CLASSES = {};

	T mManager;
	final Handler mManagerHandler = new Handler(Looper.getMainLooper()){
		@Override
		public void dispatchMessage(Message msg) {
			super.dispatchMessage(msg);
			L.v("dispatchMessage");
			BaseActivity.this.handleMessage(msg);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			L.v("handleMessage");
			BaseActivity.this.handleMessage(msg);
		}
	};

	public BaseActivity(){
		super();
		ManagerInject managerInject = getClass().getAnnotation(ManagerInject.class);
		if (managerInject != null){
			try {
				mManager = (T) managerInject.value().getConstructor(EMPTY_CLASSES).newInstance();
				mManager.setUIHandler(mManagerHandler);
			} catch (Exception e) {
				e.printStackTrace();
				L.exception(e);
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getManager().onCreate(getIntent(), savedInstanceState);
	}


	@Override
	protected void onStart() {
		super.onStart();
		getManager().onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		getManager().onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		getManager().onPause();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		getManager().onRestart();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getManager().onSaveInstanceState(outState);
	}

	@Override
	protected void onStop() {
		super.onStop();
		getManager().onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		getManager().onDestory();
	}
	public abstract void handleMessage(final Message message);

	public T getManager(){
		return mManager;
	}
}
