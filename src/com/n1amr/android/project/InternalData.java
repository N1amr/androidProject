package com.n1amr.android.project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener {

    Button bSave, bLoad;
    TextView tvData;
    EditText etData;
    public static String internalDataFileName = "InternalData";
    FileOutputStream mFileOutputStream = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.sharedpreferences);

	bSave = (Button) findViewById(R.id.bSave);
	bLoad = (Button) findViewById(R.id.bLoad);
	tvData = (TextView) findViewById(R.id.tvData);
	etData = (EditText) findViewById(R.id.etData);

	bSave.setOnClickListener(this);
	bLoad.setOnClickListener(this);

	try {
	    mFileOutputStream = openFileOutput(internalDataFileName,
		    MODE_PRIVATE);
	    mFileOutputStream.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void onClick(View v) {

	String data;
	switch (v.getId()) {
	    case R.id.bSave:
		data = etData.getText().toString();

		/*
		 * File mFile = new File(internalDataFileName); try {
		 * mFileOutputStream = new FileOutputStream(mFile);
		 * mFileOutputStream.close(); } catch (FileNotFoundException e)
		 * { e.printStackTrace(); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
		try {
		    mFileOutputStream = openFileOutput(internalDataFileName, 0);
		    mFileOutputStream.write(data.getBytes());
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		break;
	    case R.id.bLoad:
		LoadMyFiile mLoadMyFiile = new LoadMyFiile();
		mLoadMyFiile.execute(internalDataFileName);
		break;
	}
    }

    public class LoadMyFiile extends AsyncTask<String, Integer, String> {
	ProgressDialog mProgressDialog;

	@Override
	protected void onPreExecute() {
	    super.onPreExecute();
	    mProgressDialog = new ProgressDialog(InternalData.this);
	    mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	    mProgressDialog.setMax(100);
	    mProgressDialog.show();
	}

	@SuppressWarnings("finally")
	@Override
	protected String doInBackground(String... params) {

	    for (int i = 0; i < 20; i++) {
		// Call onProgressUpdate()
		publishProgress(5);
		try {
		    Thread.sleep(100);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	    // Close Progress Dialog
	    mProgressDialog.dismiss();

	    FileInputStream mFileInputStream = null;
	    String data = "";
	    try {
		mFileInputStream = openFileInput(params[0]);
		byte[] buffer = new byte[mFileInputStream.available()];
		while (mFileInputStream.read(buffer) != -1)
		    data = new String(buffer);
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    } finally {
		try {
		    mFileInputStream.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return data;
	    }
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
	    super.onProgressUpdate(values);
	    mProgressDialog.incrementProgressBy(values[0]);
	}

	@Override
	protected void onPostExecute(String result) {
	    super.onPostExecute(result);
	    tvData.setText(result);
	}
    }

}
