package com.haolyy.haolyy.entity.userinfo;

import java.util.List;

/**
 * Created by niudeyang on 2017/10/31.
 */

public class AccountCouponbean  {

    /**
     * code : success
     * msg : 成功
     * model : {"couponReceiveList":[{"endDate":"2018-03-12","effect":20,"couponNo":"FXQ_HLW_20180302_01","deliveryRuleDetail":"（1）单笔投资满100元，任意一个产品，均可使用20元返现券","type":"返现券","maxAmount":100,"startDate":"2018-03-02","status":2,"deliveryRangeProduct":"姚旭,test,预约标D7,预约标D3,预约标5,赢计划D,赢计划C,赢计划B,赢计划A,短期赢B,短期赢A,test,好利-预约,测试-理财,hl投资产品53302,hl投资产品533"},{"endDate":"2018-03-12","effect":20,"couponNo":"FXQ_HLW_20180302_01","deliveryRuleDetail":"（1）单笔投资满100元，任意一个产品，均可使用20元返现券","type":"返现券","maxAmount":100,"startDate":"2018-03-02","status":2,"deliveryRangeProduct":"姚旭,test,预约标D7,预约标D3,预约标5,赢计划D,赢计划C,赢计划B,赢计划A,短期赢B,短期赢A,test,好利-预约,测试-理财,hl投资产品53302,hl投资产品533"},{"endDate":"2018-03-12","effect":0.5,"couponNo":"JXQ_HLW_20180302_02","deliveryRuleDetail":"1112316464","type":"加息券","maxAmount":100,"startDate":"2018-03-02","status":1,"deliveryRangeProduct":"姚旭,预约标D7,预约标D3,预约标5,赢计划D,赢计划C,赢计划B,赢计划A,短期赢B,短期赢A,test,好利-预约,测试-理财,hl投资产品53302,hl投资产品533"},{"endDate":"2018-03-12","effect":0.5,"couponNo":"JXQ_HLW_20180302_02","deliveryRuleDetail":"1112316464","type":"加息券","maxAmount":100,"startDate":"2018-03-02","status":1,"deliveryRangeProduct":"姚旭,预约标D7,预约标D3,预约标5,赢计划D,赢计划C,赢计划B,赢计划A,短期赢B,短期赢A,test,好利-预约,测试-理财,hl投资产品53302,hl投资产品533"},{"endDate":"2018-03-12","effect":0.5,"couponNo":"JXQ_HLW_20180302_02","deliveryRuleDetail":"1112316464","type":"加息券","maxAmount":100,"startDate":"2018-03-02","status":1,"deliveryRangeProduct":"姚旭,预约标D7,预约标D3,预约标5,赢计划D,赢计划C,赢计划B,赢计划A,短期赢B,短期赢A,test,好利-预约,测试-理财,hl投资产品53302,hl投资产品533"},{"endDate":"2018-03-12","effect":0.5,"couponNo":"JXQ_HLW_20180302_02","deliveryRuleDetail":"1112316464","type":"加息券","maxAmount":100,"startDate":"2018-03-02","status":1,"deliveryRangeProduct":"姚旭,预约标D7,预约标D3,预约标5,赢计划D,赢计划C,赢计划B,赢计划A,短期赢B,短期赢A,test,好利-预约,测试-理财,hl投资产品53302,hl投资产品533"},{"endDate":"2018-03-12","effect":0.5,"couponNo":"JXQ_HLW_20180302_02","deliveryRuleDetail":"1112316464","type":"加息券","maxAmount":100,"startDate":"2018-03-02","status":1,"deliveryRangeProduct":"姚旭,预约标D7,预约标D3,预约标5,赢计划D,赢计划C,赢计划B,赢计划A,短期赢B,短期赢A,test,好利-预约,测试-理财,hl投资产品53302,hl投资产品533"}],"notUsedCount":5,"expiredCount":0,"usedCount":2}
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
        /**
         * couponReceiveList : [{"endDate":"2018-03-12","effect":20,"couponNo":"FXQ_HLW_20180302_01","deliveryRuleDetail":"（1）单笔投资满100元，任意一个产品，均可使用20元返现券","type":"返现券","maxAmount":100,"startDate":"2018-03-02","status":2,"deliveryRangeProduct":"姚旭,test,预约标D7,预约标D3,预约标5,赢计划D,赢计划C,赢计划B,赢计划A,短期赢B,短期赢A,test,好利-预约,测试-理财,hl投资产品53302,hl投资产品533"},{"endDate":"2018-03-12","effect":20,"couponNo":"FXQ_HLW_20180302_01","deliveryRuleDetail":"（1）单笔投资满100元，任意一个产品，均可使用20元返现券","type":"返现券","maxAmount":100,"startDate":"2018-03-02","status":2,"deliveryRangeProduct":"姚旭,test,预约标D7,预约标D3,预约标5,赢计划D,赢计划C,赢计划B,赢计划A,短期赢B,短期赢A,test,好利-预约,测试-理财,hl投资产品53302,hl投资产品533"},{"endDate":"2018-03-12","effect":0.5,"couponNo":"JXQ_HLW_20180302_02","deliveryRuleDetail":"1112316464","type":"加息券","maxAmount":100,"startDate":"2018-03-02","status":1,"deliveryRangeProduct":"姚旭,预约标D7,预约标D3,预约标5,赢计划D,赢计划C,赢计划B,赢计划A,短期赢B,短期赢A,test,好利-预约,测试-理财,hl投资产品53302,hl投资产品533"},{"endDate":"2018-03-12","effect":0.5,"couponNo":"JXQ_HLW_20180302_02","deliveryRuleDetail":"1112316464","type":"加息券","maxAmount":100,"startDate":"2018-03-02","status":1,"deliveryRangeProduct":"姚旭,预约标D7,预约标D3,预约标5,赢计划D,赢计划C,赢计划B,赢计划A,短期赢B,短期赢A,test,好利-预约,测试-理财,hl投资产品53302,hl投资产品533"},{"endDate":"2018-03-12","effect":0.5,"couponNo":"JXQ_HLW_20180302_02","deliveryRuleDetail":"1112316464","type":"加息券","maxAmount":100,"startDate":"2018-03-02","status":1,"deliveryRangeProduct":"姚旭,预约标D7,预约标D3,预约标5,赢计划D,赢计划C,赢计划B,赢计划A,短期赢B,短期赢A,test,好利-预约,测试-理财,hl投资产品53302,hl投资产品533"},{"endDate":"2018-03-12","effect":0.5,"couponNo":"JXQ_HLW_20180302_02","deliveryRuleDetail":"1112316464","type":"加息券","maxAmount":100,"startDate":"2018-03-02","status":1,"deliveryRangeProduct":"姚旭,预约标D7,预约标D3,预约标5,赢计划D,赢计划C,赢计划B,赢计划A,短期赢B,短期赢A,test,好利-预约,测试-理财,hl投资产品53302,hl投资产品533"},{"endDate":"2018-03-12","effect":0.5,"couponNo":"JXQ_HLW_20180302_02","deliveryRuleDetail":"1112316464","type":"加息券","maxAmount":100,"startDate":"2018-03-02","status":1,"deliveryRangeProduct":"姚旭,预约标D7,预约标D3,预约标5,赢计划D,赢计划C,赢计划B,赢计划A,短期赢B,短期赢A,test,好利-预约,测试-理财,hl投资产品53302,hl投资产品533"}]
         * notUsedCount : 5
         * expiredCount : 0
         * usedCount : 2
         */

        private int notUsedCount;
        private int expiredCount;
        private int usedCount;
        private List<CouponReceiveListBean> couponReceiveList;

        public int getNotUsedCount() {
            return notUsedCount;
        }

        public void setNotUsedCount(int notUsedCount) {
            this.notUsedCount = notUsedCount;
        }

        public int getExpiredCount() {
            return expiredCount;
        }

        public void setExpiredCount(int expiredCount) {
            this.expiredCount = expiredCount;
        }

        public int getUsedCount() {
            return usedCount;
        }

        public void setUsedCount(int usedCount) {
            this.usedCount = usedCount;
        }

        public List<CouponReceiveListBean> getCouponReceiveList() {
            return couponReceiveList;
        }

        public void setCouponReceiveList(List<CouponReceiveListBean> couponReceiveList) {
            this.couponReceiveList = couponReceiveList;
        }

        public static class CouponReceiveListBean {
            /**
             * endDate : 2018-03-12
             * effect : 20.0
             * couponNo : FXQ_HLW_20180302_01
             * deliveryRuleDetail : （1）单笔投资满100元，任意一个产品，均可使用20元返现券
             * type : 返现券
             * maxAmount : 100.0
             * startDate : 2018-03-02
             * status : 2
             * deliveryRangeProduct : 姚旭,test,预约标D7,预约标D3,预约标5,赢计划D,赢计划C,赢计划B,赢计划A,短期赢B,短期赢A,test,好利-预约,测试-理财,hl投资产品53302,hl投资产品533
             */

            private String endDate;
            private double effect;
            private String couponNo;
            private String deliveryRuleDetail;
            private String type;
            private double maxAmount;
            private String startDate;
            private int status;
            private String deliveryRangeProduct;

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public double getEffect() {
                return effect;
            }

            public void setEffect(double effect) {
                this.effect = effect;
            }

            public String getCouponNo() {
                return couponNo;
            }

            public void setCouponNo(String couponNo) {
                this.couponNo = couponNo;
            }

            public String getDeliveryRuleDetail() {
                return deliveryRuleDetail;
            }

            public void setDeliveryRuleDetail(String deliveryRuleDetail) {
                this.deliveryRuleDetail = deliveryRuleDetail;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getDeliveryRangeProduct() {
                return deliveryRangeProduct;
            }

            public void setDeliveryRangeProduct(String deliveryRangeProduct) {
                this.deliveryRangeProduct = deliveryRangeProduct;
            }
        }
    }
}
