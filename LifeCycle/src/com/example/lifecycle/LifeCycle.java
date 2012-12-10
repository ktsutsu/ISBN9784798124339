package com.example.lifecycle;

import android.os.Bundle;
import android.util.Log;
import android.app.Activity;

public class LifeCycle extends Activity {
	private static final String TAG = "LifeCycle";

	/** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate is called.");
        setContentView(R.layout.activity_life_cycle);
    }

	@Override
	protected void onStart() {
		super.onStart();
		Log.v(TAG, "onStart is called.");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.v(TAG, "onRestart is called.");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.v(TAG, "onResume is called.");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.v(TAG, "onPause is called.");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.v(TAG, "onStop is called.");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.v(TAG, "onDestroy is called.");
	}
}
