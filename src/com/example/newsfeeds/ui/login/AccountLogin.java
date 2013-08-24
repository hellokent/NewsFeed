package com.example.newsfeeds.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.newsfeeds.R;
import com.example.newsfeeds.utils.ViewMapUtil;
import com.example.newsfeeds.utils.ViewMapping;

/**
 * Created by chenyang.coder@gmail.com on 13-8-24 上午12:55.
 */
@ViewMapping(R.layout.account_login)
public class AccountLogin extends Activity {

	@ViewMapping(R.id.login_renren)
	Button mRenrenLoginBtn;

	@ViewMapping(R.id.login_sina)
	Button mSinaLoginBtn;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewMapUtil.mapForActivity(this);
	}

	public void loginRenrenClicked(View view) {
		startActivity(new Intent(this, LoginRenren.class));
	}

	public void loginSinaClicked(View view) {
	}
}