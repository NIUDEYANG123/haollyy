package com.haolyy.haolyy.entity.userinfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by niudeyang on 2017/10/31.
 */

public class InvestCouponBean implements Serializable {

    /**
     * code : success
     * msg : 成功
     * model : {"bestCoupon":{"borrowTypes":"2","endDate":"2019-05-29 00:00:00","couponNo":"JXQ_HLW_20180523_05","type":1,"periodUnit":1,"operator":"1","canUse":1,"receiveId":"JXQ_HLW_20180523_05_20180529_0000025","deliveryRangeProduct":"赢计划A","effect":5,"client":"1,2,3,4","periodLength":7,"deliveryRuleDetail":"测试","maxAmount":100,"startDate":"2018-05-29 00:00:00"},"dataList":[{"borrowTypes":"2","endDate":"2019-05-29","couponNo":"JXQ_HLW_20180523_05","type":1,"periodUnit":1,"operator":"1","canUse":1,"receiveId":"JXQ_HLW_20180523_05_20180529_0000025","deliveryRangeProduct":"赢计划A","effect":5,"client":"1,2,3,4","periodLength":7,"deliveryRuleDetail":"测试","maxAmount":100,"startDate":"2018-05-29"},{"borrowTypes":"2","endDate":"2019-05-29","couponNo":"JXQ_HLW_20180523_05","type":1,"periodUnit":1,"operator":"1","canUse":1,"receiveId":"JXQ_HLW_20180523_05_20180529_0000026","deliveryRangeProduct":"赢计划A","effect":5,"client":"1,2,3,4","periodLength":7,"deliveryRuleDetail":"测试","maxAmount":100,"startDate":"2018-05-29"},{"borrowTypes":"2","endDate":"2018-05-31","couponNo":"FXQ_HLW_20180529_05","type":2,"periodUnit":1,"canUse":0,"receiveId":"FXQ_HLW_20180529_05_20180529_0000023","deliveryRangeProduct":"533月标","effect":6.65,"client":"1,2,3,4","deliveryRuleDetail":"单笔投资满\n1000\n元可用","maxAmount":1000,"startDate":"2018-05-29"},{"borrowTypes":"2","endDate":"2018-05-31","couponNo":"FXQ_HLW_20180529_05","type":2,"periodUnit":1,"canUse":0,"receiveId":"FXQ_HLW_20180529_05_20180529_0000024","deliveryRangeProduct":"533月标","effect":6.65,"client":"1,2,3,4","deliveryRuleDetail":"单笔投资满\n1000\n元可用","maxAmount":1000,"startDate":"2018-05-29"}],"count":4}
     */

    private String code;
    private String msg;
    private ModelBean model;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ModelBean getModel() {
        return model;
    }

    public void setModel(ModelBean model) {
        this.model = model;
    }

    public static class ModelBean implements Serializable{
        /**
         * bestCoupon : {"borrowTypes":"2","endDate":"2019-05-29 00:00:00","couponNo":"JXQ_HLW_20180523_05","type":1,"periodUnit":1,"operator":"1","canUse":1,"receiveId":"JXQ_HLW_20180523_05_20180529_0000025","deliveryRangeProduct":"赢计划A","effect":5,"client":"1,2,3,4","periodLength":7,"deliveryRuleDetail":"测试","maxAmount":100,"startDate":"2018-05-29 00:00:00"}
         * dataList : [{"borrowTypes":"2","endDate":"2019-05-29","couponNo":"JXQ_HLW_20180523_05","type":1,"periodUnit":1,"operator":"1","canUse":1,"receiveId":"JXQ_HLW_20180523_05_20180529_0000025","deliveryRangeProduct":"赢计划A","effect":5,"client":"1,2,3,4","periodLength":7,"deliveryRuleDetail":"测试","maxAmount":100,"startDate":"2018-05-29"},{"borrowTypes":"2","endDate":"2019-05-29","couponNo":"JXQ_HLW_20180523_05","type":1,"periodUnit":1,"operator":"1","canUse":1,"receiveId":"JXQ_HLW_20180523_05_20180529_0000026","deliveryRangeProduct":"赢计划A","effect":5,"client":"1,2,3,4","periodLength":7,"deliveryRuleDetail":"测试","maxAmount":100,"startDate":"2018-05-29"},{"borrowTypes":"2","endDate":"2018-05-31","couponNo":"FXQ_HLW_20180529_05","type":2,"periodUnit":1,"canUse":0,"receiveId":"FXQ_HLW_20180529_05_20180529_0000023","deliveryRangeProduct":"533月标","effect":6.65,"client":"1,2,3,4","deliveryRuleDetail":"单笔投资满\n1000\n元可用","maxAmount":1000,"startDate":"2018-05-29"},{"borrowTypes":"2","endDate":"2018-05-31","couponNo":"FXQ_HLW_20180529_05","type":2,"periodUnit":1,"canUse":0,"receiveId":"FXQ_HLW_20180529_05_20180529_0000024","deliveryRangeProduct":"533月标","effect":6.65,"client":"1,2,3,4","deliveryRuleDetail":"单笔投资满\n1000\n元可用","maxAmount":1000,"startDate":"2018-05-29"}]
         * count : 4
         */

        private BestCouponBean bestCoupon;
        private int count;
        private List<DataListBean> dataList;

        public BestCouponBean getBestCoupon() {
            return bestCoupon;
        }

        public void setBestCoupon(BestCouponBean bestCoupon) {
            this.bestCoupon = bestCoupon;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class BestCouponBean implements Serializable{
            /**
             * borrowTypes : 2
             * endDate : 2019-05-29 00:00:00
             * couponNo : JXQ_HLW_20180523_05
             * type : 1
             * periodUnit : 1
             * operator : 1
             * canUse : 1
             * receiveId : JXQ_HLW_20180523_05_20180529_0000025
             * deliveryRangeProduct : 赢计划A
             * effect : 5.0
             * client : 1,2,3,4
             * periodLength : 7
             * deliveryRuleDetail : 测试
             * maxAmount : 100.0
             * startDate : 2018-05-29 00:00:00
             */

            private String borrowTypes;
            private String endDate;
            private String couponNo;
            private int type;
            private int periodUnit;
            private String operator;
            private int canUse;
            private String receiveId;
            private String deliveryRangeProduct;
            private double effect;
            private String client;
            private int periodLength;
            private String deliveryRuleDetail;
            private double maxAmount;
            private String startDate;

            public String getBorrowTypes() {
                return borrowTypes;
            }

            public void setBorrowTypes(String borrowTypes) {
                this.borrowTypes = borrowTypes;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public String getCouponNo() {
                return couponNo;
            }

            public void setCouponNo(String couponNo) {
                this.couponNo = couponNo;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getPeriodUnit() {
                return periodUnit;
            }

            public void setPeriodUnit(int periodUnit) {
                this.periodUnit = periodUnit;
            }

            public String getOperator() {
                return operator;
            }

            public void setOperator(String operator) {
                this.operator = operator;
            }

            public int getCanUse() {
                return canUse;
            }

            public void setCanUse(int canUse) {
                this.canUse = canUse;
            }

            public String getReceiveId() {
                return receiveId;
            }

            public void setReceiveId(String receiveId) {
                this.receiveId = receiveId;
            }

            public String getDeliveryRangeProduct() {
                return deliveryRangeProduct;
            }

            public void setDeliveryRangeProduct(String deliveryRangeProduct) {
                this.deliveryRangeProduct = deliveryRangeProduct;
            }

            public double getEffect() {
                return effect;
            }

            public void setEffect(double effect) {
                this.effect = effect;
            }

            public String getClient() {
                return client;
            }

            public void setClient(String client) {
                this.client = client;
            }

            public int getPeriodLength() {
                return periodLength;
            }

            public void setPeriodLength(int periodLength) {
                this.periodLength = periodLength;
            }

            public String getDeliveryRuleDetail() {
                return deliveryRuleDetail;
            }

            public void setDeliveryRuleDetail(String deliveryRuleDetail) {
                this.deliveryRuleDetail = deliveryRuleDetail;
            }

            public double getMaxAmount() {
                return maxAmount;
            }

            public void setMaxAmount(double maxAmount) {
                this.maxAmount = maxAmount;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }
        }

        public static class DataListBean implements Serializable{
            /**
             * borrowTypes : 2
             * endDate : 2019-05-29
             * couponNo : JXQ_HLW_20180523_05
             * type : 1
             * periodUnit : 1
             * operator : 1
             * canUse : 1
             * receiveId : JXQ_HLW_20180523_05_20180529_0000025
             * deliveryRangeProduct : 赢计划A
             * effect : 5.0
             * client : 1,2,3,4
             * periodLength : 7
             * deliveryRuleDetail : 测试
             * maxAmount : 100.0
             * startDate : 2018-05-29
             */

            private String borrowTypes;
            private String endDate;
            private String couponNo;
            private int type;
            private int periodUnit;
            private String operator;
            private String canUse;
            private String receiveId;
            private String deliveryRangeProduct;
            private double effect;
            private String client;
            private int periodLength;
            private String deliveryRuleDetail;
            private double maxAmount;
            private String startDate;

            public String getBorrowTypes() {
                return borrowTypes;
            }

            public void setBorrowTypes(String borrowTypes) {
                this.borrowTypes = borrowTypes;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public String getCouponNo() {
                return couponNo;
            }

            public void setCouponNo(String couponNo) {
                this.couponNo = couponNo;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getPeriodUnit() {
                return periodUnit;
            }

            public void setPeriodUnit(int periodUnit) {
                this.periodUnit = periodUnit;
            }

            public String getOperator() {
                return operator;
            }

            public void setOperator(String operator) {
                this.operator = operator;
            }

            public String getCanUse() {
                return canUse;
            }

            public void setCanUse(String canUse) {
                this.canUse = canUse;
            }

            public String getReceiveId() {
                return receiveId;
            }

            public void setReceiveId(String receiveId) {
                this.receiveId = receiveId;
            }

            public String getDeliveryRangeProduct() {
                return deliveryRangeProduct;
            }

            public void setDeliveryRangeProduct(String deliveryRangeProduct) {
                this.deliveryRangeProduct = deliveryRangeProduct;
            }

            public double getEffect() {
                return effect;
            }

            public void setEffect(double effect) {
                this.effect = effect;
            }

            public String getClient() {
                return client;
            }

            public void setClient(String client) {
                this.client = client;
            }

            public int getPeriodLength() {
                return periodLength;
            }

            public void setPeriodLength(int periodLength) {
                this.periodLength = periodLength;
            }

            public String getDeliveryRuleDetail() {
                return deliveryRuleDetail;
            }

            public void setDeliveryRuleDetail(String deliveryRuleDetail) {
                this.deliveryRuleDetail = deliveryRuleDetail;
            }

            public double getMaxAmount() {
                return maxAmount;
            }

            public void setMaxAmount(double maxAmount) {
                this.maxAmount = maxAmount;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }
        }
    }
}