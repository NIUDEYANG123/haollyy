package com.haolyy.haolyy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by niudeyang on 2017/8/13.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private List<Fragment> mDatas;
    private List<String> titles;
    public TabAdapter(FragmentManager fm, List<Fragment> mDatas, List<String> titles) {
        super(fm);
        this.mDatas = mDatas;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }
    //配置标题的方法
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}