package com.haolyy.haolyy.ui.my.View;

import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.entity.bank.QueryBankBean;
import com.haolyy.haolyy.entity.bank.QueryCardInfoBean;

/**
 * Created by shanghai on 2018/3/6.
 */

public interface SettingView {
    void pushActivity(String s);

    void showName(QueryCardInfoBean queryCardInfoBean);

    void showRiskLevel(String s);
    void showBank(QueryBankBean queryBankBean);

    void selectModifyBankPhone(BaseBean baseBean);
}
