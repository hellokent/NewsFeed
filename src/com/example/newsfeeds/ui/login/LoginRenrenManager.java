package com.example.newsfeeds.ui.login;

import com.example.newsfeeds.R;
import com.example.newsfeeds.net.httppool.HttpPool;
import com.example.newsfeeds.net.renren.LoginModel;
import com.example.newsfeeds.net.renren.LoginParam;
import com.example.newsfeeds.net.renren.base.CommonParams;
import com.example.newsfeeds.net.renren.base.RenrenPost;
import com.example.newsfeeds.net.renren.base.RenrenVisitor;
import com.example.newsfeeds.ui.base.BaseManager;
import com.example.newsfeeds.utils.L;
import com.example.newsfeeds.utils.LogInfo;
import com.example.newsfeeds.utils.Utils;

/**
 * Created by chenyang.coder@gmail.com on 13-8-24 下午2:57.
 */
@LogInfo(info = "login_renren_manger")
public class LoginRenrenManager extends BaseManager {

	protected void loginRenren(final String username, final String pwd){
		HttpPool.POOL.sendRequest(new RenrenVisitor<LoginParam, LoginModel>(
				"client/login", new LoginParam(username, pwd), LoginModel.class) {
			@Override
			public void onSuccess(LoginModel model) {
				L.v("Login in onSuccess callback");
				CommonParams.sSessionKey = model.session_key;
				RenrenPost.sSecretKey = model.secret_key;
				L.v("1");
				Utils.toast(R.string.login_renren_welcome, model.user_name);
				L.v("sendMessage");
				sendMessage(LoginRenren.LOGIN_SUCCESS);
			}

			@Override
			public void onError(LoginModel model) {
				sendMessage(LoginRenren.LOGIN_FAILED);

			}
		});

	}
}
