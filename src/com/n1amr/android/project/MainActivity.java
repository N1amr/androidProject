package com.n1amr.android.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    public int x;
    Button add, sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
	x = 0;
	add = (Button) findViewById(R.id.bAdd2);
	sub = (Button) findViewById(R.id.bSub2);
	add.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		x = x + 1;
		Update();
	    }

	});
	sub.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		x = x - 1;
		Update();
	    }

	});
	Update();
    }

    public void Update() {
	((TextView) findViewById(R.id.tvCounter)).setText(Integer.toString(x));
    }
}
