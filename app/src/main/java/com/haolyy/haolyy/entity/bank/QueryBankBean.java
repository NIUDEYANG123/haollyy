package com.haolyy.haolyy.entity.bank;


import com.haolyy.haolyy.base.BaseBean;

/**
 * Created by niudeyang on 2017/8/30.
 */

public class QueryBankBean extends BaseBean {

    /**
     * code : success
     * msg : 成功
     * model : {"BankName":"农业银行","BankCardNo":"6228223211212310100","BankNo":"101","AvailableAmount":0,"OpenBankId":"ABC","ThirdUserId":"6000060007674671","BankCode":"ABC","CardDailyTransQuota":"500000.00","Mobile":"13821882947","SingleTransQuota":"100000.00"}
     */

    public ModelBean model;

    public static class ModelBean {
        /**
         * BankName : 农业银行
         * BankCardNo : 6228223211212310100
         * BankNo : 101
         * AvailableAmount : 0
         * OpenBankId : ABC
         * ThirdUserId : 6000060007674671
         * BankCode : ABC
         * CardDailyTransQuota : 500000.00
         * Mobile : 13821882947
         * SingleTransQuota : 100000.00
         */

        public String BankName;
        public String BankCardNo;
        public String BankNo;
        public String AvailableAmount;
        public String OpenBankId;
        public String ThirdUserId;
        public String BankCode;
        public String CardDailyTransQuota;
        public String Mobile;
        public String SingleTransQuota;
    }
}
