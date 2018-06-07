package com.haolyy.haolyy.ui.third.view;

import com.haolyy.haolyy.entity.bank.Fee;
import com.haolyy.haolyy.entity.bank.IsCashSuccess;
import com.haolyy.haolyy.entity.bank.QueryBankBean;
import com.haolyy.haolyy.entity.my.HomePageBean;
import com.haolyy.haolyy.entity.userinfo.UserAccountInfo;


public interface WithdrawApplyView {

    void showBank(QueryBankBean queryBankBean);

    void showAccountInfo(HomePageBean homePageBean);

    void showFee(Fee fee,String flag);

    void pushActvity(int i, String s);
    void pushSucess(IsCashSuccess bBaseBean);
}
