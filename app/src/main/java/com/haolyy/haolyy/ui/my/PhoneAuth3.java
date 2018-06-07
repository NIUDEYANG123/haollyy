package com.haolyy.haolyy.ui.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
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
import com.haolyy.haolyy.base.BasePresenter;
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

public class PhoneAuth3 extends BaseFragment<PhoneAuthPresenter, PhoneAuthView> implements PhoneAuthView {
    @BindView(R.id.et_new_phone)
    EditText etNewPhone;
    @BindView(R.id.line0)
    View line0;
    @BindView(R.id.et_image2)
    EditText etImage2;
    @BindView(R.id.iv_image_code2)
    ImageView ivImageCode2;
    @BindView(R.id.cs_image)
    ConstraintLayout csImage;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.tv_message2)
    TextView tvMessage2;
    @BindView(R.id.tv_auth_change)
    TextView tvAuthChange;
    Unbinder unbinder;
    @BindView(R.id.et_sms2)
    EditText etSms2;
    private PhoneAuthActivity activity;
    private String phone;
    private String sToken;
    private String imageCode;
    private Subscription subscription;

    @Override
    protected PhoneAuthPresenter createPresenter() {
        return new PhoneAuthPresenter(mContext);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth3, container, false);
        unbinder = ButterKnife.bind(this, view);
        activity = (PhoneAuthActivity) getActivity();
        mPresenter.getPicCode();
        //发送验证码按钮
        subscription = RxView.clicks(tvMessage2).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                phone = etNewPhone.getText().toString().trim();
                imageCode = etImage2.getText().toString().trim();
                if (TextUtils.isEmpty(imageCode)) {
                    UIUtils.showToastCommon(mContext, Config.TIP_IMAGE);
                    return;
                }
                mPresenter.sendSMS(tvMessage2, phone, sToken, "update", imageCode);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_image_code2, R.id.tv_auth_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_image_code2:
                phone = etNewPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    UIUtils.showToastCommon(mContext, "请输入正确手机号");
                    return;
                }
                mPresenter.getPicCode();
                break;
            case R.id.tv_auth_change:
                String smscode = etSms2.getText().toString().trim();
                phone=etNewPhone.getText().toString().trim();
                if (TextUtils.isEmpty(smscode)) {
                    UIUtils.showToastCommon(mContext, "请输入正确的短信验证码");
                    return;
                }
                mPresenter.changeMobile(imageCode, smscode,sToken,phone);
                break;

        }
    }

    @Override
    public void showIamgeCode(TokenBean tokenBean) {
        sToken = tokenBean.model.token;
        Glide.with(mContext).load(NetConstantValues.image_url + "?token=" + tokenBean.model.token).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(ivImageCode2);
    }

    @Override
    public void showSucess() {
      activity.success(phone);
    }

    @Override
    public void next() {

    }
}
