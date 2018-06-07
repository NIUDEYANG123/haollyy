package com.haolyy.haolyy.entity.my;

/**
 * Created by liliang on 2017/9/23.
 */

public class ProductManageDetailBean {


    /**
     * code : success
     * msg : 成功
     * model : {"listDetails":{"yesProfit":2.73,"interestEndDate":"2018-02-05","orderNo":"PSI20180109104232189893","createTime":"2018-01-09 10:42:32","annualizedRate":10,"statusName":"收益中","investAmount":10000,"periodLength":"28天","targetProfit":76.71,"interestStartDate":"2018年01月09日","statusCode":5}}
     */

    public String code;
    public String msg;
    public ModelBean model;

    public static class ModelBean {
        /**
         * listDetails : {"yesProfit":2.73,"interestEndDate":"2018-02-05","orderNo":"PSI20180109104232189893","createTime":"2018-01-09 10:42:32","annualizedRate":10,"statusName":"收益中","investAmount":10000,"periodLength":"28天","targetProfit":76.71,"interestStartDate":"2018年01月09日","statusCode":5}
         */

        public ListDetailsBean listDetails;

        public static class ListDetailsBean {
            /**
             * yesProfit : 2.73
             * interestEndDate : 2018-02-05
             * orderNo : PSI20180109104232189893
             * createTime : 2018-01-09 10:42:32
             * annualizedRate : 10.0
             * statusName : 收益中
             * investAmount : 10000.0
             * periodLength : 28天
             * targetProfit : 76.71
             * interestStartDate : 2018年01月09日
             * statusCode : 5
             */

            public String yesProfit;
            public String interestEndDate;
            public String orderNo;
            public String createTime;
            public String annualizedRate;
            public String statusName;
            public String investAmount;
            public String periodLength;
            public String targetProfit;
            public String interestStartDate;
            public int statusCode;
            public double couponProfit;
            public double appendProfit;
            public int profitPlan;
            public double appendRate,couponRate;

        }
    }
}
