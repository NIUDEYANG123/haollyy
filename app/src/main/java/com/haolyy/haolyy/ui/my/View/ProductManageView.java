package com.haolyy.haolyy.ui.my.View;

import com.haolyy.haolyy.entity.my.HomePageBean;
import com.haolyy.haolyy.entity.my.ProductManageDetailBean;

/**
 * Created by liliang on 2017/9/23.
 */

public interface ProductManageView {
    void getDetail(ProductManageDetailBean detailBean);
    void showAsset(HomePageBean bean);

    void changeOpen(String model);

    void contractUrl(String model);
}
