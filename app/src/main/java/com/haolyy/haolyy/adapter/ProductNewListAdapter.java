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

public class ProductNewListAdapter extends ListBaseAdapter<BorrowPlanListBean.ModelBean.NoviciateBorrowBean> {
    public ProductNewListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_product_list;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        BorrowPlanListBean.ModelBean.NoviciateBorrowBean newBean= mDataList.get(position);
        if(position==0){
            holder.<LinearLayout>getView(R.id.ll_title).setVisibility(View.VISIBLE);
            holder.<LinearLayout>getView(R.id.ll_title).setOnClickListener(v ->{
                mContext.startActivity(ProductWinMoreActivity.getReturnIntent(mContext, "1", "新手专享"));
            });

        }else {
            holder.<LinearLayout>getView(R.id.ll_title).setVisibility(View.GONE);
        }

        //3.待售 4、开始募集（募集中） 5、已满标（募集完成） 7、计息中（还款中） 9、已结清（还款完成）
        if(newBean.getStatus()>=5){
            holder.<TextView>getView(R.id.tv_label2).setBackgroundDrawable(UIUtils.getDrawable(R.drawable.label_gray));
        }else {
            holder.<TextView>getView(R.id.tv_label2).setBackgroundDrawable(UIUtils.getDrawable(R.drawable.label_orange));
        }
        holder.<TextView>getView(R.id.tv_label2).setText(CommonUtils.getBorrowStatus(newBean.getStatus()));
        holder.<CustomizedProgressBar>getView(R.id.progress).setMaxCount(100);
        holder.<TextView>getView(R.id.tv_borrow_name).setText(newBean.getBorrowName());
        if (newBean.getAppendRate() > 0) {
            holder.<TextView>getView(R.id.tv_rate_small).setText("+"+newBean.getAppendRate() + "%");
        }
        holder.<TextView>getView(R.id.tv_rate).setText(newBean.getAnnualizedRate()+"");
        holder.<TextView>getView(R.id.tv_rate).setText(newBean.getAnnualizedRate()+"");
        holder.<TextView>getView(R.id.tv_term).setText(newBean.getPeriodLength()+ WYUtils.getInvestDeadline(newBean.getPeriodUnit()));
        holder.<TextView>getView(R.id.tv_start_invest).setText(AccountUtil.DoubleToString(newBean.getInvestMinAmount())+"元");
        holder.<TextView>getView(R.id.tv_remain).setText("剩余可投 "+AccountUtil.DoubleToString(newBean.getAmountWait())+"元");
        float progres= (float) (1-newBean.getAmountWait()/newBean.getContractAmount())*100;
        holder.<CustomizedProgressBar>getView(R.id.progress).setMaxCount(100);
        holder.<CustomizedProgressBar>getView(R.id.progress).setCurrentCount(progres);
        if(newBean.getStatus()>4){
            holder.<CustomizedProgressBar>getView(R.id.progress).setGray(true);
        }
        holder.<ConstraintLayout>getView(R.id.cs_content).setOnClickListener(v->{
            mContext.startActivity(ProductPlanDetailActivity.getDetailIntent(mContext,newBean.getBorrowName(),newBean.getBorrowNo(),false));
        });
    }
}
