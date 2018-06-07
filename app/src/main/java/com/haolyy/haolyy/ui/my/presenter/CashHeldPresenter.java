package com.haolyy.haolyy.ui.my.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.base.WebActivity;
import com.haolyy.haolyy.entity.BBaseBean;
import com.haolyy.haolyy.entity.userinfo.CashHeldBean;
import com.haolyy.haolyy.entity.userinfo.CashHeldReord;
import com.haolyy.haolyy.model.AccountModel;
import com.haolyy.haolyy.ui.my.View.CashHeldView;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import static com.haolyy.haolyy.config.Config.success;


/**
 * Created by liliang on 2018/1/12.
 */

public class CashHeldPresenter extends BasePresenter<CashHeldView> {
    public CashHeldPresenter(Context context){
        super(context);
    }
    /**
     *  预约标 （当前持有债权）
     * @param cashNo
     */
    public void getCashHeld(boolean resfreh,String cashNo,String rows,String start){
        invoke(AccountModel.getInstance().cashHeld(cashNo, rows, start),new ProgressSubscriber<CashHeldBean>(new SubscriberOnNextListener<CashHeldBean>() {
            @Override
            public void onNext(CashHeldBean bBaseBean) {
                if (bBaseBean.code.equals(success)) {
                    if (resfreh){
                        getView().getData(bBaseBean);
                    }else {
                        getView().getDataMore(bBaseBean);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                getView().noNetwork();
            }
        },mContext));
    }


    /**
     * 合同
     * @param applyNo
     */
    public void getBaoquan(String applyNo) {
        invoke(AccountModel.getInstance().getBaoquanContarct(applyNo), new ProgressSubscriber<BBaseBean>(new SubscriberOnNextListener<BBaseBean>() {
            @Override
            public void onNext(BBaseBean bBaseBean) {
                if (bBaseBean.code.equals(success)) {
                    mContext.startActivity(WebActivity.getWebIntent(mContext, "合同协议", bBaseBean.model));
                }else {
                    UIUtils.showToastCommon(mContext,"合同生成中");
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }, mContext));
    }

    /**
     * 合同
     * @param investOrderNo
     */
    public void getLocalContract(String investOrderNo,String debtNo, String cashNo) {
        invoke(AccountModel.getInstance().getLocalContract(investOrderNo,debtNo,cashNo), new ProgressSubscriber<BBaseBean>(new SubscriberOnNextListener<BBaseBean>() {
            @Override
            public void onNext(BBaseBean bBaseBean) {
                if (bBaseBean.code.equals(success)) {
                    mContext.startActivity(WebActivity.getWebIntentContent(mContext, "合同协议", bBaseBean.model));
                }else {
                    UIUtils.showToastCommon(mContext,"合同生成中");
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }, mContext));
    }
}
