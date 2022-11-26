package com.hanium.android.mydata.ui.mypage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.hanium.android.mydata.R;
import com.hanium.android.mydata.SharedPreference;
import com.hanium.android.mydata.ui.user.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class WithdrawActivity extends AppCompatActivity {

    Button withdrawBtn, cancelBtn;
    EditText confirmID, confirmPW, confirmPW2;
    CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        withdrawBtn = findViewById(R.id.withdrawBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        confirmID = findViewById(R.id.idEt);
        confirmPW = findViewById(R.id.pwEt);
        confirmPW2 = findViewById(R.id.repwEt);
        check = findViewById(R.id.checkbox);

        String userID = SharedPreference.getUserID(WithdrawActivity.this);
        String userPW = SharedPreference.getUserPW(WithdrawActivity.this);


        withdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = confirmID.getText().toString();
                String pw = confirmPW.getText().toString();
                String pw2 = confirmPW2.getText().toString();

                if(id.equals(userID)&&pw.equals(userPW)&&pw2.equals(userPW)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(WithdrawActivity.this);
                    builder.setMessage("정말로 탈퇴하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONObject jsonObject = new JSONObject(response);
                                                boolean success = jsonObject.getBoolean("success");
                                                if (success) {
                                                    SharedPreference.clearUser(getApplicationContext());
                                                    Intent withdrawIntent = new Intent(WithdrawActivity.this, LoginActivity.class);
                                                    startActivity(withdrawIntent);
                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    };
                                    WithdrawRequest withdrawRequest = new WithdrawRequest(userID, responseListener);
                                    RequestQueue queue = Volley.newRequestQueue(WithdrawActivity.this);
                                    queue.add(withdrawRequest);
                                }
                            })
                            .show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(WithdrawActivity.this);
                    builder.setMessage("모든 항목을 정확히 입력해주세요")
                            .setNegativeButton("확인", null)
                            .show();
                    return;
                }

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

