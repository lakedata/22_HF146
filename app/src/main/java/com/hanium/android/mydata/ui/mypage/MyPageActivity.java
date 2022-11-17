package com.hanium.android.mydata.ui.mypage;

import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.hanium.android.mydata.LoginActivity;
import com.hanium.android.mydata.MainActivity2;
import com.hanium.android.mydata.R;
import com.hanium.android.mydata.ui.setting.SettingActivity;

import org.w3c.dom.Text;

import java.io.InputStream;

public class MyPageActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100;
    final static String TAG = "MyPageActivity";

    ImageView profile;
    TextView nameTv, emailTv, heart, update, setting;

    private String id, name, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        // ActionBar hide
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

        Toolbar mToolbar = (Toolbar) findViewById(R.id.mypage_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기


        emailTv = (TextView) findViewById(R.id.emailTv);
        nameTv = (TextView)findViewById(R.id.nameTv);
        heart = (TextView)findViewById(R.id.heart);
        update = (TextView)findViewById(R.id.update);
        setting = (TextView)findViewById(R.id.settings);
        profile = (ImageView)findViewById(R.id.profile);


        Intent intent = getIntent();
        id = intent.getStringExtra("userID");
        name = intent.getStringExtra("userName");
        pw = intent.getStringExtra("userPW");

        if (name != null) {
            nameTv.setText(name);
        } else {
            nameTv.setText("로그인이 필요합니다");
        }

        Log.d(TAG, "in Mypage Activity ->" +name);

//        new DownloadImageTask(profile)
//                .execute("https://lh3.googleusercontent.com/a-/AOh14Gg_QB13iJ8aYHge5SjaCTd9Jg3csmTcdDRf7DcX=s576-p-no");

        //DB에서 name, email정보를 불러와 출력
//        nameTv.setText(name);
//        emailTv.setText(email);


        //즐겨찾기 페이지 액티비티 열기
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MyPageActivity.this);
                    builder.setMessage("로그인이 필요한 페이지입니다.\n로그인 페이지로 이동하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent toLoginIntent = new Intent(MyPageActivity.this, LoginActivity.class);
                                    startActivity(toLoginIntent);
                                    return;
                                }
                            })
                            .setNegativeButton("취소", null)
                            .show();

                } else {
                    Intent intent = new Intent(getApplicationContext(), FavoriteActivity.class);
                    startActivity(intent);
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MyPageActivity.this);
                    builder.setMessage("로그인이 필요한 페이지입니다.\n로그인 페이지로 이동하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent toLoginIntent = new Intent(MyPageActivity.this, LoginActivity.class);
                                    startActivity(toLoginIntent);
                                    return;
                                }
                            })
                            .setNegativeButton("취소", null)
                            .show();

                } else {
                    Intent intent = new Intent(MyPageActivity.this, UpdateActivity.class);
                    intent.putExtra("userID", id);
                    intent.putExtra("userName", name);
                    intent.putExtra("userPW", pw);
                    startActivityForResult(intent, RC_SIGN_IN);
                }
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

    }
/*
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
    }
*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN) {
            switch (resultCode) {
                case RESULT_OK:
                    id = data.getStringExtra("userID");
                    name = data.getStringExtra("userName");
                    pw = data.getStringExtra("userPW");

                    nameTv.setText(name);

                    Log.d(TAG, "id: " +id+ "  name: " +name+ "  pw: " +pw);

                    break;
                case RESULT_CANCELED:
                    Log.d(TAG, "canceled");
                    break;
            }
        }
    }
}
