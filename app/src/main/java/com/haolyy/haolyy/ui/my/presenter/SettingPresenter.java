package com.haolyy.haolyy.ui.my.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.entity.BBaseBean;
import com.haolyy.haolyy.entity.bank.AutoTenderBean;
import com.haolyy.haolyy.entity.bank.QueryBankBean;
import com.haolyy.haolyy.entity.bank.QueryCardInfoBean;
import com.haolyy.haolyy.entity.userinfo.RiskBean;
import com.haolyy.haolyy.model.AccountModel;
import com.haolyy.haolyy.model.HuifuModel;
import com.haolyy.haolyy.ui.my.View.SettingView;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import rx.Subscriber;

import static com.haolyy.haolyy.config.Config.success;

/**
 * Created by shanghai on 2018/3/6.
 */

public class SettingPresenter extends BasePresenter<SettingView> {
    public SettingPresenter(Context context) {
        super(context);
    }

    public void getCardInfo() {
        invoke(AccountModel.getInstance().queryCardInfo(), new Subscriber<QueryCardInfoBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(tag, e.toString());
            }

            @Override
            public void onNext(QueryCardInfoBean queryCardInfoBean) {
                if (queryCardInfoBean.code.equals(success)) {
                    getView().showName(queryCardInfoBean);
                }
            }
        });
    }

    public void getAutoTenderPlan(final String openFlag) {
        invoke(HuifuModel.getInstance().autoTenderPlan(openFlag), new ProgressSubscriber<AutoTenderBean>(new SubscriberOnNextListener<AutoTenderBean>() {
            @Override
            public void onNext(AutoTenderBean autoTenderBean) {
                if (Config.success.equals(autoTenderBean.code)) {
                    getView().pushActivity(new Gson().toJson(autoTenderBean));
                } else {
                    UIUtils.showToastCommon(mContext, autoTenderBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }, mContext));
    }

    public void queryTenderPlan() {
        invoke(HuifuModel.getInstance().queryTenderPlan(), new ProgressSubscriber<BaseBean>(new SubscriberOnNextListener<BaseBean>() {
            @Override
            public void onNext(BaseBean baseBean) {
                UIUtils.showToastCommon(mContext, baseBean.msg);
                selectUserState();
            }

            @Override
            public void onError(Throwable e) {

            }
        }, mContext));
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
                if (Config.success.equals(queryBankBean.code)) {
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
                if (Config.success.equals(baseBean.code)) {
                    getView().pushActivity(new Gson().toJson(baseBean));
                } else {

                }
            }
        });
    }

    public void getRiskLevel() {
        invoke(AccountModel.getInstance().getRiskBean(), new Subscriber<RiskBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(RiskBean riskBean) {
                if (Config.success.equals(riskBean.code)) {
                    int socre = Integer.parseInt(riskBean.model.userScore);
                    String s = "立即评估";
                    if (socre <= 20) {
                        s = "保守型";
                    } else if (socre <= 40) {
                        s = "谨慎型";
                    } else if (socre <= 60) {
                        s = "稳健型";
                    } else if (socre <= 80) {
                        s = "进取型";
                    } else {
                        s = "激进型";
                    }
                    getView().showRiskLevel(s);
                } else {
                    UIUtils.showToastCommon(mContext, riskBean.msg);
                }
            }
        });
    }
    public void queryBankInfo() {
        invoke(HuifuModel.getInstance().queryRecharge(),new ProgressSubscriber<QueryBankBean>(new SubscriberOnNextListener<QueryBankBean>() {
            @Override
            public void onNext(QueryBankBean queryBankBean) {
                if (success.equals(queryBankBean.code)){
                    getView().showBank(queryBankBean);

                }else {
                    UIUtils.showToastCommon(mContext,queryBankBean.msg);
                }
            }
            @Override
            public void onError(Throwable e) {
                UIUtils.showToastCommon(mContext,"网络异常，请稍后重试");
            }


        },mContext));
    }

    /**
     * 查询修改手机号是否成功
     */
    public void queryModifyTheBankPhone(String userCode,String orderNo) {
        invoke(HuifuModel.getInstance().queryModifyTheBankPhone(userCode, orderNo),new ProgressSubscriber<BaseBean>(new SubscriberOnNextListener<BaseBean>() {
            @Override
            public void onNext(BaseBean baseBean) {
                getView().selectModifyBankPhone(baseBean);
            }
            @Override
            public void onError(Throwable e) {
                UIUtils.showToastCommon(mContext,"网络异常，请稍后重试");
            }


        },mContext));
    }
}
