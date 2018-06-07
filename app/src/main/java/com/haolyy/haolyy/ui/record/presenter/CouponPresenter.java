package com.haolyy.haolyy.ui.record.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.entity.userinfo.InvestCouponBean;
import com.haolyy.haolyy.model.AccountModel;
import com.haolyy.haolyy.ui.record.view.CouponView;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.UIUtils;

import rx.Subscriber;

import static com.haolyy.haolyy.config.Config.success;


/**
 * Created by niudeyang on 2017/10/31.
 */

public class CouponPresenter extends BasePresenter<CouponView> {
    public CouponPresenter(Context context) {
        super(context);
    }
    /**
     *  投资选择卡卷
     * @param page
     * @param limit
     * @param investAmount
     * @param periodLength
     * @param periodUnit
     * @param type
     * @param productNo
     */
    public void queryInvestCoupon2(boolean resfreh,String page, String limit, String investAmount, String periodLength, String periodUnit, String type,String productNo) {
        invoke(AccountModel.getInstance().queryInvestCoupon(page, limit, investAmount, periodLength, periodUnit, type,productNo), new Subscriber<InvestCouponBean>() {
            @Override
            public void onCompleted() {

            }
            @Override
            public void onError(Throwable e) {
                LogUtils.e(tag, e.getMessage());
                getView().noNetwork();
            }
            @Override
            public void onNext(InvestCouponBean investCouponBean) {
                if (investCouponBean.getCode().equals(success)) {
                    if(resfreh){
                        getView().getData(investCouponBean);
                    }else {
                        getView().getDataMore(investCouponBean);
                    }
                }else {
                    UIUtils.showToastCommon(mContext, investCouponBean.getMsg());
                }

            }
        });


    }
}
