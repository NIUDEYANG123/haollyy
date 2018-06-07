package com.haolyy.haolyy.ui.record.view;


import com.haolyy.haolyy.entity.userinfo.InvestCouponBean;

/**
 * Created by niudeyang on 2017/10/31.
 */

public interface CouponView {

    void getData(InvestCouponBean list);
    void getDataMore(InvestCouponBean list);

    void noNetwork();

}
