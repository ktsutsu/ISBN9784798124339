package com.example.lifecycle;

import android.os.Bundle;
import android.app.Activity;

public class LifeCycle extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
    }

}
