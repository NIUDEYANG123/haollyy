package com.haolyy.haolyy.entity.bank;


import com.haolyy.haolyy.base.BaseBean;

/**
 * Created by niudeyang on 2017/9/13.
 */

public class RechargeBean extends BaseBean {

    /**
     * code : success
     * msg : 成功
     * model : {"OutMap":{"RespCode":"000","TrxId":"201709130000887734","UsrCustId":"6000060007674671","MerCustId":"6000060007051718","SecRespDesc":"","FeeCustId":"6000060007051718","DepoBankSeq":"0002839910","BgRetUrl":"http://180.167.70.150:8010/new/recharge/rechargeCallBack.html","OrdId":"20170913161051378","TransAmt":"1000.00","FeeAmt":"1.30","CmdId":"DirectRecharge","OrdDate":"20170913","FeeAcctId":"MDT000002","RespDesc":"成功","RetUrl":"","SecRespCode":"","BankId":"","ChkValue":"8C0E6EB66EB3322D1369E1172FD818B93E0599DC9C74F8AE562DAA427EA13C02E5BD7C4585A1E838807525A8D94EBF58E7AD286F8DD598F7B723101A079E87DFB5FB2B8CF386835147DE66528FD371AC0511BD7051892E615E7B2565080BE0DAD36E4E1EFD2BC1D6BAD20C11941225CBDC2EDF5D96BE05C73DEF1920D5F9B344","GateBusiId":"QP"}}
     */
    public ModelBean model;

    public static class ModelBean {
        /**
         * OutMap : {"RespCode":"000","TrxId":"201709130000887734","UsrCustId":"6000060007674671","MerCustId":"6000060007051718","SecRespDesc":"","FeeCustId":"6000060007051718","DepoBankSeq":"0002839910","BgRetUrl":"http://180.167.70.150:8010/new/recharge/rechargeCallBack.html","OrdId":"20170913161051378","TransAmt":"1000.00","FeeAmt":"1.30","CmdId":"DirectRecharge","OrdDate":"20170913","FeeAcctId":"MDT000002","RespDesc":"成功","RetUrl":"","SecRespCode":"","BankId":"","ChkValue":"8C0E6EB66EB3322D1369E1172FD818B93E0599DC9C74F8AE562DAA427EA13C02E5BD7C4585A1E838807525A8D94EBF58E7AD286F8DD598F7B723101A079E87DFB5FB2B8CF386835147DE66528FD371AC0511BD7051892E615E7B2565080BE0DAD36E4E1EFD2BC1D6BAD20C11941225CBDC2EDF5D96BE05C73DEF1920D5F9B344","GateBusiId":"QP"}
         */

        public OutMapBean OutMap;

        public static class OutMapBean {
            /**
             * RespCode : 000
             * TrxId : 201709130000887734
             * UsrCustId : 6000060007674671
             * MerCustId : 6000060007051718
             * SecRespDesc :
             * FeeCustId : 6000060007051718
             * DepoBankSeq : 0002839910
             * BgRetUrl : http://180.167.70.150:8010/new/recharge/rechargeCallBack.html
             * OrdId : 20170913161051378
             * TransAmt : 1000.00
             * FeeAmt : 1.30
             * CmdId : DirectRecharge
             * OrdDate : 20170913
             * FeeAcctId : MDT000002
             * RespDesc : 成功
             * RetUrl :
             * SecRespCode :
             * BankId :
             * ChkValue : 8C0E6EB66EB3322D1369E1172FD818B93E0599DC9C74F8AE562DAA427EA13C02E5BD7C4585A1E838807525A8D94EBF58E7AD286F8DD598F7B723101A079E87DFB5FB2B8CF386835147DE66528FD371AC0511BD7051892E615E7B2565080BE0DAD36E4E1EFD2BC1D6BAD20C11941225CBDC2EDF5D96BE05C73DEF1920D5F9B344
             * GateBusiId : QP
             */

            public String RespCode;
            public String TrxId;
            public String UsrCustId;
            public String MerCustId;
            public String SecRespDesc;
            public String FeeCustId;
            public String DepoBankSeq;
            public String BgRetUrl;
            public String OrdId;
            public String TransAmt;
            public String FeeAmt;
            public String CmdId;
            public String OrdDate;
            public String FeeAcctId;
            public String RespDesc;
            public String RetUrl;
            public String SecRespCode;
            public String BankId;
            public String ChkValue;
            public String GateBusiId;
            public String orderNo; //订单号
        }
    }
}
