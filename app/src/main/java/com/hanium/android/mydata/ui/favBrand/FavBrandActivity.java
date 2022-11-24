package com.hanium.android.mydata.ui.favBrand;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
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
import com.hanium.android.mydata.XAMPPTestActivity;
import com.hanium.android.mydata.ui.search.BrandDetailActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class FavBrandActivity extends AppCompatActivity {

    final static String TAG = "FavBrandActivity";
    // bCategory1, bCategory2, bBestPord, bIMG, bExtraInfo
    String myJSON;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_USERID = "userID";
    private static final String TAG_BRANDID = "brandID";
    private static final String TAG_BRANDSCRAP = "bScrap";
    private static final String TAG_BRANDNAME = "bName";
    private static final String TAG_BRANDCATEGORY1 = "bCategory1";
    private static final String TAG_BRANDCATEGORY2 = "bCategory2";
    private static final String TAG_BRANDBESTPROD = "bBestProd";
    private static final String TAG_BRANDEXTRAINFO = "bExtraInfo";



    JSONArray favBrands = null;
    ArrayList<HashMap<String, String>> favBrandList;

    private ListView listView = null;

    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_brand);

        listView = findViewById(R.id.favBrand_listview);

        favBrandList = new ArrayList<HashMap<String, String>>();

        userID = SharedPreference.getUserID(FavBrandActivity.this);
        Log.d(TAG, userID);


        getData("http://192.168.43.1/PHP_connection_brandScrap.php?userID=" +userID);

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
            favBrands = jsonObject.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < favBrands.length(); i++) {
                JSONObject jo = favBrands.getJSONObject(i);
                String userID = jo.getString(TAG_USERID);
                String brandID = jo.getString(TAG_BRANDID);
                String brandScrap = jo.getString(TAG_BRANDSCRAP);
                String bName = jo.getString(TAG_BRANDNAME);
                String bCategory1 = jo.getString(TAG_BRANDCATEGORY1);
                String bCategory2 = jo.getString(TAG_BRANDCATEGORY2);
                String bBestProd = jo.getString(TAG_BRANDBESTPROD);
                String bExtraInfo = jo.getString(TAG_BRANDEXTRAINFO);

                HashMap<String, String> favBrand = new HashMap<String, String>();

                favBrand.put(TAG_USERID, userID);
                favBrand.put(TAG_BRANDID, brandID);
                favBrand.put(TAG_BRANDSCRAP, brandScrap);
                favBrand.put(TAG_BRANDNAME, bName);
                favBrand.put(TAG_BRANDCATEGORY1, bCategory1);
                favBrand.put(TAG_BRANDCATEGORY2, bCategory2);
                favBrand.put(TAG_BRANDBESTPROD, bBestProd);
                favBrand.put(TAG_BRANDEXTRAINFO, bExtraInfo);

                Log.d(TAG, "<userID>: " +userID+ "  <brandID>: " +brandID+ "  <scrap>: " +brandScrap);

                favBrandList.add(favBrand);
            }

            ListAdapter adapter = new SimpleAdapter(
                    FavBrandActivity.this, favBrandList, R.layout.adapter_fav_brand,
                    new String[]{TAG_BRANDNAME, TAG_BRANDCATEGORY1, TAG_BRANDCATEGORY2},
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