package com.example.testcntprovider;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestCntProvider extends Activity {
	private Button btnQuery;
	private Button btnTel;
	private TextView txtView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// レイアウトの作成
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);

		// 電話番号有リスト取得ボタンを作成
		btnQuery = new Button(this);
		btnQuery.setText("電話番号有リスト取得");
		btnQuery.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getContactsData();
			}
		});
		btnQuery.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(btnQuery);

		// 電話番号取得ボタンを作成
		btnTel = new Button(this);
		btnTel.setText("電話番号取得");
		btnTel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getPhoneData();
			}
		});
		btnTel.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(btnTel);

		// 取得結果表示エリアを作成
		txtView = new TextView(this);
		txtView.setText("取得結果をここに表示します");
		txtView.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(txtView);

	}

	// 連絡先を取得して、結果エリアに表示する
	private void getContactsData() {
		String[] proj = new String[] { Contacts._ID, Contacts.DISPLAY_NAME,
				Contacts.HAS_PHONE_NUMBER };
		String selection = Contacts.HAS_PHONE_NUMBER + " = ?";
		String[] selectionArgs = { "1" };
		// 連絡先を取得
		Cursor cur = managedQuery(Contacts.CONTENT_URI, proj, selection, selectionArgs,
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

	// 電話番号を取得して、結果エリアに表示する
	private void getPhoneData() {
		String[] proj = new String[] {
				Phone._ID,
				Phone.CONTACT_ID,
				Phone.DISPLAY_NAME,
				Phone.NUMBER
		};
		Cursor cur = managedQuery(
				Phone.CONTENT_URI,
				proj,
				null,
				null,
				Phone.DISPLAY_NAME + " ASC"
				);

		// 結果が空なら戻る
		if (!cur.moveToFirst()) {
			txtView.setText("");
			return;
		}

		StringBuffer buff = new StringBuffer();
		String _id;
		String contact_id;
		String name;
		String number;
		// カラム名からインデックスを取得
		int _idColumn = cur
				.getColumnIndex(android.provider.ContactsContract.CommonDataKinds.Phone._ID);
		int contact_idColumn = cur
				.getColumnIndex(android.provider.ContactsContract.CommonDataKinds.Phone.CONTACT_ID);
		int nameColumn = cur
				.getColumnIndex(android.provider.ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
		int numberColumn = cur
				.getColumnIndex(android.provider.ContactsContract.CommonDataKinds.Phone.NUMBER);
		do {
			// フィールド値を取得する
			_id = cur.getString(_idColumn);
			contact_id = cur.getString(contact_idColumn);
			name = cur.getString(nameColumn);
			number = cur.getString(numberColumn);

			buff.append(_id);
			buff.append(" : ");
			buff.append(contact_id);
			buff.append(" : ");
			buff.append(name);
			buff.append(" : ");
			buff.append(number);
			buff.append("\n");
		} while (cur.moveToNext());
		cur.close();
		txtView.setText(buff.toString());
	}
}
