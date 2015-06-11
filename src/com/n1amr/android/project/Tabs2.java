package com.n1amr.android.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs2 extends Activity implements OnClickListener {
    Button bAddTab, bStart, bStop;
    TabHost mTabHost;
    TextView tvShowResults;
    long start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.tabs2);

	mTabHost = (TabHost) findViewById(R.id.tabhost);
	mTabHost.setup();

	// Setup 1st Tab
	TabSpec mTabSpec = mTabHost.newTabSpec("tag1");
	mTabSpec.setContent(R.id.tab1);
	mTabSpec.setIndicator("Stopwatch"); // Label of the tab
	mTabHost.addTab(mTabSpec);

	// Setup 2nd & 3rd Tab
	mTabHost.addTab(mTabHost.newTabSpec("tag2").setContent(R.id.tab2)
		.setIndicator("TAB 2"));
	mTabHost.addTab(mTabHost.newTabSpec("tag3").setContent(R.id.tab3)
		.setIndicator("Add a tab"));

	bAddTab = (Button) findViewById(R.id.bAddTab);
	bStart = (Button) findViewById(R.id.bStartWatck);
	bStop = (Button) findViewById(R.id.bStopWatck);
	tvShowResults = (TextView) findViewById(R.id.tvShowResults);
	bAddTab.setOnClickListener(this);
	bStart.setOnClickListener(this);
	bStop.setOnClickListener(this);

	start = 0;
    }

    @Override
    public void onClick(View v) {
	switch (v.getId()) {
	    case R.id.bAddTab:
		TabSpec mTabSpec = mTabHost.newTabSpec("tag");
		mTabSpec.setContent(new TabHost.TabContentFactory() {

		    @Override
		    public View createTabContent(String tag) {
			LinearLayout mLinearLayout = new LinearLayout(
				Tabs2.this);
			final TextView mTextView = new TextView(Tabs2.this);
			Button bClose = new Button(Tabs2.this);

			mLinearLayout.setOrientation(LinearLayout.VERTICAL);
			mTextView.setText("You've created a new tab");
			bClose.setText("Close");
			bClose.setOnClickListener(new View.OnClickListener() {

			    @Override
			    public void onClick(View v) {
				mTextView.setText("You will close");
			    }
			});
			mLinearLayout.addView(mTextView);
			mLinearLayout.addView(bClose);
			return mLinearLayout;
		    }
		});
		mTabSpec.setIndicator("new tab");
		mTabHost.addTab(mTabSpec);
		break;
	    case R.id.bStartWatck:
		start = System.currentTimeMillis();

		break;
	    case R.id.bStopWatck:
		stop = System.currentTimeMillis();
		long time = stop - start;
		int millis,
		secs,
		mins,
		hrs;
		millis = secs = mins = hrs = 0;
		millis = (int) (time % 1000);
		time /= 1000;
		secs = (int) (time % 60);
		time /= 60;
		mins = (int) (time % 60);
		time /= 60;
		hrs = (int) time;

		if (start != 0) {
		    tvShowResults.setText(String.format("%d:%02d:%02d:%03d",
			    hrs, mins, secs, millis));
		    start = stop = 0;
		} else
		    tvShowResults.setText("0");
		break;
	}
    }
}
