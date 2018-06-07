package com.haolyy.haolyy.ui.third.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.entity.bank.Fee;
import com.haolyy.haolyy.entity.bank.IsCashSuccess;
import com.haolyy.haolyy.entity.bank.QueryBankBean;
import com.haolyy.haolyy.entity.bank.WithDrawBean;
import com.haolyy.haolyy.entity.my.HomePageBean;
import com.haolyy.haolyy.entity.userinfo.UserAccountInfo;
import com.haolyy.haolyy.model.AccountModel;
import com.haolyy.haolyy.model.HuifuModel;
import com.haolyy.haolyy.ui.third.view.WithdrawApplyView;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import rx.Subscriber;

import static com.haolyy.haolyy.config.Config.success;


/**
 * Created by niudeyang on 2017/9/13.
 */

public class WithDrawApplyPresenter extends BasePresenter<WithdrawApplyView> {
    HuifuModel huifuModel;
    AccountModel accountModel;

    public WithDrawApplyPresenter(Context context) {
        super(context);
        huifuModel = HuifuModel.getInstance();
        accountModel = AccountModel.getInstance();
    }

    public void fetch() {
        invokeMerge(new ProgressSubscriber<Object>(new SubscriberOnNextListener<Object>() {
            @Override
            public void onNext(Object o) {
                if (o instanceof QueryBankBean) {
                    QueryBankBean queryBankBean = (QueryBankBean) o;
                    if (success.equals(queryBankBean.code)) {
                        getView().showBank(queryBankBean);

                    } else {
                        UIUtils.showToastCommon(mContext, queryBankBean.msg);
                    }
                } else if (o instanceof HomePageBean) {
                    HomePageBean homePageBean = (HomePageBean) o;
                    if (success.equals(homePageBean.code)) {
                        getView().showAccountInfo(homePageBean);
                    } else {
                        UIUtils.showToastCommon(mContext, homePageBean.msg);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                UIUtils.showToastCommon(mContext, "网络异常，请稍后重试");
            }
        }, mContext), huifuModel.queryRecharge(), accountModel.getHomePage());
    }




    /**
     * 计算取现手续费
     */

    public void getUserCashFee(String transAmt, String cashChl) {
        if (TextUtils.isEmpty(transAmt)||Double.valueOf(transAmt) < 100) {
            return;
        }
        invoke(huifuModel.userCashFee(transAmt, cashChl), new Subscriber<Fee>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Fee fee) {
                if (Config.success.equals(fee.code)) {
                    getView().showFee(fee,cashChl);
                }
            }
        });
    }
    /**
     * 提现
     */
    String ordId;
    public void tocash(String transAmt,String cashChl,String fee,String receiveId){
        invoke(huifuModel.toCash(transAmt,cashChl,fee,receiveId),new ProgressSubscriber<WithDrawBean>(new SubscriberOnNextListener<WithDrawBean>() {
            @Override
            public void onNext(WithDrawBean withDrawBean) {
                if(withDrawBean.code.equals(success)){
                    ordId= withDrawBean.model.InMap.OrdId;
                    getView().pushActvity(1,new Gson().toJson(withDrawBean));
                }else {
                    UIUtils.showToastCommon(mContext,withDrawBean.msg);
                    getView().pushActvity(2,withDrawBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
            }


        },mContext));
    }
    /**
     * 取现是否成功
     */
    public void isWithDrawSucess(){
        invoke(huifuModel.isCashSucess(ordId), new Subscriber<IsCashSuccess>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(IsCashSuccess bBaseBean) {
                getView().pushSucess(bBaseBean);
            }
        });
    }
}
