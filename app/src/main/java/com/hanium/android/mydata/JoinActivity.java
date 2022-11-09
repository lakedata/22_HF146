package com.hanium.android.mydata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
    private TextInputEditText joinName, joinID, joinPW, joinPWCheck, joinEmail, joinAddr, joinYear,
                    joinMonth, joinDay, joinPhone;
    private Spinner joinTele;
    private Button idCheckBtn, joinBtn;

    private boolean idCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        joinName = findViewById(R.id.joinName);
        joinID = (TextInputEditText) findViewById(R.id.joinID);
        joinPW = (TextInputEditText) findViewById(R.id.joinPW);
        joinPWCheck = (TextInputEditText) findViewById(R.id.joinPWCheck);
        joinEmail = (TextInputEditText) findViewById(R.id.joinEmail);
        joinAddr = (TextInputEditText) findViewById(R.id.joinAddr);
        joinYear = (TextInputEditText) findViewById(R.id.joinYear);
        joinMonth = (TextInputEditText) findViewById(R.id.joinMonth);
        joinDay = (TextInputEditText) findViewById(R.id.joinDay);
        joinTele = (Spinner) findViewById(R.id.joinTelecom);
        joinPhone = (TextInputEditText) findViewById(R.id.joinPhone);

        idCheckBtn = findViewById(R.id.idCheckBtn);
        joinBtn = findViewById(R.id.joinBtn);


        idCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = joinID.getText().toString();

                if (idCheck) {
                    return;
                }

                if (userID.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    builder.setMessage("아이디를 입력하세요")
                            .setPositiveButton("확인", null)
                            .show();
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) {
                                Toast.makeText(JoinActivity.this, "사용할 수 있는 아이디입니다", Toast.LENGTH_LONG).show();

                                idCheck = true;
                                idCheckBtn.setBackgroundColor(Color.GRAY);

                            } else {
                                Toast.makeText(JoinActivity.this, "이미 존재하는 아이디입니다", Toast.LENGTH_LONG).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, e.getMessage());
                        }
                    }
                };

                IDCheckRequest idCheckRequest = new IDCheckRequest(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(JoinActivity.this);
                queue.add(idCheckRequest);
            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = joinName.getText().toString();
                String userID = joinID.getText().toString();
                String userPW = joinPW.getText().toString();
                String pwCheck = joinPWCheck.getText().toString();
                String userEmail = joinEmail.getText().toString();
                String userAddr = joinAddr.getText().toString();
                String year = joinYear.getText().toString();
                String month = joinMonth.getText().toString();
                String day = joinDay.getText().toString();
                String userTelecom = joinTele.getSelectedItem().toString();
                String userPhoneNum = joinPhone.getText().toString();

                if (!idCheck) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    builder.setMessage("아이디 중복 확인이 필요합니다")
                            .setNegativeButton("확인", null)
                            .show();
                    return;
                }

                if (userID.equals("") || userName.equals("") || userPW.equals("") || userEmail.equals("") || userAddr.equals("") ||
                        year.equals("") || month.equals("") || day.equals("") || userTelecom.equals("") || userPhoneNum.equals("")) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    builder.setMessage("모든 항목을 입력해주세요")
                            .setNegativeButton("확인", null)
                            .show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if(pwCheck.equals(userPW)) {
                                if (success) {
                                    Toast.makeText(JoinActivity.this, "회원가입 성공", Toast.LENGTH_LONG).show();

                                    Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(JoinActivity.this, "회원가입 실패", Toast.LENGTH_LONG).show();
                                    return;
                                }
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                                builder.setMessage("비밀번호가 일치하지 않습니다")
                                        .setPositiveButton("확인", null)
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, e.getMessage());
                            Log.d(TAG, "error");
                        }

                    }
                };

                JoinRequest joinRequest = new JoinRequest(userName, userID, userPW, userEmail, userAddr,
                        year, month, day, userTelecom, userPhoneNum, responseListener);
                RequestQueue queue = Volley.newRequestQueue(JoinActivity.this);
                queue.add(joinRequest);
            }
        });
    }
}
