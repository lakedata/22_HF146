package com.hanium.android.mydata.ui.search;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UpdateBrandScrapRequest extends StringRequest {

    final static String TAG = "SearchDetailActivity";

    final static private String URL = "http://192.168.43.1/update_brand_scrap.php/";
    private Map<String, String> map;

    public UpdateBrandScrapRequest(String userID, String brandID, String bScrap, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        Log.d(TAG, "in BrandScrapRequest: " +brandID+ " " +bScrap);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("brandID", brandID);
        map.put("bScrap", bScrap);
    }

    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
