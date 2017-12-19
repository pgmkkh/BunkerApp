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

public class VideoAdapter extends BaseAdapter {

    private Context mContext;
    private int mResource;
    private ArrayList<IVideo> mItems = new ArrayList<IVideo>();

    public VideoAdapter(Context context, int resource, ArrayList<IVideo> items) {

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
    //자동 호출 함수
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent,false);
        }

        ImageView icon = (ImageView) convertView.findViewById(R.id.image1);
        icon.setImageResource(mItems.get(position).mIcon1);

        TextView name = (TextView) convertView.findViewById(R.id.title1);
        name.setText(mItems.get(position).nName1);

        TextView age = (TextView) convertView.findViewById(R.id.source1);
        age.setText(mItems.get(position).nHint1);

        return convertView;
    }
}

class IVideo {
    int mIcon1; // image resource
    String nName1; // text
    String nHint1;  // text
    String nDns1;

    IVideo(int aIcon, String aName, String aHintm , String aDns) {
        mIcon1 = aIcon;
        nName1 = aName;
        nHint1 = aHintm;
        nDns1 = aDns;
    }
}

