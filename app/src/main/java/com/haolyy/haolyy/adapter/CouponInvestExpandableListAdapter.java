package com.haolyy.haolyy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.entity.userinfo.AccountCouponbean;
import com.haolyy.haolyy.entity.userinfo.InvestCouponBean;
import com.haolyy.haolyy.utils.AppToast;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.UIUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CouponInvestExpandableListAdapter extends BaseExpandableListAdapter {

    private InvestCouponBean.ModelBean.DataListBean bean;

    //单元类
    class ExpandableListHolder {
        TextView child;

    }

    //父单元
    class ExpandableGroupHolder {
        TextView coupon_rate;
        TextView coupon_min_money;
        TextView tv_coupon_type;
        TextView tv_coupon_use;
        TextView tv_coupon_use_date;
        ImageView coupon_pic;
        TextView btn_coupon_use;
        TextView rate_small;
        View v_btn;

    }

    List<InvestCouponBean.ModelBean.DataListBean> couponReceiveListBeans;

    private LayoutInflater mGroupInflater; //用于加载group的布局xml
    private LayoutInflater mChildInflater; //用于加载listitem的布局xml
    private OnChangeImgCallBack onChangeImgCallBack;
    private BtnUseCallBack btnUseCallBack;
    private Context mContext;
    private Map<Integer, ImageView> imageViewMap = new HashMap<>();

    public void setOnChangeImgCallBack(OnChangeImgCallBack onChangeImgCallBack) {
        this.onChangeImgCallBack = onChangeImgCallBack;
    }

    public void setUseCallBack(BtnUseCallBack btnUseCallBack) {
        this.btnUseCallBack = btnUseCallBack;
    }

    public interface OnChangeImgCallBack {
        void changeImg(Map<Integer, ImageView> imageViewMap);
    }

    public interface BtnUseCallBack {
        void useCoupon(int pos);
    }

    //自宝义构造
    public CouponInvestExpandableListAdapter(Context context, List<InvestCouponBean.ModelBean.DataListBean> couponReceiveListBeans) {
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
        ExpandableGroupHolder holder = null; //清空临时变量holder
        bean = couponReceiveListBeans.get(groupPosition);
        if (convertView == null) { //判断view（即view是否已构建好）是否为空
            convertView = mGroupInflater.inflate(R.layout.item_expandable_group_coupon1, null);
            holder = new ExpandableGroupHolder();
            holder.coupon_rate = (TextView) convertView.findViewById(R.id.coupon_rate);
            holder.coupon_min_money = (TextView) convertView.findViewById(R.id.coupon_min_money);
            holder.tv_coupon_type = (TextView) convertView.findViewById(R.id.tv_coupon_type);
            holder.tv_coupon_use = (TextView) convertView.findViewById(R.id.tv_coupon_use);
            holder.tv_coupon_use_date = (TextView) convertView.findViewById(R.id.tv_coupon_use_date);
            holder.btn_coupon_use = (TextView) convertView.findViewById(R.id.btn_coupon_use);
            holder.coupon_pic = (ImageView) convertView.findViewById(R.id.coupon_pic);
            holder.rate_small = (TextView) convertView.findViewById(R.id.tv_rate_small);
            holder.v_btn=convertView.findViewById(R.id.v_btn);
            convertView.setTag(holder);
        } else { //若view不为空，直接从view的tag属性中获得各子视图的引用
            holder = (ExpandableGroupHolder) convertView.getTag();
        }
        imageViewMap.put(groupPosition, holder.coupon_pic);
        if (onChangeImgCallBack != null) {
            onChangeImgCallBack.changeImg(imageViewMap);
        }
        holder.v_btn.setOnClickListener(v->{
            if (couponReceiveListBeans.get(groupPosition).getCanUse().equals("0")) {
                LogUtils.e("coupon",bean.getCanUse()+bean.getEffect());
                UIUtils.showToastCommon(mContext,"当前卡券不可用");
            } else {
                btnUseCallBack.useCoupon(groupPosition);
            }
        });
       /* holder.btn_coupon_use.setOnClickListener(v -> {
            if (bean.getCanUse().equals("0")) {
                UIUtils.showToastCommon(mContext,"当前卡券不可用");
            } else {
                btnUseCallBack.useCoupon(groupPosition);
            }
        });*/
        holder.tv_coupon_type.setText(bean.getType() == 1 ? "加息券" : "返现券");
        holder.tv_coupon_use_date.setText(bean.getStartDate() + "-" + bean.getEndDate());
        holder.coupon_rate.setText(bean.getEffect()+"");
        holder.rate_small.setText(bean.getType() == 1 ? "%" : "元");
        holder.tv_coupon_use.setText(bean.getDeliveryRangeProduct());
        notifyDataSetChanged();
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup viewgroup) {
        ExpandableListHolder holder = null;
        if (convertView == null) {
            convertView = mChildInflater.inflate(R.layout.item_expandable_child, null);

            holder = new ExpandableListHolder();
//            holder.nickName = (TextView) convertView.findViewById(R.id.mainChildText1);
//            holder.ioc = (ImageView) convertView.findViewById(R.id.mainChildIcoId);
//            holder.phone = (TextView) convertView.findViewById(R.id.mainChildText2);
            holder.child = (TextView) convertView.findViewById(R.id.tv_child);
            convertView.setTag(holder);
        } else {//若行已初始化，直接从tag属性获得子视图的引用
            holder = (ExpandableListHolder) convertView.getTag();
        }
        holder.child.setText(couponReceiveListBeans.get(groupPosition).getDeliveryRuleDetail());
//        holder.nickName.setText(testEntities.get(groupPosition).getResult().getJuid());
//        holder.ioc.setImageResource(R.drawable.ic_launcher);
//        holder.phone.setText(testEntities.get(groupPosition).getResult().getJuid());
        return convertView;
    }
}