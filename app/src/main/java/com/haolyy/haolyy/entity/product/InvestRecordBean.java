package com.haolyy.haolyy.entity.product;

import java.io.Serializable;
import java.util.List;

/**
 * Created by niudeyang on 2017/11/3.
 */

public class InvestRecordBean implements Serializable {

    /**
     * code : success
     * msg : 成功
     * model : {"list":[{"id":40,"orderNo":null,"orderDate":"2017-11-03 14:20:42","orderTime":"142042","userCode":null,"accountOrgNo":null,"accountNo":null,"idnoType":null,"idno":null,"realname":"台风","registerMobile":null,"borrowNo":"HLW-1000001445563058","borrowName":null,"productNo":null,"borrowType":null,"investAmount":100,"investAmountActual":null,"annualizedRate":null,"appendRate":null,"couponRate":null,"annualizedProfit":null,"appendProfit":null,"couponProfit":null,"waitProfit":null,"yesProfit":null,"couponNo":null,"discountType":null,"interestDay":null,"recommendCode":null,"recommendUserCode":null,"unfreezeOrderId":null,"freezTrxId":null,"jzqApplyNo":null,"status":null,"versionMvcc":null,"thirdPlatformId":null,"thirdUserId":null,"thirdTranAmount":null,"thirdResponseCode":null,"thirdResponseMsg":null,"thirdResponseTime":null,"platform":null,"client":1,"version":null,"ip":null,"createTime":null,"updateTime":null},{"id":39,"orderNo":null,"orderDate":"2017-11-03 14:20:33","orderTime":"142033","userCode":null,"accountOrgNo":null,"accountNo":null,"idnoType":null,"idno":null,"realname":"台风","registerMobile":null,"borrowNo":"HLW-1000001445563058","borrowName":null,"productNo":null,"borrowType":null,"investAmount":200,"investAmountActual":null,"annualizedRate":null,"appendRate":null,"couponRate":null,"annualizedProfit":null,"appendProfit":null,"couponProfit":null,"waitProfit":null,"yesProfit":null,"couponNo":null,"discountType":null,"interestDay":null,"recommendCode":null,"recommendUserCode":null,"unfreezeOrderId":null,"freezTrxId":null,"jzqApplyNo":null,"status":null,"versionMvcc":null,"thirdPlatformId":null,"thirdUserId":null,"thirdTranAmount":null,"thirdResponseCode":null,"thirdResponseMsg":null,"thirdResponseTime":null,"platform":null,"client":1,"version":null,"ip":null,"createTime":null,"updateTime":null},{"id":29,"orderNo":null,"orderDate":"2017-11-03 11:24:30","orderTime":"112430","userCode":null,"accountOrgNo":null,"accountNo":null,"idnoType":null,"idno":null,"realname":"童之尧","registerMobile":null,"borrowNo":"HLW-1000001445563058","borrowName":null,"productNo":null,"borrowType":null,"investAmount":300,"investAmountActual":null,"annualizedRate":null,"appendRate":null,"couponRate":null,"annualizedProfit":null,"appendProfit":null,"couponProfit":null,"waitProfit":null,"yesProfit":null,"couponNo":null,"discountType":null,"interestDay":null,"recommendCode":null,"recommendUserCode":null,"unfreezeOrderId":null,"freezTrxId":null,"jzqApplyNo":null,"status":null,"versionMvcc":null,"thirdPlatformId":null,"thirdUserId":null,"thirdTranAmount":null,"thirdResponseCode":null,"thirdResponseMsg":null,"thirdResponseTime":null,"platform":null,"client":1,"version":null,"ip":null,"createTime":null,"updateTime":null}],"allCount":3}
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

    public static class ModelBean implements Serializable {
        /**
         * list : [{"id":40,"orderNo":null,"orderDate":"2017-11-03 14:20:42","orderTime":"142042","userCode":null,"accountOrgNo":null,"accountNo":null,"idnoType":null,"idno":null,"realname":"台风","registerMobile":null,"borrowNo":"HLW-1000001445563058","borrowName":null,"productNo":null,"borrowType":null,"investAmount":100,"investAmountActual":null,"annualizedRate":null,"appendRate":null,"couponRate":null,"annualizedProfit":null,"appendProfit":null,"couponProfit":null,"waitProfit":null,"yesProfit":null,"couponNo":null,"discountType":null,"interestDay":null,"recommendCode":null,"recommendUserCode":null,"unfreezeOrderId":null,"freezTrxId":null,"jzqApplyNo":null,"status":null,"versionMvcc":null,"thirdPlatformId":null,"thirdUserId":null,"thirdTranAmount":null,"thirdResponseCode":null,"thirdResponseMsg":null,"thirdResponseTime":null,"platform":null,"client":1,"version":null,"ip":null,"createTime":null,"updateTime":null},{"id":39,"orderNo":null,"orderDate":"2017-11-03 14:20:33","orderTime":"142033","userCode":null,"accountOrgNo":null,"accountNo":null,"idnoType":null,"idno":null,"realname":"台风","registerMobile":null,"borrowNo":"HLW-1000001445563058","borrowName":null,"productNo":null,"borrowType":null,"investAmount":200,"investAmountActual":null,"annualizedRate":null,"appendRate":null,"couponRate":null,"annualizedProfit":null,"appendProfit":null,"couponProfit":null,"waitProfit":null,"yesProfit":null,"couponNo":null,"discountType":null,"interestDay":null,"recommendCode":null,"recommendUserCode":null,"unfreezeOrderId":null,"freezTrxId":null,"jzqApplyNo":null,"status":null,"versionMvcc":null,"thirdPlatformId":null,"thirdUserId":null,"thirdTranAmount":null,"thirdResponseCode":null,"thirdResponseMsg":null,"thirdResponseTime":null,"platform":null,"client":1,"version":null,"ip":null,"createTime":null,"updateTime":null},{"id":29,"orderNo":null,"orderDate":"2017-11-03 11:24:30","orderTime":"112430","userCode":null,"accountOrgNo":null,"accountNo":null,"idnoType":null,"idno":null,"realname":"童之尧","registerMobile":null,"borrowNo":"HLW-1000001445563058","borrowName":null,"productNo":null,"borrowType":null,"investAmount":300,"investAmountActual":null,"annualizedRate":null,"appendRate":null,"couponRate":null,"annualizedProfit":null,"appendProfit":null,"couponProfit":null,"waitProfit":null,"yesProfit":null,"couponNo":null,"discountType":null,"interestDay":null,"recommendCode":null,"recommendUserCode":null,"unfreezeOrderId":null,"freezTrxId":null,"jzqApplyNo":null,"status":null,"versionMvcc":null,"thirdPlatformId":null,"thirdUserId":null,"thirdTranAmount":null,"thirdResponseCode":null,"thirdResponseMsg":null,"thirdResponseTime":null,"platform":null,"client":1,"version":null,"ip":null,"createTime":null,"updateTime":null}]
         * allCount : 3
         */

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

        public static class ListBean implements Serializable {
            /**
             * id : 40
             * orderNo : null
             * orderDate : 2017-11-03 14:20:42
             * orderTime : 142042
             * userCode : null
             * accountOrgNo : null
             * accountNo : null
             * idnoType : null
             * idno : null
             * realname : 台风
             * registerMobile : null
             * borrowNo : HLW-1000001445563058
             * borrowName : null
             * productNo : null
             * borrowType : null
             * investAmount : 100
             * investAmountActual : null
             * annualizedRate : null
             * appendRate : null
             * couponRate : null
             * annualizedProfit : null
             * appendProfit : null
             * couponProfit : null
             * waitProfit : null
             * yesProfit : null
             * couponNo : null
             * discountType : null
             * interestDay : null
             * recommendCode : null
             * recommendUserCode : null
             * unfreezeOrderId : null
             * freezTrxId : null
             * jzqApplyNo : null
             * status : null
             * versionMvcc : null
             * thirdPlatformId : null
             * thirdUserId : null
             * thirdTranAmount : null
             * thirdResponseCode : null
             * thirdResponseMsg : null
             * thirdResponseTime : null
             * platform : null
             * client : 1
             * version : null
             * ip : null
             * createTime : null
             * updateTime : null
             */

            private int id;
            private Object orderNo;
            private String orderDate;
            private String orderTime;
            private Object userCode;
            private Object accountOrgNo;
            private Object accountNo;
            private Object idnoType;
            private Object idno;
            private String realname;
            private String borrowNo;
            private Object borrowName;
            private Object productNo;
            private Object borrowType;
            private double investAmount;
            private Object investAmountActual;
            private Object annualizedRate;
            private Object appendRate;
            private Object couponRate;
            private Object annualizedProfit;
            private Object appendProfit;
            private Object couponProfit;
            private Object waitProfit;
            private Object yesProfit;
            private Object couponNo;
            private Object discountType;
            private Object interestDay;
            private Object recommendCode;
            private Object recommendUserCode;
            private Object unfreezeOrderId;
            private Object freezTrxId;
            private Object jzqApplyNo;
            private Object status;
            private Object versionMvcc;
            private Object thirdPlatformId;
            private Object thirdUserId;
            private Object thirdTranAmount;
            private Object thirdResponseCode;
            private Object thirdResponseMsg;
            private Object thirdResponseTime;
            private Object platform;
            private int client;
            private Object version;
            private Object ip;
            private Object createTime;
            private Object updateTime;

            public String getRegisterMobile() {
                return registerMobile;
            }

            private String registerMobile;

            public double getInvestAmount() {
                return investAmount;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(Object orderNo) {
                this.orderNo = orderNo;
            }

            public String getOrderDate() {
                return orderDate;
            }

            public void setOrderDate(String orderDate) {
                this.orderDate = orderDate;
            }

            public String getOrderTime() {
                return orderTime;
            }

            public void setOrderTime(String orderTime) {
                this.orderTime = orderTime;
            }

            public Object getUserCode() {
                return userCode;
            }

            public void setUserCode(Object userCode) {
                this.userCode = userCode;
            }

            public Object getAccountOrgNo() {
                return accountOrgNo;
            }

            public void setAccountOrgNo(Object accountOrgNo) {
                this.accountOrgNo = accountOrgNo;
            }

            public Object getAccountNo() {
                return accountNo;
            }

            public void setAccountNo(Object accountNo) {
                this.accountNo = accountNo;
            }

            public Object getIdnoType() {
                return idnoType;
            }

            public void setIdnoType(Object idnoType) {
                this.idnoType = idnoType;
            }

            public Object getIdno() {
                return idno;
            }

            public void setIdno(Object idno) {
                this.idno = idno;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getBorrowNo() {
                return borrowNo;
            }

            public void setBorrowNo(String borrowNo) {
                this.borrowNo = borrowNo;
            }

            public Object getBorrowName() {
                return borrowName;
            }

            public void setBorrowName(Object borrowName) {
                this.borrowName = borrowName;
            }

            public Object getProductNo() {
                return productNo;
            }

            public void setProductNo(Object productNo) {
                this.productNo = productNo;
            }

            public Object getBorrowType() {
                return borrowType;
            }

            public void setBorrowType(Object borrowType) {
                this.borrowType = borrowType;
            }


            public void setInvestAmount(int investAmount) {
                this.investAmount = investAmount;
            }

            public Object getInvestAmountActual() {
                return investAmountActual;
            }

            public void setInvestAmountActual(Object investAmountActual) {
                this.investAmountActual = investAmountActual;
            }

            public Object getAnnualizedRate() {
                return annualizedRate;
            }

            public void setAnnualizedRate(Object annualizedRate) {
                this.annualizedRate = annualizedRate;
            }

            public Object getAppendRate() {
                return appendRate;
            }

            public void setAppendRate(Object appendRate) {
                this.appendRate = appendRate;
            }

            public Object getCouponRate() {
                return couponRate;
            }

            public void setCouponRate(Object couponRate) {
                this.couponRate = couponRate;
            }

            public Object getAnnualizedProfit() {
                return annualizedProfit;
            }

            public void setAnnualizedProfit(Object annualizedProfit) {
                this.annualizedProfit = annualizedProfit;
            }

            public Object getAppendProfit() {
                return appendProfit;
            }

            public void setAppendProfit(Object appendProfit) {
                this.appendProfit = appendProfit;
            }

            public Object getCouponProfit() {
                return couponProfit;
            }

            public void setCouponProfit(Object couponProfit) {
                this.couponProfit = couponProfit;
            }

            public Object getWaitProfit() {
                return waitProfit;
            }

            public void setWaitProfit(Object waitProfit) {
                this.waitProfit = waitProfit;
            }

            public Object getYesProfit() {
                return yesProfit;
            }

            public void setYesProfit(Object yesProfit) {
                this.yesProfit = yesProfit;
            }

            public Object getCouponNo() {
                return couponNo;
            }

            public void setCouponNo(Object couponNo) {
                this.couponNo = couponNo;
            }

            public Object getDiscountType() {
                return discountType;
            }

            public void setDiscountType(Object discountType) {
                this.discountType = discountType;
            }

            public Object getInterestDay() {
                return interestDay;
            }

            public void setInterestDay(Object interestDay) {
                this.interestDay = interestDay;
            }

            public Object getRecommendCode() {
                return recommendCode;
            }

            public void setRecommendCode(Object recommendCode) {
                this.recommendCode = recommendCode;
            }

            public Object getRecommendUserCode() {
                return recommendUserCode;
            }

            public void setRecommendUserCode(Object recommendUserCode) {
                this.recommendUserCode = recommendUserCode;
            }

            public Object getUnfreezeOrderId() {
                return unfreezeOrderId;
            }

            public void setUnfreezeOrderId(Object unfreezeOrderId) {
                this.unfreezeOrderId = unfreezeOrderId;
            }

            public Object getFreezTrxId() {
                return freezTrxId;
            }

            public void setFreezTrxId(Object freezTrxId) {
                this.freezTrxId = freezTrxId;
            }

            public Object getJzqApplyNo() {
                return jzqApplyNo;
            }

            public void setJzqApplyNo(Object jzqApplyNo) {
                this.jzqApplyNo = jzqApplyNo;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getVersionMvcc() {
                return versionMvcc;
            }

            public void setVersionMvcc(Object versionMvcc) {
                this.versionMvcc = versionMvcc;
            }

            public Object getThirdPlatformId() {
                return thirdPlatformId;
            }

            public void setThirdPlatformId(Object thirdPlatformId) {
                this.thirdPlatformId = thirdPlatformId;
            }

            public Object getThirdUserId() {
                return thirdUserId;
            }

            public void setThirdUserId(Object thirdUserId) {
                this.thirdUserId = thirdUserId;
            }

            public Object getThirdTranAmount() {
                return thirdTranAmount;
            }

            public void setThirdTranAmount(Object thirdTranAmount) {
                this.thirdTranAmount = thirdTranAmount;
            }

            public Object getThirdResponseCode() {
                return thirdResponseCode;
            }

            public void setThirdResponseCode(Object thirdResponseCode) {
                this.thirdResponseCode = thirdResponseCode;
            }

            public Object getThirdResponseMsg() {
                return thirdResponseMsg;
            }

            public void setThirdResponseMsg(Object thirdResponseMsg) {
                this.thirdResponseMsg = thirdResponseMsg;
            }

            public Object getThirdResponseTime() {
                return thirdResponseTime;
            }

            public void setThirdResponseTime(Object thirdResponseTime) {
                this.thirdResponseTime = thirdResponseTime;
            }

            public Object getPlatform() {
                return platform;
            }

            public void setPlatform(Object platform) {
                this.platform = platform;
            }

            public int getClient() {
                return client;
            }

            public void setClient(int client) {
                this.client = client;
            }

            public Object getVersion() {
                return version;
            }

            public void setVersion(Object version) {
                this.version = version;
            }

            public Object getIp() {
                return ip;
            }

            public void setIp(Object ip) {
                this.ip = ip;
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
        }
    }
}
