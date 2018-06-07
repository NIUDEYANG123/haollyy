package com.haolyy.haolyy.ui.product;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.DialogFragment;
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
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.custom.CustomizedProgressBar;
import com.haolyy.haolyy.custom.dialog.DialogOpen;
import com.haolyy.haolyy.custom.dialog.DialogRisk;
import com.haolyy.haolyy.custom.dialog.DialogSignFragment;
import com.haolyy.haolyy.custom.dialog.DialogSignProcess;
import com.haolyy.haolyy.entity.product.ProductDetailBean;
import com.haolyy.haolyy.ui.account.CheckUsernameActivity;
import com.haolyy.haolyy.ui.product.View.DetailActivityView;
import com.haolyy.haolyy.ui.product.presenter.DetailActivityPresenter;
import com.haolyy.haolyy.ui.third.OpenBankActivity;
import com.haolyy.haolyy.ui.third.ShWebActivity;
import com.haolyy.haolyy.utils.AccountUtil;
import com.haolyy.haolyy.utils.CommonUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.haolyy.haolyy.base.BaseApplication.accessToken;
import static com.haolyy.haolyy.base.BaseApplication.step;

/**
 * Created by shanghai on 2018/2/24.
 */

public class ProductPlanDetailActivity extends BaseActivity<DetailActivityPresenter, DetailActivityView> implements DetailActivityView {
    @BindView(R.id.progress_product)
    CustomizedProgressBar progressProduct;
    @BindView(R.id.iv_open)
    ImageView ivOpen;
    @BindView(R.id.tv_text1)
    TextView tvText1;
    @BindView(R.id.tv_start_date)
    TextView tvStartDate;
    @BindView(R.id.cl_child1)
    ConstraintLayout clChild1;
    @BindView(R.id.iv_lock)
    ImageView ivLock;
    @BindView(R.id.tv_text2)
    TextView tvText2;
    @BindView(R.id.tv_start_date2)
    TextView tvStartDate2;
    @BindView(R.id.cl_child2)
    ConstraintLayout clChild2;
    @BindView(R.id.iv_exit)
    ImageView ivExit;
    @BindView(R.id.tv_text3)
    TextView tvText3;
    @BindView(R.id.tv_start_date3)
    TextView tvStartDate3;
    @BindView(R.id.cl_child3)
    ConstraintLayout clChild3;
    @BindView(R.id.cl_top)
    ConstraintLayout clTop;
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
    @BindView(R.id.tv_week_tips)
    TextView tvWeekTips;
    @BindView(R.id.tv_append)
    TextView tvAppend;
    @BindView(R.id.vv1)
    View vv1;
    @BindView(R.id.guide_line)
    Guideline guideLine;
    @BindView(R.id.vv2)
    View vv2;
    private List<Fragment> mDatas;
    private List<String> mTitles;
    private String name;
    private String borrowNo;
    private double rate, appendRate, invest_min_amount_;
    private String periodLength, periodUnit, profitPlan;
    private String contractAmount;
    private String amountWait;
    private int status;
    private boolean isDqy;
    private DialogSignProcess dialogSignProcess;

    public static Intent getDetailIntent(Context context, String name, String borrowNo, boolean isDqy) {
        Intent intent = new Intent(context, ProductPlanDetailActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("borrowNo", borrowNo);
        intent.putExtra("isDqy", isDqy);
        return intent;
    }

    @Override
    protected DetailActivityPresenter createPresenter() {
        return new DetailActivityPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producrt_detail);
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
        isDqy = getIntent().getBooleanExtra("isDqy", false);//废弃了没卵用
        title.setText(name);
        progressProduct.setCurrentCount(0);
        progressProduct.setMaxCount(100);
        mTitles = new ArrayList<>();

        mTitles.add("计划介绍");
        mTitles.add("加入记录");
        mTitles.add("常见问题");

        mDatas = new ArrayList<>();
        QAFragment qaFragment = QAFragment.newInstance("name");
        BorrowRecordFragment borrowRecordFragment1 = BorrowRecordFragment.newInstance(borrowNo);
        QAFragment qaFragment1 = QAFragment.newInstance("");
        mDatas.add(qaFragment);
        mDatas.add(borrowRecordFragment1);
        mDatas.add(qaFragment1);

        viewpage.setAdapter(new TabAdapter(getSupportFragmentManager(), mDatas, mTitles));
        viewpage.setOffscreenPageLimit(3);
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
                    if (!BaseApplication.signstatus && step >= 3) {
                        //签约
                        showSignDialog();
                    } else if (step <= 3 && step >= 1) {
                        DialogOpen dialogOpen = new DialogOpen(this, step);
                        dialogOpen.setOnDoubleClickListener(() -> {
                            if (step == 1) {
                                openActivity(new Intent(mContext, OpenBankActivity.class));
                            } else if (step == 2) {
                                //去激活
                                mPresenter.queryBank();
                            } else if (step == 3) {
                                mPresenter.getAutoTenderPlan("1", false);
                            }
                            dialogOpen.dismiss();
                        }).show();
                    } else if (BaseApplication.riskCheck == 0) {
                        //风险测评
                        //openActivity(WebActivity.getWebIntent(mContext, "风险测评", NetConstantValues.CONTRACT_URL + "about/evaluating.html?userCode=" + BaseApplication.juid + "&client=4"));
                        // openActivity(WebActivity.getWebIntent(mContext, "风险测评", NetConstantValues.risk_testgl + "?userCode=" + BaseApplication.juid + "&token=" + accessToken + "&client=4"));
                        auto2risk();
                    } else {
                        if (!TextUtils.isEmpty(periodLength) && periodLength.equals("1") && !name.contains("新手")) {
                            openActivity(SureJoinActivity.getInvestIntent(mContext, borrowNo, true));
                        } else {
                            openActivity(SureJoinActivity.getInvestIntent(mContext, borrowNo, false));
                        }
                    }
                } else {
                    //openActivity(new Intent(mContext, CheckUsernameActivity.class));
                    openActivity(CheckUsernameActivity.getReturnIntent(mContext, "invest"));
                }
                break;
        }
    }

    /**
     * 签约的弹框
     */
    void showSignDialog() {
        dialogSignProcess = new DialogSignProcess(mContext);
        DialogSignFragment dialogSignFragment = new DialogSignFragment(mContext);
        dialogSignFragment.setSignInterface(new DialogSignFragment.SignInterFace() {
            @Override
            public void sign() {
                dialogSignProcess.show();
                mPresenter.signContract();
            }

            @Override
            public void showSignH5() {
                openActivity(WebActivity.getWebIntent(mContext, " ", NetConstantValues.sign_proctor));
            }
        });

        dialogSignFragment.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (null != dialogSignProcess) {
            dialogSignProcess.dismiss();
        }
    }

    /**
     * 签约成功后直接跳转到斧头的汇付页面
     */
    @Override
    public void sign2auto() {
        //dialogSignProcess.dismiss();
        new Handler().postDelayed(() -> mPresenter.getAutoTenderPlan("1", true), 3000);
    }

    /**
     * 复投开启成功后弹出测评弹框
     */
    @Override
    public void auto2risk() {
        if (BaseApplication.riskCheck == 0) {
            DialogRisk dialogRisk = new DialogRisk(mContext);
            dialogRisk.setSignInterface(() -> {
                openActivity(WebActivity.getWebIntent(mContext, "风险测评", NetConstantValues.risk_testgl + "?userCode=" + BaseApplication.juid + "&token=" + accessToken + "&client=4"));
            }).show();
        }
    }

    @Override
    public void pushActivity(String s, int requestCode) {
        Intent intent = new Intent(mContext, ShWebActivity.class);
        intent.setAction(s);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void pushSucess(int i) {

    }

    @Override
    public void showDetail(ProductDetailBean productDetailBean) {
        rate = productDetailBean.model.annualizedRate;
        /*if (Integer.parseInt(productDetailBean.model.periodLength) < 10) {
            tvWeekTips.setText("期限10周，" + productDetailBean.model.periodLength + "周锁定期后可免费转让，存在无法转出可能");
        } else {
            tvWeekTips.setVisibility(View.GONE);
        }*/
        appendRate = productDetailBean.model.appendRate;
        if (appendRate > 0) {
            tvAppend.setText("+" + AccountUtil.singleDouble(appendRate) + "%");
        }
        tvRate.setText(AccountUtil.singleDouble(rate));
        periodLength = productDetailBean.model.periodLength;
        periodUnit = productDetailBean.model.periodUnit + "";
        profitPlan = productDetailBean.model.profitPlan;
        invest_min_amount_ = productDetailBean.model.investMinAmount;
        contractAmount = productDetailBean.model.contractAmount;
        amountWait = productDetailBean.model.amountWait;
        tvTerm.setText(periodLength + WYUtils.getInvestDeadline(productDetailBean.model.periodUnit));
        tvMin.setText(AccountUtil.DoubleToString(invest_min_amount_) + "元");
        tvTotal.setText("计划金额" + AccountUtil.StringToMString(contractAmount) + "元");
        tvRemain.setText("剩余可投" + AccountUtil.StringToMString(amountWait) + "元");
        tvStartDate.setText(productDetailBean.model.startDate);
        tvStartDate2.setText(productDetailBean.model.endDate);
        tvStartDate3.setText(productDetailBean.model.interestEndDate);
        progressProduct.setCurrentCount((float) (1 - Double.parseDouble(amountWait) / Double.parseDouble(contractAmount)) * 100);
        //////3.待售 4、开始募集（募集中） 5、已满标（募集完成） 7、计息中（还款中） 9、已结清（还款完成）12推出中
        status = productDetailBean.model.status;
        if (productDetailBean.model.status == 3) {
            tvSure.setEnabled(false);
            tvSure.setText("待售");
            tvSure.setBackgroundColor(Color.parseColor("#cbcbcb"));
        } else if (productDetailBean.model.status >= 5) {
            tvSure.setEnabled(false);
            showStepTime(1);
            if (productDetailBean.model.status == 7) {
                tvSure.setText("收益中");
                tvSure.setBackgroundColor(Color.parseColor("#cbcbcb"));
            } else if (productDetailBean.model.status == 9) {
                tvSure.setText("已结清");
                tvSure.setBackgroundColor(Color.parseColor("#cbcbcb"));
            } else if (productDetailBean.model.status == 12) {
                showStepTime(2);
                tvSure.setText("退出中");
                tvSure.setBackgroundColor(Color.parseColor("#cbcbcb"));
            } else {
                tvSure.setText("已告罄");
                tvSure.setBackgroundColor(Color.parseColor("#cbcbcb"));
            }
        } else {
            tvSure.setText("立即加入");
            tvSure.setEnabled(true);
            tvSure.setBackgroundDrawable(UIUtils.getDrawable(R.drawable.bg_gradient_no_corners));
        }
    }

    @Override
    public void noNetwork() {

    }

    @Override
    public void showSign() {
       /* if (status == 4) {
            if (!BaseApplication.signstatus) {
                tvSure.setText("签署自动授权委托书");
            } else if (BaseApplication.riskCheck == 0 && step >= 4) {
                tvSure.setText("风险测评");
            }
        }*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101) {
            if (requestCode == 0x81) {
                UIUtils.showToastCommon(mContext, "激活成功");
            } else if (requestCode == 0x82) {
                //开启复投之后需调用此接口改变复投状态
                mPresenter.queryTenderPlan();
            }
        }
    }

    void showStepTime(int tep) {
        if (tep >= 1) {
            vv1.setBackgroundColor(Color.parseColor("#fe7d3d"));
            ivLock.setImageResource(R.drawable.in_lock_orange);
        }
        if (tep == 2) {
            vv2.setBackgroundColor(Color.parseColor("#fe7d3d"));
            ivExit.setImageResource(R.drawable.icon_exit_orange);
        }
    }
}
