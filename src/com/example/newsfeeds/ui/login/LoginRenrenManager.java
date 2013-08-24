package com.example.newsfeeds.ui.login;

import com.example.newsfeeds.AccountType;
import com.example.newsfeeds.R;
import com.example.newsfeeds.net.httppool.HttpPool;
import com.example.newsfeeds.net.renren.LoginModel;
import com.example.newsfeeds.net.renren.LoginParam;
import com.example.newsfeeds.net.renren.base.RenrenVisitor;
import com.example.newsfeeds.ui.base.BaseManager;
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
				AccountType.RENREN.setDataForRenren(model.session_key, model.secret_key);
				Utils.toast(R.string.login_renren_welcome, model.user_name);
				sendMessage(LoginRenren.LOGIN_SUCCESS);
			}

			@Override
			public void onError(LoginModel model) {
				sendMessage(LoginRenren.LOGIN_FAILED);

			}
		});

	}
}
