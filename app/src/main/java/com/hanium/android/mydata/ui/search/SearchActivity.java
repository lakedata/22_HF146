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
import com.hanium.android.mydata.ui.benefit.BenefiltDetailActivity;
import com.hanium.android.mydata.ui.benefit.BenefitActivity;

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
    private static final String TAG_PLACENAME = "pName";
    private static final String TAG_PLACECATEGORY = "pCategory";

    JSONArray places = null;
    ArrayList<HashMap<String, String>> placeList;

    private SearchView searchview;
    private ListView listView;

    private SearchAdapter searchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        searchview = findViewById(R.id.searchBar);
        listView = findViewById(R.id.search_listview);

        placeList = new ArrayList<HashMap<String, String>>();

//        getData("http://192.168.43.1/PHP_connection_place.php");


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
                Intent intent = new Intent(SearchActivity.this, BenefiltDetailActivity.class);
                // Send the position number to Detail Activity too.
                intent.putExtra("position", position);
                // Run the process
                startActivity(intent);
            }
        });
    }

    public void search(String searchText) {

        placeList.clear();

        if (searchText.equals("")) {
            placeList.clear();
        } else {
            getData("http://192.168.43.1/PHP_connection_place.php?searchText=" +searchText);
        }

    }

    protected void showList() {

        try {
            JSONObject jsonObject = new JSONObject(myJSON);
            places = jsonObject.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < places.length(); i++) {
                JSONObject jo = places.getJSONObject(i);
                String placeName = jo.getString(TAG_PLACENAME);
                String placeCg = jo.getString(TAG_PLACECATEGORY);

                HashMap<String, String> place = new HashMap<String, String>();

                place.put(TAG_PLACENAME, placeName);
                place.put(TAG_PLACECATEGORY, placeCg);

                Log.d(TAG, "<place Name>: " +placeName+ "  <place Category>: " +placeCg);

                placeList.add(place);
            }

            ListAdapter adapter = new SimpleAdapter(
                    SearchActivity.this, placeList, R.layout.adapter_search,
                    new String[]{TAG_PLACENAME, TAG_PLACECATEGORY},
                    new int[]{R.id.searchBrandName, R.id.searchCategory}
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