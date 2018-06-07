package com.haolyy.haolyy.ui.my.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.entity.my.TransactionRecordBean;
import com.haolyy.haolyy.model.AccountModel;
import com.haolyy.haolyy.ui.my.View.TransactionRecordView;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;


/**
 * Created by liliang on 2017/9/21.
 */

public class TransactionRecordPresenter extends BasePresenter<TransactionRecordView> {
    public TransactionRecordPresenter(Context context){
        super(context);
    }

    public void getTransactionRecord(final boolean refresh,int type,int pageIndex){
        invoke(AccountModel.getInstance().transactionRecord(type,pageIndex),new ProgressSubscriber<TransactionRecordBean>(new SubscriberOnNextListener<TransactionRecordBean>() {
            @Override
            public void onNext(TransactionRecordBean transactionRecordBean) {

                if (Config.success.equals(transactionRecordBean.code)){
                    if (refresh){
                        getView().getData(transactionRecordBean.model.dataList);
                    }else {
                        getView().getDataMore(transactionRecordBean.model.dataList);
                    }
                }
            }
            @Override
            public void onError(Throwable e) {
                getView().noNetwork();
            }
        },mContext));
    }
}
