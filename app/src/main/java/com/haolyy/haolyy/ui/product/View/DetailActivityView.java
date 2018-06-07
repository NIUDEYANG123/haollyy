package com.haolyy.haolyy.ui.product.View;


import com.haolyy.haolyy.entity.product.ProductDetailBean;

/**
 * Created by niudeyang on 2017/8/30.
 */

public interface DetailActivityView {
    void pushActivity(String s, int requestCode);
    void pushSucess(int i);
    void showDetail(ProductDetailBean productDetailBean);

    void noNetwork();

    void showSign();

    void sign2auto();

    void auto2risk();
}
