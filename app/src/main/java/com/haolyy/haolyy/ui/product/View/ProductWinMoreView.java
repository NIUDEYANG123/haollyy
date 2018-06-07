package com.haolyy.haolyy.ui.product.View;

import com.haolyy.haolyy.entity.product.DqyBean;
import com.haolyy.haolyy.entity.product.ProductSanListBean;

/**
 * Created by shanghai on 2018/3/1.
 */

public interface ProductWinMoreView {
    void getWindata(ProductSanListBean productSanListBean);

    void getMoreWindata(ProductSanListBean productSanListBean);

    void showDqy(DqyBean dqyBean);

    void showDqyMore(DqyBean dqyBean);
}
