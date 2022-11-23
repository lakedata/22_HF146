package com.hanium.android.mydata.ui.search;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class GetBrandScrapRequest extends StringRequest {

    final static String TAG = "SearchDetailActivity";

    final static private String URL = "http://192.168.43.1/get_brand_scrap.php/";
    private Map<String, String> map;

    public GetBrandScrapRequest(String userID, String brandID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        Log.d(TAG, "in BrandScrapRequest: " +userID+ " " +brandID);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("brandID", brandID);
    }

    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
