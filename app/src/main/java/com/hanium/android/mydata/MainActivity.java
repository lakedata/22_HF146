package com.hanium.android.mydata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.searchBtn:
                Intent intentS = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intentS);
                break;
            case R.id.mainLoginBtn:
                Intent intentL = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentL);
                break;
            case R.id.mainJoinBtn:
                Intent intentJ = new Intent(MainActivity.this, JoinActivity.class);
                startActivity(intentJ);
                break;
        }
    }
}