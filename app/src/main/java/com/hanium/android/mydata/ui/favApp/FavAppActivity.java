package com.hanium.android.mydata.ui.favApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hanium.android.mydata.MainActivity2;
import com.hanium.android.mydata.R;

public class FavAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_app);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chromeBtn:
                String packageName = "com.android.chrome";
                Intent intent = this.getPackageManager().getLaunchIntentForPackage(packageName);
//                Intent intent = this.getLaunchIntentForPackage(packageName);
                startActivity(intent);
                break;
        }
    }
}