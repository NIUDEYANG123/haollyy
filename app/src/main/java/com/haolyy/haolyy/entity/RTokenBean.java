package com.haolyy.haolyy.entity;

import com.haolyy.haolyy.base.BaseBean;

/**
 * Created by liliang on 2018/1/23.
 */

public class RTokenBean extends BaseBean {
    /**
     * code : success
     * msg : 成功
     * model : {"token":"6630aec4cb144301a8184e0b215a4f83"}
     */
    public ModelBean model;

    public static class ModelBean {
        /**
         * token : 6630aec4cb144301a8184e0b215a4f83
         */
        public String accessToken;


    }
}
