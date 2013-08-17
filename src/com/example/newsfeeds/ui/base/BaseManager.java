package com.example.newsfeeds.ui.base;

import android.content.Intent;
import android.os.Handler;

/**
 * Control层，负责UI的逻辑层
 * Created by chenyang.coder@gmail.com on 13-8-17 下午10:38.
 */
public abstract class BaseManager {
	final Handler mUIHandler;
	public BaseManager(final Handler handler){
		mUIHandler = handler;
	}

	protected abstract void onInit(final Intent intent);

	protected abstract void onDestory();
}
