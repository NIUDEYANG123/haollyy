package com.haolyy.haolyy.ui.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.WebActivity;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.custom.MyItem;
import com.haolyy.haolyy.entity.my.HomePageBean;
import com.haolyy.haolyy.entity.my.ProductManageDetailBean;
import com.haolyy.haolyy.ui.my.View.ProductManageView;
import com.haolyy.haolyy.ui.my.presenter.ProductManagePresenter;
import com.haolyy.haolyy.utils.AccountUtil;
import com.haolyy.haolyy.utils.WYUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shanghai on 2018/2/8.
 */

public class AssetDetailActivity extends BaseActivity<ProductManagePresenter, ProductManageView> implements ProductManageView {
    @BindView(R.id.iv_finish)
    TextView ivFinish;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.titleBar)
    RelativeLayout titleBar;
    @BindView(R.id.tv_detail_amount)
    TextView tvDetailAmount;
    @BindView(R.id.tv_forward_help)
    TextView tvForwardHelp;
    @BindView(R.id.tv_forward_revenue)
    TextView tvForwardRevenue;
    @BindView(R.id.tv_have_help)
    TextView tvHaveHelp;
    @BindView(R.id.tv_have_revenue)
    TextView tvHaveRevenue;
    @BindView(R.id.my_join_name)
    MyItem myJoinName;
    @BindView(R.id.my_join_time)
    MyItem myJoinTime;
    @BindView(R.id.my_join_term)
    MyItem myJoinTerm;
    @BindView(R.id.my_end_time)
    MyItem myEndTime;
    @BindView(R.id.my_year_profit)
    MyItem myYearProfit;
    @BindView(R.id.my_return_way)
    MyItem myReturnWay;
    @BindView(R.id.my_auto_record)
    MyItem myAutoRecord;
    @BindView(R.id.my_proctor)
    MyItem myProctor;
    @BindView(R.id.tv_detail_type)
    TextView tvDetailType;
    @BindView(R.id.tv_rate_platform)
    TextView tvRatePlatform;
    @BindView(R.id.iv_up_arrow)
    ImageView ivUpArrow;
    @BindView(R.id.my_coupon_profit)
    MyItem myCouponProfit;
    @BindView(R.id.my_append_profit)
    MyItem myAppendProfit;
    @BindView(R.id.ll_platform)
    LinearLayout llPlatform;
    @BindView(R.id.tv_open_continue)
    TextView tvOpenContinue;
    @BindView(R.id.ll_expend)
    LinearLayout llExpend;
    private String orderNo;
    private String statusType;
    private String isContinue;//0未开启 1开启
    private ProductManageDetailBean.ModelBean.ListDetailsBean listDetails;
    private String borrowName;
    private String cashNo;
    private String borrowNo;
    private String borrow_url;
    private String applyNo;

    public static Intent getReturnIntent(Context context, String cashNo, String isContinue, String orderNo, String statusType, String borrowName, String borrowNo, String applyNo) {
        Intent intent = new Intent(context, AssetDetailActivity.class);
        intent.putExtra("orderNo", orderNo);
        intent.putExtra("statusType", statusType);
        intent.putExtra("isContinue", isContinue);
        intent.putExtra("borrowName", borrowName);
        intent.putExtra("cashNo", cashNo);
        intent.putExtra("borrowNo", borrowNo);
        intent.putExtra("applyNo", applyNo);
        return intent;
    }

    @Override
    protected ProductManagePresenter createPresenter() {
        return new ProductManagePresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_detail);
        ButterKnife.bind(this);
        orderNo = getIntent().getStringExtra("orderNo");
        statusType = getIntent().getStringExtra("statusType");
        isContinue = getIntent().getStringExtra("isContinue");
        borrowName = getIntent().getStringExtra("borrowName");
        cashNo = getIntent().getStringExtra("cashNo");
        borrowNo = getIntent().getStringExtra("borrowNo");
        applyNo = getIntent().getStringExtra("applyNo");
        initView();
    }

    private void initView() {
        tvTitle.setText("加入详情");
        mPresenter.queryDetail(orderNo, statusType);
        if (!TextUtils.isEmpty(applyNo)) {
            mPresenter.getBaoquan(applyNo);
        } else {
            myProctor.setVisibility(View.GONE);
        }
    }

    @Override
    public void getDetail(ProductManageDetailBean detailBean) {
        listDetails = detailBean.model.listDetails;
        tvDetailType.setText(listDetails.statusName);
        tvDetailAmount.setText(AccountUtil.StringToMString(listDetails.investAmount));
        tvForwardRevenue.setText(AccountUtil.StringToMString(listDetails.targetProfit) + "元");
        tvHaveRevenue.setText(AccountUtil.StringToMString(listDetails.yesProfit) + "元");
        myJoinName.setTextRight(borrowName);
        myJoinTime.setTextRight(listDetails.createTime);
        myEndTime.setTextRight(listDetails.interestEndDate);
        myJoinTerm.setTextRight(listDetails.periodLength);
        myYearProfit.setTextRight(WYUtils.getSingleNumStr(Double.parseDouble(listDetails.annualizedRate)) + "%");
        myCouponProfit.setTextRight(listDetails.couponRate + "%");
        myAppendProfit.setTextRight(listDetails.appendRate + "%");
        tvRatePlatform.setText(AccountUtil.add(listDetails.appendRate, listDetails.couponRate, 0) + "%");
        myReturnWay.setTextRight(WYUtils.getProfitPlan(listDetails.profitPlan + ""));
        if (listDetails.periodLength.equals("1周") && !borrowName.contains("新手")){
            if (!statusType.equals("1")) {
                tvOpenContinue.setVisibility(View.GONE);
            } else {
                if (isContinue.equals("1")) {
                    tvOpenContinue.setText("关闭续期");
                } else {
                    tvOpenContinue.setText("开启续期");
                }
            }
        } else {
            tvOpenContinue.setVisibility(View.GONE);
        }
    }

    @Override
    public void showAsset(HomePageBean bean) {

    }

    @Override
    public void changeOpen(String model) {
        if (!model.equals("1")) {
            tvOpenContinue.setText("开启续期");
        } else {
            tvOpenContinue.setText("关闭续期");
        }
    }

    @Override
    public void contractUrl(String model) {
        this.borrow_url = model;
    }

    @OnClick({R.id.iv_finish, R.id.ll_expend, R.id.tv_open_continue, R.id.my_proctor, R.id.my_auto_record, R.id.my_return_way})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_expend:
                if (llPlatform.getVisibility() == View.VISIBLE) {
                    llPlatform.setVisibility(View.GONE);
                } else {
                    llPlatform.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_open_continue:
                mPresenter.continueOpen(cashNo);
                break;
            case R.id.my_auto_record:
                openActivity(CashHeldActivity.getReturnIntent(mContext, cashNo));
                break;
            case R.id.my_proctor:
                openActivity(WebActivity.getWebIntent(mContext, "出借咨询服务协议", borrow_url));
                break;
            case R.id.my_return_way:
                openActivity(AssetRepayPlanActivity.getReturnIntent(mContext, borrowNo, cashNo));
                return;
            case R.id.iv_finish:
                closeActivity();
                break;
        }
    }
}
