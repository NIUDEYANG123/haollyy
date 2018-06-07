package com.haolyy.haolyy.ui.product;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.TabAdapter;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.WebActivity;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.custom.CustomizedProgressBar;
import com.haolyy.haolyy.custom.dialog.DialogOpen;
import com.haolyy.haolyy.entity.product.ProductDetailBean;
import com.haolyy.haolyy.ui.account.CheckUsernameActivity;
import com.haolyy.haolyy.ui.product.View.DetailActivityView;
import com.haolyy.haolyy.ui.product.presenter.DetailActivityPresenter;
import com.haolyy.haolyy.ui.third.OpenBankActivity;
import com.haolyy.haolyy.utils.AccountUtil;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.haolyy.haolyy.base.BaseApplication.step;

/**
 * Created by shanghai on 2018/2/24.
 */

public class ProductSanDetailActivity extends BaseActivity<DetailActivityPresenter, DetailActivityView> implements DetailActivityView {
    @BindView(R.id.progress_product)
    CustomizedProgressBar progressProduct;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_second)
    TextView titleSecond;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tablayout)
    XTabLayout tablayout;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.layout_content)
    CoordinatorLayout layoutContent;
    @BindView(R.id.layout_no_network)
    LinearLayout layoutNoNetwork;
    @BindView(R.id.tv_rate)
    TextView tvRate;
    @BindView(R.id.tv_term)
    TextView tvTerm;
    @BindView(R.id.tv_min)
    TextView tvMin;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_remain)
    TextView tvRemain;
    @BindView(R.id.tv_start_date)
    TextView tvStartDate;
    @BindView(R.id.tv_interest_date)
    TextView tvInterestDate;
    @BindView(R.id.tv_end_date)
    TextView tvEndDate;
    @BindView(R.id.tv_repay_way)
    TextView tvRepayWay;
    @BindView(R.id.tv_append)
    TextView tvAppend;
    private List<Fragment> mDatas;
    private List<String> mTitles;
    private String name;
    private String borrowNo;
    private double rate, appendRate, invest_min_amount_;
    private String periodLength, periodUnit, profitPlan;
    private String contractAmount;
    private int status;
    private String amountWait;

    public static Intent getDetailIntent(Context context, String name, String borrowNo) {
        Intent intent = new Intent(context, ProductSanDetailActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("borrowNo", borrowNo);
        return intent;
    }

    @Override
    protected DetailActivityPresenter createPresenter() {
        return new DetailActivityPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producrt_san_detail);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.queryPlanDetail(borrowNo);
    }

    private void init() {
        name = getIntent().getStringExtra("name");
        borrowNo = getIntent().getStringExtra("borrowNo");
        title.setText(name);
        progressProduct.setCurrentCount(100);
        progressProduct.setMaxCount(100);
        mTitles = new ArrayList<>();
        mTitles.add("借款人信息");
        mTitles.add("出借记录");
        mTitles.add("还款计划");

        mDatas = new ArrayList<>();
        QAFragment qaFragment = QAFragment.newInstance(borrowNo);
        BorrowRecordFragment borrowRecordFragment = BorrowRecordFragment.newInstance(borrowNo);
        RepayPlanFragment repayPlanFragment = RepayPlanFragment.newInstance(borrowNo);
        mDatas.add(qaFragment);
        mDatas.add(borrowRecordFragment);
        mDatas.add(repayPlanFragment);

        viewpage.setAdapter(new TabAdapter(getSupportFragmentManager(), mDatas, mTitles));
        tablayout.setxTabDisplayNum(3);//需要写在setupWithViewPager前
        tablayout.setupWithViewPager(viewpage);


    }

    @OnClick({R.id.iv_back, R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_sure:
                if (BaseApplication.mLoginState) {
                    if (BaseApplication.riskCheck == 0 && step >= 3) {
                        //风险测评
                        openActivity(WebActivity.getWebIntent(mContext, "风险测评", NetConstantValues.CONTRACT_URL + "about/evaluating.html?userCode=" + BaseApplication.juid + "&client=4"));
                    } else if (step <= 2) {
                        DialogOpen dialogOpen = new DialogOpen(this, step);
                        dialogOpen.setOnDoubleClickListener(() -> {
                            if (step == 1) {
                                openActivity(new Intent(mContext, OpenBankActivity.class));
                            } else if (step == 2) {
                                //去激活
                                mPresenter.queryBank();
                            }
                        }).show();
                    } else {
                        openActivity(SureJoinActivity.getInvestIntent(mContext, borrowNo, false));
                    }
                } else {
                    //openActivity(LoginActivity.getIntent(mContext, "", "invest"));
                    openActivity(new Intent(mContext, CheckUsernameActivity.class));
                }

                break;
        }
    }

    @Override
    public void pushActivity(String s, int requestCode) {

    }

    @Override
    public void pushSucess(int i) {

    }

    @Override
    public void showDetail(ProductDetailBean productDetailBean) {
        rate = productDetailBean.model.annualizedRate;
        appendRate = productDetailBean.model.appendRate;
        if (appendRate > 0) {
            tvAppend.setText("+" + AccountUtil.singleDouble(appendRate) + "%");
        }
        periodLength = productDetailBean.model.periodLength;
        periodUnit = productDetailBean.model.periodUnit + "";
        profitPlan = productDetailBean.model.profitPlan;
        tvRepayWay.setText(WYUtils.getProfitPlan(profitPlan));
        invest_min_amount_ = productDetailBean.model.investMinAmount;
        contractAmount = productDetailBean.model.contractAmount;
        amountWait = productDetailBean.model.amountWait;
        tvRate.setText(AccountUtil.singleDouble(rate));
        tvTerm.setText(periodLength + WYUtils.getInvestDeadline(productDetailBean.model.periodUnit));
        tvMin.setText(AccountUtil.DoubleToString(invest_min_amount_) + "元");
        tvTotal.setText("计划金额" + AccountUtil.StringToMString(contractAmount) + "元");
        tvRemain.setText("剩余可投" + AccountUtil.StringToMString(amountWait) + "元");
        tvStartDate.setText(productDetailBean.model.publishTime);
        if (TextUtils.isEmpty(productDetailBean.model.interestEndDate)) {
            tvEndDate.setText(productDetailBean.model.endDate);
        } else {
            tvEndDate.setText(productDetailBean.model.interestEndDate);
        }
        //tvInterestDate.setText(productDetailBean.model.interestEndDate);
        progressProduct.setCurrentCount((float) (1 - Double.parseDouble(amountWait) / Double.parseDouble(contractAmount)) * 100);
        //倒计时的预售标暂时不做
        status = productDetailBean.model.status;
        //////3.待售 4、开始募集（募集中） 5、已满标（募集完成） 7、计息中（还款中） 9、已结清（还款完成）12推出中
        if (productDetailBean.model.status == 3) {
            tvSure.setEnabled(false);
            tvSure.setText("待售");
            tvSure.setBackgroundColor(Color.parseColor("#cbcbcb"));
        } else if (productDetailBean.model.status >= 5) {
            tvSure.setEnabled(false);
            if (productDetailBean.model.status == 7) {
                tvSure.setText("收益中");
                tvSure.setBackgroundColor(Color.parseColor("#cbcbcb"));
            } else if (productDetailBean.model.status == 9) {
                tvSure.setText("已结清");
                tvSure.setBackgroundColor(Color.parseColor("#cbcbcb"));
            } else if (productDetailBean.model.status == 12) {
                tvSure.setText("退出中...");
                tvSure.setBackgroundColor(Color.parseColor("#cbcbcb"));
            } else {
                tvSure.setText("已告罄");
                tvSure.setBackgroundColor(Color.parseColor("#cbcbcb"));
            }
        } else {
            if (BaseApplication.riskCheck == 0) {
                tvSure.setText("风险测评");
            } else {
                tvSure.setText("立即加入");
            }
            tvSure.setEnabled(true);
            tvSure.setBackgroundDrawable(UIUtils.getDrawable(R.drawable.bg_gradient_no_corners));
        }
    }

    @Override
    public void noNetwork() {

    }

    @Override
    public void showSign() {

    }

    @Override
    public void sign2auto() {

    }

    @Override
    public void auto2risk() {

    }
}
