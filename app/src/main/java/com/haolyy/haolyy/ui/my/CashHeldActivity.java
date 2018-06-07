package com.haolyy.haolyy.ui.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.CashHeldAdapter;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.entity.userinfo.CashHeldBean;
import com.haolyy.haolyy.entity.userinfo.CashHeldReord;
import com.haolyy.haolyy.ui.my.View.CashHeldView;
import com.haolyy.haolyy.ui.my.presenter.CashHeldPresenter;
import com.haolyy.haolyy.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 当前持有债权(智能投标记录)
 */

public class CashHeldActivity extends BaseActivity<CashHeldPresenter, CashHeldView> implements CashHeldView {
    @BindView(R.id.iv_finish)
    TextView ivFinish;
    @BindView(R.id.recycle_asset)
    LRecyclerView recycleAsset;
    @BindView(R.id.layout_no_network)
    LinearLayout layoutNoNetwork;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.titleBar)
    RelativeLayout titleBar;
    private boolean noOne = true;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private CashHeldAdapter cashHeldAdapter;
    private int pageIndex = 0;
    List<CashHeldBean.ModelBean.ListBean> mList = new ArrayList<>();
    private static final int REQUEST_COUNT = 10; //每一页展示多少条数据
    private String cashNo;

    public static Intent getReturnIntent(Context context, String orderNo) {
        Intent intent = new Intent(context, CashHeldActivity.class);
        intent.putExtra("orderNo", orderNo);
        return intent;
    }

    @Override
    protected CashHeldPresenter createPresenter() {
        return new CashHeldPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_held);
        ButterKnife.bind(this);
        initView();
    }

    @OnClick({R.id.iv_finish, R.id.layout_no_network})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                closeActivity();
                break;
            case R.id.layout_no_network:
                layoutNoNetwork.setVisibility(View.GONE);
                recycleAsset.refresh();
                break;
        }
    }

    private void initView() {
        tvTitle.setText("智能投标记录");
        cashNo = getIntent().getStringExtra("orderNo");
        cashHeldAdapter = new CashHeldAdapter(mContext);
        lRecyclerViewAdapter = CommonUtils.initLrecycleView(cashHeldAdapter, mContext, recycleAsset);
        recycleAsset.setOnRefreshListener(() -> {
            noOne = true;
            cashHeldAdapter.clear();
            lRecyclerViewAdapter.notifyDataSetChanged();
            pageIndex = 0;
            mPresenter.getCashHeld(true, cashNo, REQUEST_COUNT + "", pageIndex + "");
        });

        recycleAsset.setOnLoadMoreListener(() -> {
            noOne = false;
            pageIndex++;
            mPresenter.getCashHeld(false, cashNo, REQUEST_COUNT + "", pageIndex + "");
        });

     lRecyclerViewAdapter.setOnItemClickListener((view, position) -> {
            CashHeldBean.ModelBean.ListBean modelBean = mList.get(position);
            if (!TextUtils.isEmpty(modelBean.jzqApplyNo)) {
                mPresenter.getBaoquan(modelBean.jzqApplyNo);
            } else {
                mPresenter.getLocalContract(modelBean.orderNo, modelBean.debtNo, cashNo);
            }
        });
        //初始刷新
        recycleAsset.refresh();
    }

    @Override
    public void getData(CashHeldBean cashHeldBean) {
        if (recycleAsset != null) {
            mList = cashHeldBean.model.list;
            if (mList.size() == 0) {
                layoutNoNetwork.setVisibility(View.VISIBLE);
            } else {
                layoutNoNetwork.setVisibility(View.GONE);
            }
            cashHeldAdapter.addAll(cashHeldBean.model.list);
            recycleAsset.refreshComplete(REQUEST_COUNT);
            lRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getDataMore(CashHeldBean cashHeldBean) {
        mList.addAll(cashHeldBean.model.list);
        cashHeldAdapter.addAll(cashHeldBean.model.list);
        recycleAsset.refreshComplete(REQUEST_COUNT);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void noNetwork() {
        recycleAsset.refreshComplete(REQUEST_COUNT);
        lRecyclerViewAdapter.notifyDataSetChanged();
        if (noOne) {
            layoutNoNetwork.setVisibility(View.VISIBLE);
        } else {
            layoutNoNetwork.setVisibility(View.GONE);
        }
    }
}
