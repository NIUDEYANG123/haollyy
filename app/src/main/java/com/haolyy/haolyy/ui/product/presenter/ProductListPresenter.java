package com.haolyy.haolyy.ui.product.presenter;

import android.content.Context;


import com.google.gson.Gson;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.entity.BBaseBean;
import com.haolyy.haolyy.entity.bank.AutoTenderBean;
import com.haolyy.haolyy.entity.bank.QueryCardInfoBean;
import com.haolyy.haolyy.entity.product.BorrowPlanListBean;
import com.haolyy.haolyy.entity.userinfo.SignContractbean;
import com.haolyy.haolyy.model.AccountModel;
import com.haolyy.haolyy.model.HuifuModel;
import com.haolyy.haolyy.model.ProductModel;
import com.haolyy.haolyy.ui.product.View.ProductListView;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import rx.Subscriber;

import static com.haolyy.haolyy.config.Config.success;


/**
 * Created by niudeyang on 2017/8/8.
 */

public class ProductListPresenter extends BasePresenter<ProductListView> {

    private final ProductModel productModel;

    public ProductListPresenter(Context context) {
        super(context);
        productModel = ProductModel.getInstance();
    }

    /**
     *
     * @param appendRate
     * @param peroidLength
     * @param pageIndex
     */
    public void getSanList(final boolean refresh, String borrowType,String appendRate, String peroidLength, int pageIndex){
        invoke(productModel.borrowPlanList(borrowType, appendRate, peroidLength, pageIndex), new Subscriber<BorrowPlanListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                  LogUtils.e(tag,"san"+e.toString());
                  getView().noNetwork();
            }

            @Override
            public void onNext(BorrowPlanListBean borrowPlanListBean) {
                        if(borrowPlanListBean.getCode().equals(success)){
                            if (refresh){
                                getView().getData(borrowPlanListBean);
                            }else {
                                getView().getDataMore(borrowPlanListBean);
                            }
                        }else {
                            UIUtils.showToastCommon(mContext,borrowPlanListBean.getMsg());
                            getView().noNetwork();
                        }
                    }
        });
    }

    public void getPlanList(final boolean refresh, String borrowType,String appendRate, String peroidLength, int pageIndex){
        invoke(productModel.borrowPlanList(borrowType, appendRate, peroidLength, pageIndex), new Subscriber<BorrowPlanListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(tag,e.toString());
                getView().noNetwork();
            }

            @Override
            public void onNext(BorrowPlanListBean baseBean) {
                if(baseBean.getCode().equals(success)){
                    getView().getPlanData(baseBean);
                }else {
                    getView().noNetwork();
                }
            }
        });
    }
}
