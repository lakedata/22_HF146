package com.hanium.android.mydata;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BenefitAdapter extends CursorAdapter {
    LayoutInflater inflater;
    int layout;

    public BenefitAdapter(Context context, int layout, Cursor c) {
        super(context, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = inflater.inflate(layout,viewGroup,false);
        ViewHolder holder = new ViewHolder();
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();

        if (holder.tvName == null) {
            holder.tvCategory = view.findViewById(R.id.benefitCategory);
            holder.tvName = view.findViewById(R.id.benefitBrandName);
            holder.tvForm = view.findViewById(R.id.benefitForm);
            holder.ivImage = view.findViewById(R.id.benefitBrandImage);
        }



//        tvName.setText(cursor.getString(1));
        holder.tvCategory.setText("카페");
        holder.tvName.setText("스타벅스");
        holder.tvForm.setText("할인");
//        holder.ivBrandImage.setImageBitmap(cursor.getBlob());;
    }

    //view holder
    static class ViewHolder {
        //명시적으로 선언
        public ViewHolder() {
            tvCategory = null;
            tvName = null;
            tvForm = null;
            ivImage = null;
        }

        TextView tvCategory;
        TextView tvName;
        TextView tvForm;
        ImageView ivImage;

    }
}
