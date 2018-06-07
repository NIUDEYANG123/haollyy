package com.haolyy.haolyy.ui.third;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.custom.ClearEditText;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.entity.bank.BankListBean;
import com.haolyy.haolyy.ui.third.presenter.BindCardPresenter;
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

public class ResetBankLastActivity extends BaseActivity<BindCardPresenter, BindCardView> implements BindCardView {

    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.iv_bank_logo)
    ImageView ivBankLogo;
    @BindView(R.id.tv_bank_name_title)
    TextView tvBankNameTitle;
    @BindView(R.id.ll_bank_list)
    LinearLayout llBankList;
    @BindView(R.id.et_bank_code)
    ClearEditText etBankCode;
    @BindView(R.id.et_reserved_phone)
    ClearEditText etReservedPhone;
    @BindView(R.id.ed_message_code)
    ClearEditText edMessageCode;
    @BindView(R.id.tv_message_code)
    TextView tvMessageCode;
    @BindView(R.id.tv_next)
    TextView tvNext;
    private boolean isSelecteBank;
    private Subscription subscription;
    private String cardNo;
    private String bankPhone;
    private String sms;
    private String bankCode; //银行编号
    private String orgSmsCode;
    private String orgSmsSeq;

    @Override
    protected BindCardPresenter createPresenter() {
        return new BindCardPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_open_bank);
        ButterKnife.bind(this);

        init();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void init() {

        orgSmsCode = getIntent().getStringExtra("sms");
        orgSmsSeq = getIntent().getStringExtra("sms_seq");
        ivBankLogo.setVisibility(View.GONE);
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                finish();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });

        etBankCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence)
                        && !TextUtils.isEmpty(etReservedPhone.getText().toString().trim())
                        && !TextUtils.isEmpty(edMessageCode.getText().toString().trim())
                        && isSelecteBank) {
                    tvNext.setEnabled(true);
                } else {
                    tvNext.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etReservedPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence)
                        && !TextUtils.isEmpty(etBankCode.getText().toString().trim())
                        && !TextUtils.isEmpty(edMessageCode.getText().toString().trim())
                        && isSelecteBank) {
                    tvNext.setEnabled(true);

                } else {
                    tvNext.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edMessageCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence)
                        && !TextUtils.isEmpty(etBankCode.getText().toString().trim())
                        && !TextUtils.isEmpty(etReservedPhone.getText().toString().trim())
                        && isSelecteBank) {
                    tvNext.setEnabled(true);
                } else {
                    tvNext.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //发送验证码按钮
        subscription = RxView.clicks(tvMessageCode).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                cardNo = etBankCode.getText().toString().trim();
                bankPhone = etReservedPhone.getText().toString().trim();
                if (!WYUtils.checkCard(cardNo)) {
                    UIUtils.showToastCommon(mContext, "请填写正确的银行卡号");
                    return;
                }
                if (!WYUtils.checkPhone(bankPhone)) {
                    UIUtils.showToastCommon(mContext, "请输入正确的手机号");
                    return;
                }

                mPresenter.sendSms(tvMessageCode, "user_register", cardNo, bankPhone, "");
            }
        });
        subscription = RxView.clicks(tvNext).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                sms = edMessageCode.getText().toString().trim();

                cardNo = etBankCode.getText().toString().trim();
                bankPhone = etReservedPhone.getText().toString().trim();
                if (!isSelecteBank) {
                    UIUtils.showToastCommon(mContext, "请选择开户银行");
                    return;
                }
                if (!WYUtils.checkCard(cardNo)) {
                    UIUtils.showToastCommon(mContext, "请填写正确的银行卡号");
                    return;
                }
                if (!WYUtils.checkPhone(bankPhone)) {
                    UIUtils.showToastCommon(mContext, "请输入正确的手机号");
                    return;
                }
                if (sms.length() < 6) {
                    UIUtils.showToastCommon(mContext, "请填写正确的验证码");
                    return;
                }

                mPresenter.rebind(BaseApplication.mUserName, bankCode, cardNo, bankPhone, sms, BaseApplication.sms_seq_, orgSmsCode, orgSmsSeq);


            }
        });
    }

    @OnClick(R.id.ll_bank_list)
    public void onViewClicked() {
        startActivityForResult(new Intent(mContext, BankListActivity.class).putExtra("busiType", "1"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e(resultCode + "");
        if (resultCode == 101) {

            new Handler().postDelayed(() -> mPresenter.selectUserState(), 3000);

        } else if (resultCode == 0x03) {
            BankListBean.ModelBean data1 = (BankListBean.ModelBean) data.getSerializableExtra("data");
            if (data1 != null) {
                isSelecteBank = true;
                bankCode = data1.getBankNo();
                ivBankLogo.setVisibility(View.VISIBLE);
                tvBankNameTitle.setText(data1.getBankName());
                WYUtils.showBankLogo(data1.getBankCode(), ivBankLogo);

                if (!TextUtils.isEmpty(etReservedPhone.getText().toString().trim())
                        && !TextUtils.isEmpty(etBankCode.getText().toString().trim())
                        && !TextUtils.isEmpty(edMessageCode.getText().toString().trim())
                        && isSelecteBank) {
                    tvNext.setEnabled(true);
                } else {
                    tvNext.setEnabled(false);
                }

            }

        }
    }


    @Override
    public void sucess() {
        startActivity(new Intent(mContext, ResetBankSuccessActivity.class));
    }
}
