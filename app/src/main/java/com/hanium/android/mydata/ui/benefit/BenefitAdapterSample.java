package com.hanium.android.mydata.ui.benefit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanium.android.mydata.R;

import java.util.List;

public class BenefitAdapterSample extends BaseAdapter {

    private final List mData;

    public BenefitAdapterSample(List mData) {
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_benefit, viewGroup, false);

        ImageView image = view.findViewById(R.id.benefitBrandImage);
        TextView name = view.findViewById(R.id.benefitBrandName);
        TextView category = view.findViewById(R.id.benefitCategory);
        TextView kind = view.findViewById(R.id.benefitForm);

        BenefitModel model = (BenefitModel) mData.get(i);
        name.setText(model.getBrandName());
        category.setText(model.getCategory());
        kind.setText(model.getBenefitKind());

        return view;
    }
}
