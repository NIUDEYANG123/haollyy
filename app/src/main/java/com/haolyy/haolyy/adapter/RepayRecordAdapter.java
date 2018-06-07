package com.haolyy.haolyy.adapter;

import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.entity.product.BorrowPlanListBean;
import com.haolyy.haolyy.entity.product.RepayPlanBean;
import com.haolyy.haolyy.entity.product.SanRepayPlanBean;
import com.haolyy.haolyy.swipe.ListBaseAdapter;
import com.haolyy.haolyy.swipe.SuperViewHolder;
import com.haolyy.haolyy.utils.AccountUtil;

import java.util.List;

import static com.haolyy.haolyy.entity.product.SanRepayPlanBean.*;


/**
 * Created by niudeyang on 2017/12/27.
 */

public class RepayRecordAdapter extends BaseQuickAdapter<RepayPlanBean.ModelBean.ResultBean, BaseViewHolder> {

    public RepayRecordAdapter(List<RepayPlanBean.ModelBean.ResultBean> list) {
        super(R.layout.item_reapy_record, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, RepayPlanBean.ModelBean.ResultBean item) {
        helper.setText(R.id.tv_term, helper.getLayoutPosition() + 1 + "期");
        helper.setText(R.id.tv_show_pay, AccountUtil.DoubleToString(item.getShouldAmount()));
        helper.setText(R.id.tv_show_pay_interest, AccountUtil.DoubleToString(item.getShouldInterest()));
        helper.setText(R.id.tv_remain, AccountUtil.DoubleToString(item.getShouldReturn()));
        //helper.setVisible(R.id.view_root_repay, false);
    }

        /*@Override
        protected void convert(BaseViewHolder helper, ModelBean.DetailBean item) {
        helper.setText(R.id.tv_term,helper.getLayoutPosition()+1+"期");
        helper.setText(R.id.tv_show_pay, AccountUtil.DoubleToString(item.getReceiveCorpus()));
        helper.setText(R.id.tv_show_pay_interest,AccountUtil.DoubleToString(item.getReceiveInterest()));
        helper.setText(R.id.tv_remain,AccountUtil.DoubleToString(item.getRestCorpusAndInterest()));
        //helper.setVisible(R.id.view_root_repay, false);
       }*/
}