package com.haolyy.haolyy.ui.third;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.ActivityCollector;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.custom.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wyman
 * @date 2018/2/7
 * description :
 */

public class RechargeError extends BaseActivity {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    private String reason;
    private String action;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    public static Intent getFailRechargeIntent(Context context, String reason, String action) {
        Intent intent = new Intent(context, RechargeError.class);
        intent.putExtra("reason", reason);
        intent.setAction(action);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_error);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        action = getIntent().getAction();
        reason = getIntent().getStringExtra("reason");
        tvMsg.setText(reason);
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                ActivityCollector.finishActivity(RechargeActivity.class);
                ActivityCollector.finishActivity(RechargeNextActivity.class);
                finish();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });
    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        if (!TextUtils.isEmpty(action) && action.equals("invest")) {
            ActivityCollector.finishActivity(RechargeNextActivity.class);
            ActivityCollector.finishActivity(RechargeActivity.class);
        } else if (!TextUtils.isEmpty(action) && action.equals("withdraw")) {
            startActivity(new Intent(mContext, WithdrawActivity.class));
        } else {
            startActivity(new Intent(mContext, RechargeActivity.class));
        }
        finish();

    }
}
