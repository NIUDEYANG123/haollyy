package com.haolyy.haolyy.ui.my.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.entity.ReturnedBean;
import com.haolyy.haolyy.model.ProductModel;
import com.haolyy.haolyy.ui.my.View.ReturnedAdvanceView;
import com.haolyy.haolyy.utils.ToastAlone;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import static com.haolyy.haolyy.config.Config.success;

/**
 * @author wyman
 * @date 2018/3/5
 * description :
 */

public class ReturnedAdvancePersenter extends BasePresenter<ReturnedAdvanceView> {

    public ReturnedAdvancePersenter(Context context) {
        super(context);
    }

    public void queryBillInvestAdvance(String pageIndex) {
            invoke(ProductModel.getInstance().queryBillInvestAdvance(pageIndex),new ProgressSubscriber<>(new SubscriberOnNextListener<ReturnedBean>() {
                @Override
                public void onNext(ReturnedBean returnedBean) {
                    if (returnedBean.getCode().equals(success)) {
                        getView().showData(returnedBean);
                    } else {
                        ToastAlone.showShortToast(mContext, returnedBean.getMsg());
                    }
                }

                @Override
                public void onError(Throwable e) {
                }
            },mContext));
    }
}
