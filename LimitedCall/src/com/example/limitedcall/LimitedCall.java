package com.example.limitedcall;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class LimitedCall extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// レイアウトの作成
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		// テキストビューの作成
		TextView txtView = new TextView(this);
		txtView.setText("00-0000-0000");
		txtView.setTextSize(30.0f);
		txtView.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(txtView);

		// ボタンの生成
		Button btn = new Button(this);
		btn.setText("このボタンを押すと電話をかける");
		// ボタンの動作を設定
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("tel:0000000000");
				Intent intent = new Intent(Intent.ACTION_CALL, uri);
				startActivity(intent);
			}
		});
		btn.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(btn);
	}
}
