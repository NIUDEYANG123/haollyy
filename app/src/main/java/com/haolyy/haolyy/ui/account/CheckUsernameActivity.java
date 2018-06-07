package com.haolyy.haolyy.ui.account;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haolyy.haolyy.MainActivity;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.base.BaseView;
import com.haolyy.haolyy.config.ConstantKey;
import com.haolyy.haolyy.custom.ClearEditText;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.guesture.GesturePwdLoginActivity;
import com.haolyy.haolyy.ui.account.presenter.CheckUsernamePresenter;
import com.haolyy.haolyy.utils.AppToast;
import com.haolyy.haolyy.utils.LockPatternUtils;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.SPUtils;
import com.haolyy.haolyy.utils.WYUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by wyman on 2018/2/5.
 */

public class CheckUsernameActivity extends BaseActivity<CheckUsernamePresenter, BaseView> implements BaseView<BaseBean> {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.btn_phone)
    ClearEditText btnPhone;
    @BindView(R.id.line_change)
    View lineChange;
    @BindView(R.id.btn_next)
    Button btnNext;
    private Subscription subscription;
    private String action;

    public static Intent getReturnIntent(Context context, String action) {
        Intent intent = new Intent(context, CheckUsernameActivity.class);
        intent.setAction(action);
        return intent;
    }

    @Override
    protected CheckUsernamePresenter createPresenter() {
        return new CheckUsernamePresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_username);
        ButterKnife.bind(this);
        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void init() {
        action = getIntent().getAction();
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                onBackPressed();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });
        btnPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                btnPhone.onFocusChange(view, b);
                if (b) {
                    lineChange.setBackgroundColor(Color.parseColor("#FE7537"));
                } else {
                    LogUtils.e("失去焦点");
                    lineChange.setBackgroundColor(Color.parseColor("#C3C3C3"));
                }
            }
        });
        btnPhone.addTextChangedListener(new TextWatcher() {
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
                BaseApplication.phone = btnPhone.getText().toString().trim();


                if (!WYUtils.checkPhone(BaseApplication.phone)) {
                    AppToast.showShortText(mContext, "手机号格式不正确");
                    return;
                }
                mPresenter.isRegister(BaseApplication.phone);

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
    public void getCommonData(BaseBean baseBean) {

    }

    @Override
    public void requestError(String s) {
        startActivity(new Intent(mContext, RegisterActivity.class));
    }

    @Override
    public void requestSuccess(String s) {
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.setAction(action);
        openActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (!TextUtils.isEmpty(action) && "logout".equals(action)) {
            startActivity(MainActivity.getMainIntent(mContext, 0));
        } else {
            finish();
        }
    }
}
