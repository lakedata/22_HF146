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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.hanium.android.mydata.MainActivity2;
import com.hanium.android.mydata.R;
import com.hanium.android.mydata.SharedPreference;
import com.hanium.android.mydata.ui.setting.SettingActivity;
import com.hanium.android.mydata.ui.user.LoginActivity;

import org.w3c.dom.Text;

import java.io.InputStream;

public class MyPageActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1000;
    final static String TAG = "MyPageActivity";

    ImageView profile;
    TextView nameTv, emailTv, heart, update, setting, logout, withdraw;

    private String id, name, pw, email;
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
        setting = (TextView)findViewById(R.id.settings);
        profile = (ImageView)findViewById(R.id.profile);
        logout = (TextView)findViewById(R.id.logout);
        withdraw = (TextView)findViewById(R.id.withdraw);

        id = SharedPreference.getUserID(MyPageActivity.this);
        name = SharedPreference.getUserName(MyPageActivity.this);
        pw = SharedPreference.getUserPW(MyPageActivity.this);
        email = SharedPreference.getUserEmail(MyPageActivity.this);

        try {
            if (name.length() != 0) {
                Log.d(TAG, "in Mypage " + name);
                nameTv.setText(name);
                emailTv.setText(email);
            } else{
                Log.d(TAG, "in Mypage need login");
                nameTv.setText("로그인이 필요합니다");
                emailTv.setText("로그인이 필요합니다");
            }
        }catch (NullPointerException e){

        }
        new DownloadImageTask(profile)
                .execute("https://lh3.googleusercontent.com/a-/AOh14Gg_QB13iJ8aYHge5SjaCTd9Jg3csmTcdDRf7DcX=s576-p-no");



        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.length() == 0) {
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
                if (name.length() == 0) {
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
                    startActivity(intent);
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
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MyPageActivity.this)
                        .setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                        .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent i = new Intent(MyPageActivity.this, LoginActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();
            }
        });
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity2.class);
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
