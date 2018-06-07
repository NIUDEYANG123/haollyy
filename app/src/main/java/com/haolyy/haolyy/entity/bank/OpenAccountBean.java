package com.haolyy.haolyy.entity.bank;


import com.haolyy.haolyy.base.BaseBean;

/**
 * Created by niudeyang on 2017/8/29.
 */

public class OpenAccountBean extends BaseBean {
    /**
     * code : success
     * msg : 成功
     * model : {"InMap":{"MerCustId":"6000060007051718","UsrName":"殳采南","AreaId":"1100","CharSet":"UTF-8","CardId":"6225801238812711583","UsrId":"fce3ef19","MerPriv":"20170921174944453","IdType":"00","UsrEmail":null,"BgRetUrl":"http://180.167.70.150:8010/new/account/openAccountCallBack.html","SmsSeq":"AAAAAAAA","UsrMp":"13609155245","Version":"10","CmdId":"UserRegister","ProvId":"0011","RetUrl":"www.chinazyjr.com","SmsCode":"666666","PageType":null,"BankId":"ABC","ChkValue":"99289608D15DAA05E59AF153E52877E9C5EB7C6D96D74F3F7761E45E6834BC7848238485F70EEAA895DD315037FDD2E70E03D94C14F9FA5A2C0044CE58BCDC3C47A179CECD1230F9D7365480DD551F86504845494189CE662FD970DA040E86F75305DBEE6CB8F153FDE07630295BD2C1E9AB96F0BF3EFA160D04EB92D8B58700","IdNo":"320982199109230491"},"RetCode":"SUCCESS","RetMessage":"用户开户请求汇付中","ServiceUrl":"http://mertest.chinapnr.com/muser/publicRequests"}
     */
    public ModelBean model;
    public static class ModelBean {
        /**
         * InMap : {"MerCustId":"6000060007051718","UsrName":"殳采南","AreaId":"1100","CharSet":"UTF-8","CardId":"6225801238812711583","UsrId":"fce3ef19","MerPriv":"20170921174944453","IdType":"00","UsrEmail":null,"BgRetUrl":"http://180.167.70.150:8010/new/account/openAccountCallBack.html","SmsSeq":"AAAAAAAA","UsrMp":"13609155245","Version":"10","CmdId":"UserRegister","ProvId":"0011","RetUrl":"www.chinazyjr.com","SmsCode":"666666","PageType":null,"BankId":"ABC","ChkValue":"99289608D15DAA05E59AF153E52877E9C5EB7C6D96D74F3F7761E45E6834BC7848238485F70EEAA895DD315037FDD2E70E03D94C14F9FA5A2C0044CE58BCDC3C47A179CECD1230F9D7365480DD551F86504845494189CE662FD970DA040E86F75305DBEE6CB8F153FDE07630295BD2C1E9AB96F0BF3EFA160D04EB92D8B58700","IdNo":"320982199109230491"}
         * RetCode : SUCCESS
         * RetMessage : 用户开户请求汇付中
         * ServiceUrl : http://mertest.chinapnr.com/muser/publicRequests
         */

        public InMapBean InMap;
        public String RetCode;
        public String RetMessage;
        public String ServiceUrl;

        public static class InMapBean {
            /**
             * MerCustId : 6000060007051718
             * UsrName : 殳采南
             * AreaId : 1100
             * CharSet : UTF-8
             * CardId : 6225801238812711583
             * UsrId : fce3ef19
             * MerPriv : 20170921174944453
             * IdType : 00
             * UsrEmail : null
             * BgRetUrl : http://180.167.70.150:8010/new/account/openAccountCallBack.html
             * SmsSeq : AAAAAAAA
             * UsrMp : 13609155245
             * Version : 10
             * CmdId : UserRegister
             * ProvId : 0011
             * RetUrl : www.chinazyjr.com
             * SmsCode : 666666
             * PageType : null
             * BankId : ABC
             * ChkValue : 99289608D15DAA05E59AF153E52877E9C5EB7C6D96D74F3F7761E45E6834BC7848238485F70EEAA895DD315037FDD2E70E03D94C14F9FA5A2C0044CE58BCDC3C47A179CECD1230F9D7365480DD551F86504845494189CE662FD970DA040E86F75305DBEE6CB8F153FDE07630295BD2C1E9AB96F0BF3EFA160D04EB92D8B58700
             * IdNo : 320982199109230491
             */

            public String MerCustId;
            public String UsrName;
            public String AreaId;
            public String CharSet;
            public String CardId;
            public String UsrId;
            public String MerPriv;
            public String IdType;
            public String UsrEmail;
            public String BgRetUrl;
            public String SmsSeq;
            public String UsrMp;
            public String Version;
            public String CmdId;
            public String ProvId;
            public String RetUrl;
            public String SmsCode;
            public String PageType;
            public String BankId;
            public String ChkValue;
            public String IdNo;

        }
    }
}
