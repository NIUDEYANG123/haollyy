package com.haolyy.haolyy.base;

import android.content.Context;
import android.widget.TextView;

import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.entity.BBaseBean;
import com.haolyy.haolyy.entity.bank.SmsCodeBean;
import com.haolyy.haolyy.entity.userinfo.GetUserStatusBean;
import com.haolyy.haolyy.model.HuifuModel;
import com.haolyy.haolyy.model.UserModel;
import com.haolyy.haolyy.utils.AppToast;
import com.haolyy.haolyy.utils.DateUtil;
import com.haolyy.haolyy.utils.WYUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import java.lang.ref.WeakReference;

import rx.Observable;
import rx.Subscriber;

import static com.haolyy.haolyy.config.Config.success;

/**
 * Created by wy on 2016/9/2.
 */
public abstract class BasePresenter<T> {

    //弱引用,有效防止view内存泄漏
    private WeakReference<T> mViewRef;
    protected Context mContext;
    public String tag = this.getClass().getSimpleName();
    public LifeSubscription lifeSubscription;

    public void setLifeSubscription(LifeSubscription lifeSubscription) {
        this.lifeSubscription = lifeSubscription;
    }

    protected <T> void invoke(Observable<T> observable, Subscriber<T> subscriber) {
        BaseModel.invoke(lifeSubscription, observable, subscriber);
    }

    protected <T> void invokeMerge(Subscriber<T> subscriber, Observable... observables) {
        BaseModel.invokeMerge(lifeSubscription, subscriber, observables);
    }
    public BasePresenter(Context context) {
        this.mContext = context;
    }

    //关联
    void attach(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    //解除关联
    void detach() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public T getView() {
        return mViewRef.get();
    }


    /**
     * 发送短信验证码
     */
    public void sendSMS(final TextView textView, String phoneNum, String token, String operationType, String imageCode) {
        invoke(UserModel.getInstance().getSmsCode(phoneNum, token, operationType, imageCode), new ProgressSubscriber<BBaseBean>(new SubscriberOnNextListener<BBaseBean>() {
            @Override
            public void onNext(BBaseBean baseBean) {
                if (baseBean.code.equals(success)) {
                    AppToast.showShortText(mContext, "短信发送成功");
                    textView.setEnabled(false);
                    DateUtil.countDown(textView, "重新发送");
                } else {
                    AppToast.showShortText(mContext, baseBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                AppToast.showShortText(mContext, "短信发送失败，请重试");
            }
        }, mContext));
    }

    /**
     * 汇付短信
     *
     * @param textView
     * @param busi_type_   业务类型:user_register(开户),rebind(换绑卡),recharge(充值)
     * @param card_number_ 银行卡号
     * @param mobile_      银行预留手机号
     * @param sms_type_    换绑卡时短信类型:O(旧的);N(新的)
     */
    public void sendSms(TextView textView, String busi_type_, String card_number_, final String mobile_, final String sms_type_) {
        invoke(HuifuModel.getInstance().sendHuifuSms(busi_type_, mobile_, card_number_, sms_type_), new ProgressSubscriber<SmsCodeBean>(new SubscriberOnNextListener<SmsCodeBean>() {
            @Override
            public void onNext(SmsCodeBean baseBean) {
                if (baseBean.code.equals(success)) {
                    if (WYUtils.isApkInDebug(BaseApplication.getContext())) {
                        BaseApplication.sms_seq_ = Config.sms_seq;
                    } else {

                        BaseApplication.sms_seq_ = baseBean.model.OutMap.SmsSeq;
                    }
                    AppToast.showShortText(mContext, "短信发送成功");
                    textView.setEnabled(false);
                    DateUtil.countDown(textView, "重新发送");
                } else {
                    AppToast.showShortText(mContext, baseBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                AppToast.showShortText(mContext, "短信发送失败，请重试");
            }


        }, mContext));
    }

    /**
     * 查询用户状态
     */
    public void selectUserState() {
        invoke(UserModel.getInstance().getUserStatus(), new Subscriber<GetUserStatusBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GetUserStatusBean getUserStatusBean) {
                if (getUserStatusBean.code.equals(success)) {
                    BaseApplication.riskCheck=getUserStatusBean.model.userStatus.riskRatingFlag;
                    overwriteSelectUserState(getUserStatusBean);
                }else {

                }
            }
        });

    }

    public void overwriteSelectUserState(GetUserStatusBean u) {

    }
}
