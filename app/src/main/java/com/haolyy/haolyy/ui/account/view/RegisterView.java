package com.haolyy.haolyy.ui.account.view;


import com.haolyy.haolyy.entity.TokenBean;

/**
 * Created by niudeyang on 2017/8/7.
 */

public interface RegisterView {
    void showIamgeCode(TokenBean tokenBean);

    void countDown(boolean b);

    void showSucess();
}
