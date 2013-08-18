/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.newsfeeds.ui.view;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.example.newsfeeds.R;
import com.example.newsfeeds.utils.L;
import com.example.newsfeeds.utils.LogInfo;

/**
 * PieMenu的菜单项如下
 * 1. 设置
 *    >夜间模式
 *    >设置
 * 2. 返回
 *    >刷新
 *    >返回
 * 3. 发布
 *    >发状态
 *    >发图片
 * 4. 收藏
 *    >收藏到本地
 *    >查看收藏列表
 * Controller for Quick Controls pie menu
 */
@LogInfo(info = "pie")
public class PieControl implements PieMenu.PieController, OnClickListener {

    protected Activity mActivity;
    protected PieMenu mPie;
    protected int mItemSize;
    protected TextView mTabsCount;
    private PieItem mBack;
    private PieItem mForward;
    private PieItem mRefresh;
    private PieItem mUrl;
    private PieItem mOptions;
    private PieItem mBookmarks;
    private PieItem mHistory;
    private PieItem mAddBookmark;
    private PieItem mNewTab;
    private PieItem mIncognito;
    private PieItem mClose;
    private PieItem mShowTabs;
    private PieItem mInfo;
    private PieItem mFind;
    private PieItem mShare;
    private PieItem mRDS;

	public PieControl(Activity activity) {
        mActivity = activity;
        mItemSize = (int) activity.getResources().getDimension(R.dimen.qc_item_size);
    }

    public void attachToContainer(FrameLayout container) {
        if (mPie == null) {
            mPie = new PieMenu(mActivity);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);
            mPie.setLayoutParams(lp);
            populateMenu();
            mPie.setController(this);
        }
        container.addView(mPie);
    }

    public void removeFromContainer(FrameLayout container) {
        container.removeView(mPie);
    }

    public void forceToTop(FrameLayout container) {
        if (mPie.getParent() != null) {
            container.removeView(mPie);
            container.addView(mPie);
        }
    }

    protected void setClickListener(OnClickListener listener, PieItem... items) {
        for (PieItem item : items) {
            item.getView().setOnClickListener(listener);
        }
    }

    @Override
    public boolean onOpen() {
        return true;
    }

	protected void populateMenu() {
        mBack = makeItem(R.drawable.ic_back_holo_dark, 1);
        mUrl = makeItem(R.drawable.ic_web_holo_dark, 1);
        mBookmarks = makeItem(R.drawable.ic_bookmarks_holo_dark, 1);
        mHistory = makeItem(R.drawable.ic_history_holo_dark, 1);
        mAddBookmark = makeItem(R.drawable.ic_bookmark_on_holo_dark, 1);
        mRefresh = makeItem(R.drawable.ic_refresh_holo_dark, 1);
        mForward = makeItem(R.drawable.ic_forward_holo_dark, 1);
        mNewTab = makeItem(R.drawable.ic_new_window_holo_dark, 1);
        mIncognito = makeItem(R.drawable.ic_new_incognito_holo_dark, 1);
        mClose = makeItem(R.drawable.ic_close_window_holo_dark, 1);
        mInfo = makeItem(android.R.drawable.ic_menu_info_details, 1);
        mFind = makeItem(R.drawable.ic_search_holo_dark, 1);
        mShare = makeItem(R.drawable.ic_share_holo_dark, 1);
        View tabs = makeTabsView();
        mShowTabs = new PieItem(tabs, 1);
        mOptions = makeItem(R.drawable.ic_settings_holo_dark, 1);
        mRDS = makeItem(R.drawable.ic_desktop_holo_dark, 1);
        setClickListener(this, mBack, mRefresh, mForward, mUrl, mFind, mInfo,
                mShare, mBookmarks, mNewTab, mIncognito, mClose, mHistory,
                mAddBookmark, mOptions, mRDS);
        // level 1
        mPie.addItem(mOptions);
        mOptions.addItem(mRDS);
        mOptions.addItem(makeFiller());
        mOptions.addItem(makeFiller());
        mOptions.addItem(makeFiller());
        mPie.addItem(mBack);
        mBack.addItem(mRefresh);
        mBack.addItem(mForward);
        mBack.addItem(makeFiller());
        mBack.addItem(makeFiller());
        mPie.addItem(mUrl);
        mUrl.addItem(mFind);
        mUrl.addItem(mShare);
        mUrl.addItem(makeFiller());
        mUrl.addItem(makeFiller());
//        mPie.addItem(mShowTabs);
//        mShowTabs.addItem(mClose);
//        mShowTabs.addItem(mIncognito);
//        mShowTabs.addItem(mNewTab);
//        mShowTabs.addItem(makeFiller());
//        mPie.addItem(mBookmarks);
//        mBookmarks.addItem(makeFiller());
//        mBookmarks.addItem(makeFiller());
//        mBookmarks.addItem(mAddBookmark);
//        mBookmarks.addItem(mHistory);
    }

	@LogInfo(showTrace = true)
	public void onClick() {

	}

    @Override
    @LogInfo(showTrace = false)
    public void onClick(View v) {
	    L.v("onClick");
	    if (mBack.getView() == v) {
	        Log.v("pie", "back");
        } else if (mForward.getView() == v) {
	        Log.v("pie", "forward");
        } else if (mRefresh.getView() == v) {
	        Log.v("pie", "refresh");
        } else if (mUrl.getView() == v) {
	        Log.v("pie", "url");
        } else if (mBookmarks.getView() == v) {
	        Log.v("pie", "boolmark");
        } else if (mHistory.getView() == v) {
	        Log.v("pie", "history");
        } else if (mAddBookmark.getView() == v) {
	        Log.v("pie", "add bookmark");
        } else if (mNewTab.getView() == v) {
	        Log.v("pie", "new tab");
        } else if (mIncognito.getView() == v) {
	        Log.v("pie", "incognito");
        } else if (mClose.getView() == v) {
	        Log.v("pie", "close");
        } else if (mOptions.getView() == v) {
	        Log.v("pie", "options");
        } else if (mShare.getView() == v) {
	        Log.v("pie", "share");
        } else if (mInfo.getView() == v) {
	        Log.v("pie", "info");
        } else if (mFind.getView() == v) {
	        Log.v("pie", "find");
        } else if (mRDS.getView() == v) {
	        Log.v("pie", "RDS");
        } else if (mShowTabs.getView() == v) {
	        Log.v("pie", "showtabs");
        }
    }

    protected PieItem makeItem(int image, int l) {
        final ImageView view = new ImageView(mActivity){
	        @Override
	        public boolean dispatchTouchEvent(MotionEvent event) {
			    final int action = event.getActionMasked();
			    switch (action) {
				    case MotionEvent.ACTION_UP:
					    performClick();
					    break;
			    }
			    return super.dispatchTouchEvent(event);
		    }
        };
        view.setImageResource(image);
        view.setMinimumWidth(mItemSize);
        view.setMinimumHeight(mItemSize);
        view.setScaleType(ScaleType.CENTER);
        LayoutParams lp = new LayoutParams(mItemSize, mItemSize);
        view.setLayoutParams(lp);
        return new PieItem(view, l);
    }

    protected PieItem makeFiller() {
        return new PieItem(null, 1);
    }

    protected View makeTabsView() {
        View v = mActivity.getLayoutInflater().inflate(R.layout.qc_tabs_view, null);
        mTabsCount = (TextView) v.findViewById(R.id.label);
        mTabsCount.setText("1");
        ImageView image = (ImageView) v.findViewById(R.id.icon);
        image.setImageResource(R.drawable.ic_windows_holo_dark);
        image.setScaleType(ScaleType.CENTER);
        LayoutParams lp = new LayoutParams(mItemSize, mItemSize);
        v.setLayoutParams(lp);
        return v;
    }
}
