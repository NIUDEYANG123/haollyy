package com.haolyy.haolyy.ui.product.View;

import com.haolyy.haolyy.entity.product.InvestRecordBean;
import com.haolyy.haolyy.entity.product.RepayPlanBean;

/**
 * Created by shanghai on 2018/3/1.
 */

public interface DetailRecordView {
    void showRepayPlan(RepayPlanBean repayPlanBean);

    void getInvestRecord(InvestRecordBean investRecordBean);

    void getMoreInvestRecord(InvestRecordBean investRecordBean);
}
