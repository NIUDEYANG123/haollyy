package com.haolyy.haolyy.ui.account;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.base.WebActivity;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.custom.ClearEditText;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.entity.TokenBean;
import com.haolyy.haolyy.ui.account.presenter.RegisterPresenter;
import com.haolyy.haolyy.ui.account.view.RegisterView;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * @author wyman
 * @date 2018/2/6
 * description :
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter, RegisterView> implements RegisterView {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.et_pic_code)
    ClearEditText etPicCode;
    @BindView(R.id.iv_pic_code)
    ImageView ivPicCode;
    @BindView(R.id.line_change)
    View lineChange;
    @BindView(R.id.ed_message_code)
    ClearEditText edMessageCode;
    @BindView(R.id.tv_message_code)
    TextView tvMessageCode;
    @BindView(R.id.line_change2)
    View lineChange2;
    @BindView(R.id.ed_password)
    ClearEditText edPassword;
    @BindView(R.id.iv_show_pwd)
    ImageView ivShowPwd;
    @BindView(R.id.line_change3)
    View lineChange3;
    @BindView(R.id.iv_up_down)
    ImageView ivUpDown;
    @BindView(R.id.et_invitation_code)
    ClearEditText etInvitationCode;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.cb_check)
    CheckBox cbCheck;
    @BindView(R.id.tv_agreed)
    TextView tvAgreed;
    @BindView(R.id.haolly_agreement)
    TextView haollyAgreement;
    private Subscription subscription;
    private boolean isShow;
    private String sToken;
    private String imageCode;
    private String pwd;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取图形验证码
        mPresenter.getPicCode();
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

        etPicCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                etPicCode.onFocusChange(view, b);
                if (b) {
                    lineChange.setBackgroundColor(Color.parseColor("#FE7537"));
                } else {
                    lineChange.setBackgroundColor(Color.parseColor("#C3C3C3"));
                }
            }
        });
        edMessageCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                edMessageCode.onFocusChange(view, b);
                if (b) {
                    lineChange2.setBackgroundColor(Color.parseColor("#FE7537"));
                } else {
                    lineChange2.setBackgroundColor(Color.parseColor("#C3C3C3"));
                }
            }
        });
        edPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                edPassword.onFocusChange(view, b);
                if (b) {
                    lineChange3.setBackgroundColor(Color.parseColor("#FE7537"));
                } else {
                    lineChange3.setBackgroundColor(Color.parseColor("#C3C3C3"));
                }
            }
        });
        etPicCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (cbCheck.isChecked() && !TextUtils.isEmpty(charSequence) && !TextUtils.isEmpty(edMessageCode.getText().toString().trim()) && !TextUtils.isEmpty(edPassword.getText().toString().trim())) {
                    btnNext.setEnabled(true);
                } else {
                    btnNext.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edMessageCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (cbCheck.isChecked() && !TextUtils.isEmpty(charSequence) && !TextUtils.isEmpty(etPicCode.getText().toString().trim()) && !TextUtils.isEmpty(edPassword.getText().toString().trim())) {
                    btnNext.setEnabled(true);
                } else {
                    btnNext.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (cbCheck.isChecked() && !TextUtils.isEmpty(charSequence) && !TextUtils.isEmpty(edMessageCode.getText().toString().trim()) && !TextUtils.isEmpty(etPicCode.getText().toString().trim())) {
                    btnNext.setEnabled(true);
                } else {
                    btnNext.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        RxCompoundButton.checkedChanges(cbCheck).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {


                if (aBoolean && !TextUtils.isEmpty(edPassword.getText().toString().trim()) && !TextUtils.isEmpty(edMessageCode.getText().toString().trim()) && !TextUtils.isEmpty(etPicCode.getText().toString().trim())) {
                    btnNext.setEnabled(true);
                } else {
                    btnNext.setEnabled(false);
                }
            }
        });
        //发送验证码按钮
        subscription = RxView.clicks(tvMessageCode).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                imageCode = etPicCode.getText().toString().trim();
                if (TextUtils.isEmpty(imageCode)) {
                    UIUtils.showToastCommon(mContext, Config.TIP_IMAGE);
                    return;
                }
                mPresenter.sendSMS(tvMessageCode, BaseApplication.phone, sToken, "register", imageCode);
            }
        });
        //注册
        subscription = RxView.clicks(btnNext).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                String smscode = edMessageCode.getText().toString().trim();
                pwd = edPassword.getText().toString().trim();
                String inviteCode = etInvitationCode.getText().toString().trim();
                if (TextUtils.isEmpty(smscode)) {
                    UIUtils.showToastCommon(mContext, Config.TIP_SMS);
                    return;
                }
                if (!WYUtils.checkPass(pwd) || TextUtils.isEmpty(pwd)) {
                    UIUtils.showToastCommon(mContext, Config.TIP_PASSS);
                    return;
                }
                if (!TextUtils.isEmpty(inviteCode) && !WYUtils.checkPass(inviteCode)) {

                    UIUtils.showToastCommon(mContext, Config.TIP_INVITE);
                    return;

                }
                mPresenter.regsieter(BaseApplication.phone, pwd, imageCode, smscode, sToken, inviteCode,WYUtils.getAppMetaData(mContext,"UMENG_CHANNEL"));
            }
        });

    }

    @OnClick({R.id.iv_pic_code, R.id.tv_message_code, R.id.iv_show_pwd, R.id.iv_up_down, R.id.btn_next, R.id.haolly_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_pic_code:
                mPresenter.getPicCode();
                break;

            case R.id.iv_show_pwd:
                if (!isShow) {
                    //可见
                    ivShowPwd.setImageResource(R.drawable.icon_eye_off);
                    edPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {

                    //不可见
                    ivShowPwd.setImageResource(R.drawable.icon_eye_on);
                    edPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                isShow = !isShow;
                edPassword.postInvalidate();
                break;
            case R.id.iv_up_down:
                if (etInvitationCode.getVisibility() == View.GONE) {
                    etInvitationCode.setVisibility(View.VISIBLE);
                    ivUpDown.setImageResource(R.drawable.icon_up);
                } else {
                    etInvitationCode.setVisibility(View.GONE);
                    ivUpDown.setImageResource(R.drawable.icon_down);
                }
                break;

            case R.id.haolly_agreement:
                openActivity(WebActivity.getWebIntent(mContext,"平台注册协议","https://hlwm.chinazyjr.com/html/Agreement/registerAgreement.html"));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void showIamgeCode(TokenBean tokenBean) {
        sToken = tokenBean.model.token;
        Glide.with(mContext).load(NetConstantValues.image_url + "?token=" + tokenBean.model.token).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(ivPicCode);
    }

    @Override
    public void countDown(boolean b) {

    }

    @Override
    public void showSucess() {
        Intent intent = new Intent(mContext, RegisterSuccessActivity.class);
        intent.putExtra("pwd",pwd);
        startActivity(intent);


    }
}
