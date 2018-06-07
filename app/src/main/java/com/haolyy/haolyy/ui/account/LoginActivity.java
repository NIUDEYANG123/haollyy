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
import android.widget.ImageView;
import android.widget.TextView;

import com.haolyy.haolyy.MainActivity;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.TestActivity;
import com.haolyy.haolyy.base.ActivityCollector;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.config.ConstantKey;
import com.haolyy.haolyy.custom.ClearEditText;
import com.haolyy.haolyy.custom.CustomerDialog;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.guesture.GesturePwdLoginActivity;
import com.haolyy.haolyy.guesture.GesturePwdSetActivity;
import com.haolyy.haolyy.ui.account.presenter.LoginPresenter;
import com.haolyy.haolyy.ui.account.view.LoginView;
import com.haolyy.haolyy.ui.third.OpenBankActivity;
import com.haolyy.haolyy.utils.AppToast;
import com.haolyy.haolyy.utils.LockPatternUtils;
import com.haolyy.haolyy.utils.LogUtils;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by wyman on 2018/2/5.
 */

public class LoginActivity extends BaseActivity<LoginPresenter, LoginView> implements LoginView {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.et_password)
    ClearEditText et_password;
    @BindView(R.id.line_change)
    View lineChange;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.iv_show_pwd)
    ImageView ivShowPwd;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    private Subscription subscription;
    private boolean isShow;
    private CustomerDialog dialog;
    private String action;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    public static Intent getIntent(Context mContext, String s, String invest) {
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.putExtra("phone", s);
        intent.setAction(invest);
        return null;
    }

    public static Intent getIntent(Context context, String phone) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("phone", phone);
        return intent;
    }

    private void init() {
        action = getIntent().getAction();
        if (!TextUtils.isEmpty(getIntent().getStringExtra("phone"))) {
            BaseApplication.phone = getIntent().getStringExtra("phone");
        }
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                finish();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });

        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                et_password.onFocusChange(view, b);
                if (b) {
                    lineChange.setBackgroundColor(Color.parseColor("#FE7537"));
                } else {
                    LogUtils.e("失去焦点");
                    lineChange.setBackgroundColor(Color.parseColor("#C3C3C3"));
                }
            }
        });
        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence)) {
                    btnNext.setEnabled(false);

                } else {
                    btnNext.setEnabled(true);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //登录按钮
        subscription = RxView.clicks(btnNext).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                if (TextUtils.isEmpty(et_password.getText().toString().trim())) {
                    AppToast.showShortText(mContext, "请输入登录密码");
                    return;
                }
                mPresenter.login(BaseApplication.phone, et_password.getText().toString().trim());
            }
        });


    }

    @OnClick({R.id.iv_show_pwd, R.id.tv_forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_show_pwd:
                if (!isShow) {
                    //可见
                    ivShowPwd.setImageResource(R.drawable.icon_eye_off);
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //不可见
                    ivShowPwd.setImageResource(R.drawable.icon_eye_on);
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                isShow = !isShow;
                et_password.postInvalidate();
                break;
            case R.id.tv_forget_pwd:
                startActivity(new Intent(mContext, ResetPasswordActivity.class));
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
    public void LoginSuccess() {
        //设置手势密码
       if (!TextUtils.isEmpty(action) && action.equals("invest")) {
            finish();
            ActivityCollector.finishActivity(CheckUsernameActivity.class);
        } else {
           startActivity(GesturePwdSetActivity.getIntentInstance(mContext, 0));
        }
    }

    @Override
    public void loginError(String msg) {
        dialog = new CustomerDialog(mContext);
        dialog.loginTips(view -> {
            if (view.getId() == R.id.login_left) {
                dialog.dismiss();
            } else if (view.getId() == R.id.login_right) {
                dialog.dismiss();
                startActivity(new Intent(mContext, ResetPasswordActivity.class));
            }
        }, msg).show();
    }


}
