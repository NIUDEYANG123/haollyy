package com.haolyy.haolyy.ui.third;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.custom.ClearEditText;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.entity.bank.QueryBankBean;
import com.haolyy.haolyy.ui.third.presenter.QueryBankPresenter;
import com.haolyy.haolyy.ui.third.view.QueryBankView;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wyman
 * @date 2018/2/7
 * description : 充值前
 */

public class RechargeActivity extends BaseActivity<QueryBankPresenter, QueryBankView> implements QueryBankView {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.iv_bank_logo)
    ImageView ivBankLogo;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_bank_type)
    TextView tvBankType;
    @BindView(R.id.tv_bank_card_no)
    TextView tvBankCardNo;
    @BindView(R.id.tv_bank_limit)
    TextView tvBankLimit;
    @BindView(R.id.account_residue_money)
    TextView accountResidueMoney;
    @BindView(R.id.tv_recharge_next)
    TextView tvRechargeNext;
    @BindView(R.id.et_recharge_amount)
    ClearEditText etRechargeAmount;
    private QueryBankBean queryBankBean;
    private String trans_amount;
    private Double singleTransQuota;
    private String action;

    @Override
    protected QueryBankPresenter createPresenter() {
        return new QueryBankPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        action = getIntent().getAction();
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener(){
            @Override
            public void OnLeftButtonClicked() {
                finish();
            }

            @Override
            public void OnRightButtonClicked() {
                Intent intent = new Intent(mContext, BankListActivity.class);
                intent.putExtra("busiType", "1");
                intent.putExtra("flag", "recharge");
                startActivity(intent);

            }
        });

        etRechargeAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                //只能输入小数点后两位
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        etRechargeAmount.setText(s);
                        etRechargeAmount.setSelection(s.length());
                    }
                }
                if (s.toString().trim().equals(".")) {
                    s = "0" + s;
                    etRechargeAmount.setText(s);
                    etRechargeAmount.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        etRechargeAmount.setText(s.subSequence(0, 1));
                        etRechargeAmount.setSelection(1);
                        return;
                    }
                }
                if (!TextUtils.isEmpty(s)) {
                    tvRechargeNext.setEnabled(true);
                } else {
                    tvRechargeNext.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.queryBank();
    }


    @Override
    public void showBank(QueryBankBean queryBankBean) {
        this.queryBankBean = queryBankBean;

        BaseApplication.userCustId = queryBankBean.model.ThirdUserId;

        singleTransQuota =Double.valueOf(queryBankBean.model.SingleTransQuota);
        tvBankName.setText(queryBankBean.model.BankName);

        WYUtils.showBankLogo(queryBankBean.model.BankCode, ivBankLogo);


        tvBankCardNo.setText(WYUtils.bankCardProcess(queryBankBean.model.BankCardNo));

        tvBankLimit.setText("单笔限额:" + singleTransQuota / 10000 + "W" + "  单日限额:" + Double.valueOf(queryBankBean.model.CardDailyTransQuota) / 10000 + "W");
        accountResidueMoney.setText(queryBankBean.model.AvailableAmount + "元");
    }
    double v;
    @OnClick(R.id.tv_recharge_next)
    public void onViewClicked() {
        trans_amount =etRechargeAmount.getText().toString().trim();
        if(TextUtils.isEmpty(trans_amount)){
            UIUtils.showToastCommon(mContext, "请输入充值金额");
            return;
        }else {
           v = Double.parseDouble(trans_amount);
        }
        if (v< 100) {
            UIUtils.showToastCommon(mContext, "充值金额不能低于100");
            return;
        }
        if (v > singleTransQuota) {
            UIUtils.showToastCommon(mContext, "充值金额不能大于单次限额额度");
            return;
        }
        startActivity(RechargeNextActivity.getIntentInstance(mContext, trans_amount,
                queryBankBean.model.Mobile, queryBankBean.model.BankCardNo,
                queryBankBean.model.BankCode,queryBankBean.model.AvailableAmount,action));
    }
}
