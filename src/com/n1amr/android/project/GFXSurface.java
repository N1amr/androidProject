package com.n1amr.android.project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener {

    MyGfxSurfaceView mMyGfxSurfaceView;
    float x, y, startX, startY, endX, endY, dx, dy, anx, any, scalex, scaley;
    Bitmap mBitmap;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	mMyGfxSurfaceView = new MyGfxSurfaceView(this);
	mMyGfxSurfaceView.setOnTouchListener(this);
	mBitmap = BitmapFactory.decodeResource(getResources(),
		R.drawable.ic_launcher);
	x = y = startX = startY = endX = endY = dx = dy = anx = any = scalex = scaley = 0;
	setContentView(mMyGfxSurfaceView);
    }

    @Override
    protected void onPause() {
	super.onPause();
	mMyGfxSurfaceView.pause();
    }

    @Override
    protected void onResume() {
	super.onResume();
	mMyGfxSurfaceView.resume();

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {

	try {
	    // 36frame per second
	    // 1000millisecons/36=16.67
	    Thread.sleep(16);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	x = event.getX();
	y = event.getY();
	switch (event.getAction()) {
	    case MotionEvent.ACTION_DOWN:
		startX = event.getX();
		startY = event.getY();

		endX = endY = dx = dy = anx = any = scalex = scaley = 0;

		break;
	    case MotionEvent.ACTION_UP:
		endX = event.getX();
		endY = event.getY();

		dx = endX - startX;
		dy = endY - startY;

		scalex = dx / 30;
		scaley = dy / 30;

		break;
	}
	// true to loop, false = no loop
	return true;
    }

    public class MyGfxSurfaceView extends SurfaceView implements Runnable {
	SurfaceHolder mSurfaceHolder;
	Thread mThread = null;
	boolean isRunning = false;

	public MyGfxSurfaceView(Context context) {
	    super(context);
	    mSurfaceHolder = getHolder();

	}

	public void pause() {
	    isRunning = false;
	    // while (true) {
	    try {
		mThread.join();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    // break;
	    // }
	    mThread = null;
	}

	public void resume() {
	    mThread = new Thread(this);
	    mThread.start();
	    isRunning = true;
	}

	@Override
	public void run() {
	    while (isRunning) {
		if (!mSurfaceHolder.getSurface().isValid())
		    continue;

		Canvas mCanvas = mSurfaceHolder.lockCanvas();

		// Background
		mCanvas.drawRGB(2, 2, 150);

		// Draw Bitmap at mouse location
		if (x != 0 && y != 0)
		    mCanvas.drawBitmap(mBitmap, x - mBitmap.getWidth() / 2, y
			    - mBitmap.getHeight() / 2, null);
		if (startX != 0 && startY != 0)
		    mCanvas.drawBitmap(BitmapFactory.decodeResource(
			    getResources(), R.drawable.ic_launcher), startX
			    - mBitmap.getWidth() / 2,
			    startY - mBitmap.getHeight() / 2, null);
		if (endX != 0 && endY != 0)
		    mCanvas.drawBitmap(BitmapFactory.decodeResource(
			    getResources(), R.drawable.ic_launcher), endX
			    - mBitmap.getWidth() / 2 - anx,
			    endY - mBitmap.getHeight() / 2 - any, null);

		anx += scalex;
		any += scaley;

		mSurfaceHolder.unlockCanvasAndPost(mCanvas);
	    }
	}
    }
}