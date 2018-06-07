package com.haolyy.haolyy.ui.my.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.entity.BBaseBean;
import com.haolyy.haolyy.entity.bank.QueryBankBean;
import com.haolyy.haolyy.entity.my.HomePageBean;
import com.haolyy.haolyy.entity.userinfo.GetUserStatusBean;
import com.haolyy.haolyy.model.AccountModel;
import com.haolyy.haolyy.model.HuifuModel;
import com.haolyy.haolyy.ui.my.View.MyView;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import rx.Subscriber;

import static com.haolyy.haolyy.config.Config.success;

/**
 * Created by haolyy on 2017/8/9.
 */

public class MyPresenter extends BasePresenter<MyView> {
    public MyPresenter(Context context) {
        super(context);
    }

    @Override
    public void overwriteSelectUserState(GetUserStatusBean u) {
        super.overwriteSelectUserState(u);
        if (u.model.userStatus.openAccountStatus.equals("1")) {
            //未开户
            BaseApplication.step = 1;
        } else if (u.model.userStatus.openAccountStatus.equals("4")) {
            //待激活
            BaseApplication.step = 2;
        } else {
            queryUserInfo();
            if (u.model.userStatus.setAutoBuyBidFlag.equals("1")) {
                //已设置主动投标
                BaseApplication.step = 4;
            } else {
                //未设置主动投标
                BaseApplication.step = 3;
            }
        }

        getView().showMyFragmentStatus();
    }

    /**
     * 查询持有资产
     */
    public void queryUserInfo() {
        invoke(AccountModel.getInstance().getHomePage(), new Subscriber<HomePageBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HomePageBean homePageBean) {
                if (homePageBean.code.equals(success)) {
                    getView().showAsset(homePageBean);
                } else {
                    UIUtils.showToastCommon(mContext, homePageBean.msg);
                }
            }
        });
    }

    //第三方客户号查询
    public void queryBank() {
        invoke(HuifuModel.getInstance().queryRecharge(), new Subscriber<QueryBankBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(QueryBankBean queryBankBean) {
                if (success.equals(queryBankBean.code)) {
                    BaseApplication.userCustId = queryBankBean.model.ThirdUserId;
                    activate();
                } else {
                    //SecurityActivity.activate =false;
                    UIUtils.showToastCommon(mContext, queryBankBean.msg);
                }

            }
        });
    }

    //激活
    public void activate() {
        invoke(HuifuModel.getInstance().bosAcctActivate(), new Subscriber<BBaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BBaseBean baseBean) {
                if (success.equals(baseBean.code)) {
                    getView().pushActivity(new Gson().toJson(baseBean));
                } else {

                }
            }
        });
    }
}
