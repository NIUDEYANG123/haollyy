package com.haolyy.haolyy.ui.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.style.SubscriptSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BaseFragment;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.entity.TokenBean;
import com.haolyy.haolyy.ui.my.View.PhoneAuthView;
import com.haolyy.haolyy.ui.my.presenter.PhoneAuthPresenter;
import com.haolyy.haolyy.utils.UIUtils;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by shanghai on 2018/2/8.
 */

public class PhoneAuth2 extends BaseFragment<PhoneAuthPresenter, PhoneAuthView> implements PhoneAuthView {
    @BindView(R.id.iv_image_code1)
    ImageView ivImageCode1;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.tv_message1)
    TextView tvMessage1;
    @BindView(R.id.tv_auth_next)
    TextView tvAuthNext;
    Unbinder unbinder;
    @BindView(R.id.et_image)
    EditText etImage;
    @BindView(R.id.et_sms)
    EditText etSms;
    private PhoneAuthActivity activity;
    private String sToken;
    private Subscription subscription;
    private String imageCode;

    @Override
    protected PhoneAuthPresenter createPresenter() {
        return new PhoneAuthPresenter(mContext);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth2, container, false);
        unbinder = ButterKnife.bind(this, view);
        activity = (PhoneAuthActivity) getActivity();
        mPresenter.getPicCode();

        //发送验证码按钮
        subscription = RxView.clicks(tvMessage1).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Action1<Void>() {


            @Override
            public void call(Void aVoid) {
                imageCode = etImage.getText().toString().trim();
                if (TextUtils.isEmpty(imageCode)) {
                    UIUtils.showToastCommon(mContext, Config.TIP_IMAGE);
                    return;
                }
                mPresenter.sendSMS(tvMessage1, BaseApplication.mUserName, sToken, "update", imageCode);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @OnClick({R.id.iv_image_code1, R.id.tv_auth_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_image_code1:
                mPresenter.getPicCode();
                break;
            case R.id.tv_auth_next:
                String smscode = etSms.getText().toString().trim();
                if (TextUtils.isEmpty(smscode)) {
                    UIUtils.showToastCommon(mContext, "请输入正确的短信验证码");
                    return;
                }
                mPresenter.validSmsCode(imageCode, smscode,sToken);
                break;
        }
    }

    @Override
    public void showIamgeCode(TokenBean tokenBean) {
        sToken = tokenBean.model.token;
        Glide.with(mContext).load(NetConstantValues.image_url + "?token=" + tokenBean.model.token).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(ivImageCode1);
    }

    @Override
    public void showSucess() {

    }

    @Override
    public void next() {
        activity.switchPage(2);
    }
}
