package com.haolyy.haolyy.entity.userinfo;

/**
 * Created by liliang on 2017/9/12.
 */

public class GetUserStatusBean {

    /**
     * code : success
     * msg : 成功
     * model : {"userStatus":{"userStatus":1,"setAutoBuyBidFlag":0,"setBuyBidFlag":0,"setPasswordFlag":0,"riskRatingFlag":0,"updateTime":null,"userCode":"f2bbfb28","bindBankCardFlag":0,"openAccountStatus":1,"setBuyNewBidFlag":0,"createTime":"2017-09-11 18:49:57","openAccountFlag":0,"id":28,"bindMobileFlag":0,"setLoginPasswordFlag":1,"setHandPasswordFlag":0},"userCode":"f2bbfb28"}
     */

    public String code;
    public String msg;
    public ModelBean model;

    public static class ModelBean {
        /**
         * userStatus : {"userStatus":1,"setAutoBuyBidFlag":0,"setBuyBidFlag":0,"setPasswordFlag":0,"riskRatingFlag":0,"updateTime":null,"userCode":"f2bbfb28","bindBankCardFlag":0,"openAccountStatus":1,"setBuyNewBidFlag":0,"createTime":"2017-09-11 18:49:57","openAccountFlag":0,"id":28,"bindMobileFlag":0,"setLoginPasswordFlag":1,"setHandPasswordFlag":0}
         * userCode : f2bbfb28
         */

        public UserStatusBean userStatus;
        public String userCode;  //平台用户编号

        public static class UserStatusBean {
            /**
             * userStatus : 1
             * setAutoBuyBidFlag : 0
             * setBuyBidFlag : 0
             * setPasswordFlag : 0
             * riskRatingFlag : 0
             * updateTime : null
             * userCode : f2bbfb28
             * bindBankCardFlag : 0
             * openAccountStatus : 1
             * setBuyNewBidFlag : 0
             * createTime : 2017-09-11 18:49:57
             * openAccountFlag : 0
             * id : 28
             * bindMobileFlag : 0
             * setLoginPasswordFlag : 1
             * setHandPasswordFlag : 0
             */

            public String userStatus;
            public String setAutoBuyBidFlag;     //本息自动投标授权标志 1:是 0:否
            public String setBuyBidFlag;         //设置是否买过标 1:是 0:否
            public String setPasswordFlag;       //设置交易密码标志 1:是 0:否
            public int riskRatingFlag;        //风险评估标志 1:是 0:否riskRatingFlag":0
            public String updateTime;            //更新时间
            public String userCode;
            public String bindBankCardFlag;      //绑定银行卡标志 1:是 0:否
            public String openAccountStatus;     //开户状态 1:未开户 3:已开户 4:待激活（汇付开户，已绑卡，未设置交易密码）
            public String setBuyNewBidFlag;      //设置是否买过新手标
            public String createTime;            //把钱打入P2P平台的认购时间
            public String openAccountFlag;       //开户标志 1:是 0:否
            public int id;                       //数据库标识
            public String bindMobileFlag;        //手机绑定标志 1:是 0:否
            public String setLoginPasswordFlag;  //设置登录密码标志 1:是 0:否
            public String setHandPasswordFlag;
            public String remark;//开户提示信息，失败时显示错误信息


        }
    }
}
