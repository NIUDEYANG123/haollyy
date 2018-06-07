package com.haolyy.haolyy.entity.my;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liliang on 2017/9/23.
 */

public class ProductManageBean implements Serializable {
    public String code;
    public String msg;
    public ModelBean model;

    public static class ModelBean implements Serializable {
        public int total;
        public List<DataListBean> dataList;

        public static class DataListBean implements Serializable {
            /**
             * borrowName : 借款产品A2
             * orderNo : PSI20171020135126922
             * profitPlan : 4
             * investDate : 2017-10-20
             * annualizedRate : 10.00
             * investAmount : 100.00
             * periodLength : 4
             * borrowNo : JJT-1000000994305581
             * profitActual : 10.00
             * repayTotal : 110.00
             * periodUnit : 3
             * status : 3
             * endDate : 2017-10-25
             */

            public String borrowName;
            public String orderNo;
            public String profitPlan;
            public String investDate;
            public String annualizedRate;
            public String investAmount;
            public String periodLength;
            public String borrowNo;
            public String profitActual;
            public String repayTotal;
            public String periodUnit;
            public String status;
            public String endDate;
        }
    }
}
