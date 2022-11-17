package com.hanium.android.mydata;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.hanium.android.mydata.databinding.ActivityMain2Binding;
import com.hanium.android.mydata.ui.benefit.BenefitActivity;
import com.hanium.android.mydata.ui.bookmark.BookmarkActivity;
import com.hanium.android.mydata.ui.favApp.FavAppActivity;
import com.hanium.android.mydata.ui.favBrand.FavBrandActivity;
import com.hanium.android.mydata.ui.map.MapActivity;
import com.hanium.android.mydata.ui.mypage.MyPageActivity;
import com.hanium.android.mydata.ui.search.SearchActivity;
import com.hanium.android.mydata.ui.setting.SettingActivity;

public class MainActivity2 extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMain2Binding binding;

    final static String TAG = "MainActivity2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int i = 0;
        Log.d(TAG, "in Main" +(i++));

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.toolbar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        Menu menu = navigationView.getMenu();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_benefit, R.id.nav_map, R.id.nav_bookmark, R.id.nav_favBrand, R.id.nav_favApp,
                R.id.nav_mypage, R.id.nav_setting)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View nav_header_view = navigationView.getHeaderView(0);
        TextView nav_header_userName = (TextView) nav_header_view.findViewById(R.id.nav_header_userName);
        TextView nav_header_point = (TextView) nav_header_view.findViewById(R.id.nav_header_point);


        Intent loginIntent = getIntent();
        String userID = loginIntent.getStringExtra("userID");
        String userName = loginIntent.getStringExtra("userName");
        String userPW = loginIntent.getStringExtra("userPW");
        int point = loginIntent.getIntExtra("point", 0);


        if (userID == null) {
            menu.findItem(R.id.nav_login).setVisible(true);
            menu.findItem(R.id.nav_join).setVisible(true);
            menu.findItem(R.id.nav_logout).setVisible(false);

            nav_header_userName.setText("로그인이 필요합니다");
            nav_header_point.setText("");
        } else {
            Log.d(TAG, "in MainActivity2 userID: " +userID+ "userName: " +userName);

            menu.findItem(R.id.nav_login).setVisible(false);
            menu.findItem(R.id.nav_join).setVisible(false);

            nav_header_userName.setText(userName+ "님");
            nav_header_point.setText(point+ "점 ");
        }


        // 좌측메뉴클릭
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_login:
                        Intent toLoginIntent = new Intent(MainActivity2.this, LoginActivity.class);
                        startActivity(toLoginIntent);
                        break;
                    case R.id.nav_join:
                        Intent toJoinIntent = new Intent(MainActivity2.this, JoinActivity.class);
                        startActivity(toJoinIntent);
                        break;
                    case R.id.nav_benefit:
                        Intent benefitIntent = new Intent(MainActivity2.this, BenefitActivity.class);
                        benefitIntent.putExtra("userID", userID);
                        startActivity(benefitIntent);
                        break;
                    case R.id.nav_map:
                        Intent mapIntent = new Intent(MainActivity2.this, MapActivity.class);
                        mapIntent.putExtra("userID", userID);
                        startActivity(mapIntent);
                        break;
                    case R.id.nav_bookmark:
                        Intent bookmarkIntent = new Intent(MainActivity2.this, BookmarkActivity.class);
                        bookmarkIntent.putExtra("userID", userID);
                        startActivity(bookmarkIntent);
                        break;
                    case R.id.nav_favBrand:
                        Intent favBIntent = new Intent(MainActivity2.this, FavBrandActivity.class);
                        favBIntent.putExtra("userID", userID);
                        startActivity(favBIntent);
                        break;
                    case R.id.nav_favApp:
                        Intent favAIntent = new Intent(MainActivity2.this, FavAppActivity.class);
                        favAIntent.putExtra("userID", userID);
                        startActivity(favAIntent);
                        break;
                    case R.id.nav_mypage:
                        Intent mypageIntent = new Intent(MainActivity2.this, MyPageActivity.class);
                        mypageIntent.putExtra("userID", userID);
                        mypageIntent.putExtra("userName", userName);
                        mypageIntent.putExtra("userPW", userPW);
                        startActivity(mypageIntent);
                        break;
                    case R.id.nav_setting:
                        Intent settingIntent = new Intent(MainActivity2.this, SettingActivity.class);
                        settingIntent.putExtra("userID", userID);
                        startActivity(settingIntent);
                        break;
                    case R.id.nav_logout:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                        builder.setMessage("로그아웃하시겠습니까?")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(MainActivity2.this, "로그아웃 되었습니다", Toast.LENGTH_LONG).show();
                                        drawer.close();

                                        loginIntent.removeExtra("userID");
                                        loginIntent.removeExtra("userName");
                                        loginIntent.removeExtra("point");
//                                        Log.d(TAG, "in Main" +(i++)+ "  " +loginIntent.getStringExtra("userName"));
                                        startActivity(loginIntent);
                                        return;
                                    }
                                })
                                .setNegativeButton("취소", null)
                                .show();
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Intent intent = new Intent(MainActivity2.this, SearchActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}