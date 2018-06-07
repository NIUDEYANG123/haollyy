package com.haolyy.haolyy.ui.my;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.ui.my.View.RiskResultView;
import com.haolyy.haolyy.ui.my.presenter.RiskResultPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shanghai on 2018/3/16.
 */

public class RiskResultActivity extends BaseActivity<RiskResultPresenter, RiskResultView> implements RiskResultView {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.iv_result)
    ImageView ivResult;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.tv_max)
    TextView tvMax;

    @Override
    protected RiskResultPresenter createPresenter() {
        return new RiskResultPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_result);
        ButterKnife.bind(this);
        mPresenter.getRiskLevel();
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
    public void showRiskLevel(String s, int amount) {
        tvResult.setText(s);
        if (amount == 0) {
            ivResult.setImageResource(R.drawable.icon_keep);
            tvMax.setText(Html.fromHtml("<font color='#222222'>最高可加入上限为</font><font color='#FF503E'>" + amount + "</font><font color='#222222'>元</font>"));
        } else if (amount == -1) {
            ivResult.setImageResource(R.drawable.icon_enterprising);
            tvMax.setText(Html.fromHtml("<font color='#222222'>最高可加入上限为</font><font color='#FF503E'>出借金额无上限</font>"));
        } else {
            tvMax.setText(Html.fromHtml("<font color='#222222'>最高可加入上限为</font><font color='#FF503E'>" + amount + "万</font><font color='#222222'>元</font>"));
            ivResult.setImageResource(R.drawable.icon_enterprising);
        }


    }
}
