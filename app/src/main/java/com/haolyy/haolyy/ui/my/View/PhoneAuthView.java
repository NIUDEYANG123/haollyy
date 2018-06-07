package com.haolyy.haolyy.ui.my.View;

import com.haolyy.haolyy.entity.TokenBean;

public interface PhoneAuthView {
    void showIamgeCode(TokenBean tokenBean);

    void showSucess();

    void next();

}
