package com.hanium.android.mydata.ui.map;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertPlaceRequest extends StringRequest {

    final static String TAG = "PlaceDetailActivity";

    final static private String URL = "http://192.168.43.1/insert_place.php/";
    private Map<String, String> map;

    public InsertPlaceRequest(String placeID, String pName, String pCategory1, String pCategory2, String pAddr, String pExtraInfo, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        Log.d(TAG, "in InsertPlaceRequest: " +pName);

        map = new HashMap<>();
        map.put("placeID", placeID);
        map.put("pName", pName);
        map.put("pCategory1", pCategory1);
        map.put("pCategory2", pCategory2);
        map.put("pAddr", pAddr);
        map.put("pExtraInfo", pExtraInfo);
    }

    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
