package com.hanium.android.mydata.ui.favPlace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.hanium.android.mydata.R;
import com.hanium.android.mydata.SharedPreference;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class FavPlaceActivity extends AppCompatActivity {

    final static String TAG = "FavPlaceActivity";

    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_USERID = "userID";
    private static final String TAG_PLACEID = "placeID";
    private static final String TAG_PLACESCRAP = "pScrap";
    private static final String TAG_PLACENAME = "pName";
    private static final String TAG_PLACECATEGORY1 = "pCategory1";
    private static final String TAG_PLACECATEGORY2 = "pCategory2";
    private static final String TAG_PLACEBESTPROD = "pBestProd";
    private static final String TAG_PLACEEXTRAINFO = "pExtraInfo";

    JSONArray favPlaces = null;
    ArrayList<HashMap<String, String>> favPlaceList;

    private ListView listView = null;

    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_place);

        listView = findViewById(R.id.favPlace_listview);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.fav_place_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        favPlaceList = new ArrayList<HashMap<String, String>>();

        userID = SharedPreference.getUserID(FavPlaceActivity.this);

        getData("http://192.168.43.1/PHP_connection_placeScrap.php?userID=" +userID);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            }
        });
    }

    protected void showList() {

        try {
            Log.d(TAG, myJSON);
            JSONObject jsonObject = new JSONObject(myJSON);
            favPlaces = jsonObject.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < favPlaces.length(); i++) {
                JSONObject jo = favPlaces.getJSONObject(i);
                String userID = jo.getString(TAG_USERID);
                String placeID = jo.getString(TAG_PLACEID);
                String placeScrap = jo.getString(TAG_PLACESCRAP);
                String pName = jo.getString(TAG_PLACENAME);
                String pCategory1 = jo.getString(TAG_PLACECATEGORY1);
                String pCategory2 = jo.getString(TAG_PLACECATEGORY2);
                String pBestProd = jo.getString(TAG_PLACEBESTPROD);
                String pExtraInfo = jo.getString(TAG_PLACEEXTRAINFO);

                HashMap<String, String> favPlace = new HashMap<String, String>();

                favPlace.put(TAG_USERID, userID);
                favPlace.put(TAG_PLACEID, placeID);
                favPlace.put(TAG_PLACESCRAP, placeScrap);
                favPlace.put(TAG_PLACENAME, pName);
                favPlace.put(TAG_PLACECATEGORY1, pCategory1);
                favPlace.put(TAG_PLACECATEGORY2, pCategory2);
                favPlace.put(TAG_PLACEBESTPROD, pBestProd);
                favPlace.put(TAG_PLACEEXTRAINFO, pExtraInfo);

                Log.d(TAG, "<userID>: " +userID+ "  <brandID>: " +placeID+ "  <scrap>: " +placeScrap);

                favPlaceList.add(favPlace);
            }

            ListAdapter adapter = new SimpleAdapter(
                    FavPlaceActivity.this, favPlaceList, R.layout.adapter_fav_brand,
                    new String[]{TAG_PLACENAME, TAG_PLACECATEGORY1, TAG_PLACECATEGORY2},
                    new int[]{R.id.favBrandName, R.id.favBrandCategory1, R.id.favBrandCategory2}
            );

            listView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage());
        }
    }


    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    Log.d(TAG, String.valueOf(con));

                    bufferedReader = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }

            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}