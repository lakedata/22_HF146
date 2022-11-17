package com.hanium.android.mydata.ui.mypage;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UpdateRequest extends StringRequest {

    final static String TAG = "UpdateActivity";

    final static  private String URL = "http://192.168.43.1/update_user.php";
    private Map<String, String> map;


    public UpdateRequest(String id, String upID, String upName, String upPW, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        Log.d(TAG, "(in UpdateActivity) upID: " +upID);
        Log.d(TAG, "upName: " +upName);
        Log.d(TAG, "upPW: " +upPW);

        map = new HashMap<>();
        map.put("userID", id);
        map.put("upID", upID);
        map.put("upName", upName);
        map.put("upPW", upPW);
    }

    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
