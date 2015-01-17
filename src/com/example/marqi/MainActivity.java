package com.example.marqi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	EditText e1;
	public static String textEntered = "  ";
	public static int counter = 0;
	Button bEnter;
	CheckBox autoRotateCheckbox;
	MarqueeNewView mainView;
	boolean isBackButtonPressed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			setContentView(R.layout.layout_land);
		} else {

			setContentView(R.layout.activity_main);
			e1 = (EditText) findViewById(R.id.etEnterText);
			autoRotateCheckbox = (CheckBox) findViewById(R.id.checkBox1);
			autoRotateCheckbox.setOnClickListener(this);
			if (android.provider.Settings.System.getInt(getContentResolver(),
					Settings.System.ACCELEROMETER_ROTATION, 0) == 1) {
				autoRotateCheckbox.setChecked(true);
			}
		}

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		// Checks the orientation of the screen
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			textEntered = e1.getText().toString().toUpperCase();

			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(e1.getWindowToken(), 0);
			e1.setVisibility(View.GONE);
			// mainView = new Marquee(this);
			mainView = new MarqueeNewView(this);

			setContentView(mainView);
		} else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
			MarqueeNewView.isRunning = false;
			Intent intent = getIntent();
			finish();
			startActivity(intent);
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			MarqueeNewView.isRunning = false;
			finish();
		} else {
			Toast.makeText(this,
					"Turn the phone to potrait mode and enter the text",
					Toast.LENGTH_SHORT).show();
			MarqueeNewView.isRunning = false;
			Intent intent = getIntent();
			finish();
			startActivity(intent);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (android.provider.Settings.System.getInt(getContentResolver(),
				Settings.System.ACCELEROMETER_ROTATION, 0) == 1) {
			android.provider.Settings.System.putInt(getContentResolver(),
					Settings.System.ACCELEROMETER_ROTATION, 0);
			// Toast.makeText(this, "Rotation OFF", Toast.LENGTH_SHORT).show();
		} else {
			android.provider.Settings.System.putInt(getContentResolver(),
					Settings.System.ACCELEROMETER_ROTATION, 1);
			// Toast.makeText(this, "Rotation ON", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		boolean previouslyStarted = prefs.getBoolean(
				getString(R.string.pref_previously_started), false);
		if (!previouslyStarted) {
			SharedPreferences.Editor edit = prefs.edit();
			edit.putBoolean(getString(R.string.pref_previously_started),
					Boolean.TRUE);
			edit.commit();
			Intent intent = new Intent("com.example.marqi.STARTER");
			startActivity(intent);

		}
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ) {
			MarqueeNewView.isRunning = false;
			 finish();
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	
}
