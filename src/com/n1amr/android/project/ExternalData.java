package com.n1amr.android.project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalData extends Activity implements OnItemSelectedListener,
	OnClickListener {
    TextView tvCanRead, tvCanWrite;
    Button bConfirm, bSaveFile;
    EditText etSaveAs;
    Spinner mSpinner;
    boolean canRead, canWrite;
    String[] paths = { "Music", "Pictures", "Download" };
    File mFile = null, mFile_path = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.external_data);
	tvCanRead = (TextView) findViewById(R.id.tvCanRead);
	tvCanWrite = (TextView) findViewById(R.id.tvCanWrite);

	checkState();

	mSpinner = (Spinner) findViewById(R.id.spinner1);
	ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_spinner_item, paths);
	mSpinner.setAdapter(mArrayAdapter);
	mSpinner.setOnItemSelectedListener(this);

	bConfirm = (Button) findViewById(R.id.bConfirm);
	bSaveFile = (Button) findViewById(R.id.bSaveFile);
	bConfirm.setOnClickListener(this);
	bSaveFile.setOnClickListener(this);
	bSaveFile.setEnabled(false);

	etSaveAs = (EditText) findViewById(R.id.etSaveAs);
    }

    private void checkState() {
	String state = Environment.getExternalStorageState();
	if (state.equals(Environment.MEDIA_MOUNTED))
	    canRead = canWrite = true;
	else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
	    canRead = true;
	    canWrite = false;
	} else
	    canRead = canWrite = false;
	tvCanRead.setText(Boolean.toString(canRead));
	tvCanWrite.setText(Boolean.toString(canWrite));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
	    long id) {
	switch (position) {
	    case 0:
		mFile_path = Environment
			.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
		break;
	    case 1:
		mFile_path = Environment
			.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		break;
	    case 2:
		mFile_path = Environment
			.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
		break;

	}
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
	switch (v.getId()) {
	    case R.id.bConfirm:
		bSaveFile.setEnabled(true);
		break;
	    case R.id.bSaveFile:
		String f = etSaveAs.getText().toString();
		mFile = new File(mFile_path, f + ".jpg");
		checkState();
		if (canWrite == canRead == true) {
		    mFile_path.mkdirs();
		    try {
			InputStream mInputStream = getResources()
				.openRawResource(R.drawable.splash);
			OutputStream mOutputStream = new FileOutputStream(mFile);
			byte[] data = new byte[mInputStream.available()];

			mInputStream.read(data);
			mOutputStream.write(data);

			mInputStream.close();
			mOutputStream.close();

			Toast.makeText(this, "Success! ", Toast.LENGTH_SHORT)
				.show();

			MediaScannerConnection
				.scanFile(
					this,
					new String[] { mFile.toString() },
					null,
					new MediaScannerConnection.OnScanCompletedListener() {

					    @SuppressLint("ShowToast")
					    @Override
					    public void onScanCompleted(
						    String path, Uri uri) {
						Toast.makeText(
							ExternalData.this,
							"Scan Completed ",
							Toast.LENGTH_LONG)
							.show();

					    }
					});
		    } catch (IOException e) {
		    }
		}
		break;
	}
    }
}
