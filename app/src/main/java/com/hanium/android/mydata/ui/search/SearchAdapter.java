package com.hanium.android.mydata.ui.search;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanium.android.mydata.R;

public class SearchAdapter extends CursorAdapter {

    LayoutInflater inflater;
    int layout;

    public SearchAdapter(Context context, int layout, Cursor c) {

        super(context, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        View view = inflater.inflate(layout,viewGroup,false);
        SearchAdapter.ViewHolder holder = new SearchAdapter.ViewHolder();
        view.setTag(holder);
        return view;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        SearchAdapter.ViewHolder holder = (SearchAdapter.ViewHolder) view.getTag();

        if (holder.tvName == null) {
            holder.tvCategory = view.findViewById(R.id.searchCategory);
            holder.tvName = view.findViewById(R.id.searchBrandName);
            holder.tvForm = view.findViewById(R.id.searchForm);
            holder.ivImage = view.findViewById(R.id.searchBrandImage);
        }

        holder.tvCategory.setText("카테고리");
        holder.tvName.setText("장소이름");
        holder.tvForm.setText("할인 / 적립 / etc");

    }

    static class ViewHolder {

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
