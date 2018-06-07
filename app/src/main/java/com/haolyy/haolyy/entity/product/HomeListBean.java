package com.haolyy.haolyy.entity.product;

import java.util.List;

/**
 * Created by niudeyang on 2017/8/16.
 */

public class HomeListBean {


    /**
     * code : success
     * msg : 成功
     * model : {"noviciateBorrow":[{"appendRate":0,"profitPlan":4,"proportion":50,"periodUnit":2,"productName":"中赢宝","borrowName":"新手标","investMinAmount":100,"annualizedRate":10,"contractAmount":10000,"periodLength":52,"borrowType":2,"investMaxAmount":10000,"borrowNo":"ZZT-1000000310834253","status":7}],"recommendBorrow":[],"planBorrow":[{"appendRate":0.05,"profitPlan":4,"proportion":61,"periodUnit":2,"productName":"月满赢","borrowName":"测试理财师","investMinAmount":100,"annualizedRate":6,"contractAmount":100000,"periodLength":5,"borrowType":2,"investMaxAmount":100000,"borrowNo":"ZZT-1000000528107073","status":4}]}
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
        private List<NoviciateBorrowBean> noviciateBorrow;
        private List<?> recommendBorrow;
        private List<PlanBorrowBean> planBorrow;

        public List<NoviciateBorrowBean> getNoviciateBorrow() {
            return noviciateBorrow;
        }

        public void setNoviciateBorrow(List<NoviciateBorrowBean> noviciateBorrow) {
            this.noviciateBorrow = noviciateBorrow;
        }

        public List<?> getRecommendBorrow() {
            return recommendBorrow;
        }

        public void setRecommendBorrow(List<?> recommendBorrow) {
            this.recommendBorrow = recommendBorrow;
        }

        public List<PlanBorrowBean> getPlanBorrow() {
            return planBorrow;
        }

        public void setPlanBorrow(List<PlanBorrowBean> planBorrow) {
            this.planBorrow = planBorrow;
        }

        public static class NoviciateBorrowBean {
            /**
             * appendRate : 0.0
             * profitPlan : 4
             * proportion : 50.0
             * periodUnit : 2
             * productName : 中赢宝
             * borrowName : 新手标
             * investMinAmount : 100.0
             * annualizedRate : 10.0
             * contractAmount : 10000.0
             * periodLength : 52
             * borrowType : 2
             * investMaxAmount : 10000.0
             * borrowNo : ZZT-1000000310834253
             * status : 7
             */

            private double appendRate;
            private int profitPlan;
            private double proportion;
            private int periodUnit;
            private String productName;
            private String borrowName;
            private double investMinAmount;
            private double annualizedRate;
            private double contractAmount;
            private int periodLength;
            private int borrowType;
            private double investMaxAmount;
            private String borrowNo;
            private int status;

            public double getAppendRate() {
                return appendRate;
            }

            public void setAppendRate(double appendRate) {
                this.appendRate = appendRate;
            }

            public int getProfitPlan() {
                return profitPlan;
            }

            public void setProfitPlan(int profitPlan) {
                this.profitPlan = profitPlan;
            }

            public double getProportion() {
                return proportion;
            }

            public void setProportion(double proportion) {
                this.proportion = proportion;
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

            public String getBorrowName() {
                return borrowName;
            }

            public void setBorrowName(String borrowName) {
                this.borrowName = borrowName;
            }

            public double getInvestMinAmount() {
                return investMinAmount;
            }

            public void setInvestMinAmount(double investMinAmount) {
                this.investMinAmount = investMinAmount;
            }

            public double getAnnualizedRate() {
                return annualizedRate;
            }

            public void setAnnualizedRate(double annualizedRate) {
                this.annualizedRate = annualizedRate;
            }

            public double getContractAmount() {
                return contractAmount;
            }

            public void setContractAmount(double contractAmount) {
                this.contractAmount = contractAmount;
            }

            public int getPeriodLength() {
                return periodLength;
            }

            public void setPeriodLength(int periodLength) {
                this.periodLength = periodLength;
            }

            public int getBorrowType() {
                return borrowType;
            }

            public void setBorrowType(int borrowType) {
                this.borrowType = borrowType;
            }

            public double getInvestMaxAmount() {
                return investMaxAmount;
            }

            public void setInvestMaxAmount(double investMaxAmount) {
                this.investMaxAmount = investMaxAmount;
            }

            public String getBorrowNo() {
                return borrowNo;
            }

            public void setBorrowNo(String borrowNo) {
                this.borrowNo = borrowNo;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class PlanBorrowBean {
            /**
             * appendRate : 0.05
             * profitPlan : 4
             * proportion : 61.0
             * periodUnit : 2
             * productName : 月满赢
             * borrowName : 测试理财师
             * investMinAmount : 100.0
             * annualizedRate : 6.0
             * contractAmount : 100000.0
             * periodLength : 5
             * borrowType : 2
             * investMaxAmount : 100000.0
             * borrowNo : ZZT-1000000528107073
             * status : 4
             */

            private double appendRate;
            private int profitPlan;
            private double proportion;
            private int periodUnit;
            private String productName;
            private String borrowName;
            private double investMinAmount;
            private double annualizedRate;
            private double contractAmount;
            private int periodLength;
            private int borrowType;
            private double investMaxAmount;
            private String borrowNo;
            private int status;

            public double getAppendRate() {
                return appendRate;
            }

            public void setAppendRate(double appendRate) {
                this.appendRate = appendRate;
            }

            public int getProfitPlan() {
                return profitPlan;
            }

            public void setProfitPlan(int profitPlan) {
                this.profitPlan = profitPlan;
            }

            public double getProportion() {
                return proportion;
            }

            public void setProportion(double proportion) {
                this.proportion = proportion;
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

            public String getBorrowName() {
                return borrowName;
            }

            public void setBorrowName(String borrowName) {
                this.borrowName = borrowName;
            }

            public double getInvestMinAmount() {
                return investMinAmount;
            }

            public void setInvestMinAmount(double investMinAmount) {
                this.investMinAmount = investMinAmount;
            }

            public double getAnnualizedRate() {
                return annualizedRate;
            }

            public void setAnnualizedRate(double annualizedRate) {
                this.annualizedRate = annualizedRate;
            }

            public double getContractAmount() {
                return contractAmount;
            }

            public void setContractAmount(double contractAmount) {
                this.contractAmount = contractAmount;
            }

            public int getPeriodLength() {
                return periodLength;
            }

            public void setPeriodLength(int periodLength) {
                this.periodLength = periodLength;
            }

            public int getBorrowType() {
                return borrowType;
            }

            public void setBorrowType(int borrowType) {
                this.borrowType = borrowType;
            }

            public double getInvestMaxAmount() {
                return investMaxAmount;
            }

            public void setInvestMaxAmount(double investMaxAmount) {
                this.investMaxAmount = investMaxAmount;
            }

            public String getBorrowNo() {
                return borrowNo;
            }

            public void setBorrowNo(String borrowNo) {
                this.borrowNo = borrowNo;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
