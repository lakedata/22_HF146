package com.hanium.android.mydata;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class IDCheckRequest extends StringRequest  {

    final static String TAG = "JoinActivity";

    final static  private String URL = "http://192.168.43.1/idCheck.php";
    private Map<String, String> map;

    public IDCheckRequest(String userID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        Log.d(TAG, "in JoinRequest -> " +userID);

        map = new HashMap<>();
        map.put("userID", userID);

    }

    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
