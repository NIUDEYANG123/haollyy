package com.haolyy.haolyy.entity.bank;

import com.haolyy.haolyy.base.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liliang on 2017/9/29.
 */

public class BankListBean extends BaseBean implements Serializable {


    private List<ModelBean> model;

    public List<ModelBean> getModel() {
        return model;
    }

    public void setModel(List<ModelBean> model) {
        this.model = model;
    }

    public static class ModelBean implements Serializable{
        /**
         * id : 1
         * bankNo : 100
         * bankCode : ICBC
         * imgUrl :
         * bankName : 工商银行
         * singleMinAmount : 0
         * singleMaxAmount : 600000
         * samedayMaxAmount : 800000
         * status : 1
         * quickPaymentFlag : 1
         * createTime : 2017-06-28 15:30:55
         * updateTime : 2018-05-21 16:21:05
         */

        private int id;
        private String bankNo;
        private String bankCode;
        private String imgUrl;
        private String bankName;
        private String singleMinAmount;
        private String singleMaxAmount;
        private String samedayMaxAmount;
        private String status;
        private String quickPaymentFlag;
        private String createTime;
        private String updateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBankNo() {
            return bankNo;
        }

        public void setBankNo(String bankNo) {
            this.bankNo = bankNo;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getSingleMinAmount() {
            return singleMinAmount;
        }

        public void setSingleMinAmount(String singleMinAmount) {
            this.singleMinAmount = singleMinAmount;
        }

        public String getSingleMaxAmount() {
            return singleMaxAmount;
        }

        public void setSingleMaxAmount(String singleMaxAmount) {
            this.singleMaxAmount = singleMaxAmount;
        }

        public String getSamedayMaxAmount() {
            return samedayMaxAmount;
        }

        public void setSamedayMaxAmount(String samedayMaxAmount) {
            this.samedayMaxAmount = samedayMaxAmount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getQuickPaymentFlag() {
            return quickPaymentFlag;
        }

        public void setQuickPaymentFlag(String quickPaymentFlag) {
            this.quickPaymentFlag = quickPaymentFlag;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
