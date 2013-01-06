package com.example.photoalbum;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PhotoAlbum extends Activity {
    private Gallery gallery;
    private TextView txtView;
    private ImageView imgView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.rgb(0, 0, 255));
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        setContentView(layout);

        // ギャラリーの生成
        gallery = new Gallery(this);
        gallery.setSpacing(3);
        gallery.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        layout.addView(gallery);

        // TextViewの生成
        txtView = new TextView(this);
        txtView.setText("日付を表示");
        txtView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        layout.addView(txtView);

        // ImageViewの生成
        imgView = new ImageView(this);
        imgView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        layout.addView(imgView);
    }
}
