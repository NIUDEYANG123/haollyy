package com.haolyy.haolyy.model;

import android.text.TextUtils;


import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.BaseModel;
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
import com.haolyy.haolyy.service.Api;
import com.haolyy.haolyy.service.UserAccountApi;
import com.haolyy.haolyy.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

import static com.haolyy.haolyy.base.BaseApplication.mUserName;
import static com.haolyy.haolyy.config.Config.returl;


/**
 * Created by niudeyang on 2017/9/4.
 */

public class AccountModel extends BaseModel {
    private UserAccountApi userApi;
    private static AccountModel userModel;

    private AccountModel() {
        super();
        userApi = retrofit.create(UserAccountApi.class);
    }

    public static AccountModel getInstance() {
        if (userModel == null) {
            synchronized (AccountModel.class) {
                if (userModel == null) {
                    userModel = new AccountModel();
                }
            }
        }
        return userModel;
    }


    public Observable<UserAccountInfo> getUserInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        // map.put("client",client);
        // map.put("platform",platform);
        return userApi.getUserInfo(map);
    }

    public Observable<HomePageBean> getHomePage() {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        // map.put("platform",platform);
        // map.put("client",client);
        return userApi.homePage(map);
    }

    public Observable<TransactionRecordBean> transactionRecord(int type, int pageIndex) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        // map.put("platform",platform);
        //map.put("client",client);
        map.put("type", type + "");
        map.put("pageIndex", pageIndex + "");
        LogUtils.e("transactionRecord", map.toString());
        return userApi.transactionRecord(map);
    }

    /**
     * 交易详情
     *
     * @param orderNo
     * @param type     加入计划 （固定传4）; 平台奖励（固定传5）；出借（固定传6）；收益（固定传3）；
     * @param bidType  1.预约标、2散标；
     * @param billDate
     * @return
     */
    public Observable<TransactionRecordDetailsBean> transactionRecordDetails(String orderNo, String type, String bidType, String billDate, String ids) {
        Map<String, String> map = new HashMap<>();
        map.put("orderNo", orderNo);
        map.put("type", type);
        map.put("bidType", bidType);
        map.put("billDate", billDate);
        map.put("id", ids);
        return userApi.transactionRecordDetails(map);
    }

    public Observable<ProductManageBean> productManage(String borrowType, String type, int pageIndex) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        // map.put("platform",platform);
        // map.put("client",client);
        map.put("borrowType", borrowType);
        map.put("type", type);
        map.put("pageIndex", pageIndex + "");
        return userApi.productManage(map);
    }

    /**
     * 账户中心列表详情
     *
     * @param orderNo
     * @return
     */
    public Observable<ProductManageDetailBean> productDetail(String orderNo, String statusType) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        map.put("orderNo", orderNo);
        map.put("statusType", statusType);
        return userApi.productDetail(map);
    }

    public Observable<QueryCardInfoBean> queryCardInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        //map.put("client",client);
        // map.put("platform",platform);
        return userApi.queryCardinfo(map);
    }

    /**
     * @return 查询提现卡券
     */
    public Observable<WithDrawCoupon> queryWithdrawCoupon(String page, String limit) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        map.put("page", page);
        map.put("limit", limit);
        return userApi.querywithCoupon(map);
    }

    /**
     * @return
     */
    public Observable<BBaseBean> querySignStatus() {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        map.put("busiType", "4");//使用场景复投
        return userApi.querySignStatus(map);
    }

    /**
     * @return
     */
    public Observable<SignContractbean> signCOntract() {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        map.put("busiType", "5");
        map.put("retUrl", returl);
        return userApi.signContract(map);
    }

    /**
     * 我的中卡卷
     * status
     * 卡券状态：1 - 未使用(已领取），2 - 已使用， 3 - 已过期
     *
     * @return
     */
    public Observable<AccountCouponbean> queryAllCoupons(String page, String limit, String status) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        map.put("page", page);
        map.put("limit", limit);
        map.put("status", status);
        return userApi.queryCoupons(map);
    }

    /**
     * 投资中卡卷
     * periodLength投资周期（天）
     * type卡卷类型 1:加息券 2:返现券
     *
     * @return
     */
    public Observable<InvestCouponBean> queryInvestCoupon(String page, String limit, String investAmount, String periodLength, String periodUnit, String type, String productNo) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        map.put("page", page);
        map.put("limit", limit);
        map.put("investAmount", investAmount);
        map.put("periodUnit", periodUnit);
        map.put("periodLength", periodLength);
//        if (!TextUtils.isEmpty(type)){
        map.put("type", type);
//        }
        map.put("productNo", productNo);
        return userApi.queryinvestCoupons(map);
    }

    /**
     * @param borrowType 标的类型（1：散标、2：预约标）
     * @param statusType 状态类型（散标时：1-投标中、2-收益中、3-已退出；预约标时：1-持有中、2-已退出、3-退出中）
     * @return
     */
    public Observable<AssetHoldListBean> queryListByStatus(String borrowType, String statusType, int pagenum) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        map.put("borrowType", borrowType);
        map.put("statusType", statusType);
        map.put("pageSize", "10");
        map.put("pageNum", pagenum + "");
        return userApi.querylistByStatus(map);
    }

    /**
     * 续投关闭或者开启
     *
     * @param cashNo
     * @return
     */
    public Observable<BBaseBean> continueOpen(String cashNo) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        map.put("cashNo", cashNo);
        return userApi.continueOpen(map);
    }


    /**
     * 本地合同
     *
     * @param investOrderNo
     * @return
     */
    public Observable<BBaseBean> getLocalContract(String investOrderNo, String debtNo, String cashNo) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        map.put("investOrderNo", investOrderNo);
        if (!TextUtils.isEmpty(debtNo)) {
            map.put("debtNo", debtNo);
            map.put("cashNo", cashNo);
        }
        return userApi.getLocalContract(map);
    }


    /**
     * 当前持有债权
     *
     * @param cashNo
     * @return
     */
    public Observable<CashHeldBean> cashHeld(String cashNo, String rows, String start) {
        Map<String, String> map = new HashMap<>();
        map.put("cashNo", cashNo);
        map.put("rows", rows);
        map.put("start", start);
        return userApi.cashHeld(map);
    }

    /**
     * 当前持有债权
     *
     * @param cashNo
     * @return
     */
    public Observable<CashHeldReord> cashHeldRecord(String cashNo, String pageSize, String pageNum) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum",mUserName);
        map.put("orderNo", cashNo);
        map.put("pageSize", pageSize);
        map.put("pageNum", pageNum);
        return userApi.cashHeldRecord(map);
    }

    /**
     * 保全合同
     *
     * @param applyNo
     * @return
     */
    public Observable<BBaseBean> getBaoquanContarct(String applyNo) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNum", mUserName);
        map.put("applyNo", applyNo);
        return userApi.getbaoquanContract(map);
    }

    /**
     * 预约标回款计划接口
     *
     * @param borrowNo
     * @return
     */
    public Observable<AssetRepayPLanBean> getAssetRepayBean(String borrowNo) {
        Map<String, String> map = new HashMap<>();
        map.put("cashNo", borrowNo);
        return userApi.getAssetRepayPlan(map);

    }

    /**
     * 测评分数
     *
     * @return
     */
    public Observable<RiskBean> getRiskBean() {
        Map<String, String> map = new HashMap<>();
        map.put("userCode", BaseApplication.juid);
        map.put("answerType", "1");
        return userApi.riskSocre(map);
    }
}
