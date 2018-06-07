package com.haolyy.haolyy.ui.third;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.custom.ClearEditText;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.ui.third.presenter.BindCardPresenter;
import com.haolyy.haolyy.ui.third.presenter.QueryBankPresenter;
import com.haolyy.haolyy.ui.third.view.BindCardView;
import com.haolyy.haolyy.utils.LogUtils;
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
 * description :
 */

public class ResetBankNextActivity extends BaseActivity<BindCardPresenter,BindCardView> implements BindCardView {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.iv_bank_logo)
    ImageView ivBankLogo;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_bank_card)
    TextView tvBankCard;
    @BindView(R.id.reserved_phone)
    TextView reservedPhone;
    @BindView(R.id.ed_message_code)
    ClearEditText edMessageCode;
    @BindView(R.id.tv_message_code)
    TextView tvMessageCode;
    @BindView(R.id.tv_next)
    TextView tvNext;
    private String bankName;
    private String bankId;
    private String bankCardNo;
    private String bankPhone;
    private Subscription subscription;
    private String sms;


    @Override
    protected BindCardPresenter createPresenter() {
        return new BindCardPresenter(mContext);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_bank_next);
        ButterKnife.bind(this);
        init();

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
        bankName =getIntent().getStringExtra("bankName");
        bankId =getIntent().getStringExtra("bankId");
        bankCardNo =getIntent().getStringExtra("bankCardNo");
        bankPhone =getIntent().getStringExtra("bankPhone");
        WYUtils.showBankLogo(bankId, ivBankLogo);
        tvBankName.setText(bankName);
        tvBankCard.setText(WYUtils.bankCardProcess(bankCardNo));
        reservedPhone.setText(WYUtils.phoneProcess(bankPhone));

        //发送验证码按钮
        subscription = RxView.clicks(tvMessageCode).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> mPresenter.sendSms(tvMessageCode, "rebind", bankCardNo, bankPhone, "O"));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @OnClick(R.id.tv_next)
    public void onViewClicked() {
        sms =edMessageCode.getText().toString().trim();
        if(sms.length()<6){
            UIUtils.showToastCommon(mContext,"请输入正确的验证码！");
            return;
        }
        Intent intent = new Intent(mContext,ResetBankLastActivity.class);
        intent.putExtra("sms_seq", BaseApplication.sms_seq_);
        intent.putExtra("sms", sms);
        startActivity(intent);
    }

    @Override
    public void sucess() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }
}
