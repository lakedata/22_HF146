package com.hanium.android.mydata;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class JoinRequest extends StringRequest {

    final static String TAG = "JoinActivity";

    final static  private String URL = "http://192.168.43.1/register.php";
    private Map<String, String> map;

    public JoinRequest(String userName, String userID, String userPW, String userEmail, String year, String month, String day,
                       String userTelecom, String userPhoneNum, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        Log.d(TAG, "in JoinRequest -> " +userEmail);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPW", userPW);
        map.put("userName", userName);
        map.put("userEmail", userEmail);
        map.put("userTelecom", userTelecom);
        map.put("userPhoneNum", userPhoneNum);
        map.put("birth", year+"-"+month+"-"+day);
    }

    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
