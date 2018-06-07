package com.haolyy.haolyy.adapter;

import android.content.Context;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.entity.userinfo.CashHeldBean;
import com.haolyy.haolyy.entity.userinfo.CashHeldReord;
import com.haolyy.haolyy.swipe.ListBaseAdapter;
import com.haolyy.haolyy.swipe.SuperViewHolder;
import com.haolyy.haolyy.utils.AccountUtil;
import com.haolyy.haolyy.utils.WYUtils;


/**
 * Created by niudeyang on 2017/8/8.
 */

public class CashHeldAdapter extends ListBaseAdapter<CashHeldBean.ModelBean.ListBean> {
    public CashHeldAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_cash_held;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        CashHeldBean.ModelBean.ListBean listBean = mDataList.get(position);
        holder.<TextView>getView(R.id.tv_borrow_name).setText(listBean.borrowName);
        holder.<TextView>getView(R.id.tv_borrow_amount).setText(listBean.matchPrincipal);
        holder.<TextView>getView(R.id.tv_level).setText("A");
    }
}
