package com.haolyy.haolyy.entity.bank;


import com.haolyy.haolyy.base.BaseBean;

import java.util.List;

/**
 * Created by niudeyang on 2017/9/25.
 */

public class QueryCardInfoBean extends BaseBean {

    /**
     * code : success
     * msg : 成功
     * model : {"RespCode":"000","UsrCustId":"6000060007679337","Version":"10","MerCustId":"6000060007051718","CmdId":"QueryCardInfo","PlainText":"QueryCardInfo000600006000705171860000600076793376225805432110240","UsrCardInfolist":[{"IsDefault":"Y","UsrCustId":"6000060007679337","MerCustId":"6000060007051718","UsrName":"喻政旭","RealFlag":"R","UpdDateTime":"20170914142038","CertId":"350802198505093330","TxnDate":"","AreaId":"1100","CardId":"6225805432110240","ExpressFlag":"Y","CanclDate":"","TxnTime":"","CanclTime":"","ProvId":"0011","BankId":"CMB"}],"CardId":"6225805432110240","RespDesc":"成功","ChkValue":"8C89A8FDBBD45A826BB11C264D5BBF75D058154C8C6E6C558256C302786CA95A1C51F0BE88789662641BFBD7C7511ED646CD51B8536F169789579377C3498D4AA9A77F77E987209AA73F138818DC5C2957DDF100B0BF5951C1CDD1222796F25A24AB8AD8E499118F29C7CF381480ADA008D945452DA55BFD514E7D3BD0B2DCC3"}
     */
    public ModelBean model;

    public static class ModelBean {
        /**
         * RespCode : 000
         * UsrCustId : 6000060007679337
         * Version : 10
         * MerCustId : 6000060007051718
         * CmdId : QueryCardInfo
         * PlainText : QueryCardInfo000600006000705171860000600076793376225805432110240
         * UsrCardInfolist : [{"IsDefault":"Y","UsrCustId":"6000060007679337","MerCustId":"6000060007051718","UsrName":"喻政旭","RealFlag":"R","UpdDateTime":"20170914142038","CertId":"350802198505093330","TxnDate":"","AreaId":"1100","CardId":"6225805432110240","ExpressFlag":"Y","CanclDate":"","TxnTime":"","CanclTime":"","ProvId":"0011","BankId":"CMB"}]
         * CardId : 6225805432110240
         * RespDesc : 成功
         * ChkValue : 8C89A8FDBBD45A826BB11C264D5BBF75D058154C8C6E6C558256C302786CA95A1C51F0BE88789662641BFBD7C7511ED646CD51B8536F169789579377C3498D4AA9A77F77E987209AA73F138818DC5C2957DDF100B0BF5951C1CDD1222796F25A24AB8AD8E499118F29C7CF381480ADA008D945452DA55BFD514E7D3BD0B2DCC3
         */

        public String RespCode;
        public String UsrCustId;
        public String Version;
        public String MerCustId;
        public String CmdId;
        public String PlainText;
        public String CardId;
        public String RespDesc;
        public String ChkValue;
        public List<UsrCardInfolistBean> UsrCardInfolist;

        public static class UsrCardInfolistBean {
            /**
             * IsDefault : Y
             * UsrCustId : 6000060007679337
             * MerCustId : 6000060007051718
             * UsrName : 喻政旭
             * RealFlag : R
             * UpdDateTime : 20170914142038
             * CertId : 350802198505093330
             * TxnDate :
             * AreaId : 1100
             * CardId : 6225805432110240
             * ExpressFlag : Y
             * CanclDate :
             * TxnTime :
             * CanclTime :
             * ProvId : 0011
             * BankId : CMB
             */

            public String IsDefault;
            public String UsrCustId;
            public String MerCustId;
            public String UsrName;
            public String RealFlag;
            public String UpdDateTime;
            public String CertId;
            public String TxnDate;
            public String AreaId;
            public String CardId;
            public String ExpressFlag;
            public String CanclDate;
            public String TxnTime;
            public String CanclTime;
            public String ProvId;
            public String BankId;
        }
    }
}
