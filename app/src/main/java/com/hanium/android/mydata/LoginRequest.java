package com.hanium.android.mydata;

import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    final static private String URL = "http://192.168.43.1/login.php/";//http://192.168.43.1/login.php"; //http://192.168.52.1/login.php" http://172.30.1.12/login.php
    private Map<String, String> map;

    final static String TAG = "LoginActivity";


    public LoginRequest(String userID, String userPW, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        Log.d(TAG, "in LoginRequest: " +userID+ " " +userPW);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPW", userPW);
    }

    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
