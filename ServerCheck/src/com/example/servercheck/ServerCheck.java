package com.example.servercheck;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.app.Activity;

public class ServerCheck extends Activity implements View.OnClickListener {
	EditText edit01;
	EditText edit02;
	EditText edit03;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// レイアウトの作成
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		// URL入力ボックス1を作成
		edit01 = new EditText(this);
		edit01.setText("");
		edit01.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(edit01);
		
		// URL入力ボックス2を作成
		edit02 = new EditText(this);
		edit02.setText("");
		edit02.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(edit02);
		
		// URL入力ボックス3を作成
		edit03 = new EditText(this);
		edit03.setText("");
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
