package com.haolyy.haolyy.entity.my;

/**
 * Created by liliang on 2017/9/22.
 */

public class HomePageBean {

    /**
     * code : success
     * msg : 成功develop
     * model : {"canWithdrawAmount":-87700,"planBorrowProfit":0,"borrowAmount":36300,"availableAmount":124300,"planBorrowAmount":0,"yesterdayIncome":0,"borrowProfit":0,"profitAll":0,"couponCount":0,"amountTotal":211400,"frozenAmount":56300}
     */

    public String code;
    public String msg;
    public ModelBean model;
    public static class ModelBean {
        /**
         * canWithdrawAmount : -87700  可提现金额
         * planBorrowProfit : 0    计划标累计收益
         * borrowAmount : 36300     散标持有资产
         * availableAmount : 124300   可用余额
         * planBorrowAmount : 0   计划标持有资产
         * yesterdayIncome : 0    昨日收益
         * borrowProfit : 0     散标累计收益
         * profitAll : 0         累计收益
         * couponCount : 0
         * amountTotal : 211400    资产总额
         * frozenAmount : 56300
         */

        public String canWithdrawAmount;
        public String planBorrowProfit;
        public String borrowAmount;
        public String availableAmount;
        public String planBorrowAmount;
        public String yesterdayIncome;
        public String borrowProfit;
        public String profitAll;
        public String couponCount;
        public String amountTotal;
        public String frozenAmount;
        public String appointmentBorrowAmount;
        public String appointmentBorrowProfit;

    }
}
