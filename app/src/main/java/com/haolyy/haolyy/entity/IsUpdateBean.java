package com.haolyy.haolyy.entity;

import com.haolyy.haolyy.base.BaseBean;

/**
 * Created by liliang on 2018/1/24.
 */

public class IsUpdateBean extends BaseBean {

    /**
     * code : success
     * msg : 成功
     * model : {"state":1,"version":"V2.0.1","updateDesc":null,"isMust":0}
     */
    public ModelBean model;
    public static class ModelBean {
        /**
         * state : 1
         * version : V2.0.1
         * updateDesc : null
         * isMust : 0
         */

        public String state;//1 不一致(老版本) 0 一致(最新版本)
        public String version;//后台最新版本 例如 V2.0.1
        public String updateDesc;//跟新描述
        public String isMust;
        public String downloadUrl;
        public String verStatus;//0 : 无需更新  1：强制更新  2:重大bug不能使用

    }
}
