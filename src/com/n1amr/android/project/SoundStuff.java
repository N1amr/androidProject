package com.n1amr.android.project;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;

public class SoundStuff extends Activity implements View.OnClickListener,
	OnLongClickListener {
    SoundPool mSoundPool;
    int explosion = 0; // SoundID to play on SoundPool

    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	View v = new View(this);
	v.setOnClickListener(this);
	v.setOnLongClickListener(this);
	setContentView(v);

	mSoundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
	explosion = mSoundPool.load(this, R.raw.splashsound, 1);

	mMediaPlayer = MediaPlayer.create(this, R.raw.splashsound);
    }

    @Override
    public void onClick(View v) {
	if (explosion != 0)
	    mSoundPool.play(explosion, 1, 1, 0, 0, 1);
    }

    @Override
    public boolean onLongClick(View v) {
	mMediaPlayer.start();
	return false;
    }

}
