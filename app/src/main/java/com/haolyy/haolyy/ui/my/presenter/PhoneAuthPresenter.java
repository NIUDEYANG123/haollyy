package com.haolyy.haolyy.ui.my.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.entity.BBaseBean;
import com.haolyy.haolyy.entity.IsUpdateBean;
import com.haolyy.haolyy.entity.TokenBean;
import com.haolyy.haolyy.entity.login.PassWordBean;
import com.haolyy.haolyy.entity.login.RegsiterBean;
import com.haolyy.haolyy.model.UserModel;
import com.haolyy.haolyy.ui.account.view.RegisterView;
import com.haolyy.haolyy.ui.my.View.PhoneAuthView;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

import static com.haolyy.haolyy.base.BaseApplication.accessToken;
import static com.haolyy.haolyy.base.BaseApplication.mUserName;
import static com.haolyy.haolyy.config.Config.success;


/**
 * Created by niudeyang on 2017/8/7.
 */

public class PhoneAuthPresenter extends BasePresenter<PhoneAuthView> {


    public PhoneAuthPresenter(Context context) {
        super(context);
    }

    public void getPicCode() {
        invoke(UserModel.getInstance().getToken(), new ProgressSubscriber<TokenBean>(new SubscriberOnNextListener<TokenBean>() {
            @Override
            public void onNext(TokenBean tokenBean) {
                if ("success".equals(tokenBean.code)) {
                    getView().showIamgeCode(tokenBean);
                } else {
                    UIUtils.showToastCommon(mContext, tokenBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {

            }


        }, mContext));

    }

    public void validSmsCode(String imageCode, String smsCode, String token) {
        invoke(UserModel.getInstance().validSmsCode(imageCode, smsCode, token), new ProgressSubscriber<BBaseBean>(new SubscriberOnNextListener<BBaseBean>() {
            @Override
            public void onNext(BBaseBean o) {
                if (o.code.equals(success)) {
                    getView().next();
                } else {
                    UIUtils.showToastCommon(mContext, o.msg);
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }, mContext));
    }

    public void changeMobile(String imageCode, String smsCode, String token, String phone) {
        invoke(UserModel.getInstance().changeMobile(imageCode, smsCode, token, phone), new ProgressSubscriber<BBaseBean>(new SubscriberOnNextListener<BBaseBean>() {
            @Override
            public void onNext(BBaseBean bBaseBean) {
                if (bBaseBean.code.equals(success)) {
                    getView().showSucess();
                } else {
                    UIUtils.showToastCommon(mContext, bBaseBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }, mContext));
    }

}
