package com.haolyy.haolyy.ui.my.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.entity.userinfo.RiskBean;
import com.haolyy.haolyy.model.AccountModel;
import com.haolyy.haolyy.ui.my.View.RiskResultView;
import com.haolyy.haolyy.utils.UIUtils;

import rx.Subscriber;

/**
 * Created by shanghai on 2018/3/16.
 */

public class RiskResultPresenter extends BasePresenter<RiskResultView> {
    public RiskResultPresenter(Context context) {
        super(context);
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
                    int amount=0;
                    if (socre <= 20) {
                        s = "保守型";
                        amount=0;
                    } else if (socre <= 40) {
                        s = "谨慎型";
                        amount=100;
                    } else if (socre <= 60) {
                        s = "稳健型";
                        amount=300;
                    } else if (socre <= 80) {
                        s = "进取型";
                        amount=500;
                    } else {
                        s = "激进型";
                        amount=-1;
                    }
                    getView().showRiskLevel(s,amount);
                } else {
                    UIUtils.showToastCommon(mContext, riskBean.msg);
                }
            }
        });
    }
}
