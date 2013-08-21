package com.example.newsfeeds;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import com.example.newsfeeds.net.httppool.HttpPool;
import com.example.newsfeeds.net.renren.LoginModel;
import com.example.newsfeeds.net.renren.LoginParam;
import com.example.newsfeeds.net.renren.base.CommonParams;
import com.example.newsfeeds.net.renren.base.RenrenPost;
import com.example.newsfeeds.net.renren.base.RenrenVisitor;
import com.example.newsfeeds.ui.view.PieControl;
import com.example.newsfeeds.utils.L;
import com.example.newsfeeds.utils.LogInfo;

@LogInfo(info = "activity")
public class MyActivity extends Activity {
	PieControl mPieControl;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPieControl = new PieControl(this);
		View contentView = getLayoutInflater().inflate(R.layout.main, null);
		setContentView(contentView);
		FrameLayout rootView = (FrameLayout) contentView.findViewById(R.id.root);
		mPieControl.attachToContainer(rootView);

		HttpPool.POOL.sendRequest(new RenrenVisitor<LoginParam, LoginModel>(
				"client/login", new LoginParam("chenyang.coder@gmail.com", "bobking999"), LoginModel.class) {

			@Override
			public void onSuccess(LoginModel model) {
				CommonParams.sSessionKey = model.session_key;
				RenrenPost.sSecretKey = model.secret_key;
			}

			@Override
			public void onError(LoginModel model) {
				L.v("onError:%s", model.toString());
			}

			@Override
			public void onFailed(Throwable throwable) {
				super.onFailed(throwable);
				L.v("onFailed");
			}
		});
	}
}
