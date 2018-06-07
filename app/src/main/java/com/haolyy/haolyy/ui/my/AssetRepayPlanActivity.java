package com.haolyy.haolyy.ui.my;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.AssetRepayPlanAdapter;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.entity.userinfo.AssetRepayPLanBean;
import com.haolyy.haolyy.ui.my.View.AssetRepayView;
import com.haolyy.haolyy.ui.my.presenter.AssetHoldPresenter;
import com.haolyy.haolyy.ui.my.presenter.AssetRepayPresenter;
import com.haolyy.haolyy.utils.CommonUtils;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shanghai on 2018/3/5.
 */

public class AssetRepayPlanActivity extends BaseActivity<AssetRepayPresenter, AssetRepayView> implements AssetRepayView {
    @BindView(R.id.iv_finish)
    TextView ivFinish;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.titleBar)
    RelativeLayout titleBar;
    @BindView(R.id.lv_repay_plan)
    RecyclerView lvRepayPlan;
    private List<AssetRepayPLanBean.ModelBean.BorrowReserveBean> list;
    private AssetRepayPLanBean.ModelBean.BorrowBean borrowBean;
    private String cashNo, borrowNo;

    public static Intent getReturnIntent(Context context, String borrowNo, String cashNo) {
        Intent intent = new Intent(context, AssetRepayPlanActivity.class);
        intent.putExtra("cashNo", cashNo);
        intent.putExtra("borrowNo", borrowNo);
        return intent;
    }

    @Override
    protected AssetRepayPresenter createPresenter() {
        return new AssetRepayPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_repay_plan);
        ButterKnife.bind(this);
        cashNo = getIntent().getStringExtra("cashNo");
        borrowNo = getIntent().getStringExtra("borrowNo");
        init();
    }

    private void init() {
        tvTitle.setText("回款计划");
        lvRepayPlan.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.getRepayPlan(cashNo);
    }

    private View getHeaderView() {
        View view = getLayoutInflater().inflate(R.layout.head_view_repay, (ViewGroup) lvRepayPlan.getParent(), false);
        TextView name, way, date1, date2, bill_time, staus, corpus, intersts;
        name = view.findViewById(R.id.tv_repay_name);
        way = view.findViewById(R.id.tv_repay_way);
        date1 = view.findViewById(R.id.tv_start_date);
        date2 = view.findViewById(R.id.tv_end_time);
        bill_time = view.findViewById(R.id.tv_repay_time);
        staus = view.findViewById(R.id.tv_repay_status);
        corpus = view.findViewById(R.id.tv_repay_corpus);
        intersts = view.findViewById(R.id.tv_repay_interest);
        name.setText(borrowBean.getBorrowName());
        way.setText(WYUtils.getProfitPlan(borrowBean.getProfitPlan() + ""));
        date1.setText(borrowBean.getInterestStartDate().substring(0, 10));
        date2.setText(borrowBean.getInterestEndDate());
        bill_time.setText(list.get(0).getBillDate().substring(0, 10));

        //1:待回款 2:已回款 3:回款中 4:回款失败
        if (list.get(0).getStatus() != 1) {
            staus.setTextColor(Color.parseColor("#9b9b9b"));
        } else {
            staus.setTextColor(Color.parseColor("#fe7537"));
        }
        staus.setText(WYUtils.getRepayStatus(list.get(0).getStatus()) + "(" + list.get(0).getCurStageNo() + ")");
        corpus.setText(list.get(0).getReserveCorpus() + "元");
        intersts.setText(list.get(0).getReserveInterest() + "元");
        return view;
    }

    @OnClick(R.id.iv_finish)
    public void onViewClicked() {
        closeActivity();
    }

    @Override
    public void showRepayPlan(AssetRepayPLanBean assetRepayPLanBean) {
        borrowBean = assetRepayPLanBean.getModel().getBorrow();
        list = assetRepayPLanBean.getModel().getBorrowReserve();
        View view = getHeaderView();
        int size = list.size();
        if (size > 1) {
            list = list.subList(1, size);
        }
        AssetRepayPlanAdapter assetRepayPlanAdapter = new AssetRepayPlanAdapter(list);
        assetRepayPlanAdapter.openLoadAnimation();
        lvRepayPlan.setAdapter(assetRepayPlanAdapter);
        assetRepayPlanAdapter.setEmptyView(CommonUtils.getEmptyView(this, lvRepayPlan, "暂无记录", R.drawable.pic_no_data));
        assetRepayPlanAdapter.addHeaderView(view);
        lvRepayPlan.setAdapter(assetRepayPlanAdapter);
    }
}
