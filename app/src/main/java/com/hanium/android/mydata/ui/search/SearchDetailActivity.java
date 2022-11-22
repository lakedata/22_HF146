package com.hanium.android.mydata.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.hanium.android.mydata.R;
import com.hanium.android.mydata.ui.benefit.BenefiltDetailActivity;

public class SearchDetailActivity extends AppCompatActivity {

    private TextView content, category1, category2, benefit, extraInfo;
    private ImageView favBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.benefit_detail_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        String brandID = intent.getStringExtra("brandID");
        String bName = intent.getStringExtra("bName");
        String bCategory1 = intent.getStringExtra("bCategory1");
        String bCategory2 = intent.getStringExtra("bCategory2");
        String bBestProd = intent.getStringExtra("bBestProd");
        String bExtraInfo = intent.getStringExtra("bExtraInfo");

        content = findViewById(R.id.search_content);
        category1 = findViewById(R.id.search_categoty1);
        category2 = findViewById(R.id.search_categoty2);
        benefit = findViewById(R.id.search_benefit);
        extraInfo = findViewById(R.id.search_extraInfo);

        favBrand = findViewById(R.id.search_like);

        content.setText(bName);
        category1.setText(bCategory1);
        category2.setText(bCategory2);
        extraInfo.setText(bExtraInfo);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_like:
                Toast.makeText(SearchDetailActivity.this, "즐겨찾기 등록", Toast.LENGTH_SHORT).show();
                favBrand.setImageResource(R.drawable.like_icon);
                break;
        }
    }

}
