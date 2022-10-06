package com.hanium.android.mydata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    private static final int CALL_MEMBERACTIVITY=0;
    EditText et1, et2, et3, et4;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

    }
    public void onClick(View view){
        switch(view.getId()) {
            case (R.id.button):
                et1 = findViewById(R.id.update_name);
                String name = et1.getText().toString();
                et2 = findViewById(R.id.update_id);
                String id = et2.getText().toString();
                et3 = findViewById(R.id.update_pw);
                String password = et3.getText().toString();
                et4 = findViewById(R.id.update_pw2);
                String password2 = et4.getText().toString();

                if(et3.equals(et4)){
                    Intent intent = new Intent(this, MemberActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("id", id);
                    intent.putExtra("password", password);
                    startActivityForResult(intent, CALL_MEMBERACTIVITY);}
                else{

                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case CALL_MEMBERACTIVITY:
                if(resultCode == RESULT_OK){
                    String name = data.getStringExtra("name");
                    String id = data.getStringExtra("id");
                    String password = data.getStringExtra("password");

                    et1.setText(name);
                    et2.setText(id);
                    et3.setText(password);
                }
//
        }
    }
}
