package com.haolyy.haolyy.ui.my.View;


import com.haolyy.haolyy.entity.userinfo.CashHeldBean;

/**
 * Created by liliang on 2018/1/12.
 */

public interface CashHeldView {

    void getData(CashHeldBean cashHeldBean);

    void getDataMore(CashHeldBean cashHeldBean);

    void noNetwork();
}
