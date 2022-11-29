package com.hanium.android.mydata.ui.mypage;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class WithdrawRequest extends StringRequest {
    final static String TAG = "WithdrawActivity";

    final static private String URL = "http://192.168.43.1/withdraw_user.php";
    private Map<String, String> map;

    public WithdrawRequest(String userID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        Log.d(TAG, "in LoginRequest: " +userID+ " " +userID);

        map = new HashMap<>();
        map.put("userID", userID);

    }

    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
