package com.hanium.android.mydata.ui.map;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class GetPlaceScrapRequest extends StringRequest {

    final static String TAG = "PlaceDetailActivity";

    final static private String URL = "http://192.168.43.1/get_place_scrap.php/";
    private Map<String, String> map;

    public GetPlaceScrapRequest(String userID, String placeID, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        Log.d(TAG, "in GetPlaceScrapRequest: " +userID+ " " +placeID);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("placeID", placeID);
    }

    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
