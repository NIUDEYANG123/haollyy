package com.haolyy.haolyy.ui.my.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.base.BaseView;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.entity.ModifyBankPhoneBean;
import com.haolyy.haolyy.entity.userinfo.RiskBean;
import com.haolyy.haolyy.model.AccountModel;
import com.haolyy.haolyy.model.HuifuModel;
import com.haolyy.haolyy.ui.my.View.RiskResultView;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import rx.Subscriber;

/**
 * Created by shanghai on 2018/3/16.
 */

public class ModifyBankPhonePresenter extends BasePresenter<BaseView<ModifyBankPhoneBean>> {
    public ModifyBankPhonePresenter(Context context) {
        super(context);
    }

    public void modifyBankPhone(String userCode,String oldMobile,String newMobile,String smsCode,String smsSeq){


        invoke(HuifuModel.getInstance().modifyBankPhone(userCode,oldMobile,newMobile,smsCode,smsSeq),new ProgressSubscriber<>(new SubscriberOnNextListener<ModifyBankPhoneBean>() {
            @Override
            public void onNext(ModifyBankPhoneBean modifyBankPhoneBean) {
                if (Config.success.equals(modifyBankPhoneBean.getCode())){
                    getView().getCommonData(modifyBankPhoneBean);
                }else {
                    UIUtils.showToastCommon(mContext,modifyBankPhoneBean.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                UIUtils.showToastCommon(mContext,"网络异常，请稍后重试");
            }
        },mContext));
    }
}
