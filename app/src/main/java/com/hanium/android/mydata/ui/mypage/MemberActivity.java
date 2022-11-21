package com.hanium.android.mydata.ui.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hanium.android.mydata.R;

public class MemberActivity extends AppCompatActivity {

    TextView name, id, email, addr, birth, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        name = findViewById(R.id.name_info);
        id = findViewById(R.id.id_info);
        email = findViewById(R.id.email_info);
        addr = findViewById(R.id.address_info);
        birth = findViewById(R.id.birth_info);
        phone = findViewById(R.id.phone_info);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        String userID = intent.getStringExtra("userID");
        String userEmail = intent.getStringExtra("userEmail");
        String userAddr = intent.getStringExtra("userAddr");
        String year = intent.getStringExtra("year");
        String month = intent.getStringExtra("month");
        String day = intent.getStringExtra("day");
        String userPhoneNum = intent.getStringExtra("userPhoneNum");

        name.setText(userName);
        id.setText(userID);
        email.setText(userEmail);
        addr.setText(userAddr);
        birth.setText(year+"년 "+month+"월 "+day+"일");
        phone.setText(userPhoneNum);


    }
    public void onClick(View view){
        switch(view.getId()) {
            case(R.id.confirmbutton):

        }
    }
}
