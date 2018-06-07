package com.haolyy.haolyy.ui.third.view;

import com.haolyy.haolyy.entity.bank.QueryBankBean;
import com.haolyy.haolyy.entity.bank.RechargeBean;


public interface RechargeView {

    void pushActivity(int i,String msg);
    void qrResult(String orderNo);
}
