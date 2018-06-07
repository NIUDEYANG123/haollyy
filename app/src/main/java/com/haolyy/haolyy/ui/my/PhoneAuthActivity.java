package com.haolyy.haolyy.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.TextView;

import com.haolyy.haolyy.MainActivity;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.custom.NoScrollViewPager;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.ui.account.CheckUsernameActivity;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shanghai on 2018/2/8.
 */

public class PhoneAuthActivity extends BaseActivity {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.vp_bind_phone)
    NoScrollViewPager vpBindPhone;
    @BindView(R.id.tv_success)
    TextView tvSuccess;
    @BindView(R.id.tv_auth_phone)
    TextView tvAuthPhone;
    @BindView(R.id.tv_back_account)
    TextView tvBackAccount;
    @BindView(R.id.cs_auth_success)
    ConstraintLayout csAuthSuccess;
    private List<Fragment> fragmentlist = new ArrayList<>();
    private int currentItem = 0;
    private boolean isSucess;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone);
        ButterKnife.bind(this);
        initFragments();
    }

    private void initFragments() {
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                onBackPressed();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });
        PhoneAuth1 phoneAuth1 = new PhoneAuth1();
        PhoneAuth2 phoneAuth2 = new PhoneAuth2();
        PhoneAuth3 phoneAuth3 = new PhoneAuth3();

        fragmentlist.add(phoneAuth1);
        fragmentlist.add(phoneAuth2);
        fragmentlist.add(phoneAuth3);
        vpBindPhone.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentlist.get(position);
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        //vpRegister.setOffscreenPageLimit(2)

    }

    public void switchPage(int page) {
        currentItem = page;
        vpBindPhone.setCurrentItem(page, true);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (isSucess) {
            //不允许返回
        } else {
            if (currentItem == 0) {
                closeActivity();

            } else {
                vpBindPhone.setCurrentItem(currentItem - 1, true);
                currentItem = currentItem - 1;
            }
        }
    }

    public void success(String phone) {
        tvAuthPhone.setText(WYUtils.phoneSecret(phone));
        WYUtils.clearData();
        isSucess = true;
        csAuthSuccess.setVisibility(View.VISIBLE);
        tvBackAccount.postDelayed(() -> {
            openActivity(CheckUsernameActivity.getReturnIntent(mContext, "logout"));
        }, 3000);
    }

    @OnClick(R.id.tv_back_account)
    public void onViewClicked() {
        // openActivity(new Intent(mContext, MainActivity.class));
    }
}
