package com.haolyy.haolyy.ui.my;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.ReturnedAdapter;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.custom.InnerScrollRecycleView;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.entity.ReturnedBean;
import com.haolyy.haolyy.ui.my.View.ReturnedAdvanceView;
import com.haolyy.haolyy.ui.my.presenter.ReturnedAdvancePersenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wyman
 * @date 2018/3/1
 * description :回款预告
 */

public class ReturnedAdvanceActivity extends BaseActivity<ReturnedAdvancePersenter, ReturnedAdvanceView> implements ReturnedAdvanceView {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;

    @BindView(R.id.rv_main)
    InnerScrollRecycleView rvMain;
    @BindView(R.id.ll_title2)
    RelativeLayout llTitle2;
    @BindView(R.id.tv_returned_amount)
    TextView tvReturnedAmount;
    @BindView(R.id.tv_returned_name)
    TextView tvReturnedName;
    @BindView(R.id.tv_returned_date)
    TextView tvReturnedDate;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    private CollapsingToolbarLayoutState state;
    private ReturnedAdapter returnedAdapter;


    @Override
    public void showData(ReturnedBean returnedBean) {

        if (returnedBean.getModel().getList().get(0) != null) {
            rvMain.setVisibility(View.VISIBLE);
            llNoData.setVisibility(View.GONE);
            ReturnedBean.ModelBean.ListBean bean = returnedBean.getModel().getList().get(0);
            tvReturnedAmount.setText(bean.getReceiveAmount() + "");
            tvReturnedName.setText(bean.getBorrowName());
            tvReturnedDate.setText(bean.getRealRtnDate());
            returnedAdapter = new ReturnedAdapter(R.layout.item_returned_advance, returnedBean.getModel().getList());
            rvMain.setLayoutManager(new LinearLayoutManager(this));
            rvMain.setAdapter(returnedAdapter);
        } else {
            rvMain.setVisibility(View.GONE);
            llNoData.setVisibility(View.VISIBLE);
        }
    }

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    @Override
    protected ReturnedAdvancePersenter createPresenter() {
        return new ReturnedAdvancePersenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returned_advance);
        ButterKnife.bind(this);
        init();

        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
//                        toolbarLayout.setBackgroundResource(R.drawable.bg_detail);
                        topBar.setVisibility(View.VISIBLE);
                        llTitle2.setVisibility(View.VISIBLE);
                        topBar.setmTitleTextColor(Color.parseColor("#FFFFFF"));
                        topBar.setmDrawableLeft(getResources().getDrawable(R.drawable.arrow_left_white));
                        topBar.setTitle("回款预告");
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
//                        toolbarLayout.setBackgroundResource(R.color.white);
                        topBar.setVisibility(View.VISIBLE);
                        llTitle2.setVisibility(View.GONE);
                        topBar.setmTitleTextColor(Color.parseColor("#FF4A4A4A"));
                        topBar.setmDrawableLeft(getResources().getDrawable(R.drawable.icon_back));
                        topBar.setTitle("全部回款预告");
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
//                            toolbarLayout.setBackgroundResource(R.drawable.bg_detail);
                            topBar.setVisibility(View.GONE);
                            llTitle2.setVisibility(View.VISIBLE);
                            topBar.setmTitleTextColor(Color.parseColor("#FFFFFF"));
                            topBar.setmDrawableLeft(getResources().getDrawable(R.drawable.arrow_left_white));
                            topBar.setTitle("回款预告");
                        }
                    }
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.queryBillInvestAdvance("1");
    }

    private void init() {
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                finish();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });


    }
}
