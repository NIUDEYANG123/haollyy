package com.haolyy.haolyy.entity.bank;


import com.haolyy.haolyy.base.BaseBean;

/**
 * Created by niudeyang on 2017/8/30.
 */

public class AutoTenderBean extends BaseBean {


    /**
     * model : {"InMap":{"UsrCustId":"6000060008358191","Version":"10","MerCustId":"6000060007051718","CmdId":"AutoTenderPlan","TenderPlanType":"W","RetUrl":"https://www.chinazyjr.com/","PageType":"1","ChkValue":"8ED6D5E325E974F9C1DD0F0186BFF9FBD43FC54E68EDA33CAF300E140F3BE4C2722860EABB6BF6DF96EF3074805CB0470C17EC6ECD4B039A61B0B45F9CDE77B1F017B40CAC8A240EF135687921CC502188C85835925BB5ADB153B18B3E43CF8D41E7DA44FBA08D72797D56B6E407E242010119EF4E321C44DBE1982402AB00A5"},"ServiceUrl":"http://mertest.chinapnr.com/muser/publicRequests"}
     */

    public ModelBean model;
    public static class ModelBean {
        /**
         * InMap : {"UsrCustId":"6000060008358191","Version":"10","MerCustId":"6000060007051718","CmdId":"AutoTenderPlan","TenderPlanType":"W","RetUrl":"https://www.chinazyjr.com/","PageType":"1","ChkValue":"8ED6D5E325E974F9C1DD0F0186BFF9FBD43FC54E68EDA33CAF300E140F3BE4C2722860EABB6BF6DF96EF3074805CB0470C17EC6ECD4B039A61B0B45F9CDE77B1F017B40CAC8A240EF135687921CC502188C85835925BB5ADB153B18B3E43CF8D41E7DA44FBA08D72797D56B6E407E242010119EF4E321C44DBE1982402AB00A5"}
         * ServiceUrl : http://mertest.chinapnr.com/muser/publicRequests
         */
        public InMapBean InMap;
        public String ServiceUrl;

        public static class InMapBean {
            /**
             * UsrCustId : 6000060008358191
             * Version : 10
             * MerCustId : 6000060007051718
             * CmdId : AutoTenderPlan
             * TenderPlanType : W
             * RetUrl : https://www.chinazyjr.com/
             * PageType : 1
             * ChkValue : 8ED6D5E325E974F9C1DD0F0186BFF9FBD43FC54E68EDA33CAF300E140F3BE4C2722860EABB6BF6DF96EF3074805CB0470C17EC6ECD4B039A61B0B45F9CDE77B1F017B40CAC8A240EF135687921CC502188C85835925BB5ADB153B18B3E43CF8D41E7DA44FBA08D72797D56B6E407E242010119EF4E321C44DBE1982402AB00A5
             */

            public String UsrCustId;
            public String Version;
            public String MerCustId;
            public String CmdId;
            public String TenderPlanType;
            public String RetUrl;
            public String PageType;
            public String ChkValue;

        }
    }
}
