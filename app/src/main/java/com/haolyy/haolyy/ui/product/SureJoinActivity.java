package com.haolyy.haolyy.ui.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.WebActivity;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.custom.CustomizedProgressBar;
import com.haolyy.haolyy.custom.SwitchButton;
import com.haolyy.haolyy.custom.dialog.DialogInvestTips;
import com.haolyy.haolyy.entity.my.HomePageBean;
import com.haolyy.haolyy.entity.product.ProductDetailBean;
import com.haolyy.haolyy.entity.product.RevenueBean;
import com.haolyy.haolyy.entity.userinfo.InvestCouponBean;
import com.haolyy.haolyy.ui.product.View.InvestView;
import com.haolyy.haolyy.ui.product.presenter.InvestPresenter;
import com.haolyy.haolyy.ui.record.InvestCouponActivity;
import com.haolyy.haolyy.ui.third.RechargeActivity;
import com.haolyy.haolyy.utils.AccountUtil;
import com.haolyy.haolyy.utils.NumEditWatcher;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;
import com.zyyoona7.lib.EasyPopup;
import com.zyyoona7.lib.HorizontalGravity;
import com.zyyoona7.lib.VerticalGravity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shanghai on 2018/2/24.
 */

public class SureJoinActivity extends BaseActivity<InvestPresenter, InvestView> implements InvestView {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.titleBar)
    RelativeLayout titleBar;
    @BindView(R.id.progress_product)
    CustomizedProgressBar progressProduct;
    @BindView(R.id.iv_finish)
    TextView ivFinish;
    @BindView(R.id.tv_sure)
    TextView tvSure;
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
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;
    @BindView(R.id.tv_all_in)
    TextView tvAllIn;
    @BindView(R.id.tv_history_rate)
    TextView tvHistoryRate;
    @BindView(R.id.tv_coupon)
    TextView tvCoupon;
    @BindView(R.id.switch_button)
    SwitchButton switchButton;
    @BindView(R.id.tv_auto_tip)
    TextView tvAutoTip;
    @BindView(R.id.ck_proctor)
    CheckBox ckProctor;
    @BindView(R.id.tv_borrow_proctor)
    TextView tvBorrowProctor;
    @BindView(R.id.tv_risk_book)
    TextView tvRiskBook;
    @BindView(R.id.tv_warn)
    TextView tvWarn;
    @BindView(R.id.et_invest_amount)
    EditText etInvestAmount;
    @BindView(R.id.tv_close)
    TextView tvClose;
    @BindView(R.id.tv_append)
    TextView tvAppend;
    @BindView(R.id.ll_continue)
    LinearLayout llContinue;
    @BindView(R.id.tv_borrow_ask)
    TextView tvBorrowAsk;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    private String borrowNo, isContinue = "0";
    private boolean isPlan;
    private boolean flag = true;//是否调用投资卡券接口
    private double amount, rate, appendRate, rateEffect, invest_min_amount_;
    private String periodLength, periodUnit, profitPlan;
    private String contractAmount;
    private String amountWait;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (flag) {
                mPresenter.queryInvestCoupon("1", "10", amount + "", periodLength, periodUnit, "", productNo);
            } else {
                getHistoryRevneue();
            }

        }
    };

    private String availableAmount;
    private double investMaxAmount;
    private String investAscendingAmount;
    private String receiveId, revenus;
    private String interestEndDate;
    private String endDate;
    private DialogInvestTips dialogInvestTips;
    private String productNo;
    private String interestDateInt;

    private void getHistoryRevneue() {
        //在这里调用服务器的接口，获取数据
        //这里获得的数据用于购买
        mPresenter.getExpectedRevenue(amount + "", AccountUtil.add(rate, rateEffect, appendRate) + "", periodLength, periodUnit, profitPlan);
        //这里获得用于展示数据
        mPresenter.getExpectedRevenueNew(amount + "", AccountUtil.add(rate, 0, appendRate) + "", periodLength, periodUnit, profitPlan, borrowNo, rateEffect + "");
    }

    /**
     * @param context
     * @param borrowNo
     * @param isDQy    是否是短期赢
     * @return
     */
    public static Intent getInvestIntent(Context context, String borrowNo, boolean isDQy) {
        Intent intent = new Intent(context, SureJoinActivity.class);
        intent.putExtra("borrowNo", borrowNo);
        intent.putExtra("isPlan", isDQy);
        return intent;
    }

    @Override
    protected InvestPresenter createPresenter() {
        return new InvestPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure_buy);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.queryPlanDetail(borrowNo, isPlan);
        mPresenter.queryUserInfo();
    }

    private void init() {
        dialogInvestTips = new DialogInvestTips(mContext);
        borrowNo = getIntent().getStringExtra("borrowNo");
        isPlan = getIntent().getBooleanExtra("isPlan", true);
        if (isPlan) {
            //短期赢
            llContinue.setVisibility(View.VISIBLE);
            tvAutoTip.setVisibility(View.VISIBLE);
        } else {
            llContinue.setVisibility(View.GONE);
            tvAutoTip.setVisibility(View.GONE);
        }
        progressProduct.setMaxCount(100);
        progressProduct.setCurrentCount(50);
        switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                isContinue = "1";
            } else {
                isContinue = "0";
            }
        });

        etInvestAmount.addTextChangedListener(new NumEditWatcher(etInvestAmount) {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                String str = etInvestAmount.getText().toString().trim();
                if (!TextUtils.isEmpty(str)) {
                    amount = Double.parseDouble(str);
                } else {
                    amount = 0.00;
                }
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                }
                handler.postDelayed(runnable, 500);
            }
        });
    }


    @Override
    public void showRevenue(String amount, String model) {
        revenus = model;
        /*if (!isPlan) {
            if ("3".equals(profitPlan) || "4".equals(profitPlan)) {
                tvHistoryRate.setText(AccountUtil.sub2(model, amount) + "");
            } else {
                tvHistoryRate.setText(model);
            }
        } else {
            tvHistoryRate.setText(AccountUtil.sub2(model, amount) + "");
        }*/

    }

    @Override
    public void showDetail(ProductDetailBean productDetailBean) {
        interestDateInt = productDetailBean.model.interestDateInt;
        tvTitle.setText(productDetailBean.model.borrowName);
        productNo = productDetailBean.model.productNo;
        rate = productDetailBean.model.annualizedRate;
        appendRate = productDetailBean.model.appendRate;
        if (appendRate > 0) {
            tvAppend.setText("+" + AccountUtil.singleDouble(appendRate) + "%");
        }
        periodLength = productDetailBean.model.periodLength;
        periodUnit = productDetailBean.model.periodUnit + "";
        profitPlan = productDetailBean.model.profitPlan;
        investMaxAmount = productDetailBean.model.investMaxAmount;
        invest_min_amount_ = productDetailBean.model.investMinAmount;
        contractAmount = productDetailBean.model.contractAmount;
        amountWait = productDetailBean.model.amountWait;
        investAscendingAmount = productDetailBean.model.investAscendingAmount;
        interestEndDate = productDetailBean.model.interestEndDate;
        endDate = productDetailBean.model.endDate;
        tvRate.setText(AccountUtil.singleDouble(rate));
        tvTerm.setText(periodLength + WYUtils.getInvestDeadline(productDetailBean.model.periodUnit));
        tvMin.setText(AccountUtil.DoubleToString(invest_min_amount_) + "元");
        tvTotal.setText("计划金额" + AccountUtil.StringToMString(contractAmount) + "元");
        tvRemain.setText("剩余可投" + AccountUtil.StringToMString(amountWait) + "元");
        tvAutoTip.setText("开启自动续投，到期后本金继续封闭一个周期(" + periodLength + "周)，收益不间断");
        progressProduct.setCurrentCount((float) (1 - Double.parseDouble(amountWait) / Double.parseDouble(contractAmount)) * 100);
    }

    @Override
    public void showAccountinfo(HomePageBean homePageBean) {
        availableAmount = homePageBean.model.availableAmount;
        tvBalance.setText(AccountUtil.StringToMString(availableAmount));
    }

    @Override
    public void showInvestCoupons(InvestCouponBean investCouponBean) {
        if (null == investCouponBean.getModel().getBestCoupon()) {
            tvClose.setVisibility(View.GONE);
            tvCoupon.setText("无可用优惠券");
        } else {
            tvClose.setVisibility(View.VISIBLE);
            InvestCouponBean.ModelBean.BestCouponBean bestCoupon = investCouponBean.getModel().getBestCoupon();
            receiveId = bestCoupon.getReceiveId();
            rateEffect = bestCoupon.getType() == 1 ? Double.parseDouble(bestCoupon.getEffect() + "") : 0.00;
            String rateS = bestCoupon.getType() == 1 ? bestCoupon.getEffect() + "%" : bestCoupon.getEffect() + "元";
            String nameS = bestCoupon.getType() == 1 ? "加息券" : "返现券";
            tvCoupon.setText("已选" + rateS + nameS);
        }
        getHistoryRevneue();
    }

    @Override
    public void pushActivity(String s, boolean b) {
        if (b) {
            //预约标跳转到投资成功页面
            openActivity(JoinSucessActivity.getReturnIntent(mContext, endDate, interestEndDate, interestDateInt));
        } else {
            //散标
            Intent intent = new Intent(mContext, InvestWebActivity.class);
            intent.setAction(s);
            openActivity(intent);
            finish();
        }
    }

    /**
     * 新的收益接口
     *
     * @param amount
     * @param model
     */
    @Override
    public void showRevenueNew(String amount, RevenueBean.ModelBean model) {
        tvHistoryRate.setText(WYUtils.getTwoNumStr(model.getFixInterest()) + "+" + WYUtils.getTwoNumStr(model.getAppendInterest()));
    }

    /**
     * 风险测评结果页
     *
     * @param socre
     */
    @Override
    public void showRiskLevel(int socre) {
        openActivity(WebActivity.getWebIntent(mContext, "出借金额超限", NetConstantValues.risk_testgl + "?userCode=" + BaseApplication.juid + "&platform=HLW&client=4" + "&score=" + socre));
    }

    @OnClick({R.id.iv_finish, R.id.tv_recharge, R.id.tv_all_in, R.id.tv_coupon, R.id.tv_warn, R.id.tv_sure, R.id.tv_close, R.id.tv_borrow_proctor, R.id.tv_risk_book, R.id.tv_borrow_ask})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                closeActivity();
                break;
            case R.id.tv_recharge:
                Intent intent = new Intent(mContext, RechargeActivity.class);
                intent.setAction("invest");
                openActivity(intent);
                break;
            case R.id.tv_all_in:
                etInvestAmount.setText(availableAmount);
                etInvestAmount.setSelection(etInvestAmount.getText().length());
                break;
            case R.id.tv_coupon:
                if (TextUtils.isEmpty(amount + "") || "0.0".equals(amount + "")) {
                    UIUtils.showToastCommon(mContext, "请输入出借金额");
                } else {
                    startActivityForResult(InvestCouponActivity.getReturnIntent(mContext, amount + "",
                            periodLength, periodUnit, "", productNo), 102);
                }
                break;
            case R.id.tv_warn:
                initWarnPop();
                break;
            case R.id.tv_sure:
                if (amount < invest_min_amount_) {
                    dialogInvestTips.setText(invest_min_amount_ + "元起投,递增金额" + investAscendingAmount + "元", "知道了");
                    dialogInvestTips.show();
                    return;
                } else if (amount > investMaxAmount) {
                    dialogInvestTips.setText("超出加入上限" + investMaxAmount + "元", "知道了");
                    dialogInvestTips.show();
                    return;
                } else if (!TextUtils.isEmpty(amountWait) && amount > Double.parseDouble(amountWait)) {
                    dialogInvestTips.setText("超出剩余可投金额", "知道了");
                    dialogInvestTips.show();
                    return;
                } else if (!TextUtils.isEmpty(investAscendingAmount) && ((amount - invest_min_amount_) % Double.parseDouble(investAscendingAmount)) != 0) {
                    dialogInvestTips.setText(invest_min_amount_ + "元起投,递增金额" + investAscendingAmount + "元", "知道了");
                    dialogInvestTips.show();
                    return;
                } else if (!TextUtils.isEmpty(availableAmount) && amount > Double.parseDouble(availableAmount)) {
                    dialogInvestTips.setText("当前余额不足", "去充值");
                    dialogInvestTips.show();
                    return;
                } else if (!ckProctor.isChecked()) {
                    UIUtils.showToastCommon(mContext, "您还未同意出借服务协议！");
                    scrollView.smoothScrollTo(0, 400);
                    return;
                }
                mPresenter.investSb(borrowNo, amount + "", revenus, receiveId, isContinue);
                break;
            case R.id.tv_close:
                flag = false;
                tvClose.setVisibility(View.GONE);
                rateEffect = 0.00;
                receiveId = "";
                tvCoupon.setText("暂不使用优惠券");
                //重新计算收益
                getHistoryRevneue();
                break;
            case R.id.tv_borrow_proctor:
                openActivity(WebActivity.getWebIntent(mContext, "借款协议", NetConstantValues.CONTRACT_URL + "about/2.html"));
                break;
            case R.id.tv_risk_book:
                openActivity(WebActivity.getWebIntent(mContext, "风险提示书", NetConstantValues.CONTRACT_URL + "Agreement/riskTips.html"));
                break;
            case R.id.tv_borrow_ask:
                openActivity(WebActivity.getWebIntent(mContext, "出借咨询服务协议", NetConstantValues.url_borrow));
                break;
        }
    }

    private void initWarnPop() {
        EasyPopup mQQPop = new EasyPopup(this)
                .setContentView(R.layout.tip_revenue)
                .setAnimationStyle(R.style.QQPopAnim)
                .setFocusAndOutsideEnable(true)
                .setBackgroundDimEnable(true)
                .setDimValue(0.5f)
//                .setDimColor(Color.RED)
//                .setDimView(mTitleBar)
                .createPopup();
        mQQPop.showAtAnchorView(tvWarn, VerticalGravity.BELOW, HorizontalGravity.LEFT, UIUtils.dip2px(40), 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 102) {
            flag = false;
            InvestCouponBean.ModelBean.DataListBean investCouponBean = null;
            if (data == null) {
                rateEffect = 0.00;
                receiveId = "";
                tvCoupon.setText("暂不使用优惠券");
                tvClose.setVisibility(View.GONE);
            } else {
                tvClose.setVisibility(View.VISIBLE);
                investCouponBean = (InvestCouponBean.ModelBean.DataListBean) data.getSerializableExtra("bean");
                receiveId = investCouponBean.getReceiveId();
                rateEffect = investCouponBean.getType() == 1 ? Double.parseDouble(investCouponBean.getEffect() + "") : 0.00;
                String rateS = investCouponBean.getType() == 1 ? investCouponBean.getEffect() + "%" : investCouponBean.getEffect() + "元";
                String nameS = investCouponBean.getType() == 1 ? "加息券" : "返现券";
                tvCoupon.setText("已选" + rateS + nameS);
            }
            //选择卡卷后目标收益是否重新计算
            getHistoryRevneue();
        }
    }
}
