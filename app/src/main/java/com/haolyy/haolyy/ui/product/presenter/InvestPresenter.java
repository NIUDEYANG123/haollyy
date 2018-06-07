package com.haolyy.haolyy.ui.product.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.entity.BBaseBean;
import com.haolyy.haolyy.entity.my.HomePageBean;
import com.haolyy.haolyy.entity.product.BorrowInvestBean;
import com.haolyy.haolyy.entity.product.ProductDetailBean;
import com.haolyy.haolyy.entity.product.RevenueBean;
import com.haolyy.haolyy.entity.userinfo.InvestCouponBean;
import com.haolyy.haolyy.entity.userinfo.RiskBean;
import com.haolyy.haolyy.model.AccountModel;
import com.haolyy.haolyy.model.HuifuModel;
import com.haolyy.haolyy.model.ProductModel;
import com.haolyy.haolyy.ui.product.View.InvestView;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import rx.Subscriber;

import static com.haolyy.haolyy.config.Config.success;


/**
 * Created by niudeyang on 2017/12/23.
 */

public class InvestPresenter extends BasePresenter<InvestView> {
    public InvestPresenter(Context context) {
        super(context);
    }

    /**
     * 标的详情
     *
     * @param borrowNo
     * @param isPlan
     */
    public void queryPlanDetail(final String borrowNo, final boolean isPlan) {
        invoke(ProductModel.getInstance().productDetail(borrowNo), new ProgressSubscriber<ProductDetailBean>(new SubscriberOnNextListener<ProductDetailBean>() {
            @Override
            public void onNext(ProductDetailBean productDetailBean) {

                getView().showDetail(productDetailBean);

            }

            @Override
            public void onError(Throwable e) {

            }
        }, mContext));
    }

    /**
     * 获取用户余额
     */
    public void queryUserInfo() {
        invoke(AccountModel.getInstance().getHomePage(), new ProgressSubscriber<HomePageBean>(new SubscriberOnNextListener<HomePageBean>() {
            @Override
            public void onNext(HomePageBean homePageBean) {
                getView().showAccountinfo(homePageBean);
            }

            @Override
            public void onError(Throwable e) {

            }
        }, mContext));
    }

    /**
     * @param amount       金额
     * @param rate         所有利率相加
     * @param periodLength 周期
     * @param periodUnit   单位
     * @param profitPlan   还款方式
     */
    public void getExpectedRevenue(final String amount, String rate, String periodLength, String periodUnit, String profitPlan) {
        invoke(HuifuModel.getInstance().getExpectedRevenue(amount, rate, periodLength, periodUnit, profitPlan), new Subscriber<BBaseBean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BBaseBean baseBean) {
                if (success.equals(baseBean.code)) {
                    getView().showRevenue(amount, baseBean.model);
                } else {
                    UIUtils.showToastCommon(mContext, baseBean.msg);
                }
            }
        });
    }

    /**
     * @param amount
     * @param rate
     * @param periodLength
     * @param periodUnit
     * @param profitPlan
     * @param borrowNo
     * @param couponRate   卡券和平台加息
     */
    public void getExpectedRevenueNew(final String amount, String rate, String periodLength, String periodUnit, String profitPlan, String borrowNo, String couponRate) {
        invoke(HuifuModel.getInstance().getExpectedRevenueNew(amount, rate, periodLength, periodUnit, profitPlan, borrowNo, couponRate), new Subscriber<RevenueBean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(RevenueBean baseBean) {
                if (success.equals(baseBean.getCode())) {
                    getView().showRevenueNew(amount, baseBean.getModel());
                } else {
                    UIUtils.showToastCommon(mContext, baseBean.getMsg());
                }
            }
        });
    }

    public void investSb(String borrowNo, String payAmount, String expectedRevenue, String receiveCouponNo, String isContinue) {
        invoke(HuifuModel.getInstance().borrowInvest(borrowNo, payAmount, expectedRevenue, receiveCouponNo, isContinue), new ProgressSubscriber<BorrowInvestBean>(new SubscriberOnNextListener<BorrowInvestBean>() {
            @Override
            public void onNext(BorrowInvestBean baseBean) {
                if (success.equals(baseBean.code)) {
                    if (null != baseBean.model.ServiceUrl) {
                        //散标跳转汇付
                        getView().pushActivity(new Gson().toJson(baseBean), false);
                    } else {
                        //计划标预约标直接成功
                        getView().pushActivity(null, true);
                    }
                } else {
                    if (TextUtils.isEmpty(baseBean.msg)) {

                    } else {
                        if (baseBean.msg.equals("尊敬的用户,根据您的风险测评承受能力,当前不可参与投资,如需投资请重新测评")) {
                            //getView().showRiskCheck("尊敬的用户,根据您的风险测评承受能力,当前不可参与出借");
                            getRiskLevel();
                        } else {
                            UIUtils.showToastCommon(mContext, baseBean.msg);
                        }
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }, mContext));
    }

    /**
     * 查询投资卡券
     *
     * @param page
     * @param limit
     * @param investAmount
     * @param periodLength
     * @param periodUnit
     * @param type
     * @param productNo
     */
    public void queryInvestCoupon(String page, String limit, String investAmount, String periodLength, String periodUnit, String type, String productNo) {
        invoke(AccountModel.getInstance().queryInvestCoupon(page, limit, investAmount, periodLength, periodUnit, type, productNo), new Subscriber<InvestCouponBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(tag, e.getMessage());
            }

            @Override
            public void onNext(InvestCouponBean investCouponBean) {
                if (investCouponBean.getCode().equals(success)) {
                    getView().showInvestCoupons(investCouponBean);
                }

            }
        });
    }

    public void getRiskLevel() {
        invoke(AccountModel.getInstance().getRiskBean(), new Subscriber<RiskBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(RiskBean riskBean) {
                if (Config.success.equals(riskBean.code)) {
                    int socre = Integer.parseInt(riskBean.model.userScore);
                    getView().showRiskLevel(socre);
                } else {
                    UIUtils.showToastCommon(mContext, riskBean.msg);
                }
            }
        });
    }
}
