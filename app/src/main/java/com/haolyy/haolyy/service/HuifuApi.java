package com.haolyy.haolyy.service;

import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.entity.BBaseBean;
import com.haolyy.haolyy.entity.ModifyBankPhoneBean;
import com.haolyy.haolyy.entity.QueryTransStatBean;
import com.haolyy.haolyy.entity.bank.AutoTenderBean;
import com.haolyy.haolyy.entity.bank.Fee;
import com.haolyy.haolyy.entity.bank.IsCashSuccess;
import com.haolyy.haolyy.entity.bank.OpenAccountBean;
import com.haolyy.haolyy.entity.bank.QueryBankBean;
import com.haolyy.haolyy.entity.bank.RechargeBean;
import com.haolyy.haolyy.entity.bank.RechargeResultBean;
import com.haolyy.haolyy.entity.bank.SmsCodeBean;
import com.haolyy.haolyy.entity.bank.WithDrawBean;
import com.haolyy.haolyy.entity.product.BorrowInvestBean;
import com.haolyy.haolyy.entity.product.RevenueBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by niudeyang on 2017/8/14.
 */

public interface HuifuApi {
    @FormUrlEncoded
    @POST(NetConstantValues.SENDSMSCODE)
    Observable<SmsCodeBean> sendSmsCode(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST(NetConstantValues.SENDSMSCODE)
    Observable<BaseBean> sendSmsCode2(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.OPENACCOUNT)
    Observable<OpenAccountBean> openAccount(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.BOSACCTACTIVATE)
    Observable<BBaseBean> bosAcctActivate(@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST(NetConstantValues.CONFIRM_RECHARGE)
    Observable<RechargeBean> confirmRechrge(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.QUERY_RECHARGE)
    Observable<QueryBankBean> queryRecharge(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.CASH)
    Observable<WithDrawBean> tocash(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.CASHFEE)
    Observable<Fee> userCashFee(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.ISWITHDRAWSUCCESS)
    Observable<IsCashSuccess> querywithdraw(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.QUIK_BIND)
    Observable<BaseBean> rebind(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.BORROW_INVEST)
    Observable<BorrowInvestBean> borrowInvest(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.EXPECTED_REVENUE)
    Observable<BBaseBean> getExpectedRevenue(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.EXPECTEDREVENUE_NEW)
    Observable<RevenueBean> getExpectedRevenueNew(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.QUERY_TRANS_STA)
    Observable<QueryTransStatBean> queryTransStat(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.AUTO_TENDER_PLAN)
    Observable<AutoTenderBean> autoTenderPlan(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.QUERY_TENDER_PLAN)
    Observable<BaseBean> queryTenderPlan(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.RECHARGE_RESULT)
    Observable<RechargeResultBean> queryRechargeResult(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(NetConstantValues.MODIFY_BANK_PHONR)
    Observable<ModifyBankPhoneBean> modifyBankPhone(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST(NetConstantValues.QUERYMODIFYTHEBANKPHONE)
    Observable<BaseBean> queryModifyTheBankPhone(@FieldMap Map<String, String> params);
}
