package com.haolyy.haolyy.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.entity.my.AssetHoldListBean;
import com.haolyy.haolyy.swipe.ListBaseAdapter;
import com.haolyy.haolyy.swipe.SuperViewHolder;
import com.haolyy.haolyy.utils.AccountUtil;
import com.haolyy.haolyy.utils.CommonUtils;
import com.haolyy.haolyy.utils.WYUtils;


/**
 * Created by niudeyang on 2017/12/27.
 */

public class ProductManageSanAdapter extends ListBaseAdapter<AssetHoldListBean.ModelBean.ListInfoBean> {
    private ContinueListener continueListener;
    private String typeS;
    public ProductManageSanAdapter(Context context, String type) {
        super(context);
        this.typeS = type;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_asset_san;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        AssetHoldListBean.ModelBean.ListInfoBean listInfoBean = mDataList.get(position);

        holder.<TextView>getView(R.id.tv_asset_title).setText(listInfoBean.getBorrowName());
        holder.<TextView>getView(R.id.tv_asset_amount).setText(AccountUtil.StringToMString(listInfoBean.getInvestAmount()));

        holder.<TextView>getView(R.id.tv_asset_rate).setText(WYUtils.getSingleNumStr(Double.parseDouble(listInfoBean.getAnnualizedRate()))+"%+"
                +WYUtils.getSingleNumStr(listInfoBean.getAppendRate()+listInfoBean.getCouponRate())+"%");
        //holder.<TextView>getView(R.id.tv_asset_terms).setText(listInfoBean.getPeriod());

        if ("1".equals(typeS)){
            holder.<TextView>getView(R.id.tv_asset_terms).setText(listInfoBean.getPeriod());
            holder.<TextView>getView(R.id.tv_asset_pact).setVisibility(View.GONE);
        }else {
            holder.<TextView>getView(R.id.tv3).setText("已获收益(元)");
            holder.<TextView>getView(R.id.tv_asset_terms).setText(listInfoBean.getYesProfit());
            if (!TextUtils.isEmpty(listInfoBean.getApplyNo()) || !TextUtils.isEmpty(listInfoBean.getOrderNo())){
                holder.<TextView>getView(R.id.tv_asset_pact).setVisibility(View.VISIBLE);
                holder.<TextView>getView(R.id.tv_asset_pact).setText(listInfoBean.getInterestEndDate());
            }else {
                holder.<TextView>getView(R.id.tv_asset_pact).setVisibility(View.GONE);
            }
        }

    }

    public  interface ContinueListener{
        void continueInvest(String no, String str);
    }

    public void setContinueListener(ContinueListener mContinueListener){
        this.continueListener=mContinueListener;
    }
}
