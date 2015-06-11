package com.n1amr.android.project;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements OnClickListener {

    Button bSave, bLoad;
    TextView tvData;
    EditText etData;
    public static String sharedPreferencesFileName = "MySharedStringFile";
    SharedPreferences mSharedPreferences;

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

	mSharedPreferences = getSharedPreferences(sharedPreferencesFileName, 0);
    }

    @Override
    public void onClick(View v) {
	String data;
	switch (v.getId()) {
	    case R.id.bSave:
		data = etData.getText().toString();
		SharedPreferences.Editor mEditor = mSharedPreferences.edit();
		mEditor.putString("sharedString", data);
		mEditor.commit();
		break;
	    case R.id.bLoad:
		data = mSharedPreferences.getString("sharedString", "Error");
		tvData.setText(data);
		break;

	}
    }

}
