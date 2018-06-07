package com.haolyy.haolyy.ui.home.View;


import com.haolyy.haolyy.entity.Notice;
import com.haolyy.haolyy.entity.product.HomeBannerBean;
import com.haolyy.haolyy.entity.product.HomeListBean;
import com.haolyy.haolyy.entity.product.PlanBorrowForAppBean;

/**
 * Created by niudeyang on 2017/8/8.
 */

public interface HomeView {
    void showBannerData(HomeBannerBean homeBannerBean);
    void showHomeList(HomeListBean homeListBean);

    void showNotice(Notice notice);
}
