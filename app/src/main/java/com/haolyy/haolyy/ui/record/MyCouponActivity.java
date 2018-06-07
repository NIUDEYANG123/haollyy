package com.haolyy.haolyy.ui.record;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import com.androidkun.xtablayout.XTabLayout;
import com.haolyy.haolyy.MainActivity;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.TabAdapter;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.entity.userinfo.AccountCouponbean;
import com.haolyy.haolyy.ui.record.presenter.CardQuanPresenter;
import com.haolyy.haolyy.ui.record.view.CardQuanView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wyman
 * @date 2018/2/8
 * description : 卡券
 */

public class MyCouponActivity extends BaseActivity {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.tablayout)
    XTabLayout tablayout;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    private List<Fragment> mDatas;
    private List<String> mTitles;
    private Coupon1Fragment coupon1Fragment;
    private Coupon2Fragment coupon2Fragment;
    private Coupon3Fragment coupon3Fragment;
    private String action;


    @Override
    protected CardQuanPresenter createPresenter() {
        return new CardQuanPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupon);
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
                if (!TextUtils.isEmpty(action) && action.equals("register")) {
                    openActivity(MainActivity.getMainIntent(mContext, 3));
                } else {
                    finish();
                }
            }
            @Override
            public void OnRightButtonClicked() {
            }
        });
        mDatas = new ArrayList<>();
        mTitles = new ArrayList<>();
        coupon1Fragment = new Coupon1Fragment();
        coupon2Fragment = new Coupon2Fragment();
        coupon3Fragment = new Coupon3Fragment();
        mTitles.add("未使用");
        mTitles.add("已使用");
        mTitles.add("已失效");

        mDatas.add(coupon1Fragment);
        mDatas.add(coupon2Fragment);
        mDatas.add(coupon3Fragment);
        viewpage.setAdapter(new TabAdapter(getSupportFragmentManager(), mDatas, mTitles));
        tablayout.setxTabDisplayNum(3);//需要写在setupWithViewPager前
        tablayout.setupWithViewPager(viewpage);
    }


}
