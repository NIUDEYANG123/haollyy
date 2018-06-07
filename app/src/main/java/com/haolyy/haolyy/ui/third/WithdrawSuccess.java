package com.haolyy.haolyy.ui.third;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.haolyy.haolyy.MainActivity;
import com.haolyy.haolyy.R;
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

public class WithdrawSuccess extends BaseActivity {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.tv_with_draw_money)
    TextView tvWithDrawMoney;
    @BindView(R.id.tv_with_draw_service_charge)
    TextView tvWithDrawServiceCharge;
    @BindView(R.id.tv_real_money)
    TextView tvRealMoney;
    @BindView(R.id.tv_bank_card)
    TextView tvBankCard;
    @BindView(R.id.tv_get_money_time)
    TextView tvGetMoneyTime;
    @BindView(R.id.btn_next)
    Button btnNext;
    private String amount;
    private String getAmount;
    private String fee;
    private String bankCode;
    private String cardNo;
    private String date;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    /**
     *
     * @param context
     * @param amount 提现金额
     * @param getAmount 到账金额
     * @param fee 手续费
     * @param cardNo 银行名
     * @param bankCode 银行编码
     * @return
     */
    public static Intent getSucessWithdrawIntent(Context context, String amount,String getAmount, String fee, String cardNo, String bankCode) {
        Intent intent = new Intent(context, WithdrawSuccess.class);
        intent.putExtra("amount", amount);
        intent.putExtra("getAmount", getAmount);
        intent.putExtra("fee", fee);
        intent.putExtra("bankCode", bankCode);
        intent.putExtra("cardNo", cardNo);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_success);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        amount = getIntent().getStringExtra("amount");
        getAmount = getIntent().getStringExtra("getAmount");
        fee = getIntent().getStringExtra("fee");
        bankCode = getIntent().getStringExtra("bankCode");
        cardNo = getIntent().getStringExtra("cardNo");

        tvWithDrawMoney.setText(amount+"元");
        tvRealMoney.setText(getAmount+"元");
        tvWithDrawServiceCharge.setText(fee+"元");
        tvBankCard.setText(cardNo+"（"+bankCode+"）");
        tvGetMoneyTime.setText(date);

        topBar.setOnItemClickListener(new TopBar.OnItemClickListener(){
            @Override
            public void OnLeftButtonClicked() {

                finish();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });
    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        openActivity(MainActivity.getMainIntent(mContext,3));
    }
}
