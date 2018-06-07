package com.haolyy.haolyy.ui.my.View;

import com.haolyy.haolyy.entity.my.HomePageBean;

/**
 * Created by haolyy on 2017/8/9.
 */

public interface MyView {
    void showMyFragmentStatus();

    void showAsset(HomePageBean homePageBean);

    void pushActivity(String s);
}
