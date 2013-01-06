package com.example.photoalbum;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PhotoAlbum extends Activity {
    private ArrayList<Bitmap> photoList = new ArrayList<Bitmap>();
    private ArrayList<String> fileList = new ArrayList<String>();
    private ArrayList<Long> dateList = new ArrayList<Long>();
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

        // ギャラリー写真の取得
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cur = this.managedQuery(uri, null, null, null, null);

        cur.moveToFirst();
        for (int i = 0; i < cur.getCount(); i++) {
            String path = cur.getString(cur.getColumnIndexOrThrow("_data"));
            long datetaken = cur.getLong(cur.getColumnIndexOrThrow("datetaken"));
            fileList.add(path);
            dateList.add(datetaken);
            photoList.add(file2bmp(path, 150, 150));
            // デバッグ用にログ出力
            for (String column : cur.getColumnNames()) {
                android.util.Log.v("columnName", column + "=" + cur.getString(cur.getColumnIndexOrThrow(column)));
            }
            cur.moveToNext();
        }

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

    // 画像を指定した幅と高さにリサイズする
    private Bitmap file2bmp(String path, int maxW, int maxH) {
        BitmapFactory.Options options;
        try {
            // 画像サイズの取得
            options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            BitmapFactory.decodeFile(path, options);
            // リサイズ時の係数を計算
            int scaleW = options.outWidth / maxW + 1;
            int scaleH = options.outHeight / maxH + 1;
            // 大きい方の係数を取得
            int scale = Math.max(scaleW, scaleH);

            // 画像の読み込み
            options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = scale;
            Bitmap bmp = BitmapFactory.decodeFile(path, options);
            return bmp;
        } catch (Exception e) {
            return null;
        }
    }
}
