package com.haolyy.haolyy.ui.third;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.haolyy.haolyy.MainActivity;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.ActivityCollector;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.utils.AccountUtil;
import com.haolyy.haolyy.utils.WYUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wyman
 * @date 2018/2/7
 * description :
 */

public class RechargeSuccess extends BaseActivity {

    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.tv_recharge_money)
    TextView tvRechargeMoney;
    @BindView(R.id.tv_recharge_residue_money)
    TextView tvRechargeResidueMoney;
    @BindView(R.id.btn_next)
    Button btnNext;
    private String amount;
    private String recharge_amount;
    private Double aDouble;
    private String action;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    /**
     * @param context
     * @param amount          账户总额
     * @param recharge_amount 充值金额
     * @return
     */
    public static Intent getSucessRechargeIntent(Context context, String amount, String recharge_amount, String action) {
        Intent intent = new Intent(context, RechargeSuccess.class);
        intent.putExtra("amount", amount);
        intent.putExtra("recharge_amount", recharge_amount);
        intent.setAction(action);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_success);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        action = getIntent().getAction();
        amount = getIntent().getStringExtra("amount");
        recharge_amount = getIntent().getStringExtra("recharge_amount");
        if (!TextUtils.isEmpty(amount) && !TextUtils.isEmpty(recharge_amount)) {
            aDouble = Double.parseDouble(recharge_amount) + Double.parseDouble(amount);
        }
        tvRechargeMoney.setText(AccountUtil.StringToMString(recharge_amount));
        tvRechargeResidueMoney.setText(AccountUtil.DoubleToString(aDouble));

        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                ActivityCollector.finishActivity(RechargeNextActivity.class);
                ActivityCollector.finishActivity(RechargeActivity.class);
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
            finish();
        } else {
            openActivity(MainActivity.getMainIntent(mContext, 3));
        }
    }
}
