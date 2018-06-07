

package com.haolyy.haolyy.entity.login;


import com.haolyy.haolyy.base.BaseBean;

/**
 * Created by niudeyang on 2017/8/15.
 */

public class LoginBean extends BaseBean {

    /**
     * code : success
     * msg : 成功
     * model : {"mobile":"13200001111","userCode":"4efdd58f","platform":"ZZT","lastLoginIp":3232237521,"access_token":"te9f6ada450c74f2dbba3fd1390814547","openAccountStatus":1,"lastLoginTime":"2018-01-21 17:10:17","refresh_token":"r7c6b8fa8d197436cb94bacd6157df3d7","refresh_expires_in":86400,"juid":"zj3SNfHx5JhKEOEjiC/Qxw==","exists":false,"id":882,"access_expires_in":1800,"status":1}
     */
    public ModelBean model;
    public static class ModelBean {
        /**
         * mobile : 13200001111
         * userCode : 4efdd58f
         * platform : ZZT
         * lastLoginIp : 3232237521
         * access_token : te9f6ada450c74f2dbba3fd1390814547
         * openAccountStatus : 1
         * lastLoginTime : 2018-01-21 17:10:17
         * refresh_token : r7c6b8fa8d197436cb94bacd6157df3d7
         * refresh_expires_in : 86400
         * juid : zj3SNfHx5JhKEOEjiC/Qxw==
         * exists : false
         * id : 882
         * access_expires_in : 1800
         * status : 1
         */

        public String mobile;
        public String userCode;
        public String platform;
        public String lastLoginIp;
        public String openAccountStatus;
        public String lastLoginTime;
        public String juid;
        public boolean exists;
        public int id;
        public String status;
        public String accessToken;//用户登录标识
        public String refreshToken;//用于刷新token
        public String refreshExpiresIn;
        public String accessExpiresIn;

    }
}

