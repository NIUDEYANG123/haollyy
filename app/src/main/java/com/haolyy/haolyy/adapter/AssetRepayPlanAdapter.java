package com.haolyy.haolyy.adapter;

import android.graphics.Color;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.entity.userinfo.AssetRepayPLanBean;
import com.haolyy.haolyy.utils.WYUtils;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class AssetRepayPlanAdapter extends BaseQuickAdapter<AssetRepayPLanBean.ModelBean.BorrowReserveBean, BaseViewHolder> {

    public AssetRepayPlanAdapter(List<AssetRepayPLanBean.ModelBean.BorrowReserveBean> list) {
        super(R.layout.item_asset_repay_plan, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, AssetRepayPLanBean.ModelBean.BorrowReserveBean item) {
        if(mData.size()>1){
            //1:待回款 2:已回款 3:回款中 4:回款失败
            if(item.getStatus()==1){
                helper.setBackgroundRes(R.id.view_root_repay,R.drawable.bg_repay_normal);
                helper.setTextColor(R.id.tv_repay_status, Color.parseColor("#FE7537"));
            }else {
                helper.setBackgroundRes(R.id.view_root_repay,R.drawable.bg_repay_have);
                helper.setTextColor(R.id.tv_repay_status, Color.parseColor("#9b9b9b"));
            }
            helper.setText(R.id.tv_repay_status, WYUtils.getRepayStatus(item.getStatus())+"("+item.getCurStageNo()+")");
            helper.setText(R.id.tv_repay_time,item.getBillDate().substring(0,10));
            helper.setText(R.id.tv_repay_corpus,item.getReserveCorpus()+"元");
            helper.setText(R.id.tv_repay_interest,item.getReserveInterest()+"元");
        }else {
            helper.setVisible(R.id.view_root_repay,false);
        }
    }

}
