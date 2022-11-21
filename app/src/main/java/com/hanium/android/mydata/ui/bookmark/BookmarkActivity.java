package com.hanium.android.mydata.ui.bookmark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hanium.android.mydata.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class BookmarkActivity extends AppCompatActivity {
    String myJSON;
    JSONArray bookmarks = null;
    ArrayList<HashMap<String, String>> bookmarkList;

    private ListView listView;
//    private BookmarkAdapter bookmarkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        listView = findViewById(R.id.bookmark_listview);

        bookmarkList = new ArrayList<HashMap<String, String>>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            }
        });
    }
}