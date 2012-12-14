package com.example.limitedmail;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.app.Activity;

public class LimitedMail extends Activity implements View.OnClickListener {
	private EditText edit01;
	private EditText edit02;
	private EditText edit03;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// レイアウトの作成
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		// エディットテキスト（メールアドレス）の作成
		edit01 = new EditText(this);
		edit01.setText("aaaaa@bbbbbbbbb.com");
		edit01.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(edit01);
		// エディットテキスト（件名）の作成
		edit02 = new EditText(this);
		edit02.setText("ごめん、飲み行く");
		edit02.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(edit02);
		// エディットテキスト（本文）の作成
		edit03 = new EditText(this);
		edit03.setText("突然、飲みに行く事になったので、食事はいらないです。");
		edit03.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(edit03);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}
}
