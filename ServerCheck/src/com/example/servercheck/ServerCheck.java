package com.example.servercheck;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.SharedPreferences;

public class ServerCheck extends Activity implements View.OnClickListener {
	private static final int MENU_ID_ITEM1 = Menu.FIRST;
	private static final int MENU_ID_ITEM2 = Menu.FIRST + 1;

	private static final String DEF_HTTP = "http://";

	private EditText edit01;
	private EditText edit02;
	private EditText edit03;
	private Button btnConnect;
	private TextView txtView;

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
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem item1 = menu.add(Menu.NONE, MENU_ID_ITEM1, Menu.NONE, "設定削除");
		item1.setIcon(android.R.drawable.ic_menu_delete);
		MenuItem item2 = menu.add(Menu.NONE, MENU_ID_ITEM2, Menu.NONE, "終了");
		item2.setIcon(android.R.drawable.ic_menu_close_clear_cancel);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onClick(View v) {
		String retStr = "";
		// URL入力ボックス1のURLの接続チェック
		if (!(DEF_HTTP.equals(edit01.getText().toString()))) {
			retStr = doGet(edit01.getText().toString());
			txtView.setText(edit01.getText().toString() + " " + retStr);
		}
		// URL入力ボックス2のURLの接続チェック
		if (!(DEF_HTTP.equals(edit02.getText().toString()))) {
			retStr = doGet(edit02.getText().toString());
			txtView.setText(txtView.getText().toString() + "\n"
					+ edit02.getText().toString() + " " + retStr);
		}
		// URL入力ボックス3のURLの接続チェック
		if (!(DEF_HTTP.equals(edit03.getText().toString()))) {
			retStr = doGet(edit03.getText().toString());
			txtView.setText(txtView.getText().toString() + "\n"
					+ edit03.getText().toString() + " " + retStr);
		}
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

	@Override
	protected void onResume() {
		super.onResume();
		getPrefer();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// プリファレンスの保存
		// 他のアプリケーションからも読み込み可能なモードでプリファレンスを開く
		SharedPreferences prefer = getPreferences(MODE_WORLD_READABLE);
		SharedPreferences.Editor editor = prefer.edit();
		editor.putString("server1", edit01.getText().toString());
		editor.putString("server2", edit02.getText().toString());
		editor.putString("server3", edit03.getText().toString());
		editor.commit();
	}

	// プリファレンスを読み込み、URL入力ボックスにセット
	private void getPrefer() {
		SharedPreferences prefer = getPreferences(MODE_WORLD_READABLE);
		// プリファレンスをセットする。空なら、デフォルトでhttp://をセット
		edit01.setText(prefer.getString("server1", DEF_HTTP));
		edit02.setText(prefer.getString("server2", DEF_HTTP));
		edit03.setText(prefer.getString("server3", DEF_HTTP));
	}
}
