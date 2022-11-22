package com.hanium.android.mydata.ui.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hanium.android.mydata.R;

public class SettingActivity2 extends AppCompatActivity {

    TextView withdraw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting2);

        withdraw = findViewById(R.id.tv_withdraw);

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WithdrawActivity.class);
                startActivity(intent);

            }
        });


    }


}