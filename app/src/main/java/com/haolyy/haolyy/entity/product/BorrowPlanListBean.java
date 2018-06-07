package com.haolyy.haolyy.entity.product;

import java.util.List;

/**
 * Created by niudeyang on 2017/12/26.
 */

public class BorrowPlanListBean {
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
        private List<NoviciateBorrowBean> noviciateBorrow;
        private List<DqyBorrowListBean> dqyBorrowList;
        private List<ListBean> list;

        public int getAllCount() {
            return allCount;
        }

        public void setAllCount(int allCount) {
            this.allCount = allCount;
        }

        public List<NoviciateBorrowBean> getNoviciateBorrow() {
            return noviciateBorrow;
        }

        public void setNoviciateBorrow(List<NoviciateBorrowBean> noviciateBorrow) {
            this.noviciateBorrow = noviciateBorrow;
        }

        public List<DqyBorrowListBean> getDqyBorrowList() {
            return dqyBorrowList;
        }

        public void setDqyBorrowList(List<DqyBorrowListBean> dqyBorrowList) {
            this.dqyBorrowList = dqyBorrowList;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class NoviciateBorrowBean {
            /**
             * appendRate : 6
             * profitPlan : 4
             * proportion : 0
             * periodUnit : 2
             * productName : 姚旭
             * borrowName : 新手7号
             * investMinAmount : 100
             * annualizedRate : 7
             * contractAmount : 15000
             * periodLength : 1
             * borrowType : 2
             * investMaxAmount : 15000
             * borrowNo : HLW-1000000497997284
             * status : 4
             */

            private double appendRate;
            private int profitPlan;
            private int proportion;
            private int periodUnit;
            private String productName;
            private String borrowName;
            private double investMinAmount;
            private double annualizedRate;
            private double contractAmount;
            private double amountWait;
            private int periodLength;
            private int borrowType;
            private int investMaxAmount;
            private String borrowNo;
            private int status;//状态 3.待售 4、开始募集（募集中） 5、已满标（募集完成） 6、放款中 7、计息中（还款中） 9、已结清（还款完成）

            public double getInvestMinAmount() {
                return investMinAmount;
            }

            public double getAppendRate() {
                return appendRate;
            }

            public double getAnnualizedRate() {
                return annualizedRate;
            }

            public double getContractAmount() {
                return contractAmount;
            }

            public double getAmountWait() {
                return amountWait;
            }

            public void setAppendRate(int appendRate) {
                this.appendRate = appendRate;
            }

            public int getProfitPlan() {
                return profitPlan;
            }

            public void setProfitPlan(int profitPlan) {
                this.profitPlan = profitPlan;
            }

            public int getProportion() {
                return proportion;
            }

            public void setProportion(int proportion) {
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


            public void setInvestMinAmount(int investMinAmount) {
                this.investMinAmount = investMinAmount;
            }


            public void setAnnualizedRate(int annualizedRate) {
                this.annualizedRate = annualizedRate;
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

            public int getInvestMaxAmount() {
                return investMaxAmount;
            }

            public void setInvestMaxAmount(int investMaxAmount) {
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

        public static class DqyBorrowListBean {
            /**
             * STATUS : 4
             * borrow_name : Test名名的名
             * period_unit : 2
             * append_rate : 2
             * annualized_rate : 6
             * period_length : 0
             * borrow_no : HLW-1000001333317410
             */

            private int status;
            private String borrow_name;
            private int period_unit;
            private double append_rate;
            private int annualized_rate;
            private int period_length;
            private String borrow_no;
            private double AmountWait;
            private double investMinAmount;
            private double annualizedRate;
            private double contractAmount;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public double getAmountWait() {
                return AmountWait;
            }

            public void setAmountWait(double amountWait) {
                AmountWait = amountWait;
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

            public double getAppend_rate() {
                return append_rate;
            }

            public String getBorrow_name() {
                return borrow_name;
            }

            public void setBorrow_name(String borrow_name) {
                this.borrow_name = borrow_name;
            }

            public int getPeriod_unit() {
                return period_unit;
            }

            public void setPeriod_unit(int period_unit) {
                this.period_unit = period_unit;
            }


            public void setAppend_rate(int append_rate) {
                this.append_rate = append_rate;
            }

            public int getAnnualized_rate() {
                return annualized_rate;
            }

            public void setAnnualized_rate(int annualized_rate) {
                this.annualized_rate = annualized_rate;
            }

            public int getPeriod_length() {
                return period_length;
            }

            public void setPeriod_length(int period_length) {
                this.period_length = period_length;
            }

            public String getBorrow_no() {
                return borrow_no;
            }

            public void setBorrow_no(String borrow_no) {
                this.borrow_no = borrow_no;
            }
        }

        public static class ListBean {
            /**
             * thirdUserId : null
             * remark : null
             * periodUnit : 2
             * firstCategoryId : null
             * platform : null
             * borrowName : 不是新手标2
             * countPeople : null
             * annualizedRate : 7
             * accountOrgId : null
             * id : 1328
             * borrowNo : HLW-1000001376279912
             * productNo : 5152da49d5c14354a90392874a0a4934
             * appendRate : 0
             * borrowActiveType : 0
             * updateTime : 2018-03-01 13:53:52
             * version : null
             * accountId : null
             * fromBorrowContractNo : null
             * createTime : 2018-03-01 13:56:40
             * thirdPlatformId : null
             * contractAmount : 40000
             * periodLength : 1
             * borrowType : 2
             * secondCategoryId : null
             * amountWait : null
             * startDate : 2018-03-01 13:56:23
             * status : 4
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
            private String startDate;
            private int status;
            private double investminamount;

            public double getAppendRate() {
                return appendRate;
            }

            public double getAnnualizedRate() {
                return annualizedRate;
            }

            public double getContractAmount() {
                return contractAmount;
            }

            public double getInvestMinAmount() {
                return investminamount;
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

            public double getAmountWait() {
                return amountWait;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
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
