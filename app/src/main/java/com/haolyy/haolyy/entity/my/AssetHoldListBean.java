package com.haolyy.haolyy.entity.my;

import java.util.List;

/**
 * Created by niudeyang on 2017/12/27.
 */

public class AssetHoldListBean {

    /**
     * code : success
     * msg : 成功
     * model : {"listInfo":[{"borrowName":"李佳001","period":"1/2","orderNo":"PSI2017112914211278845","interestEndDate":"2017-12-08","annualizedRate":8,"investAmount":2000,"borrowNo":"JJT-1000000867193050","targetProfit":4.61},{"borrowName":"李佳001","period":"1/2","orderNo":"PSI20171129142042333177","interestEndDate":"2017-12-08","annualizedRate":8,"investAmount":5000,"borrowNo":"JJT-1000000867193050","targetProfit":11.55}]}
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

    public static class ModelBean {
        private List<ListInfoBean> listInfo;

        public List<ListInfoBean> getListInfo() {
            return listInfo;
        }

        public void setListInfo(List<ListInfoBean> listInfo) {
            this.listInfo = listInfo;
        }

        public static class ListInfoBean {
            /**
             * borrowName : 李佳001
             * period : 1/2
             * orderNo : PSI2017112914211278845
             * interestEndDate : 2017-12-08
             * annualizedRate : 8
             * investAmount : 2000
             * borrowNo : JJT-1000000867193050
             * targetProfit : 4.61
             */

            private String borrowName;
            private String orderNo;
            private String interestEndDate;
            private String annualizedRate;
            private String investAmount;
            private String borrowNo;
            private String targetProfit;
            private String period;
            private String isContinue;//0 未开启 1开启
            private String cashNo;
            private double appendRate;
            private double couponRate;

            public double getCouponRate() {
                return couponRate;
            }

            public double getAppendRate() {
                return appendRate;
            }

            public String getYesProfit() {
                return yesProfit;
            }

            private String applyNo;
            private String yesProfit;

            public String getIsContinue() {
                return isContinue;
            }

            public String getCashNo() {
                return cashNo;
            }

            public String getApplyNo() {
                return applyNo;
            }

            public String getBorrowName() {
                return borrowName;
            }

            public void setBorrowName(String borrowName) {
                this.borrowName = borrowName;
            }

            public String getPeriod() {
                return period;
            }

            public void setPeriod(String period) {
                this.period = period;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getInterestEndDate() {
                return interestEndDate;
            }

            public void setInterestEndDate(String interestEndDate) {
                this.interestEndDate = interestEndDate;
            }

            public String getAnnualizedRate() {
                return annualizedRate;
            }

            public void setAnnualizedRate(String annualizedRate) {
                this.annualizedRate = annualizedRate;
            }

            public String getInvestAmount() {
                return investAmount;
            }

            public void setInvestAmount(String investAmount) {
                this.investAmount = investAmount;
            }

            public String getBorrowNo() {
                return borrowNo;
            }

            public void setBorrowNo(String borrowNo) {
                this.borrowNo = borrowNo;
            }

            public String getTargetProfit() {
                return targetProfit;
            }

            public void setTargetProfit(String targetProfit) {
                this.targetProfit = targetProfit;
            }
        }
    }
}
