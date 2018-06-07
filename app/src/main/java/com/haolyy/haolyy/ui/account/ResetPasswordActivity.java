package com.haolyy.haolyy.ui.account;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.custom.ClearEditText;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.entity.TokenBean;
import com.haolyy.haolyy.ui.account.presenter.RegisterPresenter;
import com.haolyy.haolyy.ui.account.view.RegisterView;
import com.haolyy.haolyy.utils.AppToast;
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
 * @date 2018/2/6
 * description :
 */

public class ResetPasswordActivity extends BaseActivity<RegisterPresenter, RegisterView> implements RegisterView {
    @BindView(R.id.topBar)
    TopBar topBar;

    @BindView(R.id.ed_message_code)
    ClearEditText edMessageCode;
    @BindView(R.id.tv_message_code)
    TextView tvMessageCode;

    @BindView(R.id.ed_password)
    ClearEditText edPassword;
    @BindView(R.id.iv_show_pwd)
    ImageView ivShowPwd;


    @BindView(R.id.btn_next)
    TextView btnNext;

    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.et_pic_code)
    ClearEditText etPicCode;
    @BindView(R.id.iv_pic_code)
    ImageView ivPicCode;
    @BindView(R.id.line_change)
    View lineChange;
    @BindView(R.id.line_change2)
    View lineChange2;
    @BindView(R.id.line_change3)
    View lineChange3;
    @BindView(R.id.line_change4)
    View lineChange4;

    private Subscription subscription;
    private boolean isShow;
    private String sToken;
    private String phone;
    private String imageCode;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取图形验证码
        mPresenter.getPicCode();
//        mPresenter.sendSMS(tvMessageCode, phone, sToken, "forget", imageCode);
    }

    private void init() {
        etPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        etPicCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                etPicCode.onFocusChange(view, b);
                if (b) {
                    lineChange2.setBackgroundColor(Color.parseColor("#FE7537"));
                } else {
                    lineChange2.setBackgroundColor(Color.parseColor("#C3C3C3"));
                }
            }
        });
        edMessageCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                edMessageCode.onFocusChange(view, b);
                if (b) {
                    lineChange3.setBackgroundColor(Color.parseColor("#FE7537"));
                } else {
                    lineChange3.setBackgroundColor(Color.parseColor("#C3C3C3"));
                }
            }
        });
        edPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                edPassword.onFocusChange(view, b);
                if (b) {
                    lineChange4.setBackgroundColor(Color.parseColor("#FE7537"));
                } else {
                    lineChange4.setBackgroundColor(Color.parseColor("#C3C3C3"));
                }
            }
        });

//        tvPhone.setText(WYUtils.phoneProcess(phone));

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
        subscription = RxView.clicks(tvMessageCode).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                imageCode = etPicCode.getText().toString().trim();
                phone = etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(imageCode)) {
                    UIUtils.showToastCommon(mContext, Config.TIP_IMAGE);
                    return;
                }
                mPresenter.sendSMS(tvMessageCode, phone, sToken, "forget", imageCode);
            }
        });
        //重置密码
        subscription = RxView.clicks(btnNext).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                String smscode = edMessageCode.getText().toString().trim();
                String pwd = edPassword.getText().toString().trim();
                phone = etPhone.getText().toString().trim();
                imageCode = etPicCode.getText().toString().trim();

                if (TextUtils.isEmpty(phone)) {
                    AppToast.showShortText(mContext, "请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(etPicCode.getText().toString().trim())) {
                    AppToast.showShortText(mContext, "请输入验证码");
                    return;
                }
                if (TextUtils.isEmpty(smscode)) {
                    UIUtils.showToastCommon(mContext, Config.TIP_SMS);
                    return;
                } else if (!WYUtils.checkPass(pwd) || TextUtils.isEmpty(pwd)) {
                    UIUtils.showToastCommon(mContext, Config.TIP_PASSS);
                    return;
                }
                mPresenter.resetPwd(phone, pwd, imageCode, smscode, sToken);
            }
        });

    }

    @OnClick({R.id.iv_show_pwd, R.id.iv_pic_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {


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

            case R.id.iv_pic_code:
                mPresenter.getPicCode();
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
        startActivity(LoginActivity.getIntent(mContext, phone));
    }
}
