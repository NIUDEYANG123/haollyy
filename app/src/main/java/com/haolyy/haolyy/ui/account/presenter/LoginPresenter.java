package com.haolyy.haolyy.ui.account.presenter;

import android.content.Context;
import android.gesture.GestureUtils;

import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.config.ConstantKey;
import com.haolyy.haolyy.entity.BBaseBean;
import com.haolyy.haolyy.entity.login.LoginBean;
import com.haolyy.haolyy.model.AccountModel;
import com.haolyy.haolyy.model.UserModel;
import com.haolyy.haolyy.ui.account.view.LoginView;
import com.haolyy.haolyy.utils.LockPatternUtils;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.SPUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import rx.Subscriber;

import static com.haolyy.haolyy.base.BaseApplication.context;
import static com.haolyy.haolyy.config.Config.success;



public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(Context context) {
        super(context);
    }

    public void login(String phoneNum, String passWord) {
        invoke(UserModel.getInstance().login(phoneNum,passWord),new ProgressSubscriber<LoginBean>(new SubscriberOnNextListener<LoginBean>() {
            @Override
            public void onNext(LoginBean baseBean) {
                if ("success".equals(baseBean.code)){
                    BaseApplication.mLoginState=true;
                    BaseApplication.mUserName=baseBean.model.mobile;
                    BaseApplication.userId=baseBean.model.id;
                    BaseApplication.juid=baseBean.model.userCode;//usercode是明码 juid是加密后的 世纪是一个东西
                    BaseApplication.juidMd5=baseBean.model.juid;
                    BaseApplication.accessToken=baseBean.model.accessToken;
                    BaseApplication.refreshToken=baseBean.model.refreshToken;
                    BaseApplication.exist=baseBean.model.exists;

                    SPUtils.saveBoolean(mContext,ConstantKey.LOGIN_STATE,true);
                    SPUtils.saveInt(mContext,ConstantKey.USER_ID,baseBean.model.id);
                    SPUtils.saveString(mContext,ConstantKey.USER_NAME,baseBean.model.mobile);
                    SPUtils.saveString(mContext,ConstantKey.JUID,baseBean.model.userCode);
                    SPUtils.saveString(mContext,ConstantKey.JUID_MD5,baseBean.model.juid);
                    SPUtils.saveBoolean(mContext,ConstantKey.EXISTS,baseBean.model.exists);
                    SPUtils.saveString(context, ConstantKey.NEW_TOKEN_A,baseBean.model.accessToken);
                    SPUtils.saveString(context, ConstantKey.NEW_TOKEN_R,baseBean.model.refreshToken);
                    LogUtils.e("登录保存token=="+SPUtils.getString(context, ConstantKey.NEW_TOKEN_A, "")+"========"+ SPUtils.getString(context, ConstantKey.NEW_TOKEN_R, ""));
                    //重新设置手势密码，清除密码
                    LockPatternUtils.saveLockPattern(mContext,ConstantKey.GESTURE_STATE_KEY,null);
                    getView().LoginSuccess();
                    querySignStatus();//顺便查一下签约状态
                }else {
                    getView().loginError(baseBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {

            }


        },mContext));
    }

    /**
     * 判断是否签约
     */
    public void querySignStatus() {
        invoke(AccountModel.getInstance().querySignStatus(), new Subscriber<BBaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BBaseBean bBaseBean) {
                if(bBaseBean.code.equals(success)){
                    if(bBaseBean.model.equals("0")){
                        BaseApplication.signstatus = false;
                    }else {
                        BaseApplication.signstatus=true;
                    }
                }
            }
        });
    }
}
