package com.n1amr.android.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data extends Activity implements View.OnClickListener {
    Button start, startFor;
    EditText sendET;
    TextView getAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.get);

	initialize();
    }

    private void initialize() {
	start = (Button) findViewById(R.id.bSA);
	startFor = (Button) findViewById(R.id.bSAFR);
	sendET = (EditText) findViewById(R.id.etSend);
	getAnswer = (TextView) findViewById(R.id.tvGot);

	start.setOnClickListener(this);
	startFor.setOnClickListener(this);
    }

    Bundle mBundle;
    Intent intent;

    @Override
    public void onClick(View v) {
	switch (v.getId()) {
	    case R.id.bSA:
		// Send ONLY
		String bread = sendET.getText().toString();
		mBundle = new Bundle();
		mBundle.putString("key", bread);
		intent = new Intent(this, OpenedClass.class);
		intent.putExtras(mBundle);
		startActivity(intent);
		break;
	    case R.id.bSAFR:
		// Send and Receive

		mBundle = new Bundle();
		mBundle.putString("key", sendET.getText().toString());

		intent = new Intent(this, OpenedClass.class);
		intent.putExtras(mBundle);
		startActivityForResult(intent, 0);
		break;
	}
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if (resultCode == RESULT_OK) {
	    mBundle = data.getExtras();
	    String s = (String) mBundle.get("answer");
	    getAnswer.setText(s);
	}
    }
}
