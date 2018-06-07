package com.haolyy.haolyy.ui.my.presenter;

import android.content.Context;
import android.content.Intent;

import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.base.WebActivity;
import com.haolyy.haolyy.entity.BBaseBean;
import com.haolyy.haolyy.entity.my.HomePageBean;
import com.haolyy.haolyy.entity.my.ProductManageDetailBean;
import com.haolyy.haolyy.model.AccountModel;
import com.haolyy.haolyy.ui.my.View.ProductManageView;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import rx.Subscriber;

import static com.haolyy.haolyy.config.Config.success;


/**
 * Created by liliang on 2017/9/23.
 */

public class ProductManagePresenter extends BasePresenter<ProductManageView> {
    public ProductManagePresenter(Context context) {
        super(context);
    }


    /**
     * 查询持有资产
     */
    public void queryUserInfo() {
        invoke(AccountModel.getInstance().getHomePage(), new ProgressSubscriber<>(new SubscriberOnNextListener<HomePageBean>() {
            @Override
            public void onNext(HomePageBean homePageBean) {
                if (homePageBean.code.equals(success)) {
                    getView().showAsset(homePageBean);
                }else {
                    UIUtils.showToastCommon(mContext,homePageBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(tag, e.toString());
            }
        }, mContext));
    }

    /**
     * 查详情
     */
    public void queryDetail(String orderNo, String statustype) {
        LogUtils.e(tag, orderNo + statustype);
        invoke(AccountModel.getInstance().productDetail(orderNo, statustype), new Subscriber<ProductManageDetailBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ProductManageDetailBean productManageDetailBean) {
                if (productManageDetailBean.code.equals(success)) {
                    getView().getDetail(productManageDetailBean);
                }
            }
        });

    }


    /**
     * 续投开启
     *
     * @param cashNo
     */
    public void continueOpen(String cashNo) {
        invoke(AccountModel.getInstance().continueOpen(cashNo), new ProgressSubscriber<BBaseBean>(new SubscriberOnNextListener<BBaseBean>() {
            @Override
            public void onNext(BBaseBean assetHoldListBean) {
                if (assetHoldListBean.code.equals(success)) {
                    getView().changeOpen(assetHoldListBean.model);
                } else {
                    UIUtils.showToastCommon(mContext, assetHoldListBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }, mContext));
    }


    /**
     * 查看投资咨询服务协议
     * @param applyNo
     */
    public void getBaoquan(String applyNo) {
        invoke(AccountModel.getInstance().getBaoquanContarct(applyNo), new ProgressSubscriber<BBaseBean>(new SubscriberOnNextListener<BBaseBean>() {
            @Override
            public void onNext(BBaseBean bBaseBean) {
                if (bBaseBean.code.equals(success)) {
                    getView().contractUrl(bBaseBean.model);
                }else {
                    UIUtils.showToastCommon(mContext,"合同生成中");
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }, mContext));
    }
}
