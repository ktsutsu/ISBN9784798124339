package com.example.testpreference;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.app.Activity;

public class TestPreference extends Activity implements View.OnClickListener {
	EditText edit01;
	Button btnPut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// レイアウトの作成
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		// 入力ボックスの作成
		edit01 = new EditText(this);
		edit01.setText("");
		edit01.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(edit01);

		// 保存ボタン作成
		btnPut = new Button(this);
		btnPut.setText("保存");
		btnPut.setOnClickListener(this);
		btnPut.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(btnPut);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}
}
