package com.haolyy.haolyy.entity.bank;


import com.haolyy.haolyy.base.BaseBean;

/**
 * Created by niudeyang on 2017/8/30.
 * 提现手续费
 */

public class Fee extends BaseBean {


    /**
     * code : success
     * msg : 计算取现手续费成功
     * model : {"fee":"3.00"}
     */
    public ModelBean model;
    public static class ModelBean {
        /**
         * fee : 3.00
         */
        public String fee;
    }
}
