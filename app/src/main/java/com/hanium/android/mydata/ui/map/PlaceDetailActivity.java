package com.hanium.android.mydata.ui.map;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.hanium.android.mydata.R;
import com.hanium.android.mydata.SharedPreference;
import com.hanium.android.mydata.ui.search.BrandDetailActivity;
import com.hanium.android.mydata.ui.search.GetBrandScrapRequest;
import com.hanium.android.mydata.ui.search.SetBrandScrapRequest;
import com.hanium.android.mydata.ui.search.UpdateBrandScrapRequest;
import com.hanium.android.mydata.ui.user.LoginActivity;

import org.json.JSONObject;

public class PlaceDetailActivity extends AppCompatActivity {

    final static String TAG = "PlaceDetailActivity";

    private TextView content, category1, category2, addr, benefit, extraInfo;
    private ImageView favPlace;

    private String userID, placeID, placeScrap, pName, pCategory1, pCategory2, pAddr, pExtraInfo;
    private boolean isLike = false;

    private boolean isInsert = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        Log.d(TAG, "in PlaceDetailActivity");

        Toolbar mToolbar = (Toolbar) findViewById(R.id.place_detail_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userID = SharedPreference.getUserID(PlaceDetailActivity.this);

        Intent intent = getIntent();
        placeID = intent.getStringExtra("placeID");
        pName = intent.getStringExtra("pName");
        String pPhoneNum = intent.getStringExtra("pPhoneNum");
        pAddr = intent.getStringExtra("pAddr");
        pCategory1 = intent.getStringExtra("pCategory1");
        pCategory2 = intent.getStringExtra("pCategory2");
        pExtraInfo = intent.getStringExtra("pExtraInfo");


        content = findViewById(R.id.place_content);
        category1 = findViewById(R.id.place_categoty1);
        category2 = findViewById(R.id.place_categoty2);
        addr = findViewById(R.id.place_addr);
        extraInfo = findViewById(R.id.place_extraInfo);

        favPlace = findViewById(R.id.place_like);

        Log.d(TAG, pName);

        content.setText(pName);
        category1.setText(pCategory1);
        category2.setText(pCategory2);
        addr.setText(pAddr);
        extraInfo.setText(pExtraInfo);


        insertPlace();


        favPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userID.length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PlaceDetailActivity.this);
                    builder.setMessage("로그인이 필요한 페이지입니다.\n로그인 페이지로 이동하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent toLoginIntent = new Intent(PlaceDetailActivity.this, LoginActivity.class);
                                    startActivity(toLoginIntent);
                                    return;
                                }
                            })
                            .setNegativeButton("취소", null)
                            .show();
                } else {

                    if(isLike == false) {
                        placeScrap = "Y";
                    } else {
                        placeScrap = "N";
                    }

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
//                            String pScrap = jsonObject.getString("pScrap");

                                if(success) {
                                    if(placeScrap.equals("N")) {
                                        favPlace.setImageResource(R.drawable.unlike_icon);
                                        isLike = false;
//                                        Toast.makeText(PlaceDetailActivity.this, "스크랩 해제", Toast.LENGTH_SHORT).show();
                                    } else if(placeScrap.equals("Y")) {
                                        favPlace.setImageResource(R.drawable.like_icon);
                                        isLike = true;
//                                        Toast.makeText(PlaceDetailActivity.this, "스크랩", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Toast.makeText(PlaceDetailActivity.this, "수정에 실패하였습니다.", Toast.LENGTH_LONG).show();
                                    return;
                                }
                            } catch (Exception e) {
                                Toast.makeText(PlaceDetailActivity.this, "ERROR", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                                Log.d(TAG, e.getMessage());
                            }
                        }
                    };

                    UpdatePlaceScrapRequest updateRequestPlaceScrapRequest = new UpdatePlaceScrapRequest(userID, placeID, placeScrap, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(PlaceDetailActivity.this);
                    queue.add(updateRequestPlaceScrapRequest);
                }
            }
        });

    }

    public void insertPlace() {

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    int exist = jsonObject.getInt("exist");

                    if (success) {
                        Log.d(TAG, "place db 추가");
                        getScrap();

                    } else if (exist != 0) {
                        Log.d(TAG, "place db 이미존재 ");
                        return;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, " insert error");
                    Log.d(TAG, e.getMessage());
                }

            }
        };

        InsertPlaceRequest insertPlaceScrapRequest
                = new InsertPlaceRequest(placeID, pName, pCategory1, pCategory2, pAddr, pExtraInfo, responseListener);
        RequestQueue queue = Volley.newRequestQueue(PlaceDetailActivity.this);
        queue.add(insertPlaceScrapRequest);
    }

    public void getScrap() {

        Log.d(TAG, "in GetScrap");

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if(success) {
                        String pScrap = jsonObject.getString("pScrap");

                        Log.d(TAG, "스크랩 여부: " +pScrap);

                        if (pScrap.equals("Y")) {
                            favPlace.setImageResource(R.drawable.like_icon);
                            isLike = true;
                        } else if (pScrap.equals("N")) {
                            favPlace.setImageResource(R.drawable.unlike_icon);
                            isLike = false;
                        } else { // pScrap == null
                            favPlace.setImageResource(R.drawable.unlike_icon);
                            isLike = false;
                        }

                    } else {
//                        Toast.makeText(PlaceDetailActivity.this, "스크랩 정보를 불러오지 못했습니다.", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "스크랩 불러오기 실패");

                        setScrap(); // placeScrap 테이블에 데이터 추가
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, " get error");
                    Log.d(TAG, e.getMessage());
                }
            }
        };

        GetPlaceScrapRequest placeScrapRequest = new GetPlaceScrapRequest(userID, placeID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(PlaceDetailActivity.this);
        queue.add(placeScrapRequest);
    }

    public void setScrap() {

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if (success) {
                        Log.d(TAG, "스크랩 db 추가");

                    } else {
                        Log.d(TAG, "스크랩 db 추가 실패");
                        return;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, "set error");
                    Log.d(TAG, e.getMessage());
                }

            }
        };

        SetPlaceScrapRequest setPlaceScrapRequest = new SetPlaceScrapRequest(userID, placeID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(PlaceDetailActivity.this);
        queue.add(setPlaceScrapRequest);
    }
}