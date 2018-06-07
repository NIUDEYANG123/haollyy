package com.haolyy.haolyy.ui.third.view;


import com.haolyy.haolyy.entity.bank.OpenAccountBean;
import com.haolyy.haolyy.entity.bank.QueryBankBean;
import com.haolyy.haolyy.entity.bank.SmsCodeBean;

/**
 * Created by niudeyang on 2017/8/11.
 */

public interface OpenBankView {
    void pushSucessActivity(boolean b);//开户成功界面


    void pushActivity(OpenAccountBean openAccountBean);

    void showOpenWaitDialog();

}
