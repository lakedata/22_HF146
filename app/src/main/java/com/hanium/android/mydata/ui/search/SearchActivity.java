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
    private static final String TAG_BRANDEXTRAINFO = "bExtraInfo";

    private static final String TAG_B_EVENTID = "b_eventID";
    private static final String TAG_B_DISCNTTYPE1 = "b_discntType1";
    private static final String TAG_B_DISCNTTYPE2 = "b_discntType2";
    private static final String TAG_B_DISCNTRATE = "b_discntRate";
    private static final String TAG_B_DISCNTINFO = "b_discntInfo";


    JSONArray brands = null;
    ArrayList<HashMap<String, String>> brandList;

    private SearchView searchview;
    private ListView listView;

    private SearchAdapter searchAdapter;

//    private String bID;


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
                intent.putExtra("bExtraInfo", brandList.get(position).get(TAG_BRANDEXTRAINFO));

                intent.putExtra("b_eventID", brandList.get(position).get(TAG_B_EVENTID));
                intent.putExtra("discntType1", brandList.get(position).get(TAG_B_DISCNTTYPE1));
                intent.putExtra("discntType2", brandList.get(position).get(TAG_B_DISCNTTYPE2));
                intent.putExtra("discntRate", brandList.get(position).get(TAG_B_DISCNTRATE));
                intent.putExtra("discntInfo", brandList.get(position).get(TAG_B_DISCNTINFO));


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
                String bExtraInfo = jo.getString(TAG_BRANDEXTRAINFO);

                String b_eventID = jo.getString(TAG_B_EVENTID);
                String bDiscntType1 = jo.getString(TAG_B_DISCNTTYPE1);
                String bDiscntType2 = jo.getString(TAG_B_DISCNTTYPE2);
                String bDiscntRate = jo.getString(TAG_B_DISCNTRATE);
                String bDiscntInfo = jo.getString(TAG_B_DISCNTINFO);


                HashMap<String, String> brand = new HashMap<String, String>();

                brand.put(TAG_BRANDID, brandID);
                brand.put(TAG_BRANDNAME, bName);
                brand.put(TAG_BRANDCATEGORY1, bCategory1);
                brand.put(TAG_BRANDCATEGORY2, bCategory2);
                brand.put(TAG_BRANDEXTRAINFO, bExtraInfo);

                brand.put(TAG_B_EVENTID, b_eventID);
                brand.put(TAG_B_DISCNTTYPE1, bDiscntType1);
                brand.put(TAG_B_DISCNTTYPE2, bDiscntType2);
                brand.put(TAG_B_DISCNTRATE, bDiscntRate);
                brand.put(TAG_B_DISCNTINFO, bDiscntInfo);

                Log.d(TAG, "<brand id>: " +brandID+ "  <eventID>: " +b_eventID);

                brandList.add(brand);
            }

            ListAdapter adapter = new SimpleAdapter(
                    SearchActivity.this, brandList, R.layout.adapter_search,
                    new String[]{TAG_BRANDNAME, TAG_BRANDCATEGORY1, TAG_BRANDCATEGORY2, TAG_B_DISCNTTYPE1},
                    new int[]{R.id.searchBrandName, R.id.searchCategory1, R.id.searchCategory2, R.id.searchForm}
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