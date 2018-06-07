package com.haolyy.haolyy.ui.my;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.custom.dialog.DialogDoubleButtom;
import com.haolyy.haolyy.entity.my.TransactionRecordDetailsBean;
import com.haolyy.haolyy.ui.my.View.TRDView;
import com.haolyy.haolyy.ui.my.presenter.TRDPresenter;
import com.haolyy.haolyy.utils.AccountUtil;
import com.haolyy.haolyy.utils.StringUtils;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 交易详情
 * Created by liliang on 2017/9/22.
 */

public class TransactionRecordDetailsActivity extends BaseActivity<TRDPresenter, TRDView> implements TRDView {
    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.trd_type_name)
    TextView trdTypeName;
    @BindView(R.id.trd_type_amount)
    TextView trdTypeAmount;
    @BindView(R.id.trd_withdraw_iv1)
    ImageView trdWithdrawIv1;
    @BindView(R.id.trd_withdraw_time1)
    TextView trdWithdrawTime1;
    @BindView(R.id.trd_withdraw_iv2)
    ImageView trdWithdrawIv2;
    @BindView(R.id.trd_withdraw_time2)
    TextView trdWithdrawTime2;
    @BindView(R.id.trd_withdraw_iv3)
    ImageView trdWithdrawIv3;
    @BindView(R.id.trd_withdraw_time3)
    TextView trdWithdrawTime3;
    @BindView(R.id.trd_withdraw_layout)
    LinearLayout trdWithdrawLayout;
    @BindView(R.id.trd_name_iv)
    ImageView trdNameIv;
    @BindView(R.id.trd_type_tv)
    TextView trdTypeTv;
    @BindView(R.id.trd_left_tv1)
    TextView trdLeftTv1;
    @BindView(R.id.trd_right_tv1)
    TextView trdRightTv1;
    @BindView(R.id.trd_service_charge)
    TextView trdServiceCharge;
    @BindView(R.id.lay_service_charge)
    RelativeLayout layServiceCharge;
    @BindView(R.id.trd_left_tv2)
    TextView trdLeftTv2;
    @BindView(R.id.trd_right_tv2)
    TextView trdRightTv2;
    @BindView(R.id.trd_left_tv3)
    TextView trdLeftTv3;
    @BindView(R.id.trd_right_tv3)
    TextView trdRightTv3;
    @BindView(R.id.trd_left_tv4)
    TextView trdLeftTv4;
    @BindView(R.id.trd_right_tv4)
    TextView trdRightTv4;
    @BindView(R.id.trd_loan_query)
    RelativeLayout trdLoanQuery;
    @BindView(R.id.trd_query)
    RelativeLayout trdQuery;
    @BindView(R.id.trd_withdraw3)
    TextView trdWithdraw3;


    private int type;
    private String orderNo;
    private String bidType, billDate;
    private String ids;

    @Override
    protected TRDPresenter createPresenter() {
        return new TRDPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trd_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        type = getIntent().getIntExtra("type", 0);
        orderNo = getIntent().getStringExtra("orderNo");
        bidType = getIntent().getStringExtra("bidType");
        billDate = getIntent().getStringExtra("billDate");
        ids = getIntent().getStringExtra("ids");
        tvTitle.setText("交易详情");
        mPresenter.getTRD(orderNo, type + "", bidType, billDate,ids);
    }

    @Override
    public void showData(TransactionRecordDetailsBean detailsBean) {
        /**
         *  1:充值；4:加入计划；5：平台奖励；7：提现；6：散标出借；2：回款；3：散标收益；8：退款 13 邀请奖励 14竞猜奖励 15豪享返现 16豪享排行返现；
         */
        trdTypeName.setText(detailsBean.model.typeName);
        trdTypeAmount.setText(AccountUtil.StringToMString(detailsBean.model.amount));
        trdTypeTv.setText(detailsBean.model.tradeType);

        if (type == 1) {//充值
            tvTitle.setText("充值详情");
            trdWithdrawLayout.setVisibility(View.GONE);
            layServiceCharge.setVisibility(View.GONE);
            trdLoanQuery.setVisibility(View.GONE);
            Glide.with(mContext).load(detailsBean.model.imgUrl).into(trdNameIv);
            trdLeftTv1.setText("充值方式");
            trdLeftTv2.setText("充值说明");
            trdLeftTv3.setText("创建时间");
            trdLeftTv4.setText("订单号");

            trdRightTv1.setText(detailsBean.model.rechargeWay);
            trdRightTv2.setText(detailsBean.model.remark);
            trdRightTv3.setText(detailsBean.model.createDate);
            trdRightTv4.setText(detailsBean.model.orderNo);
        } else if (type == 2) {//回款
            tvTitle.setText("回款详情");
            trdWithdrawLayout.setVisibility(View.GONE);
            layServiceCharge.setVisibility(View.GONE);
            trdLoanQuery.setVisibility(View.GONE);
            trdNameIv.setImageResource(R.drawable.icon_refund);
//            if ("1".equals(bidType)){
//                trdLeftTv1.setText("计划名称");
//            }else {
//            }
            trdLeftTv1.setText("回款来源");
            trdLeftTv2.setText("回款说明");
            trdLeftTv3.setText("创建时间");
            trdLeftTv4.setText("订单号");

            trdRightTv1.setText(detailsBean.model.planName);
            trdRightTv2.setText(detailsBean.model.remark);
            trdRightTv3.setText(detailsBean.model.createDate);
            trdRightTv4.setText(detailsBean.model.orderNo);
        } else if (type == 3) {//散标收益
            tvTitle.setText("收益详情");
            trdWithdrawLayout.setVisibility(View.GONE);
            layServiceCharge.setVisibility(View.GONE);
            trdLoanQuery.setVisibility(View.GONE);
            trdNameIv.setImageResource(R.drawable.icon_refund);

            trdLeftTv1.setText("收益来源");
            trdLeftTv2.setText("收益说明");
            trdLeftTv3.setText("创建时间");
            trdLeftTv4.setText("订单号");

            trdRightTv1.setText(detailsBean.model.planName);
            trdRightTv2.setText(detailsBean.model.remark);
            trdRightTv3.setText(detailsBean.model.createDate);
            trdRightTv4.setText(detailsBean.model.orderNo);
        } else if (type == 4) {//加入计划
            tvTitle.setText("加入计划详情");
            trdWithdrawLayout.setVisibility(View.GONE);
            layServiceCharge.setVisibility(View.GONE);

            // TODO: 2018/1/10 暂时隐藏  trdLoanQuery
            trdLoanQuery.setVisibility(View.GONE);
            trdNameIv.setImageResource(R.drawable.icon_refund);

            trdLeftTv1.setText("计划名称");
            trdLeftTv2.setText("出借金额");
            trdLeftTv3.setText("创建时间");
            trdLeftTv4.setText("订单号");

            trdRightTv1.setText(detailsBean.model.planName);
            trdRightTv2.setText(AccountUtil.StringToMString(detailsBean.model.amount));
            trdRightTv3.setText(detailsBean.model.createDate);
            trdRightTv4.setText(detailsBean.model.orderNo);
        } else if (type == 5) {//平台奖励
            tvTitle.setText("平台奖励");
            trdWithdrawLayout.setVisibility(View.GONE);
            layServiceCharge.setVisibility(View.GONE);
            trdLoanQuery.setVisibility(View.GONE);
            trdNameIv.setImageResource(R.drawable.icon_refund);

            trdLeftTv1.setText("奖励类型");
            trdLeftTv2.setText("奖励说明");
            trdLeftTv3.setText("创建时间");
            trdLeftTv4.setText("订单号");

            trdRightTv1.setText(detailsBean.model.rewardType);
            trdRightTv2.setText(detailsBean.model.remark);
            trdRightTv3.setText(detailsBean.model.createDate);
            trdRightTv4.setText(detailsBean.model.orderNo);
        }else if (type >= 13) {
            switch (type) {
                case 13:
                    tvTitle.setText("邀请奖励");
                    break;
                case 14:
                    tvTitle.setText("竞猜奖励");
                    break;
                case 15:
                    tvTitle.setText("豪享返现");
                    break;
                case 16:
                    tvTitle.setText("豪享排行返现");
                    break;
            }
            trdWithdrawLayout.setVisibility(View.GONE);
            layServiceCharge.setVisibility(View.GONE);
            trdLoanQuery.setVisibility(View.GONE);
            trdNameIv.setImageResource(R.drawable.icon_refund);

            trdLeftTv1.setText("奖励类型");
            trdLeftTv2.setText("奖励说明");
            trdLeftTv3.setText("创建时间");
            trdLeftTv4.setText("订单号");

            trdRightTv1.setText(detailsBean.model.rewardType);
            trdRightTv2.setText(detailsBean.model.remark);
            trdRightTv3.setText(detailsBean.model.createDate);
            trdRightTv4.setText(detailsBean.model.orderNo);
        }else if (type == 6) {//散标出借
            tvTitle.setText("出借详情");
            trdWithdrawLayout.setVisibility(View.GONE);
            layServiceCharge.setVisibility(View.GONE);
            // TODO: 2018/1/10 暂时隐藏（trdLoanQuery）
            trdLoanQuery.setVisibility(View.GONE);
            trdNameIv.setImageResource(R.drawable.icon_refund);

            trdLeftTv1.setText("借款标题");
            trdLeftTv2.setText("出借金额");
            trdLeftTv3.setText("创建时间");
            trdLeftTv4.setText("订单号");

            trdRightTv1.setText(detailsBean.model.planName);
            trdRightTv2.setText(AccountUtil.StringToMString(detailsBean.model.amount));
            trdRightTv3.setText(detailsBean.model.createDate);
            trdRightTv4.setText(detailsBean.model.orderNo);
        } else if (type == 7) {//提现
            tvTitle.setText("提现详情");
            trdWithdrawLayout.setVisibility(View.VISIBLE);
            layServiceCharge.setVisibility(View.VISIBLE);
            trdLoanQuery.setVisibility(View.GONE);
            Glide.with(mContext).load(detailsBean.model.imgUrl).into(trdNameIv);

            trdWithdrawTime1.setText(detailsBean.model.applyTime);
            if ("交易处理中".equals(detailsBean.model.tradeType)) {
                trdWithdrawIv3.setImageResource(R.drawable.unfinished_iocn3);
                trdWithdraw3.setTextColor(getResources().getColor(R.color.text_4a4a4a));
            } else if ("交易成功".equals(detailsBean.model.tradeType)){
                trdWithdrawIv3.setImageResource(R.drawable.offstock_iocn2);
                trdWithdraw3.setTextColor(getResources().getColor(R.color.bg_FE7537));
            } else {
                //交易失败
                trdWithdrawLayout.setVisibility(View.GONE);
            }
            trdWithdrawTime3.setText(detailsBean.model.doneTime);

            trdServiceCharge.setText(detailsBean.model.serviceFee);

            trdLeftTv1.setText("提现账户");
            trdLeftTv2.setText("到账金额");
            trdLeftTv3.setText("创建时间");
            trdLeftTv4.setText("订单号");

            trdRightTv1.setText(StringUtils.BracketString(detailsBean.model.withdrawalsAccount));
            trdRightTv2.setText(AccountUtil.StringToMString(AccountUtil.sub2(detailsBean.model.amount, detailsBean.model.serviceFee) + ""));
            trdRightTv3.setText(detailsBean.model.createDate);
            trdRightTv4.setText(detailsBean.model.orderNo);
        } else if (type == 8) {//退款
            tvTitle.setText("退款详情");
            trdWithdrawLayout.setVisibility(View.GONE);
            layServiceCharge.setVisibility(View.GONE);
            trdLoanQuery.setVisibility(View.GONE);
            trdNameIv.setImageResource(R.drawable.icon_refund);

            trdLeftTv1.setText("退款原因");
            trdLeftTv2.setText("备注");
            trdLeftTv3.setText("创建时间");
            trdLeftTv4.setText("订单号");

            trdRightTv1.setText(detailsBean.model.refundReason);
            trdRightTv2.setText(detailsBean.model.remark);
            trdRightTv3.setText(detailsBean.model.createDate);
            trdRightTv4.setText(detailsBean.model.orderNo);
        } else {
            tvTitle.setText("交易详情");
            trdNameIv.setImageResource(R.drawable.icon_refund);
            trdWithdrawLayout.setVisibility(View.GONE);
            layServiceCharge.setVisibility(View.GONE);
            trdLoanQuery.setVisibility(View.GONE);
        }

//        if ("1".equals(detailsBean.model.transactionType)) {  //操作方式：1: 收入  2: 支出
//            trdTypeAmount.setText(Html.fromHtml("<font color='#FF535D'>+" + detailsBean.model.transactionAmount + "</font><small><font color='#FF535D'>元</font></small>"));
//        } else {
//            trdTypeAmount.setText(Html.fromHtml("<font color='#20B795'>-" + detailsBean.model.transactionAmount + "</font><small><font color='#20B795'>元</font></small>"));
//        }

    }

    @OnClick({R.id.trd_loan_query, R.id.trd_query, R.id.iv_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                closeActivity();
                break;
            case R.id.trd_loan_query://查看出借详情
                // TODO: 2018/1/3  查询出借详情（暂时隐藏）
                break;
            case R.id.trd_query://对此订单有疑问
                DialogDoubleButtom dialogDoubleButtom=new DialogDoubleButtom(mContext);
                dialogDoubleButtom.setOnDoubleClickListener(new DialogDoubleButtom.OnDoubleClickListener() {
                @Override
                public void executeLeft() {

                }

                @Override
                public void executeRight() {
                    dialogDoubleButtom.dismiss();
                    RxPermissions rxPermissions = new RxPermissions(TransactionRecordDetailsActivity.this);
                    rxPermissions.request(Manifest.permission.CALL_PHONE)
                            .subscribe(aBoolean -> {
                                if (aBoolean) {
                                    //用户同意
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(Config.permisson_service_phone));
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }else {

                                }
                            });
                }
            }).show();
                break;
        }
    }
}
