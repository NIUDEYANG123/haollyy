package com.haolyy.haolyy.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wangyin on 2017/5/25.
 */

public class WyBasePagerAdapter extends PagerAdapter {

    public List list;
    public LayoutInflater inflate;
    public Context mContext;
    public WyBasePagerAdapter(List list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        inflate = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
