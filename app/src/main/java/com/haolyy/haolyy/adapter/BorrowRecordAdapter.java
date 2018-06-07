package com.haolyy.haolyy.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.custom.CustomizedProgressBar;
import com.haolyy.haolyy.entity.product.BorrowPlanListBean;
import com.haolyy.haolyy.entity.product.InvestRecordBean;
import com.haolyy.haolyy.swipe.ListBaseAdapter;
import com.haolyy.haolyy.swipe.SuperViewHolder;

import java.util.concurrent.TimeoutException;


/**
 * Created by niudeyang on 2017/12/27.
 */

public class BorrowRecordAdapter extends ListBaseAdapter<InvestRecordBean.ModelBean.ListBean> {
    public BorrowRecordAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_borrow_record;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        InvestRecordBean.ModelBean.ListBean listBean = mDataList.get(position);
        holder.<TextView>getView(R.id.tv_index).setText(position + 1 + "");
        holder.<TextView>getView(R.id.tv_phone).setText(listBean.getRegisterMobile());
        holder.<TextView>getView(R.id.tv_borrow_time).setText(listBean.getOrderDate());
        holder.<TextView>getView(R.id.tv_amount).setText(listBean.getInvestAmount() + "元");
        if (listBean.getClient() == 1) {
            holder.<ImageView>getView(R.id.tv_pic).setImageResource(R.drawable.icon_pc);
        }else {
            holder.<ImageView>getView(R.id.tv_pic).setImageResource(R.drawable.icon_phone);
        }
    }
}
