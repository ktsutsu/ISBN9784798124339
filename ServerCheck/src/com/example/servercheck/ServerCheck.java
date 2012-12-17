package com.example.servercheck;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;

public class ServerCheck extends Activity implements View.OnClickListener {
	EditText edit01;
	EditText edit02;
	EditText edit03;
	Button btnConnect;
	TextView txtView;

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

		// 接続ボタン作成
		btnConnect = new Button(this);
		btnConnect.setText("接続");
		btnConnect.setOnClickListener(this);
		btnConnect.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(btnConnect);

		// 接続結果表示エリア作成
		txtView = new TextView(this);
		txtView.setText("接続結果をここに表示します。");
		txtView.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(txtView);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

	// urlにGETメソッド発行し、ステータスコードを返す
	public String doGet(String url) {
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet method = new HttpGet(url);

			HttpResponse response = client.execute(method);
			// レスポンスからステータスコード取り出し
			int status = response.getStatusLine().getStatusCode();
			return "Status:" + status;
		} catch (Exception e) {
			// 例外時はエラーメッセージを返す
			return "Error:" + e.getMessage();
		}
	}
}
