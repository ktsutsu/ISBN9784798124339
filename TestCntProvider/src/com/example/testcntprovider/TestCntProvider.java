package com.example.testcntprovider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.app.Activity;

public class TestCntProvider extends Activity {
	private Button btnQuery;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// レイアウトの作成
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);

		// 取得するボタンを作成
		btnQuery = new Button(this);
		btnQuery.setText("取得する");
		btnQuery.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(btnQuery);
	}
}
