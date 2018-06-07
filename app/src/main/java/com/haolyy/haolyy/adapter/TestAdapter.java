package com.haolyy.haolyy.adapter;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.entity.userinfo.AssetRepayPLanBean;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class TestAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public TestAdapter(List<String> list) {
        super(R.layout.item_test, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setOnClickListener(R.id.iv_test, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView view = helper.<TextView>getView(R.id.tv_test);
                view.measure(0,0);
                Log.e(TAG, "imageView MeasuredWidth = " + view.getMeasuredWidth());
                Log.e(TAG, "imageView MeasuredHeight = " + view.getMeasuredHeight());
                int measuredHeight = view.getMeasuredHeight();
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0, measuredHeight);
                valueAnimator.setDuration(3000).start();
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        helper.<TextView>getView(R.id.tv_test).setMaxLines((int) animation.getAnimatedValue());
                        LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(2153, (int) animation.getAnimatedValue());
                        view.setLayoutParams(Params1);
                    }
                });
            }
        });
        /*f(mData.size()>1){
            helper.setText(R.id.tv_repay_status, WYUtils.getRepayStatus(item.getStatus()));
            helper.setText(R.id.tv_repay_time,item.getBillDate());
            helper.setText(R.id.tv_repay_corpus,item.getReserveCorpus()+"元");
            helper.setText(R.id.tv_repay_interest,item.getReserveInterest()+"元");
        }else {
            helper.setVisible(R.id.view_root_repay,false);
        }*/
    }


}
