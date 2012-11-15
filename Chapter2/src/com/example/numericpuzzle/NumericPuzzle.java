package com.example.numericpuzzle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.app.Activity;

public class NumericPuzzle extends Activity {
	// ボタンリソースidリスト
	static final int imageButtons[] = { R.id.image_button1, R.id.image_button2,
			R.id.image_button3, R.id.image_button4, R.id.image_button5,
			R.id.image_button6, R.id.image_button7, R.id.image_button8,
			R.id.image_button9, R.id.image_button10, R.id.image_button11,
			R.id.image_button12, R.id.image_button13, R.id.image_button14,
			R.id.image_button15, R.id.image_button16 };
	// 画像リソースidリスト
	static final int numImages[] = { R.drawable.num1, R.drawable.num2,
			R.drawable.num3, R.drawable.num4, R.drawable.num5, R.drawable.num6,
			R.drawable.num7, R.drawable.num8, R.drawable.num9,
			R.drawable.num10, R.drawable.num11, R.drawable.num12,
			R.drawable.num13, R.drawable.num14, R.drawable.num15,
			R.drawable.blank };
	OrderController orders[] = new OrderController[imageButtons.length];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_numeric_puzzle);
		createOrderController();
	}

	// OrderControllerをまとめて全部生成
	private void createOrderController() {
		for (int i = 0; i < imageButtons.length; i++) {
			// リソースidからimgBtnを取得
			ImageButton imgbtn = (ImageButton) findViewById(imageButtons[i]);
			// OrderControllerを生成
			orders[i] = new OrderController(imgbtn, i, numImages[i]);
		}
	}

	private void searchDir(int idx) {
		// 上下左右blank画像検索チェックフラグ
		boolean searchRight = true;
		boolean searchLeft = true;
		boolean searchUp = true;
		boolean searchDown = true;

		// 1行目なので上は検索しない
		if (idx < 4) {
			searchUp = false;
		}
		// 4行目なので下は検索しない
		if (idx > 11) {
			searchDown = false;
		}
		// 1列目なので左は検索しない
		if ((idx % 4) == 0) {
			searchLeft = false;
		}
		// 一行目なので上は検索しない
		if ((idx % 4) == 3) {
			searchRight = false;
		}

		// 上下左右を検索する。blank画像が見つかれば他方向の検索は不要
		if (searchUp && searchUp(idx)) {
			return;
		}
		if (searchDown && searchDown(idx)) {
			return;
		}
		if (searchLeft && searchLeft(idx)) {
			return;
		}
		if (searchRight && searchRight(idx)) {
			return;
		}
	}

	private boolean searchUp(int idx) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean searchDown(int idx) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean searchLeft(int idx) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean searchRight(int idx) {
		// TODO Auto-generated method stub
		return false;
	}

	class OrderController implements View.OnClickListener {
		ImageButton imgBtn;
		int idx = 0;
		int curImageId = 0;

		public OrderController(ImageButton ibtn, int i, int resid) {
			imgBtn = ibtn;
			idx = i;
			setImageRes(resid);
			imgBtn.setOnClickListener(this);
		}

		// ボタンに画像を設定
		public int setImageRes(int resid) {
			int old = curImageId;
			curImageId = resid;
			imgBtn.setImageResource(curImageId);
			return old;
		}

		public void onClick(View v) {
			// blank画像クリック時は反応させない
			if (curImageId == R.drawable.blank) {
				return;
			}
			searchDir(idx);

			// TODO パズル完成チェック処理
		}
	}
}
