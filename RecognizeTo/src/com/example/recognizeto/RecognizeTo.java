package com.example.recognizeto;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class RecognizeTo extends Activity implements View.OnClickListener {
    private static final int REQUEST_CODE = 0;
    private Button btnStart;
    private Button btnSend;
    private EditText editText;
    private ScrollView scView;

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
                LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.addView(btnStart);

        // 転送ボタンを生成
        btnSend = new Button(this);
        btnSend.setText("転送");
        btnSend.setOnClickListener(this);
        btnSend.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.addView(btnSend);

        // 結果表示用スクロールビューを生成
        scView = new ScrollView(this);
        scView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.addView(scView);

        // 結果表示エリアを生成
        editText = new EditText(this);
        editText.setText("");
        editText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        scView.addView(editText);
    }

    @Override
    public void onClick(View view) {
        if (view == btnStart) {
            try {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "音声認識");
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                startActivityForResult(intent, REQUEST_CODE);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(RecognizeTo.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else if (view == btnSend) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, editText.getText().toString());
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String resStr = "";
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            for (int i = 0; i < results.size(); i++) {
                resStr += results.get(i);
            }
            editText.setText(editText.getText() + resStr);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
