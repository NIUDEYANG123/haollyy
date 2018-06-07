package com.haolyy.haolyy.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haolyy.haolyy.MainActivity;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.ui.account.presenter.LoginPresenter;
import com.haolyy.haolyy.ui.account.view.LoginView;
import com.haolyy.haolyy.ui.record.MyCouponActivity;
import com.haolyy.haolyy.ui.third.OpenBankActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wyman
 * @date 2018/2/8
 * description :
 */

public class RegisterSuccessActivity extends BaseActivity<LoginPresenter, LoginView> implements LoginView {


    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.tv_skip)
    TextView tvSkip;
    private String pwd;
    private boolean flag;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(mContext);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);
        ButterKnife.bind(this);
        pwd = getIntent().getStringExtra("pwd");
    }


    @Override
    public void LoginSuccess() {
//        Intent intent = new Intent(mContext, MyCouponActivity.class);
//        intent.setAction("register");
//        openActivity(intent);
        if (flag) {
            startActivity(new Intent(mContext, OpenBankActivity.class));
        } else {
            openActivity(MainActivity.getMainIntent(mContext, 3));
        }

    }


    @Override
    public void loginError(String msg) {

    }

    @OnClick({R.id.btn_next, R.id.tv_skip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                flag = true;
                mPresenter.login(BaseApplication.phone, pwd);
                break;
            case R.id.tv_skip:
                flag = false;
                mPresenter.login(BaseApplication.phone, pwd);
                break;
        }
    }
}
