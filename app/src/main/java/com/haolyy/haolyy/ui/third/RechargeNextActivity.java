package com.haolyy.haolyy.ui.third;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.base.BaseView;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.custom.ClearEditText;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.ui.third.presenter.RechargeNextPresenter;
import com.haolyy.haolyy.ui.third.view.RechargeView;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * @author wyman
 * @date 2018/2/7
 * description : 充值
 */

public class RechargeNextActivity extends BaseActivity<RechargeNextPresenter,RechargeView> implements RechargeView {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.ed_message_code)
    ClearEditText edMessageCode;
    @BindView(R.id.tv_message_code)
    TextView tvMessageCode;
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;
    private String amount;
    private String phone;
    private Subscription subscription;
    private String card_number_;
    private String bankCode;
    private String totalAmount;
    private String action;

    @Override
    protected RechargeNextPresenter createPresenter() {
        return new RechargeNextPresenter(mContext);
    }

    /**
     *
     * @param context
     * @param amount 充值金额
     * @param phone 预留手机号
     * @param card_number_ 银行卡号
     * @param bankCode 银行code
     * @return
     */
    public static Intent getIntentInstance(Context context, String amount, String phone,String card_number_,String bankCode,String totalAmount,String action) {

        Intent intent = new Intent(context, RechargeNextActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("amount", amount);
        bundle.putString("phone", phone);
        bundle.putString("card_number_", card_number_);
        bundle.putString("bankCode", bankCode);
        bundle.putString("totalAmount", totalAmount);
        intent.putExtras(bundle);
        intent.setAction(action);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_next);
        ButterKnife.bind(this);
        init();
        //发送验证码按钮
        subscription = RxView.clicks(tvMessageCode).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {


                mPresenter.sendSms(tvMessageCode, "recharge", card_number_, phone, "");
            }
        });
        //充值
        subscription = RxView.clicks(tvRecharge).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {

                mPresenter.recharge(amount, "", BaseApplication.sms_seq_, edMessageCode.getText().toString().trim(), card_number_, bankCode);
            }
        });
    }

    private void init() {
        action = getIntent().getAction();
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                finish();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });

        amount = getIntent().getStringExtra("amount");
        phone = getIntent().getStringExtra("phone");
        card_number_ = getIntent().getStringExtra("card_number_");
        bankCode = getIntent().getStringExtra("bankCode");
        totalAmount = getIntent().getStringExtra("totalAmount");

        tvPhone.setText(WYUtils.phoneProcess(phone));

        edMessageCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence)) {
                    tvRecharge.setEnabled(true);
                } else {
                    tvRecharge.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }


    @Override
    public void pushActivity(int i,String msg) {
        if (i == 1) {
            //跳转到充值成功
            startActivity(RechargeSuccess.getSucessRechargeIntent(mContext,totalAmount,amount,action));
        } else {
            //跳转到充值失败
            startActivity(RechargeError.getFailRechargeIntent(mContext,msg,action));
        }
    }

    @Override
    public void qrResult(String orderNo) {
        mPresenter.rechargeResult(orderNo);
    }
}
