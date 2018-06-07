package com.haolyy.haolyy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.haolyy.haolyy.R;
import com.haolyy.haolyy.entity.my.GridInfo;
import com.haolyy.haolyy.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liliang on 2017/9/21.
 */

public class GridViewAdapter extends BaseAdapter{

    private List<GridInfo> mList = new ArrayList<>();
    private Context mContext;
    public GridViewAdapter(Context context, List<GridInfo> list){
        this.mContext = context;
        this.mList = list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public GridInfo getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChildHolderOne holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid_live_show, parent, false);
            holder = new ChildHolderOne();
            holder.tvTitle = (TextView)convertView.findViewById(R.id.title_tv);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolderOne) convertView.getTag();
        }
        final GridInfo gridInfo = mList.get(position);
        if(gridInfo.isSelect() == true){
            holder.tvTitle.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.tvTitle.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.shape_orange_radius5));
        } else {
            holder.tvTitle.setTextColor(mContext.getResources().getColor(R.color.text_4a4a4a));
            holder.tvTitle.setBackgroundColor(UIUtils.getColor(R.color.white));
        }
        final String number = gridInfo.getTitle();
        holder.tvTitle.setText(number);
        return convertView;
    }

    class ChildHolderOne {
        TextView tvTitle;
    }
}
