package com.hanium.android.mydata.ui.favBrand;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanium.android.mydata.R;

public class FavBrandAdapter extends CursorAdapter {

    LayoutInflater inflater;
    int layout;

    public FavBrandAdapter(Context context, int layout, Cursor c) {
        super(context, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        View view = inflater.inflate(layout,viewGroup,false);
        FavBrandAdapter.ViewHolder holder = new FavBrandAdapter.ViewHolder();
        view.setTag(holder);
        return view;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        FavBrandAdapter.ViewHolder holder = (FavBrandAdapter.ViewHolder) view.getTag();

        if (holder.tvName == null) {
            holder.tvCategory1 = view.findViewById(R.id.favBrandCategory1);
            holder.tvCategory2 = view.findViewById(R.id.favBrandCategory2);
            holder.tvName = view.findViewById(R.id.favBrandName);
            holder.tvForm = view.findViewById(R.id.favBrandForm);
            holder.ivImage = view.findViewById(R.id.favBrandImage);
        }

        holder.tvCategory1.setText("카테고리1");
        holder.tvCategory2.setText("카테고리2");
        holder.tvName.setText("장소이름");
        holder.tvForm.setText("할인 / 적립 / etc");
    }

    static class ViewHolder {

        public ViewHolder() {
            tvCategory1 = null;
            tvCategory2 = null;
            tvName = null;
            tvForm = null;
            ivImage = null;
        }

        TextView tvCategory1;
        TextView tvCategory2;
        TextView tvName;
        TextView tvForm;
        ImageView ivImage;

    }
}

