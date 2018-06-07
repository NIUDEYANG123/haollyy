package com.haolyy.haolyy.entity.userinfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by niudeyang on 2017/10/31.
 */

public class WithDrawCoupon implements Serializable {

    /**
     * code : success
     * msg : 成功
     * model : {"dataList":[{"effect":50,"client":"1,2,3,4","couponNo":"JMQ_HLW_20180305_20","deliveryRuleDetail":"测试普通提现快速提现","type":3,"periodUnit":1,"startDate":"2018-03-05","canUse":1,"receiveId":"JMQ_HLW_20180305_20_20180305_0000003"},{"effect":50,"client":"1,2,3,4","couponNo":"JMQ_HLW_20180305_21","deliveryRuleDetail":"测试普通提现快速提现","type":3,"periodUnit":1,"startDate":"2018-03-05","canUse":1,"receiveId":"JMQ_HLW_20180305_21_20180305_0000002"},{"effect":50,"client":"1,2,3,4","couponNo":"JMQ_HLW_20180305_22","deliveryRuleDetail":"测试普通提现快速提现","type":3,"periodUnit":1,"startDate":"2018-03-05","canUse":1,"receiveId":"JMQ_HLW_20180305_22_20180305_0000001"}],"count":3}
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
         * dataList : [{"effect":50,"client":"1,2,3,4","couponNo":"JMQ_HLW_20180305_20","deliveryRuleDetail":"测试普通提现快速提现","type":3,"periodUnit":1,"startDate":"2018-03-05","canUse":1,"receiveId":"JMQ_HLW_20180305_20_20180305_0000003"},{"effect":50,"client":"1,2,3,4","couponNo":"JMQ_HLW_20180305_21","deliveryRuleDetail":"测试普通提现快速提现","type":3,"periodUnit":1,"startDate":"2018-03-05","canUse":1,"receiveId":"JMQ_HLW_20180305_21_20180305_0000002"},{"effect":50,"client":"1,2,3,4","couponNo":"JMQ_HLW_20180305_22","deliveryRuleDetail":"测试普通提现快速提现","type":3,"periodUnit":1,"startDate":"2018-03-05","canUse":1,"receiveId":"JMQ_HLW_20180305_22_20180305_0000001"}]
         * count : 3
         */

        private int count;
        private List<DataListBean> dataList;

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

        public static class DataListBean implements Serializable{
            /**
             * effect : 50.0
             * client : 1,2,3,4
             * couponNo : JMQ_HLW_20180305_20
             * deliveryRuleDetail : 测试普通提现快速提现
             * type : 3
             * periodUnit : 1
             * startDate : 2018-03-05
             * canUse : 1
             * receiveId : JMQ_HLW_20180305_20_20180305_0000003
             */

            private String effect;
            private String client;
            private String couponNo;
            private String deliveryRuleDetail;
            private int type;
            private int periodUnit;
            private String startDate;
            private int canUse;
            private String receiveId;

            public String getEffect() {
                return effect;
            }

            public void setEffect(String effect) {
                this.effect = effect;
            }

            public String getClient() {
                return client;
            }

            public void setClient(String client) {
                this.client = client;
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

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
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
        }
    }
}
