package com.example.limitedcall;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.app.Activity;

public class LimitedCall extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// レイアウトの作成
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
	}
}
