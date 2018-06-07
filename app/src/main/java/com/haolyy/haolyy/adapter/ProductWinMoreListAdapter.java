package com.haolyy.haolyy.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.custom.CustomizedProgressBar;
import com.haolyy.haolyy.entity.product.BorrowPlanListBean;
import com.haolyy.haolyy.entity.product.ProductSanListBean;
import com.haolyy.haolyy.swipe.ListBaseAdapter;
import com.haolyy.haolyy.swipe.SuperViewHolder;
import com.haolyy.haolyy.ui.product.ProductPlanDetailActivity;
import com.haolyy.haolyy.utils.AccountUtil;
import com.haolyy.haolyy.utils.CommonUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;


/**
 * Created by niudeyang on 2017/12/27.
 */

public class ProductWinMoreListAdapter extends ListBaseAdapter<ProductSanListBean.ModelBean.ListBean> {
    public ProductWinMoreListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_product_list;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        ProductSanListBean.ModelBean.ListBean newBean = mDataList.get(position);
        holder.<LinearLayout>getView(R.id.ll_title).setVisibility(View.GONE);
        /*if(newBean.borrowActiveType.equals("1")){
            holder.<TextView>getView(R.id.tv_label).setVisibility(View.VISIBLE);
        }else {
        holder.<TextView>getView(R.id.tv_label).setVisibility(View.GONE);
        }*/
       /* if (Integer.parseInt(newBean.periodLength)< 10) {
            holder.<TextView>getView(R.id.tv_week_tips).setText("债权期限10周，"+newBean.periodLength+"周锁定期后可免费转让，存在无法转出可能");
        } else {
            holder.<TextView>getView(R.id.tv_week_tips).setVisibility(View.GONE);
        }*/
        if(newBean.status>=5){
            holder.<TextView>getView(R.id.tv_label2).setBackgroundDrawable(UIUtils.getDrawable(R.drawable.label_gray));
        }else {
            holder.<TextView>getView(R.id.tv_label2).setBackgroundDrawable(UIUtils.getDrawable(R.drawable.label_orange));
        }
        holder.<TextView>getView(R.id.tv_label2).setText(CommonUtils.getBorrowStatus(newBean.status));
        holder.<CustomizedProgressBar>getView(R.id.progress).setMaxCount(100);
        holder.<TextView>getView(R.id.tv_borrow_name).setText(newBean.borrowName);
        if (newBean.appendRate > 0) {
            holder.<TextView>getView(R.id.tv_rate_small).setText("+"+newBean.appendRate + "%");
        }
        holder.<TextView>getView(R.id.tv_rate).setText(newBean.annualizedRate+"");
        holder.<TextView>getView(R.id.tv_rate).setText(newBean.annualizedRate+"");
        holder.<TextView>getView(R.id.tv_term).setText(newBean.periodLength+ WYUtils.getInvestDeadline(newBean.periodUnit));
        holder.<TextView>getView(R.id.tv_start_invest).setText(AccountUtil.DoubleToString(newBean.investminamount)+"元");
        holder.<TextView>getView(R.id.tv_remain).setText("剩余可投 "+AccountUtil.DoubleToString(newBean.amountWait)+"元");
       float progres= (float) (1-newBean.amountWait/newBean.contractAmount)*100;
        holder.<CustomizedProgressBar>getView(R.id.progress).setMaxCount(100);
        holder.<CustomizedProgressBar>getView(R.id.progress).setCurrentCount(progres);
        if(newBean.status>4){
            holder.<CustomizedProgressBar>getView(R.id.progress).setGray(true);
        }
    }
}
