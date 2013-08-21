package com.example.newsfeeds.ui.feed;

import android.os.Bundle;
import android.os.Message;
import android.widget.ListView;
import com.example.newsfeeds.R;
import com.example.newsfeeds.ui.base.BaseActivity;
import com.example.newsfeeds.ui.base.ManagerInject;
import com.example.newsfeeds.utils.ViewMapUtil;
import com.example.newsfeeds.utils.ViewMapping;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * 新鲜事Activity
 * Created by chenyang.coder@gmail.com on 13-8-21 上午1:11.
 */
@ManagerInject(FeedManager.class)
@ViewMapping(R.layout.feed_activity)
public class FeedActivity extends BaseActivity<FeedManager> {

	SlidingMenu mMenu;

	@ViewMapping(R.id.feed_list)
	ListView mListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mMenu = new SlidingMenu(this);
		mMenu.setMode(SlidingMenu.LEFT);
		mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		mMenu.setShadowWidthRes(R.dimen.shadow_width);
		mMenu.setShadowDrawable(R.drawable.shadow);
		mMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mMenu.setFadeDegree(0.35f);
		mMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

		ViewMapUtil.viewMapping(this, this);
	}

	@Override
	public void handleMessage(Message message) {

	}
}
