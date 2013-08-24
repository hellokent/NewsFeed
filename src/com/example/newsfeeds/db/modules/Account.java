package com.example.newsfeeds.db.modules;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.example.newsfeeds.AccountType;

/**
 * Created by chenyang.coder@gmail.com on 13-8-24 下午7:46.
 */
@Table("account")
public class Account extends Model {

	@Column(value = "type", notNull = true, unique = true)
	public AccountType type;

	@Column(value = "data0", notNull = true)
	public String data0;

	@Column("data1")
	public String data1;

	@Override
	public Account clone() {
		return (Account) super.clone();
	}

	@Override
	protected void onEndSave(boolean isInserting) {
		super.onEndSave(isInserting);
		type.setAccount(this);
	}
}
