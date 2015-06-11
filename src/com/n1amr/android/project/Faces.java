package com.n1amr.android.project;

import java.io.OutputStream;

import android.app.Activity;
import android.content.ContentValues;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Faces extends Activity implements OnClickListener {
    Faces me = this;
    SurfaceView surfaceView;

    // Preview mPreview;
    Camera camera;
    Button btnCapture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.faces);
	btnCapture = (Button) findViewById(R.id.btnCapture);
	btnCapture.setOnClickListener(this);
	surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
	openCamera();
    }

    @Override
    protected void onResume() {
	openCamera();
	super.onResume();
    }

    void openCamera() {
	new Thread(new Runnable() {
	    @Override
	    public void run() {

		try {
		    if (camera == null) {
			camera = Camera.open();
			Camera.Parameters parameters = camera.getParameters();
			parameters.setPictureFormat(PixelFormat.JPEG);
			camera.setParameters(parameters);
			camera.setDisplayOrientation(90);
		    }
		    camera.setPreviewDisplay(surfaceView.getHolder());
		    camera.startPreview();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}).start();
    }

    @Override
    protected void onPause() {
	if (camera != null)
	    camera.release();
	camera = null;
	super.onPause();
    }

    @Override
    public void onClick(View v) {
	try {
	    camera.takePicture(null, null, pictureCallback);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
	    Uri uriTarget = getContentResolver().insert(
		    Media.EXTERNAL_CONTENT_URI, new ContentValues());
	    OutputStream imageFileOS;
	    try {
		imageFileOS = getContentResolver().openOutputStream(uriTarget);
		imageFileOS.write(data);
		imageFileOS.flush();
		imageFileOS.close();

		Toast.makeText(me, "Image saved: " + uriTarget.toString(),
			Toast.LENGTH_LONG).show();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    };
}
