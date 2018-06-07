package com.haolyy.haolyy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by wangyin on 2017/5/22.
 */

public class WyBaseAdapter extends BaseAdapter {
    public List list;
    public LayoutInflater inflater;
    protected Context mContext;

    public WyBaseAdapter(List list, Context context) {
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.mContext=context;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
