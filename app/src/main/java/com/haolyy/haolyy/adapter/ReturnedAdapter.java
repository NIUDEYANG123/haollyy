package com.haolyy.haolyy.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.entity.Notice;
import com.haolyy.haolyy.entity.ReturnedBean;
import com.haolyy.haolyy.entity.TestEntity;

import java.util.List;


/**
 * Created by niudeyang on 2017/12/27.
 */

public class ReturnedAdapter extends BaseQuickAdapter<ReturnedBean.ModelBean.ListBean, BaseViewHolder> {


    public ReturnedAdapter(int layoutResId, @Nullable List<ReturnedBean.ModelBean.ListBean> data) {
        super(layoutResId, data);
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, ReturnedBean.ModelBean.ListBean item) {

        helper.setText(R.id.returned_name, item.getBorrowName()).setText(R.id.returned_amount,item.getReceiveAmount()+"").setText(R.id.returned_date,item.getRealRtnDate());
    }
}
