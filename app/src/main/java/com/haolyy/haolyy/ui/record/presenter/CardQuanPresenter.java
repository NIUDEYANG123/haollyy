package com.haolyy.haolyy.ui.record.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.entity.userinfo.AccountCouponbean;
import com.haolyy.haolyy.model.AccountModel;
import com.haolyy.haolyy.ui.record.view.CardQuanView;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

/**
 * Created by liliang on 2018/1/10.
 */

public class CardQuanPresenter extends BasePresenter<CardQuanView> {
    public CardQuanPresenter(Context context){
        super(context);
    }

    /**
     * 查所有卡券
     */
    public void queryQuan() {
        invoke(AccountModel.getInstance().queryAllCoupons("1","100",""),new ProgressSubscriber<AccountCouponbean>(new SubscriberOnNextListener<AccountCouponbean>() {
            @Override
            public void onNext(AccountCouponbean accountCouponbean) {
                if (Config.success.equals(accountCouponbean.getCode())){


                        getView().showCoupons(accountCouponbean);
                }else {
                    UIUtils.showToastCommon(mContext,accountCouponbean.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
            }


        },mContext));
    }
}
