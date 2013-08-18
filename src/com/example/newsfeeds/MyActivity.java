package com.example.newsfeeds;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import com.example.newsfeeds.ui.view.PieControl;
import com.example.newsfeeds.utils.L;
import com.example.newsfeeds.utils.LogInfo;

@LogInfo(tag = "my", info = "activity")
public class MyActivity extends Activity {
	PieControl mPieControl;
	@Override
	@LogInfo(showTrace = true)
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPieControl = new PieControl(this);
		View contentView = getLayoutInflater().inflate(R.layout.main, null);
		setContentView(contentView);
		FrameLayout rootView = (FrameLayout) contentView.findViewById(R.id.root);
		mPieControl.attachToContainer(rootView);
		L.exception(new Exception(), "test test");
	}
}
