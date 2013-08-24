package com.example.newsfeeds.ui.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import com.example.newsfeeds.BuildConfig;
import com.example.newsfeeds.R;
import com.example.newsfeeds.ui.base.BaseActivity;
import com.example.newsfeeds.ui.base.ManagerInject;
import com.example.newsfeeds.utils.L;
import com.example.newsfeeds.utils.Utils;
import com.example.newsfeeds.utils.ViewMapUtil;
import com.example.newsfeeds.utils.ViewMapping;

/**
 * Created by chenyang.coder@gmail.com on 13-8-24 下午2:31.
 */
@ViewMapping(R.layout.login_renren)
@ManagerInject(LoginRenrenManager.class)
public class LoginRenren extends BaseActivity<LoginRenrenManager> {

	public static final int LOGIN_SUCCESS = 1;

	public static final int LOGIN_FAILED = 2;

	@ViewMapping(R.id.username_edit)
	EditText mUsername;

	@ViewMapping(R.id.pwd_edit)
	EditText mPwd;

	ProgressDialog mProgressDialog;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewMapUtil.mapForActivity(this);
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setIndeterminate(true);
		if (BuildConfig.DEBUG){
			mUsername.setText("chenyang.coder@gmail.com");
			mPwd.setText("bobking999");
		}
	}

	@Override
	public void handleMessage(Message message) {
		L.v("handleMessage:%s", message);
		switch (message.what){
			case LOGIN_SUCCESS:
				Utils.dismissDialog(mProgressDialog);
				L.v("LOGIN_SUCCESS");
				finish();
				break;
			case LOGIN_FAILED:
				Utils.dismissDialog(mProgressDialog);
				L.v("LOGIN_FAILED");
				break;
		}
	}

	public void submitClicked(View view) {
		mProgressDialog.show();
		getManager().loginRenren(mUsername.getText().toString().trim(), mPwd.getText().toString());
	}
}