package com.example.testcntprovider;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.app.Activity;

public class TestCntProvider extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// レイアウトの作成
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
	}
}
