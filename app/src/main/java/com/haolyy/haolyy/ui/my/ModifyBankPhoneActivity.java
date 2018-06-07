package com.haolyy.haolyy.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BaseView;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.custom.ClearEditText;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.entity.ModifyBankPhoneBean;
import com.haolyy.haolyy.ui.my.presenter.ModifyBankPhonePresenter;
import com.haolyy.haolyy.ui.third.MBPWebActivity;
import com.haolyy.haolyy.utils.AppToast;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * @author wyman
 * @date 2018/5/21
 * description :
 */

public class ModifyBankPhoneActivity extends BaseActivity<ModifyBankPhonePresenter,BaseView<ModifyBankPhoneBean>> implements BaseView<ModifyBankPhoneBean> {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.ed_phone)
    ClearEditText edPhone;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.ed_message_code)
    ClearEditText edMessageCode;
    @BindView(R.id.tv_message_code)
    TextView tvMessageCode;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    private Subscription subscription;
    private String oldMobile;
    private String bankCardNo;
    @Override
    protected ModifyBankPhonePresenter createPresenter() {
        return new ModifyBankPhonePresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_bank_phone);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        oldMobile= getIntent().getStringExtra("oldMobile");
        bankCardNo= getIntent().getStringExtra("bankCardNo");
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                finish();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });
        //发送验证码按钮
        subscription = RxView.clicks(tvMessageCode).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
            if (!WYUtils.checkPhone(edPhone.getText().toString().trim())) {
                AppToast.showShortText(mContext, "手机号格式不正确");
                return;
            }
            mPresenter.sendSms(tvMessageCode, "change_mp", bankCardNo, edPhone.getText().toString().trim(), "O");
        });
        //提交按钮
        subscription = RxView.clicks(tvSubmit).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {


            if (!WYUtils.checkPhone(edPhone.getText().toString().trim())) {
                AppToast.showShortText(mContext, "手机号格式不正确");
                return;
            }
            if (TextUtils.isEmpty(edMessageCode.getText().toString().trim())) {
                UIUtils.showToastCommon(mContext, Config.TIP_SMS);
                return;
            }
            mPresenter.modifyBankPhone(BaseApplication.juid,oldMobile,edPhone.getText().toString().trim(),edMessageCode.getText().toString().trim(),BaseApplication.sms_seq_);

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
    public void getCommonData(ModifyBankPhoneBean modifyBankPhoneBean) {
        BaseApplication.orderNo = modifyBankPhoneBean.getModel().getInMap().getOrdId();
        Intent intent = new Intent(mContext, MBPWebActivity.class);
        intent.setAction(new Gson().toJson(modifyBankPhoneBean) );
        intent.putExtra("flag", "ModifyBankPhoneActivity");
        startActivity(intent);

    }

    @Override
    public void requestError(String s) {

    }

    @Override
    public void requestSuccess(String s) {

    }
}
