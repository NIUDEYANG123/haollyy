package com.haolyy.haolyy.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.custom.CustomizedProgressBar;
import com.haolyy.haolyy.entity.product.BorrowPlanListBean;
import com.haolyy.haolyy.swipe.ListBaseAdapter;
import com.haolyy.haolyy.swipe.SuperViewHolder;
import com.haolyy.haolyy.ui.product.ProductPlanDetailActivity;
import com.haolyy.haolyy.ui.product.ProductWinMoreActivity;
import com.haolyy.haolyy.utils.AccountUtil;
import com.haolyy.haolyy.utils.CommonUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;


/**
 * Created by niudeyang on 2017/12/27.
 */

public class ProductShortListAdapter extends ListBaseAdapter<BorrowPlanListBean.ModelBean.DqyBorrowListBean>{
    public ProductShortListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_product_list;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        BorrowPlanListBean.ModelBean.DqyBorrowListBean newBean = mDataList.get(position);
        if(position>0){
            holder.<LinearLayout>getView(R.id.ll_title).setVisibility(View.GONE);
        }else {
            holder.<LinearLayout>getView(R.id.ll_title).setVisibility(View.VISIBLE);
            holder.<TextView>getView(R.id.tv_group).setText("短期赢");
            holder.<TextView>getView(R.id.tv_group_small).setText("到期一次性还本息");
            holder.<LinearLayout>getView(R.id.ll_title).setOnClickListener(v ->{
                mContext.startActivity(ProductWinMoreActivity.getReturnIntent(mContext,"-1","短期赢"));
            });
        }
        if(newBean.getStatus()>=5){
            holder.<TextView>getView(R.id.tv_label2).setBackgroundDrawable(UIUtils.getDrawable(R.drawable.label_gray));
        }else {
            holder.<TextView>getView(R.id.tv_label2).setBackgroundDrawable(UIUtils.getDrawable(R.drawable.label_orange));
        }
        holder.<TextView>getView(R.id.tv_label2).setText(CommonUtils.getBorrowStatus(newBean.getStatus()));
        holder.<TextView>getView(R.id.tv_label).setVisibility(View.GONE);
        holder.<TextView>getView(R.id.tv_borrow_name).setText(newBean.getBorrow_name());
        if (newBean.getAppend_rate() > 0) {
            holder.<TextView>getView(R.id.tv_rate_small).setText("+"+newBean.getAppend_rate() + "%");
        }
        holder.<TextView>getView(R.id.tv_rate).setText(newBean.getAnnualized_rate()+"");
        holder.<TextView>getView(R.id.tv_rate).setText(newBean.getAnnualizedRate()+"");
        holder.<TextView>getView(R.id.tv_term).setText(newBean.getPeriod_length()+ WYUtils.getInvestDeadline(newBean.getPeriod_unit()));
        holder.<TextView>getView(R.id.tv_start_invest).setText(AccountUtil.DoubleToString(newBean.getInvestMinAmount())+"元");
        holder.<TextView>getView(R.id.tv_remain).setText("剩余可投 "+AccountUtil.DoubleToString(newBean.getAmountWait())+"元");
       /* if (newBean.getPeriod_length() < 10) {
            holder.<TextView>getView(R.id.tv_week_tips).setText("债权期限10周，"+newBean.getPeriod_length()+"周锁定期后可免费转让，存在无法转出可能");
        } else {
            holder.<TextView>getView(R.id.tv_week_tips).setVisibility(View.GONE);
        }*/
        float progres= (float) (1-newBean.getAmountWait()/newBean.getContractAmount())*100;
        holder.<CustomizedProgressBar>getView(R.id.progress).setMaxCount(100);
        holder.<CustomizedProgressBar>getView(R.id.progress).setCurrentCount(progres);
        if(newBean.getStatus()>4){
            holder.<CustomizedProgressBar>getView(R.id.progress).setGray(true);
        }
        holder.<ConstraintLayout>getView(R.id.cs_content).setOnClickListener(v->{
            mContext.startActivity(ProductPlanDetailActivity.getDetailIntent(mContext,newBean.getBorrow_name(),newBean.getBorrow_no(),true));
        });
    }
}
