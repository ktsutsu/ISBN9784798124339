package com.example.testrecognizerintent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class TestRecognizerIntent extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);
    }
}
