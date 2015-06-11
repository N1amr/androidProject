package com.n1amr.android.project;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity {
    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.splash);

	// to get value from references file
	SharedPreferences mSharedPreferences = PreferenceManager
		.getDefaultSharedPreferences(getBaseContext());
	boolean isMusicActive = mSharedPreferences.getBoolean("MusicON", true);

	mMediaPlayer = MediaPlayer.create(this, R.raw.splashsound);

	if (isMusicActive)
	    mMediaPlayer.start();

	// Setting Timer
	final int musicTime = 500;
	Thread mThread = new Thread() {
	    @Override
	    public void run() {
		try {
		    // waits for x milliseconds
		    sleep(musicTime);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		} finally {
		    // starts the app activity with action name
		    // "com.n1amr.android.project.START"
		    Intent intent = new Intent(
			    "com.n1amr.android.project.MenuManager");
		    startActivity(intent);
		}
	    }
	};
	mThread.start();
    }

    // Kill the splash activity when the app activity starts
    @Override
    protected void onPause() {
	super.onPause();
	mMediaPlayer.release();

	finish();
    }
}
