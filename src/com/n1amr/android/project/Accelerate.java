package com.n1amr.android.project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Accelerate extends Activity implements SensorEventListener {

    MyGfxSurfaceView mMyGfxSurfaceView;
    float x, y, sensorX, sensorY;
    Bitmap mBitmap;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	mMyGfxSurfaceView = new MyGfxSurfaceView(this);

	mBitmap = BitmapFactory.decodeResource(getResources(),
		R.drawable.ic_launcher);
	x = y = sensorX = sensorY = 0;

	setContentView(mMyGfxSurfaceView);

	// Setup Sensor
	SensorManager mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	if (mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
	    Sensor mSensor = mSensorManager.getSensorList(
		    Sensor.TYPE_ACCELEROMETER).get(0);
	    mSensorManager.registerListener(this, mSensor,
		    SensorManager.SENSOR_DELAY_FASTEST);
	}
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
		mCanvas.drawRGB(0, 0, 0);

		float centerX = mCanvas.getWidth() / 2;
		float centerY = mCanvas.getHeight() / 2;

		mCanvas.drawBitmap(mBitmap,
			centerX - sensorX * mCanvas.getWidth() / 10, centerY
				+ sensorY * mCanvas.getHeight() / 10, null);
		mSurfaceHolder.unlockCanvasAndPost(mCanvas);
	    }
	}
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
	sensorX = event.values[0];
	sensorY = event.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
	// TODO Auto-generated method stub

    }
}