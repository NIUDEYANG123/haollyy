package com.haolyy.haolyy.ui.product.View;


import com.haolyy.haolyy.entity.product.BorrowPlanListBean;
import com.haolyy.haolyy.entity.product.PlanBorrowForAppBean;

/**
 * Created by niudeyang on 2017/8/8.
 */

public interface ProductListView {
    void getData(BorrowPlanListBean planListBean);
    void getDataMore(BorrowPlanListBean planListBean);
    void noNetwork();

    void getPlanData(BorrowPlanListBean baseBean);
}
