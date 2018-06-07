package com.haolyy.haolyy.ui.my.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.entity.my.TransactionRecordDetailsBean;
import com.haolyy.haolyy.model.AccountModel;
import com.haolyy.haolyy.ui.my.View.TRDView;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

/**
 * Created by liliang on 2017/9/22.
 */

public class TRDPresenter extends BasePresenter<TRDView> {
    public TRDPresenter(Context context){
        super(context);
    }
    public void getTRD(String orderNo, String type, String bidType,String billDate,String ids){
        invoke(AccountModel.getInstance().transactionRecordDetails(orderNo, type, bidType,billDate,ids),new ProgressSubscriber<TransactionRecordDetailsBean>(new SubscriberOnNextListener<TransactionRecordDetailsBean>() {
            @Override
            public void onNext(TransactionRecordDetailsBean detailsBean) {
                if (Config.success.equals(detailsBean.code)){
                    getView().showData(detailsBean);
                }else {
                    UIUtils.showToastCommon(mContext,detailsBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        },mContext));
    }
}
