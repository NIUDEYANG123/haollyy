package com.haolyy.haolyy.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.haolyy.haolyy.MainActivity;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.entity.userinfo.AccountCouponbean;
import com.haolyy.haolyy.utils.AppToast;


public class Coupon1ExpandableListAdapter extends BaseExpandableListAdapter {

    //单元类
    class ExpandableListHolder {
        TextView child;

    }

    //父单元
    class ExpandableGroupHolder {
        TextView coupon_rate;
        TextView tv_coupon_type;
        TextView tv_coupon_use_date;
        ImageView coupon_pic;
        TextView btn_coupon_use;
        TextView rate_small;
    }

    List<AccountCouponbean.ModelBean.CouponReceiveListBean> couponReceiveListBeans;

    private LayoutInflater mGroupInflater; //用于加载group的布局xml
    private LayoutInflater mChildInflater; //用于加载listitem的布局xml
    private OnChangeImgCallBack onChangeImgCallBack;
    private Context mContext;
    private Map<Integer, ImageView> imageViewMap = new HashMap<>();

    public void setOnChangeImgCallBack(OnChangeImgCallBack onChangeImgCallBack) {
        this.onChangeImgCallBack = onChangeImgCallBack;
    }

    public interface OnChangeImgCallBack {
        void changeImg(Map<Integer, ImageView> imageViewMap);
    }


    //自宝义构造
    public Coupon1ExpandableListAdapter(Context context, List<AccountCouponbean.ModelBean.CouponReceiveListBean> couponReceiveListBeans) {
        this.mContext = context;
        this.couponReceiveListBeans = couponReceiveListBeans;

        mChildInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mGroupInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //必须实现 得到子数据
    @Override
    public Object getChild(int groupPosition, int j) {
        return couponReceiveListBeans.get(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int j) {
        return groupPosition;
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return couponReceiveListBeans.get(i);
    }

    @Override
    public int getGroupCount() {
        return couponReceiveListBeans.size();
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {//行是否具有唯一id
        return false;
    }

    @Override
    public boolean isChildSelectable(int i, int j) {//行是否可选
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean flag, View convertView, ViewGroup viewgroup) {
        AccountCouponbean.ModelBean.CouponReceiveListBean couponReceiveListBean = couponReceiveListBeans.get(groupPosition);
        ExpandableGroupHolder holder = null; //清空临时变量holder
        if (convertView == null) { //判断view（即view是否已构建好）是否为空
            convertView = mGroupInflater.inflate(R.layout.item_expandable_group_coupon1, null);
            holder = new ExpandableGroupHolder();
            holder.coupon_rate = (TextView) convertView.findViewById(R.id.coupon_rate);
            holder.tv_coupon_type = (TextView) convertView.findViewById(R.id.tv_coupon_type);
            holder.tv_coupon_use_date = (TextView) convertView.findViewById(R.id.tv_coupon_use_date);
            holder.btn_coupon_use = (TextView) convertView.findViewById(R.id.btn_coupon_use);
            holder.coupon_pic = (ImageView) convertView.findViewById(R.id.coupon_pic);
            holder.rate_small = (TextView) convertView.findViewById(R.id.tv_rate_small);
            convertView.setTag(holder);
        } else { //若view不为空，直接从view的tag属性中获得各子视图的引用
            holder = (ExpandableGroupHolder) convertView.getTag();
        }
        imageViewMap.put(groupPosition, holder.coupon_pic);
        holder.rate_small.setText(couponReceiveListBean.getType().equals("加息券") ? "%" : "元");
        holder.coupon_rate.setText(couponReceiveListBean.getEffect() + "");
        holder.tv_coupon_type.setText(couponReceiveListBean.getType());
        if(couponReceiveListBean.getType().equals("提现券")){
            holder.tv_coupon_use_date.setVisibility(View.INVISIBLE);
        }else {
            String end_date=TextUtils.isEmpty(couponReceiveListBean.getEndDate())?" ":couponReceiveListBean.getEndDate();
            holder.tv_coupon_use_date.setText(couponReceiveListBean.getStartDate() + "~" + end_date);
        }

        holder.btn_coupon_use.setOnClickListener(view -> {
            if (couponReceiveListBean.getType().equals("加息券")) {
                mContext.startActivity(MainActivity.getMainIntent(mContext,1));
            } else if (couponReceiveListBean.getType().equals("返现券")) {
                mContext.startActivity(MainActivity.getMainIntent(mContext,1));
            } else if (couponReceiveListBean.getType().equals("提现券")) {
                mContext.startActivity(MainActivity.getMainIntent(mContext,3));
            }
        });
        if (onChangeImgCallBack != null) {
            onChangeImgCallBack.changeImg(imageViewMap);
        }
        notifyDataSetChanged();
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup viewgroup) {

        AccountCouponbean.ModelBean.CouponReceiveListBean couponReceiveListBean = couponReceiveListBeans.get(groupPosition);
        ExpandableListHolder holder = null;
        if (convertView == null) {
            convertView = mChildInflater.inflate(R.layout.item_expandable_child, null);

            holder = new ExpandableListHolder();
            holder.child = (TextView) convertView.findViewById(R.id.tv_child);
            convertView.setTag(holder);
        } else {//若行已初始化，直接从tag属性获得子视图的引用
            holder = (ExpandableListHolder) convertView.getTag();
        }
        holder.child.setText(couponReceiveListBean.getDeliveryRuleDetail());
        return convertView;
    }
}