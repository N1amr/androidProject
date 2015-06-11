package com.n1amr.android.project;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class OpenedClass extends Activity implements View.OnClickListener,
	OnCheckedChangeListener {
    TextView tvQuestion, tvAnswer;
    Button returnData;
    RadioGroup selectionlist;
    String gotBread, receivedAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.send);
	initialize();

	SharedPreferences mSharedPreferences = PreferenceManager
		.getDefaultSharedPreferences(getBaseContext());
	String et = mSharedPreferences.getString("DesiredQuestion",
		"How are you?");
	String value = mSharedPreferences.getString("ListPref", "4");
	if (value.contentEquals("4"))
	    tvQuestion.setText(et);

	// try {
	// Bundle mBundle = getIntent().getExtras();
	// gotBread = mBundle.getString("key");
	// tvQuestion.setText(gotBread);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
    }

    private void initialize() {
	tvQuestion = (TextView) findViewById(R.id.tvQuestion);
	tvAnswer = (TextView) findViewById(R.id.tvText);

	returnData = (Button) findViewById(R.id.bReturn);
	returnData.setOnClickListener(this);

	selectionlist = (RadioGroup) findViewById(R.id.rgAnswers);
	selectionlist.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
	switch (v.getId()) {
	    case R.id.bReturn:
		Intent person = new Intent();
		Bundle backpack = new Bundle();
		backpack.putString("answer", receivedAnswer);
		person.putExtras(backpack);
		setResult(RESULT_OK, person);
		finish();
		break;
	}
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
	switch (checkedId) {
	    case R.id.rCrazy:
		receivedAnswer = "Probably right!";
		break;
	    case R.id.rSexy:
		receivedAnswer = "Definitly right!";
		break;
	    case R.id.rBoth:
		receivedAnswer = "Spot On!";
		break;
	}
	tvAnswer.setText(receivedAnswer);
    }
}
