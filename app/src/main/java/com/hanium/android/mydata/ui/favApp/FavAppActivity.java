package com.hanium.android.mydata.ui.favApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hanium.android.mydata.MainActivity2;
import com.hanium.android.mydata.R;

import java.util.List;

public class FavAppActivity extends AppCompatActivity {

    final static String TAG = "FavAppActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_app);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chromeBtn:
//                String packageName = "com.android.chrome";
                String packageName = "com.instagram.android";

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
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" +packageName));
                        startActivity(intent);
                        Log.d(TAG, "open browser");
                    }
                }

                break;
        }
    }

    public boolean checkAppExist(String packageName) {

        boolean exist = false;

        if (packageName == null) {
            return exist;
        }

        PackageManager pm = getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> apps = pm.queryIntentActivities(mainIntent, 0);

        try {
            for (int i = 0; i < apps.size(); i++){
                if (apps.get(i).activityInfo.packageName.startsWith(packageName)) {
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