package com.haolyy.haolyy.entity.userinfo;

/**
 * Created by niudeyang on 2017/8/29.
 */

public class UserAccountInfo {


    /**
     * code : success
     * msg : 成功
     * model : {"frozenAmountHidden":null,"profitWait":null,"profitAll":null,"updateTime":null,"version":null,"platform":null,"userCode":null,"frozenAmount":2010,"accountId":null,"totalAmount":13000,"availableAmount":10990,"profitYes":null,"createTime":null,"cashWait":null,"accountOrgId":null,"investAmount":null,"id":37,"investReturnAmount":null,"status":null}
     */

    public String code;
    public String msg;
    public ModelBean model;

    public static class ModelBean {
        /**
         * frozenAmountHidden : null
         * profitWait : null
         * profitAll : null
         * updateTime : null
         * version : null
         * platform : null
         * userCode : null
         * frozenAmount : 2010
         * accountId : null
         * totalAmount : 13000
         * availableAmount : 10990
         * profitYes : null
         * createTime : null
         * cashWait : null
         * accountOrgId : null
         * investAmount : null
         * id : 37
         * investReturnAmount : null
         * status : null
         */

        public String frozenAmountHidden;
        public String profitWait;
        public String profitAll;
        public String updateTime;
        public String version;
        public String platform;
        public String userCode;
        public String frozenAmount;
        public String accountId;
        public String totalAmount;
        public String availableAmount;
        public String profitYes;
        public String createTime;
        public String cashWait;
        public String accountOrgId;
        public String investAmount;
        public int id;
        public String investReturnAmount;
        public String status;
    }
}
