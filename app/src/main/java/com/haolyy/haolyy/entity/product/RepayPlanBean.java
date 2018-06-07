package com.haolyy.haolyy.entity.product;

import java.util.List;

/**
 * Created by shanghai on 2018/3/1.
 */

public class RepayPlanBean {

    /**
     * code : success
     * msg : 成功
     * model : {"result":[{"shouldInterest":21.41,"shouldServiceFee":0,"shouldReturn":386.28,"endPrincipal":12765.9,"earlySettlement":13152.18,"shouldAmount":364.87,"billDate":"2018-05-03","state":1,"beginPrincipal":13130.77,"borrowNo":"JJT-1000001385819199","periodNo":1},{"shouldInterest":20.81,"shouldServiceFee":0,"shouldReturn":386.27,"endPrincipal":12400.44,"earlySettlement":12786.71,"shouldAmount":365.46,"billDate":"2018-05-10","state":1,"beginPrincipal":12765.9,"borrowNo":"JJT-1000001385819199","periodNo":2},{"shouldInterest":20.21,"shouldServiceFee":0,"shouldReturn":386.27,"endPrincipal":12034.38,"earlySettlement":12420.65,"shouldAmount":366.06,"billDate":"2018-05-17","state":1,"beginPrincipal":12400.44,"borrowNo":"JJT-1000001385819199","periodNo":3},{"shouldInterest":19.62,"shouldServiceFee":0,"shouldReturn":386.28,"endPrincipal":11667.72,"earlySettlement":12054,"shouldAmount":366.66,"billDate":"2018-05-24","state":1,"beginPrincipal":12034.38,"borrowNo":"JJT-1000001385819199","periodNo":4},{"shouldInterest":19.02,"shouldServiceFee":0,"shouldReturn":386.27,"endPrincipal":11300.47,"earlySettlement":11686.74,"shouldAmount":367.25,"billDate":"2018-05-31","state":1,"beginPrincipal":11667.72,"borrowNo":"JJT-1000001385819199","periodNo":5},{"shouldInterest":18.42,"shouldServiceFee":0,"shouldReturn":386.27,"endPrincipal":10932.62,"earlySettlement":11318.89,"shouldAmount":367.85,"billDate":"2018-06-07","state":1,"beginPrincipal":11300.47,"borrowNo":"JJT-1000001385819199","periodNo":6},{"shouldInterest":17.82,"shouldServiceFee":0,"shouldReturn":386.27,"endPrincipal":10564.17,"earlySettlement":10950.44,"shouldAmount":368.45,"billDate":"2018-06-14","state":1,"beginPrincipal":10932.62,"borrowNo":"JJT-1000001385819199","periodNo":7},{"shouldInterest":17.22,"shouldServiceFee":0,"shouldReturn":386.27,"endPrincipal":10195.12,"earlySettlement":10581.39,"shouldAmount":369.05,"billDate":"2018-06-21","state":1,"beginPrincipal":10564.17,"borrowNo":"JJT-1000001385819199","periodNo":8},{"shouldInterest":16.62,"shouldServiceFee":0,"shouldReturn":386.28,"endPrincipal":9825.46,"earlySettlement":10211.74,"shouldAmount":369.66,"billDate":"2018-06-28","state":1,"beginPrincipal":10195.12,"borrowNo":"JJT-1000001385819199","periodNo":9},{"shouldInterest":16.02,"shouldServiceFee":0,"shouldReturn":386.28,"endPrincipal":9455.2,"earlySettlement":9841.48,"shouldAmount":370.26,"billDate":"2018-07-05","state":1,"beginPrincipal":9825.46,"borrowNo":"JJT-1000001385819199","periodNo":10}],"total":35}
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
         * result : [{"shouldInterest":21.41,"shouldServiceFee":0,"shouldReturn":386.28,"endPrincipal":12765.9,"earlySettlement":13152.18,"shouldAmount":364.87,"billDate":"2018-05-03","state":1,"beginPrincipal":13130.77,"borrowNo":"JJT-1000001385819199","periodNo":1},{"shouldInterest":20.81,"shouldServiceFee":0,"shouldReturn":386.27,"endPrincipal":12400.44,"earlySettlement":12786.71,"shouldAmount":365.46,"billDate":"2018-05-10","state":1,"beginPrincipal":12765.9,"borrowNo":"JJT-1000001385819199","periodNo":2},{"shouldInterest":20.21,"shouldServiceFee":0,"shouldReturn":386.27,"endPrincipal":12034.38,"earlySettlement":12420.65,"shouldAmount":366.06,"billDate":"2018-05-17","state":1,"beginPrincipal":12400.44,"borrowNo":"JJT-1000001385819199","periodNo":3},{"shouldInterest":19.62,"shouldServiceFee":0,"shouldReturn":386.28,"endPrincipal":11667.72,"earlySettlement":12054,"shouldAmount":366.66,"billDate":"2018-05-24","state":1,"beginPrincipal":12034.38,"borrowNo":"JJT-1000001385819199","periodNo":4},{"shouldInterest":19.02,"shouldServiceFee":0,"shouldReturn":386.27,"endPrincipal":11300.47,"earlySettlement":11686.74,"shouldAmount":367.25,"billDate":"2018-05-31","state":1,"beginPrincipal":11667.72,"borrowNo":"JJT-1000001385819199","periodNo":5},{"shouldInterest":18.42,"shouldServiceFee":0,"shouldReturn":386.27,"endPrincipal":10932.62,"earlySettlement":11318.89,"shouldAmount":367.85,"billDate":"2018-06-07","state":1,"beginPrincipal":11300.47,"borrowNo":"JJT-1000001385819199","periodNo":6},{"shouldInterest":17.82,"shouldServiceFee":0,"shouldReturn":386.27,"endPrincipal":10564.17,"earlySettlement":10950.44,"shouldAmount":368.45,"billDate":"2018-06-14","state":1,"beginPrincipal":10932.62,"borrowNo":"JJT-1000001385819199","periodNo":7},{"shouldInterest":17.22,"shouldServiceFee":0,"shouldReturn":386.27,"endPrincipal":10195.12,"earlySettlement":10581.39,"shouldAmount":369.05,"billDate":"2018-06-21","state":1,"beginPrincipal":10564.17,"borrowNo":"JJT-1000001385819199","periodNo":8},{"shouldInterest":16.62,"shouldServiceFee":0,"shouldReturn":386.28,"endPrincipal":9825.46,"earlySettlement":10211.74,"shouldAmount":369.66,"billDate":"2018-06-28","state":1,"beginPrincipal":10195.12,"borrowNo":"JJT-1000001385819199","periodNo":9},{"shouldInterest":16.02,"shouldServiceFee":0,"shouldReturn":386.28,"endPrincipal":9455.2,"earlySettlement":9841.48,"shouldAmount":370.26,"billDate":"2018-07-05","state":1,"beginPrincipal":9825.46,"borrowNo":"JJT-1000001385819199","periodNo":10}]
         * total : 35
         */

        private int total;
        private List<ResultBean> result;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * shouldInterest : 21.41
             * shouldServiceFee : 0.0
             * shouldReturn : 386.28
             * endPrincipal : 12765.9
             * earlySettlement : 13152.18
             * shouldAmount : 364.87
             * billDate : 2018-05-03
             * state : 1
             * beginPrincipal : 13130.77
             * borrowNo : JJT-1000001385819199
             * periodNo : 1
             */

            private double shouldInterest;
            private double shouldServiceFee;
            private double shouldReturn;
            private double endPrincipal;
            private double earlySettlement;
            private double shouldAmount;
            private String billDate;
            private int state;
            private double beginPrincipal;
            private String borrowNo;
            private int periodNo;

            public double getShouldInterest() {
                return shouldInterest;
            }

            public void setShouldInterest(double shouldInterest) {
                this.shouldInterest = shouldInterest;
            }

            public double getShouldServiceFee() {
                return shouldServiceFee;
            }

            public void setShouldServiceFee(double shouldServiceFee) {
                this.shouldServiceFee = shouldServiceFee;
            }

            public double getShouldReturn() {
                return shouldReturn;
            }

            public void setShouldReturn(double shouldReturn) {
                this.shouldReturn = shouldReturn;
            }

            public double getEndPrincipal() {
                return endPrincipal;
            }

            public void setEndPrincipal(double endPrincipal) {
                this.endPrincipal = endPrincipal;
            }

            public double getEarlySettlement() {
                return earlySettlement;
            }

            public void setEarlySettlement(double earlySettlement) {
                this.earlySettlement = earlySettlement;
            }

            public double getShouldAmount() {
                return shouldAmount;
            }

            public void setShouldAmount(double shouldAmount) {
                this.shouldAmount = shouldAmount;
            }

            public String getBillDate() {
                return billDate;
            }

            public void setBillDate(String billDate) {
                this.billDate = billDate;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public double getBeginPrincipal() {
                return beginPrincipal;
            }

            public void setBeginPrincipal(double beginPrincipal) {
                this.beginPrincipal = beginPrincipal;
            }

            public String getBorrowNo() {
                return borrowNo;
            }

            public void setBorrowNo(String borrowNo) {
                this.borrowNo = borrowNo;
            }

            public int getPeriodNo() {
                return periodNo;
            }

            public void setPeriodNo(int periodNo) {
                this.periodNo = periodNo;
            }
        }
    }
}
