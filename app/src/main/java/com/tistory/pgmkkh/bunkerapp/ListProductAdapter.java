package com.tistory.pgmkkh.bunkerapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kyungho on 2017-12-06.
 */

public class ListProductAdapter extends BaseAdapter {
    private Context mContext;
    private List<Product> mProductList;

    public ListProductAdapter(Context mContext, List<Product> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }
    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mProductList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.item_listview, null);
        TextView Name = (TextView)v.findViewById(R.id.aName);
        TextView Loca = (TextView)v.findViewById(R.id.Loc);
        TextView Phonenum = (TextView)v.findViewById(R.id.Phone);
        Name.setText(mProductList.get(position).getaName());
        Loca.setText(mProductList.get(position).getLoc());
        Phonenum.setText(mProductList.get(position).getPhone());
        return v;
    }
}
