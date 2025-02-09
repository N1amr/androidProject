package com.n1amr.android.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;

public class Flipper extends Activity implements OnClickListener {
    ViewFlipper mViewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.flipper);

	mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper1);
	mViewFlipper.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
	mViewFlipper.showNext();
    }
}
