package com.haolyy.haolyy.entity.userinfo;

import java.util.List;

/**
 * Created by shanghai on 2018/3/5.
 */

public class AssetRepayPLanBean {
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
        private BorrowBean borrow;
        private List<BorrowReserveBean> borrowReserve;

        public BorrowBean getBorrow() {
            return borrow;
        }

        public void setBorrow(BorrowBean borrow) {
            this.borrow = borrow;
        }

        public List<BorrowReserveBean> getBorrowReserve() {
            return borrowReserve;
        }

        public void setBorrowReserve(List<BorrowReserveBean> borrowReserve) {
            this.borrowReserve = borrowReserve;
        }

        public static class BorrowBean {
            /**
             * id : null
             * borrowNo : HLW-1000001220431892
             * profitPlan : 3
             * startDate : 2017-11-16 10:17:52
             * endDate : 2017-11-29 00:00:00
             * investMinAmount : 100.0
             * investAscendingAmount : 100.0
             * investMaxAmount : 2000.0
             * publishTime : 2017-10-29 18:18:20
             * raisePeriod : 3
             * lockPeriod : 1
             * fullBidTime : null
             * fullBidTimeFact : null
             * interestDateInt : null
             * interestStartDate : 2017-11-15 00:00:00
             * interestEndDate : 2018-02-15
             * remark : null
             * transferRate : 0.0
             * discountMaxRate : null
             * discountMinRate : null
             * thresholdAmount : null
             * redeemFeeRate : 0.0
             * createTime : null
             * updateTime : null
             * borrowName : HLW散标1
             * annualizedRate : 10.0
             * contractAmount : 2000.00
             * periodUnit : 3
             * periodLength : 3
             * status : 7
             * appendRate : 0.0
             * fromBorrowContractNo : 1029315
             * productNo : 1094f79406384422801bf403606bd6a2
             * secondTime : null
             * amountWait : 2000.0
             */

            private Object id;
            private String borrowNo;
            private int profitPlan;
            private String startDate;
            private String endDate;
            private double investMinAmount;
            private double investAscendingAmount;
            private double investMaxAmount;
            private String publishTime;
            private int raisePeriod;
            private int lockPeriod;
            private Object fullBidTime;
            private Object fullBidTimeFact;
            private Object interestDateInt;
            private String interestStartDate;
            private String interestEndDate;
            private Object remark;
            private double transferRate;
            private Object discountMaxRate;
            private Object discountMinRate;
            private Object thresholdAmount;
            private double redeemFeeRate;
            private Object createTime;
            private Object updateTime;
            private String borrowName;
            private double annualizedRate;
            private String contractAmount;
            private String periodUnit;
            private int periodLength;
            private int status;
            private double appendRate;
            private String fromBorrowContractNo;
            private String productNo;
            private Object secondTime;
            private double amountWait;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public String getBorrowNo() {
                return borrowNo;
            }

            public void setBorrowNo(String borrowNo) {
                this.borrowNo = borrowNo;
            }

            public int getProfitPlan() {
                return profitPlan;
            }

            public void setProfitPlan(int profitPlan) {
                this.profitPlan = profitPlan;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public double getInvestMinAmount() {
                return investMinAmount;
            }

            public void setInvestMinAmount(double investMinAmount) {
                this.investMinAmount = investMinAmount;
            }

            public double getInvestAscendingAmount() {
                return investAscendingAmount;
            }

            public void setInvestAscendingAmount(double investAscendingAmount) {
                this.investAscendingAmount = investAscendingAmount;
            }

            public double getInvestMaxAmount() {
                return investMaxAmount;
            }

            public void setInvestMaxAmount(double investMaxAmount) {
                this.investMaxAmount = investMaxAmount;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public int getRaisePeriod() {
                return raisePeriod;
            }

            public void setRaisePeriod(int raisePeriod) {
                this.raisePeriod = raisePeriod;
            }

            public int getLockPeriod() {
                return lockPeriod;
            }

            public void setLockPeriod(int lockPeriod) {
                this.lockPeriod = lockPeriod;
            }

            public Object getFullBidTime() {
                return fullBidTime;
            }

            public void setFullBidTime(Object fullBidTime) {
                this.fullBidTime = fullBidTime;
            }

            public Object getFullBidTimeFact() {
                return fullBidTimeFact;
            }

            public void setFullBidTimeFact(Object fullBidTimeFact) {
                this.fullBidTimeFact = fullBidTimeFact;
            }

            public Object getInterestDateInt() {
                return interestDateInt;
            }

            public void setInterestDateInt(Object interestDateInt) {
                this.interestDateInt = interestDateInt;
            }

            public String getInterestStartDate() {
                return interestStartDate;
            }

            public void setInterestStartDate(String interestStartDate) {
                this.interestStartDate = interestStartDate;
            }

            public String getInterestEndDate() {
                return interestEndDate;
            }

            public void setInterestEndDate(String interestEndDate) {
                this.interestEndDate = interestEndDate;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public double getTransferRate() {
                return transferRate;
            }

            public void setTransferRate(double transferRate) {
                this.transferRate = transferRate;
            }

            public Object getDiscountMaxRate() {
                return discountMaxRate;
            }

            public void setDiscountMaxRate(Object discountMaxRate) {
                this.discountMaxRate = discountMaxRate;
            }

            public Object getDiscountMinRate() {
                return discountMinRate;
            }

            public void setDiscountMinRate(Object discountMinRate) {
                this.discountMinRate = discountMinRate;
            }

            public Object getThresholdAmount() {
                return thresholdAmount;
            }

            public void setThresholdAmount(Object thresholdAmount) {
                this.thresholdAmount = thresholdAmount;
            }

            public double getRedeemFeeRate() {
                return redeemFeeRate;
            }

            public void setRedeemFeeRate(double redeemFeeRate) {
                this.redeemFeeRate = redeemFeeRate;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
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

            public String getContractAmount() {
                return contractAmount;
            }

            public void setContractAmount(String contractAmount) {
                this.contractAmount = contractAmount;
            }

            public String getPeriodUnit() {
                return periodUnit;
            }

            public void setPeriodUnit(String periodUnit) {
                this.periodUnit = periodUnit;
            }

            public int getPeriodLength() {
                return periodLength;
            }

            public void setPeriodLength(int periodLength) {
                this.periodLength = periodLength;
            }

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

            public String getFromBorrowContractNo() {
                return fromBorrowContractNo;
            }

            public void setFromBorrowContractNo(String fromBorrowContractNo) {
                this.fromBorrowContractNo = fromBorrowContractNo;
            }

            public String getProductNo() {
                return productNo;
            }

            public void setProductNo(String productNo) {
                this.productNo = productNo;
            }

            public Object getSecondTime() {
                return secondTime;
            }

            public void setSecondTime(Object secondTime) {
                this.secondTime = secondTime;
            }

            public double getAmountWait() {
                return amountWait;
            }

            public void setAmountWait(double amountWait) {
                this.amountWait = amountWait;
            }
        }

        public static class BorrowReserveBean {
            /**
             * reserveCorpus : 0.0
             * reserveInterest : 4.16
             * billDate : 2017-11-15 00:00:00
             * status : 2
             */

            private double reserveCorpus;
            private double reserveInterest;
            private String billDate;
            private int status;
            private int curStageNo;

            public int getCurStageNo() {
                return curStageNo;
            }

            public double getReserveCorpus() {
                return reserveCorpus;
            }

            public void setReserveCorpus(double reserveCorpus) {
                this.reserveCorpus = reserveCorpus;
            }

            public double getReserveInterest() {
                return reserveInterest;
            }

            public void setReserveInterest(double reserveInterest) {
                this.reserveInterest = reserveInterest;
            }

            public String getBillDate() {
                return billDate;
            }

            public void setBillDate(String billDate) {
                this.billDate = billDate;
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
