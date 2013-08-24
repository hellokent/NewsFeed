package com.example.newsfeeds.ui.feed;

import com.example.newsfeeds.ui.base.BaseManager;

import java.util.LinkedList;

/**
 * Created by chenyang.coder@gmail.com on 13-8-21 上午1:13.
 */
public class FeedManager extends BaseManager {

	static int sIndex = 0;
	public static final int LOAD_LIST_FINISHED = ++sIndex;

	LinkedList<BaseFeedModel> kFeedModels = new LinkedList<BaseFeedModel>();

	public void loadFeeds(int offset){

	}
}
