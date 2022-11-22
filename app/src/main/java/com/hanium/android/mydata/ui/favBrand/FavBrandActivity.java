package com.hanium.android.mydata.ui.favBrand;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hanium.android.mydata.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class FavBrandActivity extends AppCompatActivity {

    String myJSON;
    JSONArray favBrands = null;
    ArrayList<HashMap<String, String>> favBrandList;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_brand);

        listView = findViewById(R.id.favBrand_listview);

        favBrandList = new ArrayList<HashMap<String, String>>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            }
        });
    }
}