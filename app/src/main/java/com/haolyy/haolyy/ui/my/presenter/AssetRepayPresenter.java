package com.haolyy.haolyy.ui.my.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.entity.userinfo.AssetRepayPLanBean;
import com.haolyy.haolyy.model.AccountModel;
import com.haolyy.haolyy.ui.my.View.AssetRepayView;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import static com.haolyy.haolyy.config.Config.success;

/**
 * Created by shanghai on 2018/3/5.
 */

public class AssetRepayPresenter extends BasePresenter<AssetRepayView> {
    public AssetRepayPresenter(Context context) {
        super(context);
    }

    public void getRepayPlan(String cashNo){
        invoke(AccountModel.getInstance().getAssetRepayBean(cashNo),new ProgressSubscriber<AssetRepayPLanBean>(new SubscriberOnNextListener<AssetRepayPLanBean>() {
            @Override
            public void onNext(AssetRepayPLanBean assetRepayPLanBean) {
                if(assetRepayPLanBean.getCode().equals(success)){
                    getView().showRepayPlan(assetRepayPLanBean);
                }else {
                    UIUtils.showToastCommon(mContext,assetRepayPLanBean.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        },mContext));
    }
}
