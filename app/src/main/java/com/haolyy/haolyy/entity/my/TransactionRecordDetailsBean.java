package com.haolyy.haolyy.entity.my;

/**
 * Created by liliang on 2017/9/22.
 */

public class TransactionRecordDetailsBean {


    /**
     * code : success
     * msg : 成功
     * model : {"imgUrl":"http://static.chinazyjr.net/static/picture/bank/CMB.png","serviceFee":3,"amount":100000,"orderNo":"UMC20180102155135102","withdrawalsAccount":"招商银行0447","typeName":"招商银行","remark":"取现","doneTime":"1个工作日内","applyTime":"2018-01-02 15:51:35","tradeType":"交易处理中","createDate":"2018-01-02 15:51:35"}
     */

    public String code;
    public String msg;
    public ModelBean model;

    public static class ModelBean {
        /**
         * imgUrl : http://static.chinazyjr.net/static/picture/bank/CMB.png
         * serviceFee : 3
         * amount : 100000
         * orderNo : UMC20180102155135102
         * withdrawalsAccount : 招商银行0447
         * typeName : 招商银行
         * remark : 取现
         * doneTime : 1个工作日内
         * applyTime : 2018-01-02 15:51:35
         * tradeType : 交易处理中
         * createDate : 2018-01-02 15:51:35
         */

        public String imgUrl;
        public String serviceFee;
        public String amount;
        public String orderNo;
        public String withdrawalsAccount;
        public String typeName;
        public String remark;
        public String doneTime;
        public String applyTime;
        public String tradeType;
        public String createDate;

        public String rechargeWay;
        public String planName;
        public String rewardType;
        public String refundReason;

    }
}
