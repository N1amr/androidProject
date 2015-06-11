package com.n1amr.android.project;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SQLView extends Activity {
    TextView tvSQLInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_sqlview);

	tvSQLInfo = (TextView) findViewById(R.id.tvSQLInfo);
	HotOrNot mHotOrNot = new HotOrNot(this);
	mHotOrNot.open();
	String data = mHotOrNot.getData();
	mHotOrNot.close();
	tvSQLInfo.setText(data);

    }

}
