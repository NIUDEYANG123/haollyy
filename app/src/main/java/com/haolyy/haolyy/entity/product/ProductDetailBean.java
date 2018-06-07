package com.haolyy.haolyy.entity.product;

/**
 * Created by liliang on 2017/9/13.
 */

public class ProductDetailBean {

    /**
     * code : success
     * msg : 成功
     * model : {"interestDateInt":null,"endDate":null,"remark":null,"raisePeriod":null,"periodUnit":"月","discountMaxRate":null,"transferRate":null,"borrowName":"测试散标1","investMinAmount":100,"discountMinRate":null,"annualizedRate":7,"id":null,"investMaxAmount":50000,"borrowNo":"333334","interestStartDate":null,"publishTime":null,"profitPlan":1,"interestEndDate":"2017-08-29","thresholdAmount":null,"fullBidTime":null,"redeemFeeRate":4,"updateTime":null,"lockPeriod":null,"fullBidTimeFact":null,"createTime":null,"contractAmount":"100000.00","periodLength":1,"investAscendingAmount":100,"startDate":null,"amountWait":50000,"status":4}
     */

    public String code;
    public String msg;
    public ModelBean model;


    public static class ModelBean {
        /**
         * interestDateInt : null
         * endDate : null
         * remark : null
         * raisePeriod : null
         * periodUnit : 月
         * discountMaxRate : null
         * transferRate : null
         * borrowName : 测试散标1
         * investMinAmount : 100
         * discountMinRate : null
         * annualizedRate : 7
         * id : null
         * investMaxAmount : 50000
         * borrowNo : 333334
         * interestStartDate : null
         * publishTime : null
         * profitPlan : 1
         * interestEndDate : 2017-08-29
         * thresholdAmount : null
         * fullBidTime : null
         * redeemFeeRate : 4
         * updateTime : null
         * lockPeriod : null
         * fullBidTimeFact : null
         * createTime : null
         * contractAmount : 100000.00
         * periodLength : 1
         * investAscendingAmount : 100
         * startDate : null
         * amountWait : 50000
         * status : 4
         */

        public String interestDateInt;
        public String endDate;
        public String remark;
        public String raisePeriod;
        public int periodUnit;
        public String periodLength;
        public String discountMaxRate;
        public String transferRate;
        public String borrowName;
        public String discountMinRate;
        public double annualizedRate;
        public double appendRate;
        public int id;
        public double investMinAmount;
        public double investMaxAmount;
        public String investAscendingAmount;
        public String borrowNo;
        public String interestStartDate;
        public String publishTime;
        public String profitPlan;
        public String interestEndDate;
        public String thresholdAmount;
        public String fullBidTime;
        public String redeemFeeRate;
        public String updateTime;
        public String lockPeriod;
        public String fullBidTimeFact;
        public String createTime;
        public String contractAmount;
        public String startDate;
        public String amountWait;
        public int status;
        public String productNo;
        public long secondTime;

    }
}
