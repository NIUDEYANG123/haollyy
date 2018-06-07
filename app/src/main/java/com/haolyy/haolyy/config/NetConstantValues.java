package com.haolyy.haolyy.config;

import com.haolyy.haolyy.BuildConfig;
import com.haolyy.haolyy.base.BaseApplication;

/**
 * 网络调用请求地址
 * 作者：User on 2016/11/23 21:19
 */
public interface NetConstantValues {
    String HOST_URL = BuildConfig.INTERFACE;
    String CONTRACT_URL=BuildConfig.INTERFACE_CONTART;
    /**
     * 公共
     */
    String image_url = HOST_URL + "common/getValidateImage";//图形验证码
    String SMSCODE = "common/getSmsCode";//短信验证码
    String TOKEN = "common/getToken";//获取token
    String REFRESH_TOKEN = "user/token/refresh";//刷新token
    String USERSTATUS = "common/getUserStatus";//根据手机号获取用户状态
    String GET_BANKS = "common/getBanks";//银行卡列表
    String IS_UPDATE = "version/isUpdate";//获取版本更新
    String VALIDATESMSCODE="common/validateSmsCode";//验证短信验证码
    /**
     * 第一板块
     */
    String USER_LOGIN = "user/login";//登陆
    String USER_LOGINOUT = "user/logout";//退出登录
    String USER_REGISTER = "user/register";//注册
    String USER_RESETPWD = "user/forgetPassWord";//重置密码
    String USER_CAHNGE_PASS = "user/changePassword";//修改密码
    String USER_IS_REGIESTER = "user/isRegister";//用户是否注册
    String USER_CHANGE_MOBILE="user/changeUserMobile";//更改手机号

    /**
     * 汇付(账户)
     */
    String SENDSMSCODE = "account/sendSmsCode";//发送汇付短信验证码
    String OPENACCOUNT = "account/openAccount";//开户
    String BOSACCTACTIVATE = "user/bosAcctActivate";//激活
    String CONFIRM_RECHARGE = "recharge/confirmRecharge";//提交充值
    String RECHARGE_RESULT = "recharge/queryRechargeResult";//充值结果查询
    String QUERY_RECHARGE = "recharge/queryBeforeRecharge";//充值前查询银行卡查询
    String ISWITHDRAWSUCCESS = "usertrade/isWithdrawSuccess";//取现是否成功
    String CASH = "usertrade/userCash";//取现
    String QUIK_BIND = "account/quickBinding";//换绑卡
    String CASHFEE = "usertrade/userCashFee";//计算取现手续费
    String AUTO_TENDER_PLAN = "userManage/autoTenderPlan";//自动投标计划
    String QUERY_TENDER_PLAN = "userManage/queryTenderPlan";//自动投标计划状态查询
    String MODIFY_BANK_PHONR = "account/modifyTheBankPhone";//修改银行预留手机号
    String QUERYMODIFYTHEBANKPHONE = "account/queryModifyTheBankPhone";//修改银行预留手机号

    /**
     * 产品列表
     */
    String PRODUCT_LIST = "product/planList"; //产品列表()
    String PRODUCT_DETAIL = "product/productDetail";//标的详情
    String INVEST_RECORD = "product/investRecord";//投资记录
    String REPAY_PLAN="product/getReturn";//散标还款计划
    /*散标还款计划new*/
    String SAN_REPAY_PLAN="billInvestDetail/queryByBorrowNo";
    String SYANDARD_PLANLIST = "product/standardAndPlanList";//散标、预约标列表
    String HOME_BORROW_LIST = "homePage/homePageBorrowList";//首页产品列表
    String HOME_BANNER = "homePage/homePageBannerArticle";//首页banner和公告
    String PLANBORROWFORAPP = "product/fingPlanBorrowForApp";//卓头预约标列表
    String BORROWPLANLIST = "product/borrowPlanList";//赢计划散标列表
    String DQYMORE="product/queryBorrowDqyList";//短期赢更多

    String NOTICE = "notice/selectNotice";//公告

    /**
     * 购买
     */
    String BORROW_INVEST = "invest/borrowInvest";//购买
    String EXPECTED_REVENUE = "invest/getExpectedRevenue";//计算收益
    String EXPECTEDREVENUE_NEW="invest/getHistoryExpectedRevenue";//计算基础收益和平台奖励收益
    String QUERY_TRANS_STA = "invest/queryTransStat"; //交易状态查询
    String BILL_ADVANCE = "invest/queryBillInvestAdvance";//回款预告

    /**
     * 第二板块
     */
    String BID = "do.jhtml?router=bidsService";
    /*借款人信息 H5写*/
    String BID_QUERYBIDTABURL = BID + ".queryBidTab";
    /*散标购标记录*/
    String BID_QUERYINVESTLISTURL = BID + ".queryInvestList";
    /*平台交易信息*/
    String INDEXCOUNTBID = "do.jhtml?router=indexCountService.get";


    /**
     * 第四板块
     */
    String USERACCOUNT = "account/userAccountInfo";//用户账户信息
    String INVESTCOUPON = "coupon/investCoupon";//投资卡券
    String WITHDRAWCOUPON = "coupon/withDrawCoupon";//提现券
    String ALLCOUPON = "coupon/accountCoupon";//账户卡券纵览

    /**
     * 账户中心
     */

    String HOME_PAGE = "useraccount/homePage";//账户中心首页
    String TRANSACTION_RECORD = "useraccount/transactionRecord";//交易记录
    String TRANSACTION_RECORD_DETAILS = "useraccount/tradeRecordDetails";//交易详情
    String PRODUCT_MANAGE = "useraccount/productManage"; //产品列表
    String QUERYLISTBYSTATUSTYPE = "my/queryListByStatusType";//列表页各个状态相应查询
    String CONTINUE_OPEN_CLOSE = "useraccount/continueOpenClose";//续投关闭或者开启
    String PRODUCT_MANAGE_DETAIL = "my/queryListDetailsByStatusType"; //账户中心产品详情
    String QUERYCARDINFO = "userQuery/queryCardInfo";//查询银行卡信息
    String QUERYSIGNSTATUS = "signing/querySigningStatus";//查询签约状态
    String SIGNCONTRACT = "signing/signingContract";//签约
    String LOCALCONTRACT = "signing/previewSigningContract";//本地合同
    String BAOQUAN_CONTRACT = "signing/getPreviewContractLink";//保全合同
    String CASH_HELD = "debt/cash/held";//当前持有债权
    String CASH_HELD_NEW="my/queryMatchDebtByOrderNo";//投标记录
    String MESSAGE_LIST ="account/";//消息列表;
    String MODIFICATION_MESSAGE ="account/deleteMessage";//修改消息
    String ASSET_REPAY_PLAN="borrowReserve/getBorrowReserveHuman";//个人中心预约标回款计划
    String RISK_SOCRE="user/getUserAnswer";//风险测评分数






    /**
     * 手势密码
     */
    String GESTURE ="user/updateSetHandPasswordFlag";

    /**
     * h5
     */

    String disclosure = CONTRACT_URL+"Agreement/bidplancontract.html";//详情1的借款协议
    String watch_old2 = "https://hlwm.chinazyjr.net/?loginStatus";//检测到返回新版app
    String watch_old="https://hlwm.chinazyjr.com/?loginStatus";
    String risk_testgl = CONTRACT_URL+"about/evaluating.html";//风险测评
    String back_old="http://weixin.haolyy.com/Index/juidLogin?juid=";//返回旧版http:
    String back_old_uat="http://192.168.4.117:8099/Index/juidLogin?juid=";
    String url_borrow=CONTRACT_URL+"Agreement/loanService.html";//出借咨询服务协议
    String about2_url=CONTRACT_URL+"about/2.html";//散标的出借协议
    String invite=CONTRACT_URL+"activity/inviteIndex.html?";
    String sign_proctor=CONTRACT_URL+"Agreement/tBcontent.html?"+"headerToken="+ BaseApplication.accessToken+
    "&phoneNum="+BaseApplication.mUserName+"&client=4&platform=HLW";
    //String invite="http://192.168.20.82:8000/html/activity/inviteindex.html";
    //String invite="http://192.168.21.170:8000/html/activity/inviteindex.html";
}
