package com.haolyy.haolyy.ui.third;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.custom.ClearEditText;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.entity.bank.Fee;
import com.haolyy.haolyy.entity.bank.IsCashSuccess;
import com.haolyy.haolyy.entity.bank.QueryBankBean;
import com.haolyy.haolyy.entity.my.HomePageBean;
import com.haolyy.haolyy.entity.userinfo.InvestCouponBean;
import com.haolyy.haolyy.entity.userinfo.WithDrawCoupon;
import com.haolyy.haolyy.ui.record.WithdrawCouponActivity;
import com.haolyy.haolyy.ui.third.presenter.WithDrawApplyPresenter;
import com.haolyy.haolyy.ui.third.view.WithdrawApplyView;
import com.haolyy.haolyy.utils.AppToast;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.NumEditWatcher;
import com.haolyy.haolyy.utils.StringUtils;
import com.haolyy.haolyy.utils.ToastAlone;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.haolyy.haolyy.config.Config.success;

/**
 * @author wyman
 * @date 2018/2/7
 * description :    提现
 */

public class WithdrawActivity extends BaseActivity<WithDrawApplyPresenter, WithdrawApplyView> implements WithdrawApplyView {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.iv_bank_logo)
    ImageView ivBankLogo;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_bank_type)
    TextView tvBankType;
    @BindView(R.id.tv_bank_card_no)
    TextView tvBankCardNo;
    @BindView(R.id.et_withdraw_money)
    ClearEditText etWithdrawMoney;
    @BindView(R.id.tv_all_withdraw)
    TextView tvAllWithdraw;
    @BindView(R.id.use_withdraw_money)
    TextView useWithdrawMoney;
    @BindView(R.id.account_residue_money)
    TextView accountResidueMoney;
    @BindView(R.id.cb_common_withdraw)
    CheckBox cbCommonWithdraw;
    @BindView(R.id.common_service_charge)
    TextView commonServiceCharge;
    @BindView(R.id.ll_use_coupon)
    LinearLayout llUseCoupon;
    @BindView(R.id.cb_quick_withdraw)
    CheckBox cbQuickWithdraw;
    @BindView(R.id.quick_service_charge)
    TextView quickServiceCharge;
    @BindView(R.id.ll_use_coupon_too)
    LinearLayout llUseCouponToo;
    @BindView(R.id.tv_with_draw_real_money)
    TextView tvWithDrawRealMoney;
    @BindView(R.id.tv_next_withdraw)
    TextView tvNextWithdraw;
    @BindView(R.id.tv_use_coupon)
    TextView tvUseCoupon;
    private String availableAmount;
    private String canWithdrawAmount="0";

    private String commonFee; //手续费
    private String quickFee; //手续费
    private String realMony;//实际到账金额
    private String bankName;
    private String receiveFee;
    private Handler handler = new Handler();
    /**
     * 延迟线程，看是否还有下一个字符输入
     */
    private Runnable delayRun = new Runnable() {

        @Override
        public void run() {
            //在这里调用服务器的接口，获取数据
            mPresenter.getUserCashFee(trans_amt, withdrawWayCommon);
            mPresenter.getUserCashFee(trans_amt, withdrawWayQuick);

        }
    };
    private String withdrawWayCommon = "GENERAL";
    private String withdrawWayQuick = "IMMEDIATE";
    private String withdrawWay = "GENERAL";
    private String trans_amt;
    private Subscription subscription;
    private String sFee;
    private String bankNo;
    private String receiveId;
    private String effect;

    @Override
    protected WithDrawApplyPresenter createPresenter() {
        return new WithDrawApplyPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        ButterKnife.bind(this);

        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                finish();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });

        initListener();
    }

    private void initListener() {


        cbQuickWithdraw.setOnCheckedChangeListener((compoundButton, b) -> {
            mPresenter.getUserCashFee(trans_amt, withdrawWayCommon);
            mPresenter.getUserCashFee(trans_amt, withdrawWayQuick);
            if (b) {
                cbCommonWithdraw.setChecked(false);
                receiveId = "";
                effect="0";
                tvUseCoupon.setText("使用优惠券");
                cbQuickWithdraw.setChecked(true);
                withdrawWay = withdrawWayQuick;
                /*if (!TextUtils.isEmpty(trans_amt) && quickFee != null && Double.valueOf(quickFee) > 0) {
                    realMony = WYUtils.getTwoNumStr((Double.parseDouble(trans_amt) - Double.parseDouble(quickFee)));
                    tvWithDrawRealMoney.setText(realMony + "元");
                } else {
                    tvWithDrawRealMoney.setText("0.00元");
                }*/
            } else {

            }
        });


        cbCommonWithdraw.setOnCheckedChangeListener((c, b) -> {
            mPresenter.getUserCashFee(trans_amt, withdrawWayCommon);
            mPresenter.getUserCashFee(trans_amt, withdrawWayQuick);
            if (b) {
                cbQuickWithdraw.setChecked(false);
                withdrawWay = withdrawWayCommon;
                /*if (!TextUtils.isEmpty(trans_amt) && commonFee != null && Double.valueOf(commonFee) > 0) {
                    realMony = WYUtils.getTwoNumStr((Double.parseDouble(trans_amt) - Double.parseDouble(commonFee)));
                    tvWithDrawRealMoney.setText(realMony + "元");
                } else {
                    tvWithDrawRealMoney.setText("0.00元");
                }*/
            } else {

            }
        });

        cbCommonWithdraw.setChecked(true);
        /**
         * editText 计算收益
         */
        subscription = RxTextView.textChanges(etWithdrawMoney)
                .debounce(1, TimeUnit.SECONDS).skip(1).observeOn(AndroidSchedulers.mainThread())
                .subscribe(charSequence -> {
                    trans_amt = etWithdrawMoney.getText().toString().trim();
                    LogUtils.e("trans_amt", trans_amt);
                    if (!TextUtils.isEmpty(trans_amt)) {
                        if (Double.valueOf(trans_amt) >= 100) {
                            mPresenter.getUserCashFee(trans_amt, withdrawWayCommon);
                            mPresenter.getUserCashFee(trans_amt, withdrawWayQuick);
                        } else {
//                        commonServiceCharge.setText("0.00元");
                            quickServiceCharge.setText("0.00元");
                            tvWithDrawRealMoney.setText("0.00元");
                        }
                        //设定输入超过余额进行提示无法输入
                        if (!TextUtils.isEmpty(availableAmount)) {
                            if (Double.valueOf(trans_amt) > Double.valueOf(availableAmount)) {
                                etWithdrawMoney.setFilters(new InputFilter[]{new InputFilter.LengthFilter(trans_amt.length())});
                                UIUtils.showToastCommon(mContext, "可提现金额不足！");
                            } else {
                                etWithdrawMoney.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
                            }
                        }
                    } else {
//                    commonServiceCharge.setText("0.00元");
                        quickServiceCharge.setText("0.00元");
                        tvWithDrawRealMoney.setText("0.00元");
                    }
                });

        etWithdrawMoney.addTextChangedListener(new NumEditWatcher(etWithdrawMoney) {
            @Override
            public void afterTextChanged(Editable editable) {


//                trans_amt = etWithdrawMoney.getText().toString().trim();
//                LogUtils.e("trans_amt", trans_amt);
//                if (!TextUtils.isEmpty(trans_amt)) {
//                    if (Double.valueOf(trans_amt) >= 100) {
//                        if (delayRun != null) {
//                            //每次editText有变化的时候，则移除上次发出的延迟线程
//                            handler.removeCallbacks(delayRun);
//                        }
//                        handler.postDelayed(delayRun, 1000);
//                    } else {
////                        commonServiceCharge.setText("0.00元");
//                        quickServiceCharge.setText("0.00元");
//                        tvWithDrawRealMoney.setText("0.00元");
//                    }
//                    //设定输入超过余额进行提示无法输入
//                    if (!TextUtils.isEmpty(availableAmount)) {
//                        if (Double.valueOf(trans_amt) > Double.valueOf(availableAmount)) {
//                            etWithdrawMoney.setFilters(new InputFilter[]{new InputFilter.LengthFilter(trans_amt.length())});
//                            UIUtils.showToastCommon(mContext, "可提现金额不足！");
//                        } else {
//                            etWithdrawMoney.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
//                        }
//                    }
//                } else {
////                    commonServiceCharge.setText("0.00元");
//                    quickServiceCharge.setText("0.00元");
//                    tvWithDrawRealMoney.setText("0.00元");
//                }
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        etWithdrawMoney.setText(s);
                        etWithdrawMoney.setSelection(s.length());
                    }
                }
                if (s.toString().trim().equals(".")) {
                    s = "0" + s;
                    etWithdrawMoney.setText(s);
                    etWithdrawMoney.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        etWithdrawMoney.setText(s.subSequence(0, 1));
                        etWithdrawMoney.setSelection(1);
                        return;
                    }
                }

                if (!TextUtils.isEmpty(etWithdrawMoney.getText().toString().trim())) {
                    tvNextWithdraw.setEnabled(true);
                } else {
                    tvNextWithdraw.setEnabled(false);
                }
            }
        });

        subscription = RxView.clicks(tvNextWithdraw).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                if (TextUtils.isEmpty(trans_amt)) {
                    AppToast.makeShortToast(mContext, "提现金额不能为空");
                    return;
                }
                if (Double.parseDouble(trans_amt) < 100) {
                    UIUtils.showToastCommon(mContext, "最小提现金额100元");
                    return;
                }
                if (Double.valueOf(trans_amt) > Double.parseDouble(canWithdrawAmount)) {
                    AppToast.makeShortToast(mContext, "余额不足");
                    return;
                }
                if (cbCommonWithdraw.isChecked()) {
                    if (TextUtils.isEmpty(receiveId)) {
                        sFee = commonFee;
                    } else {
                        sFee = receiveFee;
                    }

                } else if (cbQuickWithdraw.isChecked()) {
                    sFee = quickFee;
                    receiveId = "";
                }

                mPresenter.tocash(trans_amt, withdrawWay, sFee, receiveId);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.fetch();
    }

    @Override
    public void showBank(QueryBankBean queryBankBean) {
        BaseApplication.userCustId = queryBankBean.model.ThirdUserId;
        bankName = queryBankBean.model.BankName;

        bankNo = queryBankBean.model.BankNo;
        tvBankName.setText(bankName);

        WYUtils.showBankLogo(queryBankBean.model.BankCode, ivBankLogo);
        tvBankCardNo.setText(WYUtils.bankCardProcess(queryBankBean.model.BankCardNo));

    }

    @Override
    public void showAccountInfo(HomePageBean homePageBean) {
        //可用余额
        availableAmount = homePageBean.model.availableAmount;
        canWithdrawAmount = homePageBean.model.canWithdrawAmount;

        useWithdrawMoney.setText(WYUtils.getTwoNumStr(Double.parseDouble(canWithdrawAmount)) + "元");
        accountResidueMoney.setText(WYUtils.getTwoNumStr(Double.parseDouble(availableAmount))+ "元");

    }


    @Override
    public void showFee(Fee fee, String flag) {
        if (withdrawWayQuick.equals(flag)) {
            quickFee = fee.model.fee;
            quickServiceCharge.setText(quickFee+ "元");
            if (!TextUtils.isEmpty(trans_amt) && cbQuickWithdraw.isChecked()) {
                realMony = WYUtils.getTwoNumStr((Double.parseDouble(trans_amt) - Double.parseDouble(quickFee)));
                tvWithDrawRealMoney.setText(realMony + "元");
            }
        } else if (withdrawWayCommon.equals(flag)) {
            commonFee = fee.model.fee;
            if (TextUtils.isEmpty(receiveId)) {
                commonServiceCharge.setText(commonFee+ "元");
                if (!TextUtils.isEmpty(trans_amt) && cbCommonWithdraw.isChecked()) {
                    realMony = WYUtils.getTwoNumStr((Double.parseDouble(trans_amt) - Double.parseDouble(commonFee)));
                    tvWithDrawRealMoney.setText(realMony + "元");
                }
            } else {
                if (Double.parseDouble(effect) >= Double.parseDouble(commonFee)) {
                    commonServiceCharge.setText("0.00元");
                    receiveFee = "0.00";
                    if (!TextUtils.isEmpty(trans_amt) && cbCommonWithdraw.isChecked()) {
                        realMony = WYUtils.getTwoNumStr((Double.parseDouble(trans_amt)));
                        tvWithDrawRealMoney.setText(realMony + "元");
                    }
                } else {
                    receiveFee = WYUtils.getTwoNumStrUp(Double.parseDouble(commonFee) - Double.parseDouble(effect));
                    commonServiceCharge.setText(receiveFee + "元");
                    if (!TextUtils.isEmpty(trans_amt) && cbCommonWithdraw.isChecked()) {
                        realMony = WYUtils.getTwoNumStr((Double.parseDouble(trans_amt) - Double.parseDouble(commonFee) + Double.parseDouble(effect)));
                        tvWithDrawRealMoney.setText(realMony + "元");
                    }
                }


            }


        }


    }

    @Override
    public void pushActvity(int i, String s) {
        if (i == 1) {
            //跳转到银行
            Intent intent = new Intent(mContext, WDWebActivity.class);
            intent.setAction(s);
            startActivityForResult(intent, 1);
        } else if (i == 2) {
            //体现失败
            openActivity(WithdrawError.getFailOpenIntent(mContext, s));
        }
    }

    @Override
    public void pushSucess(IsCashSuccess bBaseBean) {
        if (bBaseBean.code.equals(success)) {
            openActivity(WithdrawSuccess.getSucessWithdrawIntent(mContext, trans_amt, realMony, sFee, bankName, bankNo));
        } else {
            //这个版本没有体现失败页面
            openActivity(WithdrawError.getFailOpenIntent(mContext, bBaseBean.msg));
        }
    }


    @OnClick({R.id.tv_all_withdraw, R.id.ll_use_coupon, R.id.ll_use_coupon_too, R.id.tv_next_withdraw, R.id.tv_use_coupon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_all_withdraw:
                etWithdrawMoney.setText(canWithdrawAmount);
                break;
            case R.id.tv_use_coupon:
                if (!cbCommonWithdraw.isChecked()) {
                    ToastAlone.showShortToast(mContext, "普通提现可用");
                    return;
                }else if(TextUtils.isEmpty(trans_amt)||Double.parseDouble(trans_amt)==0){
                    ToastAlone.showShortToast(mContext, "您还未输入提现金额");
                    return;
                }
                startActivityForResult(new Intent(mContext, WithdrawCouponActivity.class), 0x100);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) {
            subscription.unsubscribe();
        }
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e("resultCode", resultCode + "");
        if (resultCode == 101) {

            mPresenter.isWithDrawSucess();
        } else if (resultCode == 102) {
            if (null == data) {
                receiveId = "";
                tvUseCoupon.setText("使用优惠券");
                effect="0";
                commonServiceCharge.setText(commonFee+100+"元");
                realMony = WYUtils.getTwoNumStr((Double.parseDouble(trans_amt) - Double.parseDouble(commonFee) + Double.parseDouble(effect)));
                tvWithDrawRealMoney.setText(realMony + "元");
            } else {
                WithDrawCoupon.ModelBean.DataListBean dataListBean = (WithDrawCoupon.ModelBean.DataListBean) data.getSerializableExtra("bean");
                this.receiveId = dataListBean.getReceiveId();
                //提现券金额
                effect = StringUtils.subZeroAndDot(dataListBean.getEffect());
                tvUseCoupon.setText(StringUtils.subZeroAndDot(dataListBean.getEffect()) + "元提现券");
                if (Double.parseDouble(dataListBean.getEffect()) >= Double.parseDouble(commonFee)) {
                    receiveFee = "0.00";
                    commonServiceCharge.setText("0.00元");
                    if (!TextUtils.isEmpty(trans_amt) && cbCommonWithdraw.isChecked()) {
                        realMony = WYUtils.getTwoNumStr((Double.parseDouble(trans_amt)));
                        tvWithDrawRealMoney.setText(realMony + "元");
                    }
                } else {
                    receiveFee = WYUtils.getTwoNumStrUp(Double.parseDouble(commonFee) - Double.parseDouble(effect));
                    commonServiceCharge.setText(receiveFee + "元");
                    if (!TextUtils.isEmpty(trans_amt) && cbCommonWithdraw.isChecked()) {
                        realMony = WYUtils.getTwoNumStr((Double.parseDouble(trans_amt) - Double.parseDouble(commonFee) + Double.parseDouble(effect)));
                        tvWithDrawRealMoney.setText(realMony + "元");
                    }
                }


            }


        }
    }
}
