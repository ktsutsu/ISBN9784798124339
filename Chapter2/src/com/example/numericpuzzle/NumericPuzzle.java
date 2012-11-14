package com.example.numericpuzzle;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;

public class NumericPuzzle extends Activity {
	// ボタンリソースidリスト
	static final int imageButtons[] = { R.id.image_button1, R.id.image_button2,
			R.id.image_button3, R.id.image_button4, R.id.image_button5,
			R.id.image_button6, R.id.image_button7, R.id.image_button8,
			R.id.image_button9, R.id.image_button10, R.id.image_button11,
			R.id.image_button12, R.id.image_button13, R.id.image_button14,
			R.id.image_button15, R.id.image_button16 };
	// 画像リソースidリスト
	static final int numImages[] = { R.drawable.num1, R.drawable.num2,
			R.drawable.num3, R.drawable.num4, R.drawable.num5, R.drawable.num6,
			R.drawable.num7, R.drawable.num8, R.drawable.num9,
			R.drawable.num10, R.drawable.num11, R.drawable.num12,
			R.drawable.num13, R.drawable.num14, R.drawable.num15,
			R.drawable.blank };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_numeric_puzzle);
	}

	class OrderController implements View.OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub

		}
	}
}
