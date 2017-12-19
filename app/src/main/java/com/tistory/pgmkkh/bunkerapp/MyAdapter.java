package com.tistory.pgmkkh.bunkerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pgmkkh on 2017-12-20.
 */

public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private int mResource;
    private ArrayList<Safe> mItems = new ArrayList<Safe>();

    public MyAdapter(Context context, int resource, ArrayList<Safe> items) {
        mContext = context;
        mItems = items;
        mResource = resource;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent,false);
        }
        // Set Icon
        // items.xml의 iconItem에 ArrayList의 mIcone 값(이미지)을 할당
        ImageView icon = (ImageView) convertView.findViewById(R.id.iconItem);
        icon.setImageResource(mItems.get(position).mIcon);

        // Set Text 01
        // items.xml의 textItem1에 ArrayList의 nName 값(이름)을 할당
        TextView name = (TextView) convertView.findViewById(R.id.textItem11);
        name.setText(mItems.get(position).nName);

        // Set Text 02
        // items.xml의 textItem2에 ArrayList의 nAge 값(나이)을 할당
        TextView age = (TextView) convertView.findViewById(R.id.textItem22);
        age.setText(mItems.get(position).nhint);

        return convertView;
    }
}

class Safe {
    int mIcon; // image resource
    String nName; // text
    String nhint;  // text
    String nTel;
    String nDns;
    Safe(int aIcon, String aName, String aHint,String aTel, String aDns) {
        mIcon = aIcon;
        nName = aName;
        nhint = aHint;
        nTel = aTel;
        nDns = aDns;
    }
}