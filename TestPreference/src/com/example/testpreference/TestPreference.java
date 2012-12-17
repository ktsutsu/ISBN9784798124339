package com.example.testpreference;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.app.Activity;
import android.content.SharedPreferences;

public class TestPreference extends Activity implements View.OnClickListener {
	EditText edit01;
	Button btnPut;
	Button btnGet;

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

		// 読込みボタン作成
		btnGet = new Button(this);
		btnGet.setText("読込み");
		btnGet.setOnClickListener(this);
		btnGet.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(btnGet);
	}

	@Override
	public void onClick(View v) {
		if (v == btnPut) {
			// 保存ボタン押下時処理
			// プライベートモードでプリファレンスにアクセス
			SharedPreferences prefer = getSharedPreferences("TestPreference",
					MODE_PRIVATE);
			SharedPreferences.Editor editor = prefer.edit();
			// setting1というキーに文字列を保存
			editor.putString("settings1", edit01.getText().toString());
			// プリファレンスに値を書き込み
			editor.commit();
		}
	}
}
