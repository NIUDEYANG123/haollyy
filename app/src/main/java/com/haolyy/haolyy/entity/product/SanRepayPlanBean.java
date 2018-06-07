package com.haolyy.haolyy.entity.product;

import java.util.List;

/**
 * Created by shanghai on 2018/3/6.
 */

public class SanRepayPlanBean {
    /**
     * code : success
     * msg : success
     * model : {"totalCorpusAndInterest":0,"totalCorpus":0,"detail":[{"curStageNo":1,"receiveCorpus":2,"receiveInterest":2,"restCorpusAndInterest":36}],"totalInterest":0}
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
         * totalCorpusAndInterest : 0
         * totalCorpus : 0
         * detail : [{"curStageNo":1,"receiveCorpus":2,"receiveInterest":2,"restCorpusAndInterest":36}]
         * totalInterest : 0
         */

        private double totalCorpusAndInterest;
        private double totalCorpus;
        private double totalInterest;
        private List<DetailBean> detail;

        public double getTotalInterest() {
            return totalInterest;
        }

        public double getTotalCorpusAndInterest() {
            return totalCorpusAndInterest;
        }

        public void setTotalCorpusAndInterest(int totalCorpusAndInterest) {
            this.totalCorpusAndInterest = totalCorpusAndInterest;
        }

        public double getTotalCorpus() {
            return totalCorpus;
        }

        public void setTotalCorpus(double totalCorpus) {
            this.totalCorpus = totalCorpus;
        }


        public void setTotalInterest(int totalInterest) {
            this.totalInterest = totalInterest;
        }

        public List<DetailBean> getDetail() {
            return detail;
        }

        public void setDetail(List<DetailBean> detail) {
            this.detail = detail;
        }

        public static class DetailBean {
            /**
             * curStageNo : 1
             * receiveCorpus : 2
             * receiveInterest : 2
             * restCorpusAndInterest : 36
             */

            private int curStageNo;
            private double receiveCorpus;
            private double receiveInterest;
            private double restCorpusAndInterest;

            public int getCurStageNo() {
                return curStageNo;
            }

            public void setCurStageNo(int curStageNo) {
                this.curStageNo = curStageNo;
            }

            public double getReceiveCorpus() {
                return receiveCorpus;
            }

            public void setReceiveCorpus(int receiveCorpus) {
                this.receiveCorpus = receiveCorpus;
            }

            public double getReceiveInterest() {
                return receiveInterest;
            }

            public void setReceiveInterest(int receiveInterest) {
                this.receiveInterest = receiveInterest;
            }

            public double getRestCorpusAndInterest() {
                return restCorpusAndInterest;
            }

            public void setRestCorpusAndInterest(int restCorpusAndInterest) {
                this.restCorpusAndInterest = restCorpusAndInterest;
            }
        }
    }
}
