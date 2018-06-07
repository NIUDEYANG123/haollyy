package com.haolyy.haolyy.ui.product.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.entity.product.DqyBean;
import com.haolyy.haolyy.entity.product.ProductSanListBean;
import com.haolyy.haolyy.model.ProductModel;
import com.haolyy.haolyy.ui.product.View.ProductWinMoreView;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import static com.haolyy.haolyy.config.Config.success;

/**
 * Created by shanghai on 2018/3/1.
 */

public class ProductWinMorePresenter extends BasePresenter<ProductWinMoreView> {
    public ProductWinMorePresenter(Context context) {
        super(context);
    }

    public void getWinMore(boolean resfresh, String borrowActiveType, String pageIndex) {
        invoke(ProductModel.getInstance().standardAndPlanList("", borrowActiveType, pageIndex), new ProgressSubscriber<ProductSanListBean>(new SubscriberOnNextListener<ProductSanListBean>() {
            @Override
            public void onNext(ProductSanListBean productSanListBean) {
                if (productSanListBean.code.equals(success)) {
                    if (resfresh) {
                        getView().getWindata(productSanListBean);
                    } else {
                        getView().getMoreWindata(productSanListBean);
                    }
                } else {
                    UIUtils.showToastCommon(mContext,productSanListBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }, mContext));
    }


    public void getDqyMore(boolean refresh, String pageIndex) {
        invoke(ProductModel.getInstance().getDqyMore(pageIndex), new ProgressSubscriber<DqyBean>(new SubscriberOnNextListener<DqyBean>() {
            @Override
            public void onNext(DqyBean dqyBean) {
                if(success.equals(dqyBean.getCode())){
                if (refresh) {
                    getView().showDqy(dqyBean);
                } else {
                    getView().showDqyMore(dqyBean);
                }}else {
                    UIUtils.showToastCommon(mContext,dqyBean.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(tag, e.toString());
            }
        }, mContext));
    }
}
