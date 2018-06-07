package com.haolyy.haolyy.entity;


import com.haolyy.haolyy.base.BaseBean;

/**
 * Created by liliang on 2017/9/14.
 */

public class BorrowInvestBean extends BaseBean {


    /**
     * code : success
     * msg : 成功
     * model : {"InMap":{"UsrCustId":"6000060007679337","MaxTenderRate":"0.50","MerCustId":"6000060007051718","IsFreeze":"N","BgRetUrl":"http://180.167.70.150:8010/new/invest/borrowInvestCallBack.html","OrdId":"2017091914370633","TransAmt":"100.00","BorrowerDetails":"[{\"BorrowerAmt\":\"100.00\",\"BorrowerRate\":\"2.00\",\"BorrowerCustId\":\"6000060007651026\",\"ProId\":\"1000002099294713\"}]","Version":"20","CmdId":"InitiativeTender","OrdDate":"20170919","RetUrl":"www.chinazyjr.com","PageType":"1","ChkValue":"28EF5798B61F6369E209BA3CAF16418FF4CC051D49032AECEE77DD23FC5E5D70426F530B248E7C01737E10D54976A027F480E568C03CA8A80C780655AD0C04CA8D9222E177EB6E99675DE9295E63CEE48FC72B0114842DE91248D698B200A2CE6BF7AD36C3F8B2E56DF50F19AF275E4CF8C82DBF329E27BD7632B939B62099E3"},"ServiceUrl":"http://mertest.chinapnr.com/muser/publicRequests","AccountOrderNo":"20170919143706372"}
     */
    public ModelBean model;
    public static class ModelBean {
        /**
         * InMap : {"UsrCustId":"6000060007679337","MaxTenderRate":"0.50","MerCustId":"6000060007051718","IsFreeze":"N","BgRetUrl":"http://180.167.70.150:8010/new/invest/borrowInvestCallBack.html","OrdId":"2017091914370633","TransAmt":"100.00","BorrowerDetails":"[{\"BorrowerAmt\":\"100.00\",\"BorrowerRate\":\"2.00\",\"BorrowerCustId\":\"6000060007651026\",\"ProId\":\"1000002099294713\"}]","Version":"20","CmdId":"InitiativeTender","OrdDate":"20170919","RetUrl":"www.chinazyjr.com","PageType":"1","ChkValue":"28EF5798B61F6369E209BA3CAF16418FF4CC051D49032AECEE77DD23FC5E5D70426F530B248E7C01737E10D54976A027F480E568C03CA8A80C780655AD0C04CA8D9222E177EB6E99675DE9295E63CEE48FC72B0114842DE91248D698B200A2CE6BF7AD36C3F8B2E56DF50F19AF275E4CF8C82DBF329E27BD7632B939B62099E3"}
         * ServiceUrl : http://mertest.chinapnr.com/muser/publicRequests
         * AccountOrderNo : 20170919143706372
         */

        public InMapBean InMap;
        public String ServiceUrl;
        public String AccountOrderNo;
        public static class InMapBean {
            /**
             * UsrCustId : 6000060007679337
             * MaxTenderRate : 0.50
             * MerCustId : 6000060007051718
             * IsFreeze : N
             * BgRetUrl : http://180.167.70.150:8010/new/invest/borrowInvestCallBack.html
             * OrdId : 2017091914370633
             * TransAmt : 100.00
             * BorrowerDetails : [{"BorrowerAmt":"100.00","BorrowerRate":"2.00","BorrowerCustId":"6000060007651026","ProId":"1000002099294713"}]
             * Version : 20
             * CmdId : InitiativeTender
             * OrdDate : 20170919
             * RetUrl : www.chinazyjr.com
             * PageType : 1
             * ChkValue : 28EF5798B61F6369E209BA3CAF16418FF4CC051D49032AECEE77DD23FC5E5D70426F530B248E7C01737E10D54976A027F480E568C03CA8A80C780655AD0C04CA8D9222E177EB6E99675DE9295E63CEE48FC72B0114842DE91248D698B200A2CE6BF7AD36C3F8B2E56DF50F19AF275E4CF8C82DBF329E27BD7632B939B62099E3
             */

            public String UsrCustId;
            public String MaxTenderRate;
            public String MerCustId;
            public String IsFreeze;
            public String FreezeOrdId;
            public String BgRetUrl;
            public String OrdId;
            public String TransAmt;
            public String BorrowerDetails;
            public String Version;
            public String CmdId;
            public String OrdDate;
            public String RetUrl;
            public String PageType;
            public String ChkValue;

        }
    }
}
