package com.haolyy.haolyy.service;

import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.entity.BBaseBean;
import com.haolyy.haolyy.entity.bank.QueryCardInfoBean;
import com.haolyy.haolyy.entity.my.AssetHoldListBean;
import com.haolyy.haolyy.entity.my.HomePageBean;
import com.haolyy.haolyy.entity.my.ProductManageBean;
import com.haolyy.haolyy.entity.my.ProductManageDetailBean;
import com.haolyy.haolyy.entity.my.TransactionRecordBean;
import com.haolyy.haolyy.entity.my.TransactionRecordDetailsBean;
import com.haolyy.haolyy.entity.userinfo.AccountCouponbean;
import com.haolyy.haolyy.entity.userinfo.AssetRepayPLanBean;
import com.haolyy.haolyy.entity.userinfo.CashHeldBean;
import com.haolyy.haolyy.entity.userinfo.CashHeldReord;
import com.haolyy.haolyy.entity.userinfo.InvestCouponBean;
import com.haolyy.haolyy.entity.userinfo.RiskBean;
import com.haolyy.haolyy.entity.userinfo.SignContractbean;
import com.haolyy.haolyy.entity.userinfo.UserAccountInfo;
import com.haolyy.haolyy.entity.userinfo.WithDrawCoupon;

import java.util.Map;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by niudeyang on 2017/9/4.
 */

public interface UserAccountApi {
    @FormUrlEncoded
    @POST(NetConstantValues.USERACCOUNT)
    Observable<UserAccountInfo> getUserInfo(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.HOME_PAGE)
    Observable<HomePageBean> homePage(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.TRANSACTION_RECORD)
    Observable<TransactionRecordBean> transactionRecord(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.TRANSACTION_RECORD_DETAILS)
    Observable<TransactionRecordDetailsBean> transactionRecordDetails(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.PRODUCT_MANAGE)
    Observable<ProductManageBean> productManage(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.PRODUCT_MANAGE_DETAIL)
    Observable<ProductManageDetailBean> productDetail(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.QUERYCARDINFO)
    Observable<QueryCardInfoBean> queryCardinfo(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.WITHDRAWCOUPON)
    Observable<WithDrawCoupon> querywithCoupon(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.QUERYSIGNSTATUS)
    Observable<BBaseBean> querySignStatus(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.SIGNCONTRACT)
    Observable<SignContractbean> signContract(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.INVESTCOUPON)
    Observable<InvestCouponBean> queryinvestCoupons(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.QUERYLISTBYSTATUSTYPE)
    Observable<AssetHoldListBean> querylistByStatus(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.CONTINUE_OPEN_CLOSE)
    Observable<BBaseBean> continueOpen(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.ALLCOUPON)
    Observable<AccountCouponbean> queryCoupons(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.LOCALCONTRACT)
    Observable<BBaseBean> getLocalContract(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.BAOQUAN_CONTRACT)
    Observable<BBaseBean> getbaoquanContract(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.CASH_HELD)
    Observable<CashHeldBean> cashHeld(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST(NetConstantValues.CASH_HELD_NEW)
    Observable<CashHeldReord> cashHeldRecord(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.ASSET_REPAY_PLAN)
    Observable<AssetRepayPLanBean> getAssetRepayPlan(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.RISK_SOCRE)
    Observable<RiskBean> riskSocre(@FieldMap Map<String, String> params);
}
