package com.hanium.android.mydata.ui.search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.widget.SearchView;

import com.hanium.android.mydata.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchActivity extends AppCompatActivity {

    final static String TAG = "SearchActivity";

    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_BRANDID = "brandID";
    private static final String TAG_BRANDNAME = "bName";
    private static final String TAG_BRANDCATEGORY1 = "bCategory1";
    private static final String TAG_BRANDCATEGORY2 = "bCategory2";
    private static final String TAG_BRANDBESTPROD = "bBestProd";
    private static final String TAG_BRANDEXTRAINFO = "bExtraInfo";

    JSONArray brands = null;
    ArrayList<HashMap<String, String>> brandList;

    private SearchView searchview;
    private ListView listView;

    private SearchAdapter searchAdapter;

    private String bID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        searchview = findViewById(R.id.searchBar);
        listView = findViewById(R.id.search_listview);

        brandList = new ArrayList<HashMap<String, String>>();


        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchText) {
                search(searchText);
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(SearchActivity.this, BrandDetailActivity.class);

                intent.putExtra("brandID", brandList.get(position).get(TAG_BRANDID));
                intent.putExtra("bName", brandList.get(position).get(TAG_BRANDNAME));
                intent.putExtra("bCategory1", brandList.get(position).get(TAG_BRANDCATEGORY1));
                intent.putExtra("bCategory2", brandList.get(position).get(TAG_BRANDCATEGORY2));
                intent.putExtra("bBestProd", brandList.get(position).get(TAG_BRANDBESTPROD));
                intent.putExtra("bExtraInfo", brandList.get(position).get(TAG_BRANDEXTRAINFO));

//                Log.d(TAG, "click item ID: " +placeList.get(position).get(TAG_PLACEID));
                startActivity(intent);
            }
        });
    }

    public void search(String searchText) {

        brandList.clear();

        if (searchText.equals("")) {
            brandList.clear();
        } else {
            getData("http://192.168.43.1/PHP_connection_brand.php?searchText=" +searchText);
        }

    }

    protected void showList() {

        try {
            JSONObject jsonObject = new JSONObject(myJSON);
            brands = jsonObject.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < brands.length(); i++) {
                JSONObject jo = brands.getJSONObject(i);
                String brandID = jo.getString(TAG_BRANDID);
                String bName = jo.getString(TAG_BRANDNAME);
                String bCategory1 = jo.getString(TAG_BRANDCATEGORY1);
                String bCategory2 = jo.getString(TAG_BRANDCATEGORY2);
                String bBestProd = jo.getString(TAG_BRANDBESTPROD);
                String bExtraInfo = jo.getString(TAG_BRANDEXTRAINFO);


                HashMap<String, String> brand = new HashMap<String, String>();

                brand.put(TAG_BRANDID, brandID);
                brand.put(TAG_BRANDNAME, bName);
                brand.put(TAG_BRANDCATEGORY1, bCategory1);
                brand.put(TAG_BRANDCATEGORY2, bCategory2);
                brand.put(TAG_BRANDBESTPROD, bBestProd);
                brand.put(TAG_BRANDEXTRAINFO, bExtraInfo);

                Log.d(TAG, "<brand id>: " +brandID+ "  <brand name>: " +bName);

                brandList.add(brand);
            }

            ListAdapter adapter = new SimpleAdapter(
                    SearchActivity.this, brandList, R.layout.adapter_search,
                    new String[]{TAG_BRANDNAME, TAG_BRANDCATEGORY1, TAG_BRANDCATEGORY2},
                    new int[]{R.id.searchBrandName, R.id.searchCategory1, R.id.searchCategory2}
            );

            listView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
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
                    Log.d(TAG, String.valueOf(url));

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    Log.d(TAG, String.valueOf(con));
                    Log.d(TAG, String.valueOf(con.getResponseCode()));

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