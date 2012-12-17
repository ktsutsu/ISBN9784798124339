package com.example.servercheck;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.app.Activity;

public class ServerCheck extends Activity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// レイアウトの作成
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}
}
