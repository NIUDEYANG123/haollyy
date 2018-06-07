package com.haolyy.haolyy.ui.third.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.entity.bank.QueryBankBean;
import com.haolyy.haolyy.model.HuifuModel;
import com.haolyy.haolyy.ui.third.view.QueryBankView;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import static com.haolyy.haolyy.config.Config.success;


/**
 * Created by niudeyang on 2017/9/13.
 */

public class QueryBankPresenter extends BasePresenter<QueryBankView> {
    HuifuModel huifuModel;

    public QueryBankPresenter(Context context) {
        super(context);
        huifuModel = HuifuModel.getInstance();
    }



    public void queryBank() {
        invoke(huifuModel.queryRecharge(),new ProgressSubscriber<QueryBankBean>(new SubscriberOnNextListener<QueryBankBean>() {
            @Override
            public void onNext(QueryBankBean queryBankBean) {
                if (success.equals(queryBankBean.code)){
                    getView().showBank(queryBankBean);

                }else {
                    UIUtils.showToastCommon(mContext,queryBankBean.msg);
                }
            }
            @Override
            public void onError(Throwable e) {
                UIUtils.showToastCommon(mContext,"网络异常，请稍后重试");
            }


        },mContext));
    }



}
