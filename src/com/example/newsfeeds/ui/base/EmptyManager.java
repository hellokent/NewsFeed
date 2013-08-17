package com.example.newsfeeds.ui.base;

import android.content.Intent;
import android.os.Handler;

/**
 * BaseManager的空实现，用于其他不需要Manager的地方
 * Created by chenyang.coder@gmail.com on 13-8-17 下午11:04.
 */
public final class EmptyManager extends BaseManager {

	public EmptyManager(Handler handler) {
		super(handler);
	}

	@Override
	protected void onInit(Intent intent) {

	}

	@Override
	protected void onDestory() {

	}
}
