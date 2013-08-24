package com.example.newsfeeds.db.modules;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.example.newsfeeds.AccountType;
import com.example.newsfeeds.db.NewsFeedType;

import java.util.Calendar;

/**
 * Created by chenyang.coder@gmail.com on 13-8-23 上午12:04.
 */
@Table("newsfeed")
public class NewsFeedModel extends Model {

	@Column(value = "feedid", notNull = true)
	public String feedId;

	@Column(value = "created_time", notNull = true)
	public Calendar createdTime;

	@Column(value = "source", notNull = true)
	public AccountType sourceType;

	@Column(value = "publisher", notNull = true)
	public BaseFriendModel publisher;

	@Column(value = "comment_type", notNull = true)
	public int commentCount;

	@Column(value = "type", notNull = true)
	public NewsFeedType type;

	@Column(value = "data0", notNull = true)
	public String data0;

	@Column("data1")
	public String data1;

	@Column("data2")
	public String data2;

	@Column("data3")
	public String data3;
}
