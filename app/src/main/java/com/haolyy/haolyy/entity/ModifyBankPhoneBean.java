package com.haolyy.haolyy.entity;

/**
 * @author wyman
 * @date 2018/5/21
 * description :
 */

public class ModifyBankPhoneBean {

    /**
     * code : success
     * msg : 成功
     * model : {"InMap":{"UsrCustId":"6000060010844723","MerCustId":"6000060007051718","MerPriv":"","BgRetUrl":"http://101.95.160.70:8020/local/OrderChangeMobile/changeMobileCallBack.html","SmsSeq":"AAAAAAAA","OrdId":"20180521114347349164","ReqExt":"","UsrMp":"17621573868","Version":"10","CmdId":"ChangeMp","SmsCode":"54s","RetUrl":"https://www.haolyy.com","PageType":"","ChkValue":"4CF1F54A756A9D4BD2118C43D3AAE440CE45CBF7605CF535FD2F85B9C406A9607A8A8305E94DA6774B51878B00DC87547D2FA870447D920AC18C3825354E60F47796053454D829C5AF57E5F91FEDD6C3EF54DBD0A0C0C8D1A4A254177A3FA6D65CF1468F1C954C5669ADBDBC65A94BD09334C96BB928EA0449606D385E3EB6A8"},"RetCode":"SUCCESS","RetMessage":"用户正在修改银行预留手机号","ServiceUrl":"http://mertest.chinapnr.com/muser/publicRequests"}
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
         * InMap : {"UsrCustId":"6000060010844723","MerCustId":"6000060007051718","MerPriv":"","BgRetUrl":"http://101.95.160.70:8020/local/OrderChangeMobile/changeMobileCallBack.html","SmsSeq":"AAAAAAAA","OrdId":"20180521114347349164","ReqExt":"","UsrMp":"17621573868","Version":"10","CmdId":"ChangeMp","SmsCode":"54s","RetUrl":"https://www.haolyy.com","PageType":"","ChkValue":"4CF1F54A756A9D4BD2118C43D3AAE440CE45CBF7605CF535FD2F85B9C406A9607A8A8305E94DA6774B51878B00DC87547D2FA870447D920AC18C3825354E60F47796053454D829C5AF57E5F91FEDD6C3EF54DBD0A0C0C8D1A4A254177A3FA6D65CF1468F1C954C5669ADBDBC65A94BD09334C96BB928EA0449606D385E3EB6A8"}
         * RetCode : SUCCESS
         * RetMessage : 用户正在修改银行预留手机号
         * ServiceUrl : http://mertest.chinapnr.com/muser/publicRequests
         */

        private InMapBean InMap;
        private String RetCode;
        private String RetMessage;
        private String ServiceUrl;

        public InMapBean getInMap() {
            return InMap;
        }

        public void setInMap(InMapBean InMap) {
            this.InMap = InMap;
        }

        public String getRetCode() {
            return RetCode;
        }

        public void setRetCode(String RetCode) {
            this.RetCode = RetCode;
        }

        public String getRetMessage() {
            return RetMessage;
        }

        public void setRetMessage(String RetMessage) {
            this.RetMessage = RetMessage;
        }

        public String getServiceUrl() {
            return ServiceUrl;
        }

        public void setServiceUrl(String ServiceUrl) {
            this.ServiceUrl = ServiceUrl;
        }

        public static class InMapBean {
            /**
             * UsrCustId : 6000060010844723
             * MerCustId : 6000060007051718
             * MerPriv :
             * BgRetUrl : http://101.95.160.70:8020/local/OrderChangeMobile/changeMobileCallBack.html
             * SmsSeq : AAAAAAAA
             * OrdId : 20180521114347349164
             * ReqExt :
             * UsrMp : 17621573868
             * Version : 10
             * CmdId : ChangeMp
             * SmsCode : 54s
             * RetUrl : https://www.haolyy.com
             * PageType :
             * ChkValue : 4CF1F54A756A9D4BD2118C43D3AAE440CE45CBF7605CF535FD2F85B9C406A9607A8A8305E94DA6774B51878B00DC87547D2FA870447D920AC18C3825354E60F47796053454D829C5AF57E5F91FEDD6C3EF54DBD0A0C0C8D1A4A254177A3FA6D65CF1468F1C954C5669ADBDBC65A94BD09334C96BB928EA0449606D385E3EB6A8
             */

            private String UsrCustId;
            private String MerCustId;
            private String MerPriv;
            private String BgRetUrl;
            private String SmsSeq;
            private String OrdId;
            private String ReqExt;
            private String UsrMp;
            private String Version;
            private String CmdId;
            private String SmsCode;
            private String RetUrl;
            private String PageType;
            private String ChkValue;

            public String getUsrCustId() {
                return UsrCustId;
            }

            public void setUsrCustId(String UsrCustId) {
                this.UsrCustId = UsrCustId;
            }

            public String getMerCustId() {
                return MerCustId;
            }

            public void setMerCustId(String MerCustId) {
                this.MerCustId = MerCustId;
            }

            public String getMerPriv() {
                return MerPriv;
            }

            public void setMerPriv(String MerPriv) {
                this.MerPriv = MerPriv;
            }

            public String getBgRetUrl() {
                return BgRetUrl;
            }

            public void setBgRetUrl(String BgRetUrl) {
                this.BgRetUrl = BgRetUrl;
            }

            public String getSmsSeq() {
                return SmsSeq;
            }

            public void setSmsSeq(String SmsSeq) {
                this.SmsSeq = SmsSeq;
            }

            public String getOrdId() {
                return OrdId;
            }

            public void setOrdId(String OrdId) {
                this.OrdId = OrdId;
            }

            public String getReqExt() {
                return ReqExt;
            }

            public void setReqExt(String ReqExt) {
                this.ReqExt = ReqExt;
            }

            public String getUsrMp() {
                return UsrMp;
            }

            public void setUsrMp(String UsrMp) {
                this.UsrMp = UsrMp;
            }

            public String getVersion() {
                return Version;
            }

            public void setVersion(String Version) {
                this.Version = Version;
            }

            public String getCmdId() {
                return CmdId;
            }

            public void setCmdId(String CmdId) {
                this.CmdId = CmdId;
            }

            public String getSmsCode() {
                return SmsCode;
            }

            public void setSmsCode(String SmsCode) {
                this.SmsCode = SmsCode;
            }

            public String getRetUrl() {
                return RetUrl;
            }

            public void setRetUrl(String RetUrl) {
                this.RetUrl = RetUrl;
            }

            public String getPageType() {
                return PageType;
            }

            public void setPageType(String PageType) {
                this.PageType = PageType;
            }

            public String getChkValue() {
                return ChkValue;
            }

            public void setChkValue(String ChkValue) {
                this.ChkValue = ChkValue;
            }
        }
    }
}
