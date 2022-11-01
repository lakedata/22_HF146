package com.hanium.android.mydata.ui.mypage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import com.hanium.android.mydata.MainActivity2;
import com.hanium.android.mydata.R;

import org.w3c.dom.Text;

import java.io.InputStream;

public class MyPageActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1000;
    final static String TAG = "MyPageActivity";

    ImageView profile;
    TextView nameTv, emailTv, heart, update, setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        // ActionBar hide
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

        emailTv = (TextView) findViewById(R.id.emailTv);
        nameTv = (TextView)findViewById(R.id.nameTv);
        heart = (TextView)findViewById(R.id.heart);
        update = (TextView)findViewById(R.id.update);
        setting = (TextView)findViewById(R.id.setting);
        profile = (ImageView)findViewById(R.id.profile);


        Intent intent = getIntent();
        String name = intent.getStringExtra("userName");
        if (name != null) {
            nameTv.setText(name);
        }


        Log.d(TAG, "in Mypage Activity ->" +name);

        new DownloadImageTask(profile)
                .execute("https://lh3.googleusercontent.com/a-/AOh14Gg_QB13iJ8aYHge5SjaCTd9Jg3csmTcdDRf7DcX=s576-p-no");

        //DB에서 name, email정보를 불러와 출력
//        nameTv.setText(name);
//        emailTv.setText(email);




        //즐겨찾기 페이지 액티비티 열기
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FavoriteActivity.class);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
                startActivity(intent);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FavoriteActivity.class);
                startActivity(intent);
            }
        });

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;


        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            //Glide.with(getApplicationContext()).load(result).circleCrop().into(bmImage);
//            bmImage.setImageBitmap(result);
        }
    }}
