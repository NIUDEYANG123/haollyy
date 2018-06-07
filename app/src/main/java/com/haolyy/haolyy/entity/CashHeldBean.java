package com.haolyy.haolyy.entity;

import com.haolyy.haolyy.base.BaseBean;

import java.util.List;

/**
 * Created by liliang on 2018/1/11.
 */

public class CashHeldBean extends BaseBean {


    /**
     * code : success
     * msg : 成功
     * model : {"totalValue":0,"totalAmount":1,"list":[{"matchDate":"2018-01-09","matchPrincipal":1500.61,"orderNo":"PSI20180109115257074768","currPrincipal":0,"currValue":0,"cashNo":"ZZT-20180109114948624798","borrowName":"JJT借款产品1","accountId":"4df39ccc","realName":"薛*","jzqApplyNo":"APL950575747238608896","debtNo":"ZQ20180109115515922187","id":4857,"borrowNo":"JJT-1000001428770042"}]}
     */
    public ModelBean model;

    public static class ModelBean {
        /**
         * totalValue : 0.0
         * totalAmount : 1
         * list : [{"matchDate":"2018-01-09","matchPrincipal":1500.61,"orderNo":"PSI20180109115257074768","currPrincipal":0,"currValue":0,"cashNo":"ZZT-20180109114948624798","borrowName":"JJT借款产品1","accountId":"4df39ccc","realName":"薛*","jzqApplyNo":"APL950575747238608896","debtNo":"ZQ20180109115515922187","id":4857,"borrowNo":"JJT-1000001428770042"}]
         */

        public String totalValue;
        public String totalAmount;
        public List<ListBean> list;
        public static class ListBean {
            /**
             * matchDate : 2018-01-09
             * matchPrincipal : 1500.61
             * orderNo : PSI20180109115257074768
             * currPrincipal : 0.0
             * currValue : 0.0
             * cashNo : ZZT-20180109114948624798
             * borrowName : JJT借款产品1
             * accountId : 4df39ccc
             * realName : 薛*
             * jzqApplyNo : APL950575747238608896
             * debtNo : ZQ20180109115515922187
             * id : 4857
             * borrowNo : JJT-1000001428770042
             */
            public String matchDate;
            public String matchPrincipal;
            public String orderNo;
            public String currPrincipal;
            public String currValue;
            public String cashNo;
            public String borrowName;
            public String accountId;
            public String realName;
            public String jzqApplyNo;
            public String debtNo;
            public String id;
            public String borrowNo;
        }
    }
}
