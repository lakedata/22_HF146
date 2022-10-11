package com.hanium.android.mydata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MemberActivity extends AppCompatActivity {

    EditText et5, et6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String id = intent.getStringExtra("id");

        et5 = findViewById(R.id.confirm_name);
        et5.setText(name);
        et6 = findViewById(R.id.id);
        et6.setText(id);

    }
    public void onClick(View view){
        switch(view.getId()) {
            case(R.id.confirm):
                Intent i = new Intent(this, UpdateActivity.class);
                startActivity(i);

        }
    }
}
