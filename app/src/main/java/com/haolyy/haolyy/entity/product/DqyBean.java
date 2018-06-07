package com.haolyy.haolyy.entity.product;

import java.util.List;

/**
 * Created by shanghai on 2018/3/1.
 */

public class DqyBean {
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
        private int allCount;
        private List<ListBean> list;

        public int getAllCount() {
            return allCount;
        }

        public void setAllCount(int allCount) {
            this.allCount = allCount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * thirdUserId : null
             * remark : null
             * periodUnit : 2
             * firstCategoryId : null
             * platform : null
             * borrowName : 533短期A005
             * countPeople : null
             * annualizedRate : 5
             * accountOrgId : null
             * id : 1164
             * borrowNo : HLW-1000000848208899
             * productNo : 98dd0b134fd44d7bb04c528172fc545c
             * appendRate : 2
             * investminamount : 100
             * borrowActiveType : 0
             * updateTime : 2018-02-05 19:07:42
             * version : null
             * accountId : null
             * fromBorrowContractNo : null
             * createTime : 2018-02-05 18:52:46
             * thirdPlatformId : null
             * contractAmount : 3000
             * periodLength : 1
             * borrowType : 2
             * secondCategoryId : null
             * amountWait : 0
             * startDate : null
             * status : 9
             */

            private Object thirdUserId;
            private Object remark;
            private String periodUnit;
            private Object firstCategoryId;
            private Object platform;
            private String borrowName;
            private Object countPeople;
            private double annualizedRate;
            private Object accountOrgId;
            private int id;
            private String borrowNo;
            private String productNo;
            private double appendRate;
            private double investminamount;
            private int borrowActiveType;
            private String updateTime;
            private Object version;
            private Object accountId;
            private Object fromBorrowContractNo;
            private String createTime;
            private Object thirdPlatformId;
            private double contractAmount;
            private int periodLength;
            private int borrowType;
            private Object secondCategoryId;
            private double amountWait;
            private Object startDate;
            private int status;

            public double getAnnualizedRate() {
                return annualizedRate;
            }

            public double getAppendRate() {
                return appendRate;
            }

            public double getInvestminamount() {
                return investminamount;
            }

            public double getContractAmount() {
                return contractAmount;
            }

            public double getAmountWait() {
                return amountWait;
            }

            public Object getThirdUserId() {
                return thirdUserId;
            }

            public void setThirdUserId(Object thirdUserId) {
                this.thirdUserId = thirdUserId;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public String getPeriodUnit() {
                return periodUnit;
            }

            public void setPeriodUnit(String periodUnit) {
                this.periodUnit = periodUnit;
            }

            public Object getFirstCategoryId() {
                return firstCategoryId;
            }

            public void setFirstCategoryId(Object firstCategoryId) {
                this.firstCategoryId = firstCategoryId;
            }

            public Object getPlatform() {
                return platform;
            }

            public void setPlatform(Object platform) {
                this.platform = platform;
            }

            public String getBorrowName() {
                return borrowName;
            }

            public void setBorrowName(String borrowName) {
                this.borrowName = borrowName;
            }

            public Object getCountPeople() {
                return countPeople;
            }

            public void setCountPeople(Object countPeople) {
                this.countPeople = countPeople;
            }


            public void setAnnualizedRate(int annualizedRate) {
                this.annualizedRate = annualizedRate;
            }

            public Object getAccountOrgId() {
                return accountOrgId;
            }

            public void setAccountOrgId(Object accountOrgId) {
                this.accountOrgId = accountOrgId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getBorrowNo() {
                return borrowNo;
            }

            public void setBorrowNo(String borrowNo) {
                this.borrowNo = borrowNo;
            }

            public String getProductNo() {
                return productNo;
            }

            public void setProductNo(String productNo) {
                this.productNo = productNo;
            }


            public void setAppendRate(int appendRate) {
                this.appendRate = appendRate;
            }


            public void setInvestminamount(int investminamount) {
                this.investminamount = investminamount;
            }

            public int getBorrowActiveType() {
                return borrowActiveType;
            }

            public void setBorrowActiveType(int borrowActiveType) {
                this.borrowActiveType = borrowActiveType;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public Object getVersion() {
                return version;
            }

            public void setVersion(Object version) {
                this.version = version;
            }

            public Object getAccountId() {
                return accountId;
            }

            public void setAccountId(Object accountId) {
                this.accountId = accountId;
            }

            public Object getFromBorrowContractNo() {
                return fromBorrowContractNo;
            }

            public void setFromBorrowContractNo(Object fromBorrowContractNo) {
                this.fromBorrowContractNo = fromBorrowContractNo;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getThirdPlatformId() {
                return thirdPlatformId;
            }

            public void setThirdPlatformId(Object thirdPlatformId) {
                this.thirdPlatformId = thirdPlatformId;
            }


            public void setContractAmount(int contractAmount) {
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

            public Object getSecondCategoryId() {
                return secondCategoryId;
            }

            public void setSecondCategoryId(Object secondCategoryId) {
                this.secondCategoryId = secondCategoryId;
            }


            public void setAmountWait(int amountWait) {
                this.amountWait = amountWait;
            }

            public Object getStartDate() {
                return startDate;
            }

            public void setStartDate(Object startDate) {
                this.startDate = startDate;
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
