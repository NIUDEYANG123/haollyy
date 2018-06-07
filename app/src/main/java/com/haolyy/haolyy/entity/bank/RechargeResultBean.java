package com.haolyy.haolyy.entity.bank;


import com.haolyy.haolyy.base.BaseBean;

/**
 * Created by liliang on 2018/1/9.
 */

public class RechargeResultBean extends BaseBean {

    public ModelBean model;
    public static class ModelBean {
        public String orderStatus;
        public String orderRemark;
    }
}
