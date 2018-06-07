package com.haolyy.haolyy.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.custom.CustomizedProgressBar;
import com.haolyy.haolyy.entity.product.DqyBean;
import com.haolyy.haolyy.entity.product.ProductSanListBean;
import com.haolyy.haolyy.swipe.ListBaseAdapter;
import com.haolyy.haolyy.swipe.SuperViewHolder;
import com.haolyy.haolyy.utils.AccountUtil;
import com.haolyy.haolyy.utils.CommonUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;


/**
 * Created by niudeyang on 2017/12/27.
 */

public class ProductDqyMoreListAdapter extends ListBaseAdapter<DqyBean.ModelBean.ListBean> {
    public ProductDqyMoreListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_product_list;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        DqyBean.ModelBean.ListBean newBean = mDataList.get(position);
        holder.<LinearLayout>getView(R.id.ll_title).setVisibility(View.GONE);
        if (newBean.getStatus() >= 5) {
            holder.<TextView>getView(R.id.tv_label2).setBackgroundDrawable(UIUtils.getDrawable(R.drawable.label_gray));
        } else {
            holder.<TextView>getView(R.id.tv_label2).setBackgroundDrawable(UIUtils.getDrawable(R.drawable.label_orange));
        }
        holder.<TextView>getView(R.id.tv_label2).setText(CommonUtils.getBorrowStatus(newBean.getStatus()));
        holder.<TextView>getView(R.id.tv_label).setVisibility(View.GONE);
       /* if (newBean.getPeriodLength() < 10) {
            holder.<TextView>getView(R.id.tv_week_tips).setText("债权期限10周，" + newBean.getPeriodLength() + "周锁定期后可免费转让，存在无法转出可能");
        } else {
            holder.<TextView>getView(R.id.tv_week_tips).setVisibility(View.GONE);
        }*/
        holder.<CustomizedProgressBar>getView(R.id.progress).setMaxCount(100);
        holder.<TextView>getView(R.id.tv_borrow_name).setText(newBean.getBorrowName());
        if (newBean.getAppendRate() > 0) {
            holder.<TextView>getView(R.id.tv_rate_small).setText("+"+newBean.getAppendRate() + "%");
        }
        holder.<TextView>getView(R.id.tv_rate).setText(newBean.getAnnualizedRate() + "");
        holder.<TextView>getView(R.id.tv_term).setText(newBean.getPeriodLength() + WYUtils.getInvestDeadline(Integer.parseInt(newBean.getPeriodUnit())));
        holder.<TextView>getView(R.id.tv_start_invest).setText(AccountUtil.DoubleToString(newBean.getInvestminamount()) + "元");
        holder.<TextView>getView(R.id.tv_remain).setText("剩余可投 " + AccountUtil.DoubleToString(newBean.getAmountWait()) + "元");
        float progres = (float) (1 - newBean.getAmountWait() / newBean.getContractAmount()) * 100;
        holder.<CustomizedProgressBar>getView(R.id.progress).setMaxCount(100);
        holder.<CustomizedProgressBar>getView(R.id.progress).setCurrentCount(progres);
        if(newBean.getStatus()>4){
            holder.<CustomizedProgressBar>getView(R.id.progress).setGray(true);
        }
    }
}
