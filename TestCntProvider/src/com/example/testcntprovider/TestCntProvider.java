package com.example.testcntprovider;

import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.database.Cursor;

public class TestCntProvider extends Activity implements View.OnClickListener {
	private Button btnQuery;
	private TextView txtView;

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
		btnQuery.setOnClickListener(this);
		btnQuery.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(btnQuery);

		// 取得結果表示エリアを作成
		txtView = new TextView(this);
		txtView.setText("取得結果をここに表示します");
		txtView.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(txtView);

	}

	@Override
	public void onClick(View v) {
		getContactsData();
	}

	// 連絡先を取得して、結果エリアに表示する
	private void getContactsData() {
		String[] proj = new String[] { Contacts._ID, Contacts.DISPLAY_NAME,
				Contacts.HAS_PHONE_NUMBER };
		// 連絡先を取得
		Cursor cur = managedQuery(Contacts.CONTENT_URI, proj, null, null,
				Contacts.DISPLAY_NAME + " ASC");

		// 結果が空なら戻る
		if (!cur.moveToFirst()) {
			txtView.setText("");
			return;
		}

		StringBuffer buff = new StringBuffer();
		String _id;
		String name;
		String hasPhoneNumber;
		// カラム名からインデックスを取得
		int _idColumn = cur
				.getColumnIndex(android.provider.ContactsContract.Contacts._ID);
		int nameColumn = cur
				.getColumnIndex(android.provider.ContactsContract.Contacts.DISPLAY_NAME);
		int phoneColumn = cur
				.getColumnIndex(android.provider.ContactsContract.Contacts.HAS_PHONE_NUMBER);
		do {
			// フィールド値を取得する
			_id = cur.getString(_idColumn);
			name = cur.getString(nameColumn);
			hasPhoneNumber = cur.getString(phoneColumn);

			buff.append(_id);
			buff.append(" : ");
			buff.append(name);
			buff.append(" : ");
			buff.append(hasPhoneNumber);
			buff.append("\n");
		} while (cur.moveToNext());
		cur.close();
		txtView.setText(buff.toString());
	}
}
