package com.example.marqi;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.style.BulletSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

public class Starter extends Activity  {

	static int counter = 0;
	ImageView ivCheckbox, ivTextView, ivRotate;
	Animation fade_out_textview,fade_in_textview,fade_out_checkbox,fade_in_checkbox,fade_in_rotate,fade_out_rotate;
	AnimationSet animationCheckBox, animationTextView,animationRotate;
	static boolean flag = false;
	Intent openMain;
	AlertDialog.Builder builder1; 
	TextView tv ; 
	AlertDialog alert11 ;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.onstart);
		set_up();

	}

	public void set_up() {
		ivCheckbox = (ImageView) findViewById(R.id.ivCheckBox);
		ivTextView = (ImageView) findViewById(R.id.ivTextView);
		ivRotate = (ImageView)findViewById(R.id.ivRotate);
		tv = new TextView(this);
		tv.setText("Repeat the tutorial ?");
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(20);
		openMain = new Intent(this,MainActivity.class);
		fade_in_checkbox = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
		fade_out_checkbox = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
		fade_in_textview = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
		fade_out_textview= AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
		fade_in_rotate = AnimationUtils.loadAnimation(this, android.R.anim.fade_in); 
		fade_out_rotate = AnimationUtils.loadAnimation(this, android.R.anim.fade_out); 
		animationCheckBox = new AnimationSet(false);
		animationTextView = new AnimationSet(false);
		animationRotate = new AnimationSet(false);
		animationCheckBox.addAnimation(fade_in_checkbox);
		animationCheckBox.addAnimation(fade_out_checkbox);
		animationCheckBox.setDuration(2500);
		animationTextView.addAnimation(fade_in_textview);
		animationTextView.addAnimation(fade_out_textview);
		animationTextView.setDuration(2500);
		animationRotate.addAnimation(fade_in_rotate);
		animationRotate.addAnimation(fade_out_rotate);
		animationRotate.setDuration(2500);
		 builder1 = new AlertDialog.Builder(this);
		 builder1.setView(tv);
         builder1.setCancelable(true);
         builder1.setPositiveButton("Yes, it's still unclear",
                 new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int id) {
                 dialog.dismiss();
                 alert11.dismiss();
                 begin_animation();
             }
         });
         builder1.setNegativeButton("No,Thanks I got it",
                 new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int id) {
                 dialog.dismiss();
                 finish();
             }
         });
		

		animationCheckBox.getAnimations().get(1).setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				ivCheckbox.setVisibility(View.GONE);
				ivTextView.startAnimation(animationTextView);
			}
		});
		animationTextView.getAnimations().get(1).setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				ivRotate.startAnimation(animationRotate);
			}
		});
		animationRotate.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
	            	if(!flag){
	            		alert11= builder1.create();
	            		flag = true ;
	            	}
				    
		            alert11.show();
			}
		});
		
		begin_animation();
	}
	public void begin_animation() {
		ivCheckbox.startAnimation(animationCheckBox);
	}
}
	
