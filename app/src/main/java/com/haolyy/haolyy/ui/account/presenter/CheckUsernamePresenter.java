package com.haolyy.haolyy.ui.account.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.base.BaseView;
import com.haolyy.haolyy.model.UserModel;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import static com.haolyy.haolyy.config.Config.success;

/**
 * @author wyman
 * @date 2018/3/1
 * description :
 */

public class CheckUsernamePresenter extends BasePresenter<BaseView> {
    public CheckUsernamePresenter(Context context) {
        super(context);
    }

    public void isRegister(String phone) {
        invoke(UserModel.getInstance().isRegister(phone),new ProgressSubscriber<>(new SubscriberOnNextListener<BaseBean>() {
            @Override
            public void onNext(BaseBean baseBean) {
                if (baseBean.code.equals(success)) {
                    getView().requestSuccess("");

                } else {
                    getView().requestError("");

                }
            }

            @Override
            public void onError(Throwable e) {

            }
        },mContext));

    }

}
