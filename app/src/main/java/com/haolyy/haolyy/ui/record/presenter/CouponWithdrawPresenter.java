package com.haolyy.haolyy.ui.record.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.entity.userinfo.InvestCouponBean;
import com.haolyy.haolyy.entity.userinfo.WithDrawCoupon;
import com.haolyy.haolyy.model.AccountModel;
import com.haolyy.haolyy.ui.record.view.CouponView;
import com.haolyy.haolyy.ui.record.view.CouponWithdrawView;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import rx.Subscriber;

import static com.haolyy.haolyy.config.Config.success;


/**
 * Created by niudeyang on 2017/10/31.
 */

public class CouponWithdrawPresenter extends BasePresenter<CouponWithdrawView> {
    public CouponWithdrawPresenter(Context context) {
        super(context);
    }

    public void queryWithDraw(String page,String limit){
        invoke(AccountModel.getInstance().queryWithdrawCoupon(page,limit),new ProgressSubscriber<WithDrawCoupon>(new SubscriberOnNextListener<WithDrawCoupon>() {
            @Override
            public void onNext(WithDrawCoupon withDrawCoupon) {
                if(withDrawCoupon.getCode().equals(success)){
                    getView().showWithDrawCoupon(withDrawCoupon);
                }
            }

            @Override
            public void onError(Throwable e) {

            }


        },mContext));
    }
}
