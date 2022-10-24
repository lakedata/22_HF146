package com.hanium.android.mydata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class JoinActivity extends AppCompatActivity {

    final static String TAG = "JoinActivity";

    // 아이디, 비번, 이름, 이메일, 통신사, 전화번호, 생년월일, 주소, 포인트(기본값=0)
    private TextInputEditText joinName, joinID, joinPW, joinEmail, joinYear,
                    joinMonth, joinDay, joinPhone;
    private Spinner joinTele;
    private Button joinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        joinName = findViewById(R.id.joinName);
        joinID = (TextInputEditText) findViewById(R.id.joinID);
        joinPW = (TextInputEditText) findViewById(R.id.joinPW);
        joinEmail = (TextInputEditText) findViewById(R.id.joinEmail);
        joinYear = (TextInputEditText) findViewById(R.id.joinYear);
        joinMonth = (TextInputEditText) findViewById(R.id.joinMonth);
        joinDay = (TextInputEditText) findViewById(R.id.joinDay);
        joinTele = (Spinner) findViewById(R.id.joinTelecom);
        joinPhone = (TextInputEditText) findViewById(R.id.joinPhone);

        joinBtn = findViewById(R.id.joinBtn);

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = joinName.getText().toString();
                String userID = joinID.getText().toString();
                String userPW = joinPW.getText().toString();
                String userEmail = joinEmail.getText().toString();
                String year = joinYear.getText().toString();
                String month = joinMonth.getText().toString();
                String day = joinDay.getText().toString();
                String userTelecom = joinTele.getSelectedItem().toString();
                String userPhoneNum = joinPhone.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if(success) {
                                Toast.makeText(JoinActivity.this, "회원가입 성공", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(JoinActivity.this, "회원가입 실패", Toast.LENGTH_LONG).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                JoinRequest joinRequest = new JoinRequest(userName, userID, userPW, userEmail,
                        year, month, day, userTelecom, userPhoneNum, responseListener);
                RequestQueue queue = Volley.newRequestQueue(JoinActivity.this);
                queue.add(joinRequest);
            }
        });
    }
}
