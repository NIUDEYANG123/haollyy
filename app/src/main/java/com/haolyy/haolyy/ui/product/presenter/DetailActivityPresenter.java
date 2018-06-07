package com.haolyy.haolyy.ui.product.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.base.WebActivity;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.entity.BBaseBean;
import com.haolyy.haolyy.entity.bank.AutoTenderBean;
import com.haolyy.haolyy.entity.bank.QueryCardInfoBean;
import com.haolyy.haolyy.entity.product.ProductDetailBean;
import com.haolyy.haolyy.entity.userinfo.GetUserStatusBean;
import com.haolyy.haolyy.entity.userinfo.SignContractbean;
import com.haolyy.haolyy.model.AccountModel;
import com.haolyy.haolyy.model.HuifuModel;
import com.haolyy.haolyy.model.ProductModel;
import com.haolyy.haolyy.ui.product.View.DetailActivityView;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import rx.Subscriber;

import static com.haolyy.haolyy.config.Config.success;


/**
 * Created by niudeyang on 2017/8/30.
 */

public class DetailActivityPresenter extends BasePresenter<DetailActivityView> {

    private final HuifuModel huifuModel;

    public DetailActivityPresenter(Context context) {
        super(context);
        huifuModel = HuifuModel.getInstance();
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
            querySignStatus();
            if (u.model.userStatus.setAutoBuyBidFlag.equals("1")) {
                //已设置主动投标
                BaseApplication.step = 4;
            } else {
                //未设置主动投标
                BaseApplication.step = 3;
            }
        }
    }

    /**
     * 查询签约状态
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
                if (bBaseBean.code.equals(success)) {
                    if (bBaseBean.model.equals("0")) {
                        BaseApplication.signstatus = false;
                    } else {
                        BaseApplication.signstatus = true;
                    }
                    getView().showSign();//按钮显示签署自动授权委托书
                }
            }
        });
    }

    /**
     * 标的详情
     *
     * @param borrowNo
     */
    public void queryPlanDetail(final String borrowNo) {
        invoke(ProductModel.getInstance().productDetail(borrowNo), new ProgressSubscriber<ProductDetailBean>(new SubscriberOnNextListener<ProductDetailBean>() {
            @Override
            public void onNext(ProductDetailBean productDetailBean) {
                if (BaseApplication.mLoginState) {
                    selectUserState();
                }
                if (productDetailBean.code.equals(success)) {
                    getView().showDetail(productDetailBean);
                } else {
                    UIUtils.showToastCommon(mContext, productDetailBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(tag, e.toString());
                getView().noNetwork();
            }
        }, mContext));
    }


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
                    getView().pushActivity(new Gson().toJson(baseBean), 0x81);
                }
            }
        });
    }

    public void queryBank() {
        invoke(AccountModel.getInstance().queryCardInfo(), new Subscriber<QueryCardInfoBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(QueryCardInfoBean queryBankBean) {
                if (Config.success.equals(queryBankBean.code)) {
                    BaseApplication.userCustId = queryBankBean.model.UsrCustId;
                    activate();
                } else {
                    UIUtils.showToastCommon(mContext, queryBankBean.msg);
                }

            }
        });
    }

    /**
     * @param openFlag 1开启，2关闭
     */
    public void getAutoTenderPlan(String openFlag, boolean isAuto) {
        invoke(HuifuModel.getInstance().autoTenderPlan(openFlag), new ProgressSubscriber<AutoTenderBean>(new SubscriberOnNextListener<AutoTenderBean>() {
            @Override
            public void onNext(AutoTenderBean autoTenderBean) {
                if (Config.success.equals(autoTenderBean.code)) {
                    getView().pushActivity(new Gson().toJson(autoTenderBean), 0x82);
                } else {
                    if (isAuto) {
                       //如果是签约成功之后的后续跳转
                        getView().sign2auto();
                    } else {
                        UIUtils.showToastCommon(mContext, autoTenderBean.msg);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }, mContext));
    }

    /**
     * 查询复投是否开启成功，调用此接口更改复投状态
     */
    public void queryTenderPlan() {
        invoke(HuifuModel.getInstance().queryTenderPlan(), new ProgressSubscriber<BaseBean>(new SubscriberOnNextListener<BaseBean>() {
            @Override
            public void onNext(BaseBean baseBean) {
                if (success.equals(baseBean.code)) {
                    UIUtils.showToastCommon(mContext, baseBean.msg);
                    BaseApplication.step = 4;
                    getView().auto2risk();
                } else {
                    UIUtils.showToastCommon(mContext, baseBean.msg);
                    BaseApplication.step = 3;
                }
                //selectUserState();
            }

            @Override
            public void onError(Throwable e) {

            }
        }, mContext));
    }

    /**
     * 签约
     */
    public void signContract() {
        invoke(AccountModel.getInstance().signCOntract(), new ProgressSubscriber<SignContractbean>(new SubscriberOnNextListener<SignContractbean>() {
            @Override
            public void onNext(SignContractbean signContractbean) {
                if (signContractbean.getCode().equals(success)) {
                    //mContext.startActivity(WebActivity.getWebIntent(mContext, "合同签字", signContractbean.getModel().getLink()));
                    getView().sign2auto();

                } else {
                    UIUtils.showToastCommon(mContext, signContractbean.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }, mContext));
    }
}
