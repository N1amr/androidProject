package com.n1amr.android.project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

public class MyGfxView extends View {

    Bitmap mBitmap;
    double Y, X;
    int direction = 1;

    Typeface mTypeface;

    public MyGfxView(Context context) {
	super(context);
	mBitmap = BitmapFactory.decodeResource(getResources(),
		R.drawable.ic_launcher);
	Y = 0;

	// Font
	mTypeface = Typeface.createFromAsset(context.getAssets(), "G-Unit.ttf");
    }

    Rect mRect = new Rect();
    Paint mPaint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
	super.onDraw(canvas);
	canvas.drawColor(Color.WHITE);

	mPaint.setARGB(50, 255, 10, 50);
	mPaint.setTextAlign(Align.CENTER);
	mPaint.setTextSize(50);
	mPaint.setTypeface(mTypeface);
	canvas.drawText("Canvas Text", canvas.getWidth() / 2, 200, mPaint);

	canvas.drawBitmap(mBitmap, (float) X, (float) Y, null);

	// Moving the Droid
	Y += 10 * direction;
	if (Y < 0) {
	    Y = 0;
	    direction = 1;
	} else if (Y > canvas.getHeight() - mBitmap.getHeight()) {
	    Y = canvas.getHeight() - mBitmap.getHeight();
	    direction = -1;
	}
	X = direction
		* (Math.sin(2 * 3.14 * (Y - canvas.getHeight() / 2)
			/ canvas.getHeight())
			* canvas.getWidth() / 2) + canvas.getWidth() / 2;

	// Draw Rectangle
	mRect.set(0, 400, canvas.getWidth(), 500);
	mPaint.setColor(Color.BLUE);
	canvas.drawRect(mRect, mPaint);

	// Repeat
	invalidate();
    }

}
