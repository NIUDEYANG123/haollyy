package com.haolyy.haolyy.entity.userinfo;

import java.util.List;

/**
 * Created by shanghai on 2018/4/27.
 */

public class CashHeldReord {


    /**
     * code : success
     * msg : 成功
     * model : {"totalCount":1,"listInfo":[{"borrowName":"散标D7","creditLevel":"A","orderNo":"PSI20180423144159204688","createTime":"2018-04-23 16:45:02","contractAmount":202445.84,"periodLength":"10周","borrowNo":"JJT-1000001855067510"}]}
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
         * totalCount : 1
         * listInfo : [{"borrowName":"散标D7","creditLevel":"A","orderNo":"PSI20180423144159204688","createTime":"2018-04-23 16:45:02","contractAmount":202445.84,"periodLength":"10周","borrowNo":"JJT-1000001855067510"}]
         */

        private int totalCount;
        private List<ListInfoBean> listInfo;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<ListInfoBean> getListInfo() {
            return listInfo;
        }

        public void setListInfo(List<ListInfoBean> listInfo) {
            this.listInfo = listInfo;
        }

        public static class ListInfoBean {
            /**
             * borrowName : 散标D7
             * creditLevel : A
             * orderNo : PSI20180423144159204688
             * createTime : 2018-04-23 16:45:02
             * contractAmount : 202445.84
             * periodLength : 10周
             * borrowNo : JJT-1000001855067510
             */

            private String borrowName;
            private String creditLevel;
            private String orderNo;
            private String createTime;
            private double contractAmount;
            private String periodLength;
            private String borrowNo;

            public String getBorrowName() {
                return borrowName;
            }

            public void setBorrowName(String borrowName) {
                this.borrowName = borrowName;
            }

            public String getCreditLevel() {
                return creditLevel;
            }

            public void setCreditLevel(String creditLevel) {
                this.creditLevel = creditLevel;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public double getContractAmount() {
                return contractAmount;
            }

            public void setContractAmount(double contractAmount) {
                this.contractAmount = contractAmount;
            }

            public String getPeriodLength() {
                return periodLength;
            }

            public void setPeriodLength(String periodLength) {
                this.periodLength = periodLength;
            }

            public String getBorrowNo() {
                return borrowNo;
            }

            public void setBorrowNo(String borrowNo) {
                this.borrowNo = borrowNo;
            }
        }
    }
}
