package com.hanium.android.mydata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.hanium.android.mydata.ui.map.MapActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText loginID, loginPW;
    Button loginBtn;

    final static String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginID = findViewById(R.id.loginID);
        loginPW = findViewById(R.id.loginPW);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = loginID.getText().toString();
                String userPW = loginPW.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) {

                                String userID = jsonObject.getString("userID");
                                String userPW = jsonObject.getString("userPW");
                                String userName = jsonObject.getString("userName");

                                Toast.makeText(getApplicationContext(), userName+ "님 환영합니다.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(LoginActivity.this, MainActivity2.class );

                                intent.putExtra("userID", userID);
                                intent.putExtra("userPW", userPW);
                                intent.putExtra("userName", userName);

                                startActivity(intent);

                            } else {
                                Toast.makeText(getApplicationContext(), "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d(TAG, e.getMessage());
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(userID, userPW, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);

                // click -> loginRequest -> responseListener
            }
        });

    }
}