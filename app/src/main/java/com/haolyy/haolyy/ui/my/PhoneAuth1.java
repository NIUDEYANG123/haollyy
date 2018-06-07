package com.haolyy.haolyy.ui.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BaseFragment;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.utils.WYUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by shanghai on 2018/2/8.
 */

public class PhoneAuth1 extends BaseFragment {
    @BindView(R.id.tv_success)
    TextView tvSuccess;
    @BindView(R.id.tv_auth_phone)
    TextView tvAuthPhone;
    @BindView(R.id.iv_auth_phone)
    ImageView ivAuthPhone;
    @BindView(R.id.tv_change_phone)
    TextView tvChangePhone;
    Unbinder unbinder;
    private PhoneAuthActivity activity;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth1, container, false);
        unbinder = ButterKnife.bind(this, view);
        activity = (PhoneAuthActivity) getActivity();
        tvAuthPhone.setText(WYUtils.phoneSecret(BaseApplication.mUserName));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_change_phone)
    public void onViewClicked() {
        activity.switchPage(1);
    }
}
