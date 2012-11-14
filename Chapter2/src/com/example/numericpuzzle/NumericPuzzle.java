package com.example.numericpuzzle;

import android.os.Bundle;
import android.app.Activity;

public class NumericPuzzle extends Activity {
	// ボタンリソースidリスト
	static final int imageButtons[] = { R.id.image_button1, R.id.image_button2,
			R.id.image_button3, R.id.image_button4, R.id.image_button5,
			R.id.image_button6, R.id.image_button7, R.id.image_button8,
			R.id.image_button9, R.id.image_button10, R.id.image_button11,
			R.id.image_button12, R.id.image_button13, R.id.image_button14,
			R.id.image_button15, R.id.image_button16 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_numeric_puzzle);
	}
}
