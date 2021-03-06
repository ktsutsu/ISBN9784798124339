package com.example.numericpuzzle;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

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
	// ゲーム開始フラグ
	private boolean gameStarted = false;
	OrderController orders[] = new OrderController[imageButtons.length];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_numeric_puzzle);
		createOrderController();
		setStartButtonListener();
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

	// ゲーム開始処理
	private void startGame() {
		int size = numImages.length;
		// 最後のマス目は交換しないので-2
		for (int i = 0; i < size - 2; i++) {
			int swap = (int) (Math.random() * (size - (i + 1)));
			orders[i].swapImage(orders[i + swap]);
		}
		gameStarted = true;
	}

	// 完成チェック
	private boolean isCompleted() {
		if (!gameStarted) {
			return false;
		}
		// 全マス順番にチェックする
		for (int i = 0; i < numImages.length; i++) {
			if (numImages[i] != orders[i].getImageRes()) {
				return false;
			}
		}
		return true;
	}

	// タイマー開始処理
	private void startChronometer() {
		Chronometer chrono = (Chronometer) findViewById(R.id.chronometer);
		// 基準時刻としてデバイスが起動してから経過した時間を設定
		chrono.setBase(SystemClock.elapsedRealtime());
		chrono.start();
	}

	// タイマー停止処理
	private long stopChronometer() {
		Chronometer chrono = (Chronometer) findViewById(R.id.chronometer);
		chrono.stop();
		return SystemClock.elapsedRealtime() - chrono.getBase();
	}

	// スタートボタン押下時処理
	private void setStartButtonListener() {
		Button btn = (Button) findViewById(R.id.start_button);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startGame();
				startChronometer();
			}
		});
	}

	// 完成後処理
	private void complete() {
		long msec = stopChronometer();
		AlertDialog.Builder alertDlgBld = new AlertDialog.Builder(this);
		alertDlgBld.setTitle(R.string.complete_title);
		alertDlgBld.setMessage(msec / 1000 + " 秒");
		alertDlgBld.setPositiveButton(R.string.complete_title,
				new DialogInterface.OnClickListener() {
					// ボタンが押されたらダイアログを閉じる
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		alertDlgBld.show();
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

	// 上方向検索処理
	private boolean searchUp(int idx) {
		int distance = 0;
		// 指定対象の上の行から検索する
		for (int i = idx - 4; i > -1; i -= 4) {
			distance--;
			if (orders[i].getImageRes() == R.drawable.blank) {
				swapUp(idx, distance);
				return true;
			}
		}
		return false;
	}

	// 下方向検索処理
	private boolean searchDown(int idx) {
		int distance = 0;
		// 指定対象の下の行から検索する
		for (int i = idx + 4; i < 16; i += 4) {
			distance++;
			if (orders[i].getImageRes() == R.drawable.blank) {
				swapDown(idx, distance);
				return true;
			}
		}
		return false;
	}

	// 左方向検索処理
	private boolean searchLeft(int idx) {
		int distance = 0;
		int min = 0;
		// 同列の最小数を取得
		min = idx - (idx % 4);
		// 指定対象の左の行から検索する
		for (int i = idx - 1; i >= min; i--) {
			distance--;
			if (orders[i].getImageRes() == R.drawable.blank) {
				swapLeft(idx, distance);
				return true;
			}
		}
		return false;
	}

	// 右方向検索処理
	private boolean searchRight(int idx) {
		int distance = 0;
		int max = 15;
		// 最大数(次列の最小数)を取得
		max = idx - (idx % 4) + 4;
		// 指定対象の右の行から検索する
		for (int i = idx + 1; i < max; i++) {
			distance++;
			if (orders[i].getImageRes() == R.drawable.blank) {
				swapRight(idx, distance);
				return true;
			}
		}
		return false;
	}

	// 指定distance分上に詰める
	private void swapUp(int idx, int distance) {
		for (int i = idx + (distance * 4); i < idx; i += 4) {
			// 隣と画像交換
			orders[i].swapImage(orders[i + 4]);
		}
	}

	// 指定distance分下に詰める
	private void swapDown(int idx, int distance) {
		for (int i = idx + (distance * 4); i > idx; i -= 4) {
			// 隣と画像交換
			orders[i].swapImage(orders[i - 4]);
		}
	}

	// 指定distance分左に詰める
	private void swapLeft(int idx, int distance) {
		for (int i = idx + distance; i < idx; i++) {
			// 隣と画像交換
			orders[i].swapImage(orders[i + 1]);
		}
	}

	// 指定distance分右に詰める
	private void swapRight(int idx, int distance) {
		for (int i = idx + distance; i > idx; i--) {
			// 隣と画像交換
			orders[i].swapImage(orders[i - 1]);
		}
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

		// 画像リソースidを返す
		public int getImageRes() {
			return curImageId;
		}

		public void onClick(View v) {
			// blank画像クリック時は反応させない
			if (curImageId == R.drawable.blank) {
				return;
			}
			searchDir(idx);

			if (isCompleted()) {
				complete();
			}
		}

		// otherの画像と現在の画像を交換する
		public void swapImage(OrderController other) {
			int previous = other.setImageRes(curImageId);
			setImageRes(previous);
		}
	}
}
