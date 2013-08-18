package com.example.newsfeeds;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import com.example.newsfeeds.ui.view.PieControl;

public class MyActivity extends Activity {
	PieControl mPieControl;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPieControl = new PieControl(this);
		View contentView = getLayoutInflater().inflate(R.layout.main, null);
		setContentView(contentView);
		FrameLayout rootView = (FrameLayout) contentView.findViewById(R.id.root);
		mPieControl.attachToContainer(rootView);

	}
}
