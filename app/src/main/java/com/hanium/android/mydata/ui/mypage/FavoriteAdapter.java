package com.hanium.android.mydata.ui.mypage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hanium.android.mydata.ui.benefit.BenefitModel;

import java.util.ArrayList;

public class FavoriteAdapter extends BaseAdapter {
    private Manager mgr = Manager.getInstance();

    ArrayList<BenefitModel> benefitList=mgr.getBenefitData();

    @Override
    public int getCount() {
        return benefitList.size();
    }
    public void remove(int position){
        benefitList.remove(position);
    }
    @Override
    public Object getItem(int i) {
        return benefitList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
