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
 * description :
 */

public class ResetBankActivity extends BaseActivity<QueryBankPresenter, QueryBankView> implements QueryBankView {
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

    @BindView(R.id.tv_next)
    TextView tvRechargeNext;

    private QueryBankBean queryBankBean;
    private String trans_amount;
    private Double singleTransQuota;
    private String bankName;
    private String bankId;
    private String bankCardNo;
    private String bankPhone;

    @Override
    protected QueryBankPresenter createPresenter() {
        return new QueryBankPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_bank);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                finish();
            }

            @Override
            public void OnRightButtonClicked() {

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


        bankName = queryBankBean.model.BankName;
        bankId = queryBankBean.model.BankCode;

        bankCardNo = queryBankBean.model.BankCardNo;

        bankPhone = queryBankBean.model.Mobile;


        singleTransQuota =Double.valueOf(queryBankBean.model.SingleTransQuota);
        tvBankName.setText(queryBankBean.model.BankName);

        WYUtils.showBankLogo(queryBankBean.model.BankCode, ivBankLogo);


        tvBankCardNo.setText(WYUtils.bankCardProcess(queryBankBean.model.BankCardNo));

        tvBankLimit.setText("单笔限额:" + singleTransQuota / 10000 + "W" + "  单日限额:" + Double.valueOf(queryBankBean.model.CardDailyTransQuota) / 10000 + "W");



    }

    @OnClick(R.id.tv_next)
    public void onViewClicked() {
        Intent intent = new Intent(mContext, ResetBankNextActivity.class);
        intent.putExtra("bankName", bankName);
        intent.putExtra("bankId", bankId);
        intent.putExtra("bankCardNo", bankCardNo);
        intent.putExtra("bankPhone", bankPhone);
        startActivity(intent);
    }
}
