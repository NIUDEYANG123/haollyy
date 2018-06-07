package com.haolyy.haolyy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author wyman
 * @date 2018/3/5
 * description :
 */

public class ReturnedBean implements Serializable {

    /**
     * code : success
     * msg : 成功
     * model : {"list":[{"borrowName":"盈计划A20180305第二期","realRtnDate":"","receiveAmount":201.14,"borrowNo":"HLW-1000002110442857"},{"borrowName":"不是新手标","realRtnDate":"","receiveAmount":2004.98,"borrowNo":"HLW-1000000699856564"},{"borrowName":"20180301第一期","realRtnDate":"","receiveAmount":402.5,"borrowNo":"HLW-1000001967537099"},{"borrowName":"赢计划D20180305第二期","realRtnDate":"","receiveAmount":1230,"borrowNo":"HLW-1000000605125792"},{"borrowName":"盈计划D20180305第一期","realRtnDate":"","receiveAmount":2012.86,"borrowNo":"HLW-1000001636462927"},{"borrowName":"盈计划A20180305第一期","realRtnDate":"","receiveAmount":6035,"borrowNo":"HLW-1000000410638952"},{"borrowName":"20180305第三期","realRtnDate":"","receiveAmount":2360,"borrowNo":"HLW-1000000916226466"},{"borrowName":"盈计划C20180302第一期","realRtnDate":"","receiveAmount":201.9,"borrowNo":"HLW-1000000403988901"},{"borrowName":"赢计划B_20180205第一期6个月","realRtnDate":"","receiveAmount":1640.48,"borrowNo":"HLW-1000000742431421"}],"allCount":9}
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
         * list : [{"borrowName":"盈计划A20180305第二期","realRtnDate":"","receiveAmount":201.14,"borrowNo":"HLW-1000002110442857"},{"borrowName":"不是新手标","realRtnDate":"","receiveAmount":2004.98,"borrowNo":"HLW-1000000699856564"},{"borrowName":"20180301第一期","realRtnDate":"","receiveAmount":402.5,"borrowNo":"HLW-1000001967537099"},{"borrowName":"赢计划D20180305第二期","realRtnDate":"","receiveAmount":1230,"borrowNo":"HLW-1000000605125792"},{"borrowName":"盈计划D20180305第一期","realRtnDate":"","receiveAmount":2012.86,"borrowNo":"HLW-1000001636462927"},{"borrowName":"盈计划A20180305第一期","realRtnDate":"","receiveAmount":6035,"borrowNo":"HLW-1000000410638952"},{"borrowName":"20180305第三期","realRtnDate":"","receiveAmount":2360,"borrowNo":"HLW-1000000916226466"},{"borrowName":"盈计划C20180302第一期","realRtnDate":"","receiveAmount":201.9,"borrowNo":"HLW-1000000403988901"},{"borrowName":"赢计划B_20180205第一期6个月","realRtnDate":"","receiveAmount":1640.48,"borrowNo":"HLW-1000000742431421"}]
         * allCount : 9
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

        public static class ListBean implements Serializable{
            /**
             * borrowName : 盈计划A20180305第二期
             * realRtnDate :
             * receiveAmount : 201.14
             * borrowNo : HLW-1000002110442857
             */

            private String borrowName;
            private String realRtnDate;
            private double receiveAmount;
            private String borrowNo;

            public String getBorrowName() {
                return borrowName;
            }

            public void setBorrowName(String borrowName) {
                this.borrowName = borrowName;
            }

            public String getRealRtnDate() {
                return realRtnDate;
            }

            public void setRealRtnDate(String realRtnDate) {
                this.realRtnDate = realRtnDate;
            }

            public double getReceiveAmount() {
                return receiveAmount;
            }

            public void setReceiveAmount(double receiveAmount) {
                this.receiveAmount = receiveAmount;
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
