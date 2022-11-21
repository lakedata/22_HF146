package com.hanium.android.mydata.ui.mypage;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hanium.android.mydata.R;
import com.hanium.android.mydata.ui.benefit.BenefitModel;

public class FavoriteActivity extends AppCompatActivity {
    private static final int BOOKMARK = 101;
    private Manager mgr = Manager.getInstance();
    private FavoriteAdapter adapter;
    private ListView listview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        listview2=(ListView)findViewById(R.id.listView);

        adapter=new FavoriteAdapter();
        listview2.setAdapter(adapter);

        registerForContextMenu(listview2); //context메뉴 쓰기 위해서는 등록
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE,BOOKMARK,Menu.NONE,"즐겨찾기삭제");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==BOOKMARK){
            AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

            BenefitModel benefit=(BenefitModel)adapter.getItem(info.position);

            adapter.remove(info.position);

            Toast.makeText(this, "즐겨찾기 해제되었습니다", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
