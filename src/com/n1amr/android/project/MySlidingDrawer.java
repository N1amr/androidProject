package com.n1amr.android.project;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

@SuppressWarnings("deprecation")
public class MySlidingDrawer extends Activity implements OnClickListener,
	OnCheckedChangeListener, OnDrawerOpenListener, OnDrawerCloseListener {
    Button bHandle, bHandle1, bHandle2, bHandle3, bHandle4;
    CheckBox cbCheck;
    SlidingDrawer mSlidingDrawer;

    // MySlidingDrawer mSlidingDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.sliding_drawer);
	initialize();

    }

    private void initialize() {
	bHandle = (Button) findViewById(R.id.bHandle);
	bHandle1 = (Button) findViewById(R.id.bHandle1);
	bHandle2 = (Button) findViewById(R.id.bHandle2);
	bHandle3 = (Button) findViewById(R.id.bHandle3);
	bHandle4 = (Button) findViewById(R.id.bHandle4);
	cbCheck = (CheckBox) findViewById(R.id.cbCheck);
	mSlidingDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);

	bHandle.setOnClickListener(this);
	bHandle1.setOnClickListener(this);
	bHandle2.setOnClickListener(this);
	bHandle3.setOnClickListener(this);
	bHandle4.setOnClickListener(this);
	cbCheck.setOnCheckedChangeListener(this);
	mSlidingDrawer.setOnDrawerOpenListener(this);
	mSlidingDrawer.setOnDrawerCloseListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	if (buttonView.isChecked())
	    mSlidingDrawer.lock();
	else
	    mSlidingDrawer.unlock();
    }

    @Override
    public void onClick(View v) {
	switch (v.getId()) {
	    case R.id.bHandle:

		break;
	    case R.id.bHandle1:
		mSlidingDrawer.open();
		break;
	    case R.id.bHandle2:
		break;
	    case R.id.bHandle3:
		mSlidingDrawer.toggle();
		break;
	    case R.id.bHandle4:
		mSlidingDrawer.close();
		break;
	}
    }

    MediaPlayer mMediaPlayer;

    @Override
    public void onDrawerOpened() {
	mMediaPlayer = MediaPlayer.create(this, R.raw.splashsound);
	mMediaPlayer.start();
    }

    @Override
    public void onDrawerClosed() {
	mMediaPlayer.release();
    }
}
