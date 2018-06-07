package com.haolyy.haolyy.ui.my.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.entity.my.AssetHoldListBean;
import com.haolyy.haolyy.model.AccountModel;
import com.haolyy.haolyy.ui.my.View.AssetHoldView;

import rx.Subscriber;

import static com.haolyy.haolyy.config.Config.success;

/**
 * Created by shanghai on 2018/2/7.
 */

public class AssetHoldPresenter extends BasePresenter<AssetHoldView> {
    public AssetHoldPresenter(Context context) {
        super(context);
    }
    /**
     * @param borrowType 标的类型（1：散标、2：预约标）
     * @param statusType 状态类型（散标时：1-投标中、2-收益中、3-已退出；预约标时：1-持有中、2-已退出、3-退出中）
     * @return
     */
    public void queryListbyStatus(boolean resfreh, String borrowType, String statusType, int pagenum) {
        invoke(AccountModel.getInstance().queryListByStatus(borrowType, statusType, pagenum), new Subscriber<AssetHoldListBean>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(AssetHoldListBean assetHoldListBean) {
                if (assetHoldListBean.getCode().equals(success)) {
                    if (resfreh) {
                        getView().getData(assetHoldListBean);
                    } else {
                        getView().getDataMore(assetHoldListBean);
                    }
                }
            }
        });
    }
}
