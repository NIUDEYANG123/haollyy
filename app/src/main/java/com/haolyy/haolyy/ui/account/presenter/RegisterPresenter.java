package com.haolyy.haolyy.ui.account.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.ActivityCollector;
import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.entity.TokenBean;
import com.haolyy.haolyy.entity.login.PassWordBean;
import com.haolyy.haolyy.entity.login.RegsiterBean;
import com.haolyy.haolyy.model.UserModel;
import com.haolyy.haolyy.ui.account.RegisterActivity;
import com.haolyy.haolyy.ui.account.view.RegisterView;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;


/**
 * Created by niudeyang on 2017/8/7.
 */

public class RegisterPresenter extends BasePresenter<RegisterView> {


    public RegisterPresenter(Context context) {
        super(context);
    }

    public void getPicCode() {
        invoke(UserModel.getInstance().getToken(), new ProgressSubscriber<TokenBean>(new SubscriberOnNextListener<TokenBean>() {
            @Override
            public void onNext(TokenBean tokenBean) {
                if ("success".equals(tokenBean.code)) {
                    getView().showIamgeCode(tokenBean);
                }else{
                    UIUtils.showToastCommon(mContext,tokenBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {

            }


        }, mContext));

    }


    /**
     * 注册
     *
     * @param phoneNum
     * @param passWord
     * @param imageCode
     * @param SmsCode
     * @param token
     */
    public void regsieter(String phoneNum, String passWord, String imageCode, String SmsCode, String token,String inviteCode,String bdUrl) {
        invoke(UserModel.getInstance().register(phoneNum, passWord, imageCode, SmsCode, token,inviteCode,bdUrl), new ProgressSubscriber<RegsiterBean>(new SubscriberOnNextListener<RegsiterBean>() {
            @Override
            public void onNext(RegsiterBean regsiterBean) {
                if ("success".equals(regsiterBean.code)) {
                    getView().showSucess();
                }
                UIUtils.showToastCommon(mContext, regsiterBean.msg);
            }

            @Override
            public void onError(Throwable e) {

            }


        }, mContext));

    }

    /**
     * 密码重置
     *
     * @param phoneNum
     * @param passWord
     * @param imageCode
     * @param SmsCode
     * @param token
     */
    public void resetPwd(String phoneNum, String passWord, String imageCode, String SmsCode, String token) {
        invoke(UserModel.getInstance().forgetPassWord(phoneNum, passWord, imageCode, SmsCode, token), new ProgressSubscriber<PassWordBean>(new SubscriberOnNextListener<PassWordBean>() {
            @Override
            public void onNext(PassWordBean passWordBean) {
                if ("success".equals(passWordBean.code)) {
                    UIUtils.showToastCommon(mContext, "密码重置成功");
                    getView().showSucess();
                } else {
                    UIUtils.showToastCommon(mContext, passWordBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {

            }


        }, mContext));

    }
}
