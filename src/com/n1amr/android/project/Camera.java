package com.n1amr.android.project;

import java.io.*;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class Camera extends Activity implements View.OnClickListener {
    ImageView iv;
    ImageButton ib;
    Button b;
    Intent intent;
    final static int cameraData = 0;
    Bitmap mBitmap;

    private void initializeVars() {
	iv = (ImageView) findViewById(R.id.ivReturnedPicture);
	ib = (ImageButton) findViewById(R.id.ibTakePic);
	b = (Button) findViewById(R.id.bSetWall);
	b.setOnClickListener(this);
	ib.setOnClickListener(this);
	InputStream mInputStream = getResources().openRawResource(
		R.drawable.ic_launcher);
	mBitmap = BitmapFactory.decodeStream(mInputStream);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.camera);
	initializeVars();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onClick(View v) {
	switch (v.getId()) {
	    case R.id.bSetWall:
		try {
		    getApplicationContext().setWallpaper(mBitmap);
		    Toast.makeText(this, "Wallpaper Changed Successfully",
			    Toast.LENGTH_LONG).show();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		break;
	    case R.id.ibTakePic:
		intent = new Intent(
			android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, cameraData);
		break;

	    default:
		break;
	}
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if (resultCode == RESULT_OK) {
	    Bundle mBundle = data.getExtras();
	    mBitmap = (Bitmap) mBundle.get("data");
	    iv.setImageBitmap(mBitmap);
	}
    }
}