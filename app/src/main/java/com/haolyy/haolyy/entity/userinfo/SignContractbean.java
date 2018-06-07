package com.haolyy.haolyy.entity.userinfo;

/**
 * Created by niudeyang on 2017/11/1.
 */

public class SignContractbean {

    /**
     * code : success
     * msg : 成功
     * model : {"ApplyNo":"APL925649344936415232","Link":"http://sandbox.junziqian.com/signInfo/viewDetail?timestamp=1509526734221&signNo=SSN925649345519423488&sign=4a30de0517f53aecb07cb962031611d1ffad9235&backUrl=www"}
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
         * ApplyNo : APL925649344936415232
         * Link : http://sandbox.junziqian.com/signInfo/viewDetail?timestamp=1509526734221&signNo=SSN925649345519423488&sign=4a30de0517f53aecb07cb962031611d1ffad9235&backUrl=www
         */

        private String ApplyNo;
        private String Link;

        public String getApplyNo() {
            return ApplyNo;
        }

        public void setApplyNo(String ApplyNo) {
            this.ApplyNo = ApplyNo;
        }

        public String getLink() {
            return Link;
        }

        public void setLink(String Link) {
            this.Link = Link;
        }
    }
}
