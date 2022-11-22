package com.hanium.android.mydata.ui.mypage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.hanium.android.mydata.R;
import com.hanium.android.mydata.SharedPreference;

import org.json.JSONObject;

public class UpdateActivity extends AppCompatActivity {

    private static final int CALL_MEMBERACTIVITY = 0;
    final static String TAG = "UpdateActivity";

    private EditText updateName, updateID, updatePW, updatePW2;

    private String id, name, pw;
    private Button updateBtn, updateCancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        id = SharedPreference.getUserID(UpdateActivity.this);
        name = SharedPreference.getUserName(UpdateActivity.this);
        pw = SharedPreference.getUserPW(UpdateActivity.this);


        updateName = findViewById(R.id.update_name);
        updateID = findViewById(R.id.update_id);
        updatePW = findViewById(R.id.update_pw);
        updatePW2 = findViewById(R.id.update_pw2);

        updateName.setText(name);
        updateID.setText(id);
        updatePW.setText(pw);

        updateBtn = findViewById(R.id.updateBtn);
        updateCancelBtn = findViewById(R.id.updateCancelBtn);


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String upID = updateID.getText().toString();
                String upName = updateName.getText().toString();
                String upPW = updatePW.getText().toString();
                String upPW2 = updatePW2.getText().toString();

                if (upID.equals("") || upName.equals("") || upPW.equals("") || upPW2.equals("")) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
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

                            if (upPW2.equals(upPW)) {
                                if (success) {
                                    Toast.makeText(UpdateActivity.this, "정보를 성공적으로 수정하였습니다.", Toast.LENGTH_LONG).show();

                                    SharedPreference.setUserID(UpdateActivity.this, upID);
                                    SharedPreference.setUserName(UpdateActivity.this, upName);
                                    SharedPreference.setUserPW(UpdateActivity.this, upPW);

                                    Intent upIntent = new Intent(UpdateActivity.this, MyPageActivity.class);
                                    startActivity(upIntent);

                                    finish();

                                } else {
                                    Toast.makeText(UpdateActivity.this, "정보 수정에 실패하였습니다.", Toast.LENGTH_LONG).show();
                                    return;
                                }
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                                builder.setMessage("비밀번호가 일치하지 않습니다")
                                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                updatePW2.requestFocus();
                                            }
                                        })
                                        .show();
                                return;
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d(TAG, e.getMessage());
                            Log.d(TAG, "error");
                        }
                    }
                };

                UpdateRequest updateRequest = new UpdateRequest(id, upID, upName, upPW, responseListener);
                RequestQueue queue = Volley.newRequestQueue(UpdateActivity.this);
                queue.add(updateRequest);
            }
        });

        updateCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdateActivity.this, "정보 수정을 취소합니다.", Toast.LENGTH_LONG).show();

                Intent upIntent = new Intent(UpdateActivity.this, MyPageActivity.class);
                startActivity(upIntent);

                finish();
            }
        });
    }


}
