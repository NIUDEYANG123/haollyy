package com.haolyy.haolyy.entity.bank;


import com.haolyy.haolyy.base.BaseBean;

/**
 * Created by niudeyang on 2017/8/30.
 */

public class WithDrawBean extends BaseBean {

    /**
     * code : success
     * msg : 成功
     * model : {"InMap":{"UsrCustId":"6000060007674671","MerCustId":"6000060007051718","ServFee":"3.00","ServFeeAcctId":"MDT000001","Remark":"","BgRetUrl":"http://180.167.70.150:8010/new/usertrade/userCashCallBack.html","OrdId":"20170913162118862","TransAmt":"1000.00","ReqExt":"[{\"CashChl\":\"GENERAL\",\"FeeAcctId\":\"MDT000002\"}]","Version":"20","CmdId":"Cash","RetUrl":"www","PageType":"","ChkValue":"60CC26B5662E87A2CA7A8EFB8DCF03934F0CBF6C36E189B6D53E301F198D708EDA08B651E769E45FC3E51895993A5A28B6CF68387D85653914B372D71F1A32C7E4A711683D2375BF369999CE6DE8EF7C48ED8CE8C60872225435B1860B253BAF20E7C376F0AAD9B96A334C28F65415F5559A1EC47B3C7B82A190057C278D2D30"},"RetCode":"SUCCESS","RetMessage":"用户取现成功","ServiceUrl":"http://mertest.chinapnr.com/muser/publicRequests"}
     */
    public ModelBean model;

    public static class ModelBean {
        /**
         * InMap : {"UsrCustId":"6000060007674671","MerCustId":"6000060007051718","ServFee":"3.00","ServFeeAcctId":"MDT000001","Remark":"","BgRetUrl":"http://180.167.70.150:8010/new/usertrade/userCashCallBack.html","OrdId":"20170913162118862","TransAmt":"1000.00","ReqExt":"[{\"CashChl\":\"GENERAL\",\"FeeAcctId\":\"MDT000002\"}]","Version":"20","CmdId":"Cash","RetUrl":"www","PageType":"","ChkValue":"60CC26B5662E87A2CA7A8EFB8DCF03934F0CBF6C36E189B6D53E301F198D708EDA08B651E769E45FC3E51895993A5A28B6CF68387D85653914B372D71F1A32C7E4A711683D2375BF369999CE6DE8EF7C48ED8CE8C60872225435B1860B253BAF20E7C376F0AAD9B96A334C28F65415F5559A1EC47B3C7B82A190057C278D2D30"}
         * RetCode : SUCCESS
         * RetMessage : 用户取现成功
         * ServiceUrl : http://mertest.chinapnr.com/muser/publicRequests
         */

        public InMapBean InMap;
        public String RetCode;
        public String RetMessage;
        public String ServiceUrl;

        public static class InMapBean {
            /**
             * UsrCustId : 6000060007674671
             * MerCustId : 6000060007051718
             * ServFee : 3.00
             * ServFeeAcctId : MDT000001
             * Remark :
             * BgRetUrl : http://180.167.70.150:8010/new/usertrade/userCashCallBack.html
             * OrdId : 20170913162118862
             * TransAmt : 1000.00
             * ReqExt : [{"CashChl":"GENERAL","FeeAcctId":"MDT000002"}]
             * Version : 20
             * CmdId : Cash
             * RetUrl : www
             * PageType :
             * ChkValue : 60CC26B5662E87A2CA7A8EFB8DCF03934F0CBF6C36E189B6D53E301F198D708EDA08B651E769E45FC3E51895993A5A28B6CF68387D85653914B372D71F1A32C7E4A711683D2375BF369999CE6DE8EF7C48ED8CE8C60872225435B1860B253BAF20E7C376F0AAD9B96A334C28F65415F5559A1EC47B3C7B82A190057C278D2D30
             */

            public String UsrCustId;
            public String MerCustId;
            public String ServFee;
            public String ServFeeAcctId;
            public String Remark;
            public String BgRetUrl;
            public String OrdId;
            public String TransAmt;
            public String ReqExt;
            public String Version;
            public String CmdId;
            public String RetUrl;
            public String PageType;
            public String ChkValue;
        }
    }
}
