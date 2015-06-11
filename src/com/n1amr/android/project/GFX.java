package com.n1amr.android.project;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

public class GFX extends Activity {

    MyGfxView mMyGfxView;
    WakeLock mWakeLock;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

	// wake-lock
	PowerManager mPowerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
	mWakeLock = mPowerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
		"n1amr");

	super.onCreate(savedInstanceState);
	mWakeLock.acquire();
	// Remove Title Bar from app
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	// Fullscreen
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);

	mMyGfxView = new MyGfxView(this);
	setContentView(mMyGfxView);

    }

    @Override
    protected void onPause() {
	super.onPause();
	mWakeLock.release();
    }
}
