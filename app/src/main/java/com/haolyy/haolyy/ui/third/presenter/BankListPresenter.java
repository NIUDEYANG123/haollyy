package com.haolyy.haolyy.ui.third.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.entity.bank.BankListBean;
import com.haolyy.haolyy.model.UserModel;
import com.haolyy.haolyy.ui.third.view.BankListView;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

/**
 * Created by liliang on 2017/9/29.
 */

public class BankListPresenter extends BasePresenter<BankListView> {
    public BankListPresenter(Context context){
        super(context);
    }

    public void getBanks(String busiType){
        invoke(UserModel.getInstance().getBanks(busiType),new ProgressSubscriber<BankListBean>(new SubscriberOnNextListener<BankListBean>() {
            @Override
            public void onNext(BankListBean listBean) {
                if (Config.success.equals(listBean.code)){
                    getView().showBank(listBean);
                }else {
                    UIUtils.showToastCommon(mContext,listBean.msg);
                }
            }
            @Override
            public void onError(Throwable e) {

            }

        },mContext));
    }
}
