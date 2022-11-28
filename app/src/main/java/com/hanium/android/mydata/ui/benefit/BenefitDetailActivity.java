package com.hanium.android.mydata.ui.benefit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.hanium.android.mydata.R;

public class BenefitDetailActivity extends AppCompatActivity {

    final static String TAG = "BenefitDetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benefit_detail);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.benefit_detail_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_like:
                Toast.makeText(BenefitDetailActivity.this, "즐겨찾기 등록", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
