package com.hanium.android.mydata.ui.mypage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.hanium.android.mydata.ui.favBrand.FavBrandActivity;
import com.hanium.android.mydata.ui.user.LoginActivity;
import com.hanium.android.mydata.R;
import com.hanium.android.mydata.SharedPreference;
import com.hanium.android.mydata.ui.setting.SettingActivity;

import org.w3c.dom.Text;

public class MyPageActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100;
    final static String TAG = "MyPageActivity";

    ImageView profile;
    TextView nameTv, heart, update, setting, withdraw, brand;

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


        nameTv = (TextView)findViewById(R.id.nameTv);
        heart = (TextView)findViewById(R.id.heart);
        brand =(TextView)findViewById(R.id.brand);
        update = (TextView)findViewById(R.id.update);
        setting = (TextView)findViewById(R.id.settings);
        profile = (ImageView)findViewById(R.id.profile);
        withdraw = (TextView)findViewById(R.id.withdraw);

        id = SharedPreference.getUserID(MyPageActivity.this);
        name = SharedPreference.getUserName(MyPageActivity.this);
        pw = SharedPreference.getUserPW(MyPageActivity.this);


        if (name.length() != 0) {
            Log.d(TAG, "in Mypage " +name);
            nameTv.setText(name);
        } else {
            Log.d(TAG, "in Mypage need login");
            nameTv.setText("로그인이 필요합니다");
        }

//        new DownloadImageTask(profile)
//                .execute("https://lh3.googleusercontent.com/a-/AOh14Gg_QB13iJ8aYHge5SjaCTd9Jg3csmTcdDRf7DcX=s576-p-no");


        //즐겨찾기 페이지 액티비티 열기
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

        brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, FavBrandActivity.class);
                startActivity(intent);
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

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WithdrawActivity.class);
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

}
