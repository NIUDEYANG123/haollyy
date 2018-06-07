package com.haolyy.haolyy.entity.bank;


import com.haolyy.haolyy.base.BaseBean;

/**
 * Created by liliang on 2017/9/12.
 */

public class SmsCodeBean extends BaseBean {

    /**
     * code : success
     * msg : 成功
     * model : {"RetCode":"SUCCESS","RetMessage":"获取短信验证码成功","OutMap":{"RespCode":"000","BusiType":"user_register","SmsSeq":"iKbNyDl2","UsrCustId":"","MerCustId":"6000060007051718","CmdId":"SendSmsCode","RespDesc":"成功","ChkValue":"B4B6E4037BD7758028AB8DB2E9F9DB8F826D79723D91D69A23FD0E2AAC156F4B48D01213A9A56C7FD86BC03981DDD153C003C096E991F64670DE4A1F9A2966020224AB75CA2408D47CD012D1218B5E6B1E2D3488EF7AAFD192868032935DAF646F27CA012F0A33FEF7875C1FBE48ACCC5E9B78D1880B2212A2226EC5F164C3DA","DepoBankSeq":"201709040405362062"}}
     */

    public ModelBean model;

    public static class ModelBean {
        /**
         * RetCode : SUCCESS
         * RetMessage : 获取短信验证码成功
         * OutMap : {"RespCode":"000","BusiType":"user_register","SmsSeq":"iKbNyDl2","UsrCustId":"","MerCustId":"6000060007051718","CmdId":"SendSmsCode","RespDesc":"成功","ChkValue":"B4B6E4037BD7758028AB8DB2E9F9DB8F826D79723D91D69A23FD0E2AAC156F4B48D01213A9A56C7FD86BC03981DDD153C003C096E991F64670DE4A1F9A2966020224AB75CA2408D47CD012D1218B5E6B1E2D3488EF7AAFD192868032935DAF646F27CA012F0A33FEF7875C1FBE48ACCC5E9B78D1880B2212A2226EC5F164C3DA","DepoBankSeq":"201709040405362062"}
         */

        public String RetCode;
        public String RetMessage;
        public OutMapBean OutMap;

        public static class OutMapBean {
            /**
             * RespCode : 000
             * BusiType : user_register
             * SmsSeq : iKbNyDl2
             * UsrCustId :
             * MerCustId : 6000060007051718
             * CmdId : SendSmsCode
             * RespDesc : 成功
             * ChkValue : B4B6E4037BD7758028AB8DB2E9F9DB8F826D79723D91D69A23FD0E2AAC156F4B48D01213A9A56C7FD86BC03981DDD153C003C096E991F64670DE4A1F9A2966020224AB75CA2408D47CD012D1218B5E6B1E2D3488EF7AAFD192868032935DAF646F27CA012F0A33FEF7875C1FBE48ACCC5E9B78D1880B2212A2226EC5F164C3DA
             * DepoBankSeq : 201709040405362062
             */

            public String RespCode;
            public String BusiType;
            public String SmsSeq;
            public String UsrCustId;
            public String MerCustId;
            public String CmdId;
            public String RespDesc;
            public String ChkValue;
            public String DepoBankSeq;


        }
    }
}
