package com.haolyy.haolyy.ui.record;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.CouponWithdrawExpandableListAdapter;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.entity.userinfo.InvestCouponBean;
import com.haolyy.haolyy.entity.userinfo.WithDrawCoupon;
import com.haolyy.haolyy.ui.record.presenter.CouponWithdrawPresenter;
import com.haolyy.haolyy.ui.record.view.CouponWithdrawView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wyman
 * @date 2018/2/8
 * description : 卡券
 */

public class WithdrawCouponActivity extends BaseActivity<CouponWithdrawPresenter, CouponWithdrawView> implements CouponWithdrawView {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.expandable_invest)
    ExpandableListView expandableInvest;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    // 声明对象
    private CouponWithdrawExpandableListAdapter adapter;
    private int pageIndex = 1;
    List<InvestCouponBean.ModelBean.DataListBean> mList = new ArrayList<>();
    private String investAmount, periodLength, periodUnit, type, productNo;
    private List<WithDrawCoupon.ModelBean.DataListBean> dataList;
    private View viewById;

    @Override
    protected CouponWithdrawPresenter createPresenter() {
        return new CouponWithdrawPresenter(mContext);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_coupon);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.queryWithDraw("1", "100");
    }

    private void init() {
        viewById = findViewById(R.id.empty_v);
        tvEmpty.setText("暂无提现券");
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                finish();
            }

            @Override
            public void OnRightButtonClicked() {
            }
        });

        expandableInvest.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> false);

        expandableInvest.setOnGroupClickListener((expandableListView, view, i, l) -> {
            adapter.setOnChangeImgCallBack(imageViewMap -> {
                if (expandableListView.isGroupExpanded(i)) {
                    imageViewMap.get(i).setImageResource(R.drawable.coupon_up);
                } else {
                    imageViewMap.get(i).setImageResource(R.drawable.coupon_down);
                }
            });
            return false;
        });


    }

    @Override
    public void showWithDrawCoupon(WithDrawCoupon withDrawCoupon) {
        dataList = withDrawCoupon.getModel().getDataList();
        if (null == dataList || dataList.size() == 0) {
            viewById.setVisibility(View.VISIBLE);
        } else {
            adapter = new CouponWithdrawExpandableListAdapter(mContext, withDrawCoupon.getModel().getDataList());
            expandableInvest.setAdapter(adapter);
            adapter.setUseCallBack(pos -> {
                Intent intent = new Intent();
                intent.putExtra("bean", dataList.get(pos));
                setResult(102, intent);
                finish();
            });
        }
    }
}
