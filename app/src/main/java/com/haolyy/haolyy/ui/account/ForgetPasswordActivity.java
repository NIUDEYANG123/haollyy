package com.haolyy.haolyy.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.custom.ClearEditText;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.entity.TokenBean;
import com.haolyy.haolyy.ui.account.presenter.RegisterPresenter;
import com.haolyy.haolyy.ui.account.view.RegisterView;
import com.haolyy.haolyy.utils.AppToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wyman
 * @date 2018/2/28
 * description : 弃用
 */

public class ForgetPasswordActivity extends BaseActivity<RegisterPresenter, RegisterView> implements RegisterView {

    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.et_pic_code)
    ClearEditText etPicCode;
    @BindView(R.id.iv_pic_code)
    ImageView ivPicCode;
    @BindView(R.id.btn_next)
    TextView btnNext;
    private String sToken;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取图形验证码
        mPresenter.getPicCode();
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

    }

    @OnClick({R.id.iv_pic_code, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_pic_code:
                mPresenter.getPicCode();
                break;
            case R.id.btn_next:

                if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
                    AppToast.showShortText(mContext, "请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(etPicCode.getText().toString().trim())) {
                    AppToast.showShortText(mContext, "请输入验证码");
                    return;
                }
                Intent intent = new Intent(mContext, ResetPasswordActivity.class);
                intent.putExtra("sToken", sToken);
                intent.putExtra("phone", etPhone.getText().toString().trim());
                intent.putExtra("imgCode", etPicCode.getText().toString().trim());
                startActivity(intent);

                break;
        }
    }
}
