package com.haolyy.haolyy.entity.login;


import com.haolyy.haolyy.base.BaseBean;

/**
 * Created by niudeyang on 2017/8/14.
 */

public class RegsiterBean extends BaseBean {
    /**
     * code : param_not_right
     * msg : 用户名格式错误
     * model : {"registerIp":3232238584,"gesturePassword":null,"mobile":"18221740024","updateTime":null,"channal":null,"version":null,"userCode":"b41d9dea","platform":"HLW","lastLoginIp":null,"openAccountStatus":1,"lastLoginTime":null,"password":"dc483e80a7a0bd9ef71d8cf973673924","createTime":"2017-09-11 18:09:04","client":1,"id":47,"registerCode":null,"status":1}
     */
    public ModelBean model;

    public static class ModelBean {
        /**
         * registerIp : 3232238584
         * gesturePassword : null
         * mobile : 18221740024
         * updateTime : null
         * channal : null
         * version : null
         * userCode : b41d9dea
         * platform : HLW
         * lastLoginIp : null
         * openAccountStatus : 1
         * lastLoginTime : null
         * password : dc483e80a7a0bd9ef71d8cf973673924
         * createTime : 2017-09-11 18:09:04
         * client : 1
         * id : 47
         * registerCode : null
         * status : 1
         */

        public String registerIp;
        public String gesturePassword;
        public String mobile;
        public String updateTime;
        public String channal;
        public String version;
        public String userCode;
        public String platform;
        public String lastLoginIp;
        public String openAccountStatus;
        public String lastLoginTime;
        public String password;
        public String createTime;
        public String client;
        public String id;
        public String registerCode;
        public String status;


    }
}
