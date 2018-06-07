package com.haolyy.haolyy.entity.product;

/**
 * Created by liliang on 2017/9/19.
 */

public class QueryTransStatBean {

    /**
     * code : success
     * msg : 成功
     * model : {"RetCode":"SUCCESS","RetMessage":"交易状态信息查询成功","OutMap":{"RespCode":"000","QueryTransType":"TENDER","OrdId":"20170919125903874","Version":"null","MerCustId":"6000060007051718","CmdId":"QueryTransStat","PlainStr":"QueryTransStat00060000600070517182017091912590387420170919TENDERN","OrdDate":"20170919","TransStat":"N","RespDesc":"成功","ChkValue":"21820CB72BFA911725508AF9A5B4A28CE180E2118D1B4B0A252B1DADD0961D9AA45AFEF983E5A44829C34B74E1D988C26561712DB376DA398FA9AB958F9E74FE00D44139A6461F34BE3D2BA2C125DBA747531AF34D684A2C0A17FF2C6DFE53D0652B537E0943D3570AE7663A66C019E68DD3E7A347ECA5A5CC028BD6429B52D8"}}
     */

    public String code;
    public String msg;
    public ModelBean model;

    public static class ModelBean {
        /**
         * RetCode : SUCCESS
         * RetMessage : 交易状态信息查询成功
         * OutMap : {"RespCode":"000","QueryTransType":"TENDER","OrdId":"20170919125903874","Version":"null","MerCustId":"6000060007051718","CmdId":"QueryTransStat","PlainStr":"QueryTransStat00060000600070517182017091912590387420170919TENDERN","OrdDate":"20170919","TransStat":"N","RespDesc":"成功","ChkValue":"21820CB72BFA911725508AF9A5B4A28CE180E2118D1B4B0A252B1DADD0961D9AA45AFEF983E5A44829C34B74E1D988C26561712DB376DA398FA9AB958F9E74FE00D44139A6461F34BE3D2BA2C125DBA747531AF34D684A2C0A17FF2C6DFE53D0652B537E0943D3570AE7663A66C019E68DD3E7A347ECA5A5CC028BD6429B52D8"}
         */

        public String RetCode;
        public String RetMessage;
        public OutMapBean OutMap;

        public static class OutMapBean {
            /**
             * RespCode : 000
             * QueryTransType : TENDER
             * OrdId : 20170919125903874
             * Version : null
             * MerCustId : 6000060007051718
             * CmdId : QueryTransStat
             * PlainStr : QueryTransStat00060000600070517182017091912590387420170919TENDERN
             * OrdDate : 20170919
             * TransStat : N
             * RespDesc : 成功
             * ChkValue : 21820CB72BFA911725508AF9A5B4A28CE180E2118D1B4B0A252B1DADD0961D9AA45AFEF983E5A44829C34B74E1D988C26561712DB376DA398FA9AB958F9E74FE00D44139A6461F34BE3D2BA2C125DBA747531AF34D684A2C0A17FF2C6DFE53D0652B537E0943D3570AE7663A66C019E68DD3E7A347ECA5A5CC028BD6429B52D8
             */

            public String RespCode;
            public String QueryTransType;
            public String OrdId;
            public String Version;
            public String MerCustId;
            public String CmdId;
            public String PlainStr;
            public String OrdDate;
            public String TransStat;
            public String RespDesc;
            public String ChkValue;


        }
    }
}
