package com.haolyy.haolyy.ui.third;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.ActivityCollector;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.base.WebActivity;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.custom.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.haolyy.haolyy.base.BaseApplication.accessToken;

/**
 * @author wyman
 * @date 2018/2/7
 * description :
 */

public class OpenBankSuccess extends BaseActivity {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.btn_next2)
    TextView btnNext2;
    @BindView(R.id.btn_skip)
    Button btnSkip;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_bank_success);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        if (BaseApplication.riskCheck == 0) {
            btnNext2.setVisibility(View.VISIBLE);
        } else {
            btnNext2.setVisibility(View.GONE);
        }

    }



    @OnClick({R.id.btn_next, R.id.btn_next2,R.id.btn_skip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                openActivity(new Intent(mContext, RechargeActivity.class));
                break;
            case R.id.btn_next2:
                ActivityCollector.finishActivity(OpenBankActivity.class);
                startActivity(WebActivity.getWebIntentFlag(mContext, "风险测评", NetConstantValues.risk_testgl + "?userCode=" + BaseApplication.juid + "&token=" + accessToken + "&client=4", "new"));
                break;
            case R.id.btn_skip:
                finish();
                break;
        }
    }
}
