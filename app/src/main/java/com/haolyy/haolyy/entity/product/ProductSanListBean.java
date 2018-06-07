package com.haolyy.haolyy.entity.product;

import java.util.List;

/**
 * Created by liliang on 2017/9/13.
 * 散标
 */

public class ProductSanListBean {

    /**
     * code : success
     * msg : 成功
     * model : {"list":[{"thirdUserId":null,"appendRate":0,"borrowActiveType":1,"remark":null,"updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"购买测试散标2","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":0.12,"thirdPlatformId":null,"contractAmount":1000000,"periodLength":2,"id":2,"borrowType":1,"borrowNo":"33333","secondCategoryId":2,"productNo":"DQYB","status":4},{"thirdUserId":null,"appendRate":0,"borrowActiveType":2,"remark":"test","updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"测试散标1","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":7,"thirdPlatformId":null,"contractAmount":100000,"periodLength":1,"id":3,"borrowType":1,"borrowNo":"333334","secondCategoryId":2,"productNo":"DQYC","status":4},{"thirdUserId":null,"appendRate":0,"borrowActiveType":0,"remark":"test","updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"测试散标2","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":7,"thirdPlatformId":null,"contractAmount":100000,"periodLength":1,"id":4,"borrowType":1,"borrowNo":"00002","secondCategoryId":2,"productNo":"DQYA","status":4},{"thirdUserId":null,"appendRate":0,"borrowActiveType":0,"remark":"test","updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"测试散标3","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":7,"thirdPlatformId":null,"contractAmount":100000,"periodLength":1,"id":5,"borrowType":1,"borrowNo":"00003","secondCategoryId":2,"productNo":"DQYA","status":4},{"thirdUserId":null,"appendRate":0,"borrowActiveType":0,"remark":"test","updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"测试散标4","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":7,"thirdPlatformId":null,"contractAmount":100000,"periodLength":1,"id":6,"borrowType":1,"borrowNo":"00004","secondCategoryId":2,"productNo":"DQYA","status":4},{"thirdUserId":null,"appendRate":0,"borrowActiveType":0,"remark":"test","updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"测试散标5","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":7,"thirdPlatformId":null,"contractAmount":100000,"periodLength":1,"id":7,"borrowType":1,"borrowNo":"00005","secondCategoryId":2,"productNo":"DQYA","status":4},{"thirdUserId":null,"appendRate":0,"borrowActiveType":0,"remark":"test","updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"测试散标6","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":7,"thirdPlatformId":null,"contractAmount":100000,"periodLength":1,"id":8,"borrowType":1,"borrowNo":"00006","secondCategoryId":2,"productNo":"DQYA","status":4},{"thirdUserId":null,"appendRate":0,"borrowActiveType":0,"remark":"test","updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"测试散标7","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":7,"thirdPlatformId":null,"contractAmount":100000,"periodLength":1,"id":9,"borrowType":1,"borrowNo":"00007","secondCategoryId":2,"productNo":"DQYA","status":4},{"thirdUserId":null,"appendRate":0,"borrowActiveType":0,"remark":"test","updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"测试散标8","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":7,"thirdPlatformId":null,"contractAmount":100000,"periodLength":1,"id":10,"borrowType":1,"borrowNo":"00008","secondCategoryId":2,"productNo":"DQYA","status":4},{"thirdUserId":null,"appendRate":0,"borrowActiveType":0,"remark":"test","updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"测试散标9","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":7,"thirdPlatformId":null,"contractAmount":100000,"periodLength":1,"id":11,"borrowType":1,"borrowNo":"00009","secondCategoryId":2,"productNo":"DQYA","status":4}],"allCount":0}
     */

    public String code;
    public String msg;
    public ModelBean model;


    public static class ModelBean {
        /**
         * list : [{"thirdUserId":null,"appendRate":0,"borrowActiveType":1,"remark":null,"updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"购买测试散标2","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":0.12,"thirdPlatformId":null,"contractAmount":1000000,"periodLength":2,"id":2,"borrowType":1,"borrowNo":"33333","secondCategoryId":2,"productNo":"DQYB","status":4},{"thirdUserId":null,"appendRate":0,"borrowActiveType":2,"remark":"test","updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"测试散标1","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":7,"thirdPlatformId":null,"contractAmount":100000,"periodLength":1,"id":3,"borrowType":1,"borrowNo":"333334","secondCategoryId":2,"productNo":"DQYC","status":4},{"thirdUserId":null,"appendRate":0,"borrowActiveType":0,"remark":"test","updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"测试散标2","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":7,"thirdPlatformId":null,"contractAmount":100000,"periodLength":1,"id":4,"borrowType":1,"borrowNo":"00002","secondCategoryId":2,"productNo":"DQYA","status":4},{"thirdUserId":null,"appendRate":0,"borrowActiveType":0,"remark":"test","updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"测试散标3","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":7,"thirdPlatformId":null,"contractAmount":100000,"periodLength":1,"id":5,"borrowType":1,"borrowNo":"00003","secondCategoryId":2,"productNo":"DQYA","status":4},{"thirdUserId":null,"appendRate":0,"borrowActiveType":0,"remark":"test","updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"测试散标4","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":7,"thirdPlatformId":null,"contractAmount":100000,"periodLength":1,"id":6,"borrowType":1,"borrowNo":"00004","secondCategoryId":2,"productNo":"DQYA","status":4},{"thirdUserId":null,"appendRate":0,"borrowActiveType":0,"remark":"test","updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"测试散标5","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":7,"thirdPlatformId":null,"contractAmount":100000,"periodLength":1,"id":7,"borrowType":1,"borrowNo":"00005","secondCategoryId":2,"productNo":"DQYA","status":4},{"thirdUserId":null,"appendRate":0,"borrowActiveType":0,"remark":"test","updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"测试散标6","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":7,"thirdPlatformId":null,"contractAmount":100000,"periodLength":1,"id":8,"borrowType":1,"borrowNo":"00006","secondCategoryId":2,"productNo":"DQYA","status":4},{"thirdUserId":null,"appendRate":0,"borrowActiveType":0,"remark":"test","updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"测试散标7","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":7,"thirdPlatformId":null,"contractAmount":100000,"periodLength":1,"id":9,"borrowType":1,"borrowNo":"00007","secondCategoryId":2,"productNo":"DQYA","status":4},{"thirdUserId":null,"appendRate":0,"borrowActiveType":0,"remark":"test","updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"测试散标8","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":7,"thirdPlatformId":null,"contractAmount":100000,"periodLength":1,"id":10,"borrowType":1,"borrowNo":"00008","secondCategoryId":2,"productNo":"DQYA","status":4},{"thirdUserId":null,"appendRate":0,"borrowActiveType":0,"remark":"test","updateTime":null,"periodUnit":"月","firstCategoryId":1,"borrowName":"测试散标9","accountId":null,"fromBorrowContractNo":null,"createTime":1505114855000,"annualizedRate":7,"thirdPlatformId":null,"contractAmount":100000,"periodLength":1,"id":11,"borrowType":1,"borrowNo":"00009","secondCategoryId":2,"productNo":"DQYA","status":4}]
         * allCount : 0
         */

        public int allCount;
        public List<ListBean> list;


        public static class ListBean {
            /**
             * thirdUserId : null
             * appendRate : 0
             * borrowActiveType : 1
             * remark : null
             * updateTime : null
             * periodUnit : 月
             * firstCategoryId : 1
             * borrowName : 购买测试散标2
             * accountId : null
             * fromBorrowContractNo : null
             * createTime : 1505114855000
             * annualizedRate : 0.12
             * thirdPlatformId : null
             * contractAmount : 1000000
             * periodLength : 2
             * id : 2
             * borrowType : 1
             * borrowNo : 33333
             * secondCategoryId : 2
             * productNo : DQYB
             * status : 4
             */

            public String thirdUserId;
            public double appendRate;
            public double annualizedRate;
            public String borrowActiveType;
            public String remark;
            public String updateTime;
            public int periodUnit;
            public String periodLength;
            public String firstCategoryId;
            public String borrowName;
            public String accountId;
            public String fromBorrowContractNo;
            public String createTime;
            public String thirdPlatformId;
            public double contractAmount;
            public double amountWait;
            public int id;
            public String borrowType;
            public String borrowNo;
            public String secondCategoryId;
            public String productNo;
            public int status;
            public double investminamount;

        }
    }
}
