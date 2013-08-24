package com.example.newsfeeds.db.modules;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.example.newsfeeds.AccountType;

/**
 * Created by chenyang.coder@gmail.com on 13-8-23 上午12:07.
 */
public abstract class BaseFriendModel extends Model {

	@Column(value = "uid", notNull = true)
	public String friendId;

	@Column(value = "uname", notNull = true)
	public String name;

	@Column(value = "head_url", notNull = true)
	public String headUrl;

	@Column(value = "account", notNull = true)
	public AccountType accountType;

}
