package com.haolyy.haolyy.model;


import android.text.TextUtils;

import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.BaseModel;
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
import com.haolyy.haolyy.service.HuifuApi;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

import static com.haolyy.haolyy.base.BaseApplication.juid;
import static com.haolyy.haolyy.base.BaseApplication.mUserName;
import static com.haolyy.haolyy.base.BaseApplication.userCustId;
import static com.haolyy.haolyy.config.Config.returl;


/**
 * Created by niudeyang on 2017/8/14.
 */

public class HuifuModel extends BaseModel {
    private HuifuApi huifuApi;
    private static HuifuModel userModel;

    private HuifuModel() {
        super();
        huifuApi = retrofit.create(HuifuApi.class);
    }

    public static HuifuModel getInstance() {
        if (userModel == null) {
            synchronized (HuifuModel.class) {
                if (userModel == null) {
                    userModel = new HuifuModel();
                }
            }
        }
        return userModel;
    }

    /**
     * @param busiType    业务类型:rebind(换绑卡) (需要model中的字段)
     * @param mobile
     * @param cardno
     * @param smsTempType 换绑卡时短信类型:O(旧的);N(新的)
     * @return
     */
    public Observable<SmsCodeBean> sendHuifuSms(String busiType, String mobile, String cardno, String smsTempType) {
        Map<String, String> map = new HashMap<>();
        map.put("BusiType", busiType);
        map.put("mobile", mobile);
        map.put("bankCardNo", cardno);
        map.put("SmsTempType", smsTempType);
        map.put("usrCustId", userCustId);
        map.put("userCode", BaseApplication.juid);
        return huifuApi.sendSmsCode(map);
    }

    /**
     * @param busiType    业务类型:user_register(开户),recharge(充值) (不需要model中的字段)
     * @param mobile
     * @param cardno
     * @param smsTempType 换绑卡时短信类型:O(旧的);N(新的)
     * @return
     */
    public Observable<BaseBean> sendHuifuSms2(String busiType, String mobile, String cardno, String smsTempType) {
        Map<String, String> map = new HashMap<>();
        map.put("BusiType", busiType);
        map.put("mobile", mobile);
        map.put("bankCardNo", cardno);
        map.put("SmsTempType", smsTempType);
        map.put("usrCustId", userCustId);
        return huifuApi.sendSmsCode2(map);
    }


    /**
     * 开户
     *
     * @param realName
     * @param idno
     * @param bankno     101
     * @param bankCardNo 6228223211212310128
     * @param smscode    666666
     * @param smsSeq     AAAAAA
     * @param mobile
     * @return
     */
    public Observable<OpenAccountBean> openAccount(String realName, String idno, String bankno, String bankCardNo, String smscode, String smsSeq, String mobile) {
        Map<String, String> map = new HashMap<>();
        map.put("userCode", juid);
        map.put("realName", realName);
        map.put("idno", idno);
        map.put("bankNo", bankno);
        map.put("bankCardNo", bankCardNo);
        map.put("smsCode", smscode);
        map.put("userType", "2");
        map.put("smsSeq", smsSeq);
        map.put("RetUrl", returl);
        map.put("mobile", mobile);
        return huifuApi.openAccount(map);
    }

    /**
     * 激活
     *
     * @return
     */
    public Observable<BBaseBean> bosAcctActivate() {
        Map<String, String> map = new HashMap<>();
        map.put("userCode", juid);
        map.put("thirdUserId", userCustId);
        map.put("retUrl", returl);
        return huifuApi.bosAcctActivate(map);
    }


    public Observable<QueryBankBean> queryRecharge() {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        return huifuApi.queryRecharge(map);
    }

    /**
     * 充值
     *
     * @return
     */
    public Observable<RechargeBean> recharge(String trans_amt_, String rechargeFee, String sms_seq_, String sms_code_, String bankno, String bankcode) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        map.put("gateBusiId", "QP");
        map.put("rechargeAmt", trans_amt_);
        map.put("rechargeFee", rechargeFee);
        map.put("bankSmsSeq", sms_seq_);
        map.put("bankSmsCode", sms_code_);
        map.put("bankNo", bankno);
        map.put("bankCode", bankcode);
        map.put("pageRetUrl", returl);
        return huifuApi.confirmRechrge(map);
    }

    /**
     * 充值结果查询
     *
     * @return
     */
    public Observable<RechargeResultBean> queryRechargeResult(String orderNo) {
        Map<String, String> map = new HashMap<>();
        map.put("orderNo", orderNo);
        return huifuApi.queryRechargeResult(map);
    }

    /**
     * 提现
     *
     * @return
     */
    public Observable<WithDrawBean> toCash(String transAmt, String cashChl, String fee, String receiveId) {
        Map<String, String> map = new HashMap<>();

        if (!TextUtils.isEmpty(receiveId)) {
            map.put("receiveId", receiveId);
        }
        map.put("usrCustId", userCustId);
        map.put("transAmt", transAmt);
        map.put("fee", fee);
        map.put("cashChl", cashChl);
        map.put("retUrl", returl);

        return huifuApi.tocash(map);
    }

    /**
     * 计算取现手续费
     *
     * @return
     */
    public Observable<Fee> userCashFee(String transAmt, String cashChl) {
        Map<String, String> map = new HashMap<>();
        map.put("transAmt", transAmt);
        map.put("cashChl", cashChl);
        return huifuApi.userCashFee(map);
    }

    /**
     * 取现是否成功
     *
     * @return
     */
    public Observable<IsCashSuccess> isCashSucess(String orderNo) {
        Map<String, String> map = new HashMap<>();
        map.put("userCode", juid);
        map.put("orderNo", orderNo);
        return huifuApi.querywithdraw(map);
    }

    /**
     * @param phoneNum   用户账号
     * @param bankId     银行编号
     * @param openAcctId 新绑定银行卡号
     * @param usrMp      绑定银行卡预留手机号
     * @param smsCode    新绑定银行卡短信验证码
     * @param smsSeq     新绑定银行卡短信序号
     * @param orgSmsCode 旧绑定银行卡短信验证码
     * @param orgSmsSeq  旧绑定银行卡短信验证码
     * @return
     */
    public Observable<BaseBean> rebind(String phoneNum, String bankId, String openAcctId, String usrMp, String smsCode, String smsSeq, String orgSmsCode, String orgSmsSeq) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", phoneNum);
        map.put("bankId", bankId);
        map.put("openAcctId", openAcctId);
        map.put("usrMp", usrMp);
        map.put("smsCode", smsCode);
        map.put("smsSeq", smsSeq);
        map.put("orgSmsCode", orgSmsCode);
        map.put("orgSmsSeq", orgSmsSeq);
        map.put("bgRetUrl", returl);
        return huifuApi.rebind(map);
    }

    /**
     * 购买
     *
     * @param borrowNo        标号   9081539190372
     * @param payAmount       购买金额
     * @param expectedRevenue 本息和（计算收益接口计算得到）
     * @return
     */
    public Observable<BorrowInvestBean> borrowInvest(String borrowNo, String payAmount, String expectedRevenue, String receiveCouponNo, String isContinue) {
        Map<String, String> map = new HashMap<>();
        map.put("borrowNo", borrowNo);
        map.put("payAmount", payAmount);
        map.put("expectedRevenue", expectedRevenue);
        map.put("phoneNum", mUserName);
        map.put("retUrl", returl);
        if (!TextUtils.isEmpty(receiveCouponNo)) {
            map.put("receiveCouponNo", receiveCouponNo);
        }
        map.put("isContinue", isContinue);
        return huifuApi.borrowInvest(map);
    }

    /**
     * 计算收益
     *
     * @param amount
     * @param rate
     * @param periodLength
     * @param periodUnit
     * @param profitPlan
     * @return
     */
    public Observable<BBaseBean> getExpectedRevenue(String amount, String rate, String periodLength, String periodUnit, String profitPlan) {
        Map<String, String> map = new HashMap<>();
        map.put("amount", amount);
        map.put("rate", rate);
        map.put("periodLength", periodLength);
        map.put("periodUnit", periodUnit);
        map.put("profitPlan", profitPlan);
        return huifuApi.getExpectedRevenue(map);
    }

    /**
     * @param amount
     * @param rate
     * @param periodLength
     * @param periodUnit
     * @param profitPlan
     * @param borrowNo
     * @param couponRate
     * @return
     */
    public Observable<RevenueBean> getExpectedRevenueNew(String amount, String rate, String periodLength, String periodUnit, String profitPlan, String borrowNo, String couponRate) {
        Map<String, String> map = new HashMap<>();
        map.put("amount", amount);
        map.put("rate", rate);
        map.put("periodLength", periodLength);
        map.put("periodUnit", periodUnit);
        map.put("profitPlan", profitPlan);
        map.put("borrowNo", borrowNo);
        map.put("couponRate", couponRate);
        return huifuApi.getExpectedRevenueNew(map);
    }

    /**
     * 交易状态查询
     *
     * @param ordId
     * @param queryTransType LOANS：放款交易查询 REPAYMENT：还款交易查询 TENDER：投标交易查询 CASH：取现交易查询FREEZE：冻结解冻交易查询
     * @return
     */
    public Observable<QueryTransStatBean> queryTransStat(String ordId, String queryTransType) {
        Map<String, String> map = new HashMap<>();
        map.put("ordId", ordId);
        map.put("queryTransType", queryTransType);
        return huifuApi.queryTransStat(map);
    }


    /**
     * 自动投标
     *
     * @param openFlag 1:开启、0:关闭
     * @return
     */
    public Observable<AutoTenderBean> autoTenderPlan(String openFlag) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        map.put("openFlag", openFlag);
        map.put("retUrl", returl);
        return huifuApi.autoTenderPlan(map);
    }

    //自动投标计划状态查询
    public Observable<BaseBean> queryTenderPlan() {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        return huifuApi.queryTenderPlan(map);
    }

    //修改预留手机号
    public Observable<ModifyBankPhoneBean> modifyBankPhone(String userCode, String oldMobile, String newMobile, String smsCode, String smsSeq) {
        Map<String, String> map = new HashMap<>();
        map.put("userCode", userCode);
        map.put("oldMobile", oldMobile);
        map.put("newMobile", newMobile);
        map.put("smsCode", smsCode);
        map.put("smsSeq", smsSeq);
        map.put("retUrl", returl);
        return huifuApi.modifyBankPhone(map);
    }

    //查询预留手机号是否修改成功
    public Observable<BaseBean> queryModifyTheBankPhone(String userCode,String orderNo) {
        Map<String, String> map = new HashMap<>();
        map.put("userCode", userCode);
        map.put("orderNo", orderNo);
        return huifuApi.queryModifyTheBankPhone(map);
    }
}
