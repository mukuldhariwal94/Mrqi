package com.example.marqi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.os.Vibrator;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

public class MarqueeNewView extends SurfaceView implements Runnable {

	SurfaceHolder ourHolder;
	Thread ourThread = null;
	public static boolean isRunning = false;
	float changingX;
	float width, height;
	boolean flag1 = true, flag2 = true;
	Paint textPaint;
	Vibrator v;
	float speed;
	int appleColor ;
	public MarqueeNewView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();
		textPaint = new Paint();
		changingX = height;
		appleColor = context.getResources().getColor(com.example.marqi.R.color.apple);
		v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		if (MainActivity.textEntered.length() != 0) {
			ourHolder = getHolder();
			ourThread = new Thread(this);
			ourThread.start();

		} else {
			setBackgroundColor(Color.WHITE);
			Toast.makeText(context, "Please enter text , don't leave it blank",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		super.onTouchEvent(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (flag1 == false && flag2 == false) {
				flag1 = true;
				flag2 = true;
			} else {
				flag1 = false;
				flag2 = false;
			}
			break;
		}
		return true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (MainActivity.textEntered.length() != 0)
			isRunning = true;

		while (isRunning) {
			if (!ourHolder.getSurface().isValid())
				continue;
			Canvas canvas = ourHolder.lockCanvas();
			if (flag1 || flag2) {
				canvas.drawRGB(241, 242, 242);;
				textPaint.setColor(Color.BLACK);
			} else {
				canvas.drawColor(Color.BLACK);
				textPaint.setColor(appleColor);
			}
			textPaint.setTextAlign(Align.LEFT);
			textPaint.setTextSize((float) ((float) width /2.25));
			speed = height / 50;
			float x = textPaint.measureText(MainActivity.textEntered);
			float canvasWidth = canvas.getWidth();
			canvas.drawText(MainActivity.textEntered, changingX,
					canvasWidth / 2, textPaint);
			if (changingX <= (-1 * x - 250)) {
				changingX = width;
				v.vibrate(300);
			} else {
				changingX -= speed;

			}
			ourHolder.unlockCanvasAndPost(canvas);
		}

	}

}
