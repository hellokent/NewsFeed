package com.example.newsfeeds;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import com.activeandroid.ActiveAndroid;

/**
 * Created by chenyang.coder@gmail.com on 13-8-24 下午3:59.
 */
public class GlobalApplication extends Application {
	static Application sApp;
	static Handler sUIHandler;

	@Override
	public void onCreate() {
		super.onCreate();
		sApp = this;
		ActiveAndroid.initialize(sApp, BuildConfig.DEBUG);
	}

	public static Application getApplication(){
		return sApp;
	}

	public static Handler getUiIHandler(){
		if (sUIHandler == null){
			sUIHandler = new Handler(Looper.getMainLooper());
		}
		return sUIHandler;
	}
}
