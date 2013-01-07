package com.example.testrecognizerintent;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TestRecognizerIntent extends Activity implements View.OnClickListener {
    private static final int REQUEST_CODE = 0;
    private Button btnStart;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        // 開始ボタンを生成
        btnStart = new Button(this);
        btnStart.setText("開始");
        btnStart.setOnClickListener(this);
        btnStart.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        layout.addView(btnStart);
    }

    @Override
    public void onClick(View view) {
        try {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            // 言語モデル設定
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            // マイクアイコン下のテキスト設定
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "音声認識");
            // 結果を1つだけ取得
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            // 音声認識機能がない場合エラーをトースト表示
            Toast.makeText(TestRecognizerIntent.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
