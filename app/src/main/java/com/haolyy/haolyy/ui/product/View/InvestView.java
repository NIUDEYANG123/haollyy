package com.haolyy.haolyy.ui.product.View;


import com.haolyy.haolyy.entity.my.HomePageBean;
import com.haolyy.haolyy.entity.product.ProductDetailBean;
import com.haolyy.haolyy.entity.product.RevenueBean;
import com.haolyy.haolyy.entity.userinfo.InvestCouponBean;

/**
 * Created by niudeyang on 2017/12/23.
 */

public interface InvestView {
  void showRevenue(String amount, String model) ;

    void showDetail(ProductDetailBean productDetailBean);

    void showAccountinfo(HomePageBean homePageBean);

    void showInvestCoupons(InvestCouponBean investCouponBean);

    void pushActivity(String s, boolean b);

    void showRevenueNew(String amount, RevenueBean.ModelBean model);


    void showRiskLevel(int socre);
}
