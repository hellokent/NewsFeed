package com.example.newsfeeds.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Control层，负责UI的逻辑层
 * Created by chenyang.coder@gmail.com on 13-8-17 下午10:38.
 */
public abstract class BaseManager {
	Handler mUIHandler;

	public void setUIHandler(Handler handler) {
		this.mUIHandler = handler;
	}

	public void sendMessage(final int what){
		mUIHandler.obtainMessage(what).sendToTarget();
	}

	public void sendMessage(final int what, final int arg1, final int arg2){
		mUIHandler.obtainMessage(what, arg1, arg2).sendToTarget();
	}

	public void sendMessage(final int what, final Object obj){
		mUIHandler.obtainMessage(what, obj).sendToTarget();
	}

	public void sendMessage(final int what, final int arg1, final int arg2, final Object obj){
		mUIHandler.obtainMessage(what, arg1, arg2, obj).sendToTarget();
	}

	protected void onCreate(final Intent intent, final Bundle savedInstanceState){}
	protected void onStart(){}
	protected void onResume(){}
	protected void onPause(){}
	protected void onRestart(){}
	protected void onSaveInstanceState(Bundle bundle){}
	protected void onStop(){}
	protected void onDestory(){}
}
