package com.haolyy.haolyy.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.ActivityCollector;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.ui.account.LoginActivity;
import com.haolyy.haolyy.ui.my.View.ResetPassView;
import com.haolyy.haolyy.ui.my.presenter.ResetPassPresenter;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shanghai on 2018/3/5.
 */

public class ChangePassActivity extends BaseActivity<ResetPassPresenter, ResetPassView> implements ResetPassView {

    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.et_pass_new)
    EditText etPassNew;
    @BindView(R.id.et_pass2)
    EditText etPass2;
    @BindView(R.id.btn_pass2)
    TextView btnPass2;
    private String passnew;
    private String pass1;
    private String pass2;

    @Override
    protected ResetPassPresenter createPresenter() {
        return new ResetPassPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                closeActivity();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });
    }

    @Override
    public void close() {
        ActivityCollector.finishAll();
        openActivity(new Intent(mContext, LoginActivity.class));
    }

    @OnClick(R.id.btn_pass2)
    public void onViewClicked() {
        pass1 = etPass.getText().toString().trim();
        passnew = etPassNew.getText().toString().trim();
        pass2 = etPass2.getText().toString().trim();
        if (TextUtils.isEmpty(pass1)) {
            UIUtils.showToastCommon(mContext, "请输入登录密码");
            return;
        }
        if (TextUtils.isEmpty(passnew) || !WYUtils.checkPass(passnew)) {
            UIUtils.showToastCommon(mContext, Config.TIP_PASSS);
            return;
        }
        if (TextUtils.isEmpty(pass2) || !WYUtils.checkPass(pass2)) {
            UIUtils.showToastCommon(mContext, Config.TIP_PASSS);
            return;
        }
        if (!passnew.equals(pass2)) {
            UIUtils.showToastCommon(mContext, "两次输入密码不一致");
            return;
        }
        mPresenter.changePass(pass1, passnew, pass2);
    }
}
