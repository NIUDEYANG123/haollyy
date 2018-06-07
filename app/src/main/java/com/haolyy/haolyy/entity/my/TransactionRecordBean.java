package com.haolyy.haolyy.entity.my;

import java.util.List;

/**
 * Created by liliang on 2017/9/21.
 */

public class TransactionRecordBean {

    /**
     * code : success
     * msg : 成功
     * model : {"total":27,"dataList":[{"optype":2,"orderNo":"20171227201534826414","availableAmountAfter":"1210.55","createTime":"2017-12-27","operationAmount":"1000.00","typeName":"加入计划","type":3},{"optype":2,"orderNo":"UMC20171227171551672","availableAmountAfter":"2210.55","createTime":"2017-12-27","operationAmount":"100.00","typeName":"提现","type":2},{"optype":2,"orderNo":"UMC20171226160324395","availableAmountAfter":"2310.55","createTime":"2017-12-26","operationAmount":"100.00","typeName":"提现","type":2},{"optype":2,"orderNo":"2017112116040135136","availableAmountAfter":"2410.55","createTime":"2017-11-21","operationAmount":"500.00","typeName":"散标出借","type":4},{"optype":2,"orderNo":"PSI20171121150149254953","availableAmountAfter":"2910.55","createTime":"2017-11-21","operationAmount":"100.00","typeName":"散标出借","type":4},{"optype":1,"orderNo":"JJT-1000000063634368","availableAmountAfter":"3010.55","createTime":"2017-11-20","operationAmount":"9.59","typeName":"收益","type":8},{"optype":1,"orderNo":"JJT-1000000063634368","availableAmountAfter":"3000.96","createTime":"2017-11-20","operationAmount":"0.96","typeName":"收益","type":8},{"optype":1,"orderNo":"UMR20171117101412395","availableAmountAfter":"3000.00","createTime":"2017-11-17","operationAmount":"100.00","typeName":"充值","type":1},{"optype":1,"orderNo":"UMR20171116180000390","availableAmountAfter":"2900.00","createTime":"2017-11-16","operationAmount":"1000.00","typeName":"充值","type":1},{"optype":1,"orderNo":"PSI20171116172542417","availableAmountAfter":"1900.00","createTime":"2017-11-16","operationAmount":"100.00","typeName":"其他","type":12}]}
     */

    public String code;
    public String msg;
    public ModelBean model;

    public static class ModelBean {
        /**
         * total : 27
         * dataList : [{"optype":2,"orderNo":"20171227201534826414","availableAmountAfter":"1210.55","createTime":"2017-12-27","operationAmount":"1000.00","typeName":"加入计划","type":3},{"optype":2,"orderNo":"UMC20171227171551672","availableAmountAfter":"2210.55","createTime":"2017-12-27","operationAmount":"100.00","typeName":"提现","type":2},{"optype":2,"orderNo":"UMC20171226160324395","availableAmountAfter":"2310.55","createTime":"2017-12-26","operationAmount":"100.00","typeName":"提现","type":2},{"optype":2,"orderNo":"2017112116040135136","availableAmountAfter":"2410.55","createTime":"2017-11-21","operationAmount":"500.00","typeName":"散标出借","type":4},{"optype":2,"orderNo":"PSI20171121150149254953","availableAmountAfter":"2910.55","createTime":"2017-11-21","operationAmount":"100.00","typeName":"散标出借","type":4},{"optype":1,"orderNo":"JJT-1000000063634368","availableAmountAfter":"3010.55","createTime":"2017-11-20","operationAmount":"9.59","typeName":"收益","type":8},{"optype":1,"orderNo":"JJT-1000000063634368","availableAmountAfter":"3000.96","createTime":"2017-11-20","operationAmount":"0.96","typeName":"收益","type":8},{"optype":1,"orderNo":"UMR20171117101412395","availableAmountAfter":"3000.00","createTime":"2017-11-17","operationAmount":"100.00","typeName":"充值","type":1},{"optype":1,"orderNo":"UMR20171116180000390","availableAmountAfter":"2900.00","createTime":"2017-11-16","operationAmount":"1000.00","typeName":"充值","type":1},{"optype":1,"orderNo":"PSI20171116172542417","availableAmountAfter":"1900.00","createTime":"2017-11-16","operationAmount":"100.00","typeName":"其他","type":12}]
         */

        public int total;
        public List<DataListBean> dataList;

        public static class DataListBean {
            /**
             * optype : 2
             * orderNo : 20171227201534826414
             * availableAmountAfter : 1210.55
             * createTime : 2017-12-27
             * operationAmount : 1000.00
             * typeName : 加入计划
             * type : 3
             */

            public int optype;
            public String orderNo;
            public String availableAmountAfter;
            public String createTime;
            public String operationAmount;
            public String typeName;
            public String id;
            public int type;
        }
    }
}
