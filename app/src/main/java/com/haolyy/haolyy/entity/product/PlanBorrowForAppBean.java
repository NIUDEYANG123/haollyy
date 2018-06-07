package com.haolyy.haolyy.entity.product;

import java.util.List;

/**
 * Created by niudeyang on 2017/11/18.
 */

public class PlanBorrowForAppBean {

    /**
     * code : success
     * msg : 成功
     * model : [{"amountScale":52,"borrowName":"借款项目Z3","annualizedRate":9.99,"periodLenth":6,"borrowNo":"ZZT-1000000347505918","periodUnit":3,"amountWait":4800,"productName":"卓投6月"},null,null,null,null]
     */

    private String code;
    private String msg;
    private List<ModelBean> model;

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

    public List<ModelBean> getModel() {
        return model;
    }

    public void setModel(List<ModelBean> model) {
        this.model = model;
    }

    public static class ModelBean {
        /**
         * amountScale : 52
         * borrowName : 借款项目Z3
         * annualizedRate : 9.99
         * periodLenth : 6
         * borrowNo : ZZT-1000000347505918
         * periodUnit : 3
         * amountWait : 4800
         * productName : 卓投6月
         */

        private double amountScale;
        private String borrowName;
        private double annualizedRate;
        private int periodLenth;
        private String borrowNo;
        private int periodUnit;
        private double amountWait;
        private String productName;
        private double appendRate;
        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public double getAppendRate() {
            return appendRate;
        }

        public void setAppendRate(double appendRate) {
            this.appendRate = appendRate;
        }

        public double getAmountWait() {
            return amountWait;
        }

        public void setAmountWait(double amountWait) {
            this.amountWait = amountWait;
        }

        public double getAmountScale() {
            return amountScale;
        }


        public String getBorrowName() {
            return borrowName;
        }

        public void setBorrowName(String borrowName) {
            this.borrowName = borrowName;
        }

        public double getAnnualizedRate() {
            return annualizedRate;
        }

        public void setAnnualizedRate(double annualizedRate) {
            this.annualizedRate = annualizedRate;
        }

        public int getPeriodLenth() {
            return periodLenth;
        }

        public void setPeriodLenth(int periodLenth) {
            this.periodLenth = periodLenth;
        }

        public String getBorrowNo() {
            return borrowNo;
        }

        public void setBorrowNo(String borrowNo) {
            this.borrowNo = borrowNo;
        }

        public int getPeriodUnit() {
            return periodUnit;
        }

        public void setPeriodUnit(int periodUnit) {
            this.periodUnit = periodUnit;
        }
        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    }
}
