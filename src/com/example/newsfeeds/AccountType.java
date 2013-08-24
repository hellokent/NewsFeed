package com.example.newsfeeds;

import android.text.TextUtils;
import com.activeandroid.Model;
import com.example.newsfeeds.db.modules.Account;

/**
 * Created by chenyang.coder@gmail.com on 13-8-23 上午12:10.
 */
public enum AccountType {
	RENREN,
	SINA;
	Account mAccount;
	public void setAccount(Account account){
		mAccount = account.clone();
	}

	public String getSessionKeyForRenren(){
		return mAccount.data0;
	}

	public String getSecretKeyForRenren(){
		if (mAccount == null || TextUtils.isEmpty(mAccount.data1)){
			return Config.RENREN_SECRET_KEY;
		}
		return mAccount.data1;
	}

	public void setDataForRenren(String sessionKey, String secretKey){
		Account account = Model.load(Account.class, "type = ?", RENREN);
		if (account == null){
			account = new Account();
			account.type = RENREN;
		}
		account.data0 = sessionKey;
		account.data1 = secretKey;
		account.save();
	}

	public void destory(){
		mAccount = null;
	}
}
