package com.hanium.android.mydata.ui.favApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hanium.android.mydata.R;

import java.util.List;

public class FavAppActivity extends AppCompatActivity {

    final static String TAG = "FavAppActivity";

    private String packageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_app);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.fav_app_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_T: //cj는 열리는데 t멤버십은 안됨 .. 엥
                packageName = "com.tms";
                openApp(packageName);
                break;
            case R.id.app_LG:
                packageName = "uplus.membership";
                openApp(packageName);
                break;
            case R.id.app_KT:
                packageName = "com.olleh.android.oc";
                openApp(packageName);
                break;
            case R.id.app_CJ:
                packageName = "kr.co.ivlog.mobile.app.cjonecard";
                openApp(packageName);
                break;
            case R.id.app_SB:
                packageName = "com.starbucks.co";
                openApp(packageName);
                break;
            case R.id.app_GC:
                packageName = "com.easymembership.gongcha";
                openApp(packageName);
                break;
            case R.id.chromeBtn:
                packageName = "com.google.android.youtube";
                openApp(packageName);
                break;
        }
    }

    public void openApp(String packageName) {

        if (checkAppExist(packageName)) {
            Log.d(TAG, String.valueOf(checkAppExist(packageName)));
            Intent intent = this.getPackageManager().getLaunchIntentForPackage(packageName);
//                Intent intent = this.getLaunchIntentForPackage(packageName);
            startActivity(intent);
        }
        else {
            Log.d(TAG, "checkAppExist: " +String.valueOf(checkAppExist(packageName))+ " -> open playstore");

            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=" +packageName));
                startActivity(intent);
                Log.d(TAG, "open app");

            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" +packageName));
                startActivity(intent);
                Log.d(TAG, "open browser");
            }
        }

    }

    public boolean checkAppExist(String packageName) {

        Log.d(TAG, "in checkAppExist -> " +packageName);

        boolean exist = false;

        if (packageName == null) {
            return exist;
        }

        PackageManager pm = getPackageManager();
//        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
//        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//
//        List<ResolveInfo> apps = pm.queryIntentActivities(mainIntent, 0);
        List<PackageInfo> apps = pm.getInstalledPackages(0);
        Log.d(TAG, "apps size: " +apps.size());

        try {
            for (int i = 0; i < apps.size(); i++){

//                if (apps.get(i).activityInfo.packageName.startsWith(packageName)) {
                if(apps.get(i).packageName.startsWith(packageName)) {
                    exist = true;
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            exist = false;
        }

        return exist;
    }

}