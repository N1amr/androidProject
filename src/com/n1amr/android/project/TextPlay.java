package com.n1amr.android.project;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity implements View.OnClickListener {

    Button bEnter;
    ToggleButton tbShowPass;
    EditText etInput;
    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.text);
	initialize();

	tbShowPass.setOnClickListener(this);
	bEnter.setOnClickListener(this);
    }

    private void initialize() {
	bEnter = (Button) findViewById(R.id.bResults);
	tbShowPass = (ToggleButton) findViewById(R.id.tbPassword);
	etInput = (EditText) findViewById(R.id.etCommands);
	tvDisplay = (TextView) findViewById(R.id.tvResults);

    }

    @Override
    protected void onPause() {
	super.onPause();
    }

    @Override
    public void onClick(View v) {
	switch (v.getId()) {
	    case R.id.bResults:
		String check = etInput.getText().toString();
		tvDisplay.setText(check);
		if (check.contentEquals("Left "))
		    tvDisplay.setGravity(Gravity.LEFT);
		else if (check.contentEquals("Center "))
		    tvDisplay.setGravity(Gravity.CENTER_HORIZONTAL);
		else if (check.contentEquals("Right "))
		    tvDisplay.setGravity(Gravity.RIGHT);
		else if (check.contentEquals("Blue "))
		    tvDisplay.setTextColor(Color.BLUE);
		else if (check.contentEquals("Red "))
		    tvDisplay.setTextColor(Color.RED);
		else if (check.toLowerCase().contentEquals("black "))
		    tvDisplay.setTextColor(Color.BLACK);
		else if (check.contentEquals("WTF ")) {
		    Random mRandom = new Random();
		    tvDisplay.setText("WTF!!!!");
		    tvDisplay.setTextSize(mRandom.nextInt(75));
		    tvDisplay.setTextColor(Color.rgb(mRandom.nextInt(255),
			    mRandom.nextInt(255), mRandom.nextInt(255)));
		    switch (mRandom.nextInt(3)) {
			case 0:
			    tvDisplay.setGravity(Gravity.LEFT);
			    break;
			case 1:
			    tvDisplay.setGravity(Gravity.CENTER);
			    break;
			case 2:
			    tvDisplay.setGravity(Gravity.RIGHT);
			    break;

			default:
			    break;
		    }
		} else {
		    tvDisplay.setText("Invalid");
		    tvDisplay.setGravity(Gravity.CENTER);
		    tvDisplay.setTextColor(Color.BLACK);
		}
		break;
	    case R.id.tbPassword:

		if (tbShowPass.isChecked())
		    etInput.setInputType(InputType.TYPE_CLASS_TEXT
			    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		else
		    etInput.setInputType(InputType.TYPE_CLASS_TEXT);
		break;

	    default:
		break;
	}
    }
}
