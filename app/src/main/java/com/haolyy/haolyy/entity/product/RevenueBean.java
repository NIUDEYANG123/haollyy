package com.haolyy.haolyy.entity.product;

/**
 * Created by shanghai on 2018/3/3.
 */

public class RevenueBean {

    /**
     * code : success
     * msg : 成功
     * model : {"fixInterest":59.83,"appendInterest":29.91}
     */

    private String code;
    private String msg;
    private ModelBean model;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ModelBean getModel() {
        return model;
    }

    public void setModel(ModelBean model) {
        this.model = model;
    }

    public static class ModelBean {
        /**
         * fixInterest : 59.83
         * appendInterest : 29.91
         */

        private double fixInterest;
        private double appendInterest;

        public double getFixInterest() {
            return fixInterest;
        }

        public void setFixInterest(double fixInterest) {
            this.fixInterest = fixInterest;
        }

        public double getAppendInterest() {
            return appendInterest;
        }

        public void setAppendInterest(double appendInterest) {
            this.appendInterest = appendInterest;
        }
    }
}
