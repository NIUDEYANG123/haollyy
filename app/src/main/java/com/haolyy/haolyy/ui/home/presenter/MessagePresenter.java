package com.haolyy.haolyy.ui.home.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.entity.my.MessageBean;
import com.haolyy.haolyy.model.WymanModel;
import com.haolyy.haolyy.ui.home.MessageDetail;
import com.haolyy.haolyy.ui.home.View.MessageView;
import com.haolyy.haolyy.utils.AppToast;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import rx.Subscriber;

import static com.haolyy.haolyy.config.Config.success;


/**
 * @author wangyin
 * @date 2017/10/27.
 * @description :
 */

public class MessagePresenter extends BasePresenter<MessageView> {
    public MessagePresenter(Context context) {
        super(context);
    }

    public void getMessage(String pageNum) {
        invoke(WymanModel.getInstance().getMessage(pageNum), new ProgressSubscriber<MessageBean>(new SubscriberOnNextListener<MessageBean>() {
            @Override
            public void onNext(MessageBean baseBean) {
                if (
                        success.equals(baseBean.getCode())) {
                    getView().getMessage(baseBean);
                } else {
                    AppToast.showShortText(mContext, baseBean.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                AppToast.showShortText(mContext, "网络异常，请稍后重试");
            }
        }, mContext));
    }

    public void getMessageMore(String router, String pageNum, String pageSize) {
        invoke(WymanModel.getInstance().getMessage(pageNum), new ProgressSubscriber<MessageBean>(new SubscriberOnNextListener<MessageBean>() {
            @Override
            public void onNext(MessageBean baseBean) {
                if (success.equals(baseBean.getCode())) {
                    getView().getMessageMore(baseBean);
                } else {
                    AppToast.showShortText(mContext, baseBean.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                AppToast.showShortText(mContext, "网络异常，请稍后重试");
            }
        }, mContext));
    }

    public void updateUserNews(String router, String getNews_id, final String opr_type, final int position) {
        invoke(WymanModel.getInstance().modificationStatus(getNews_id, opr_type), new ProgressSubscriber<BaseBean>(new SubscriberOnNextListener<BaseBean>() {
            @Override
            public void onNext(BaseBean baseBean) {
                if (success.equals(baseBean.msg)) {
                    switch (opr_type) {
                        case "1":
                            //更新
                            getView().updateMessage(opr_type, position);
                            break;
                        case "2":
                            //删去
                            getView().updateMessage(opr_type, position);
                            break;
                        case "3":
                            //清空
                            getView().updateMessage(opr_type, position);
                            break;
                    }

                } else {

                    AppToast.showShortText(mContext, baseBean.msg);

                }
            }

            @Override
            public void onError(Throwable e) {
                AppToast.showShortText(mContext, "网络异常，请稍后重试");
            }
        }, mContext));
    }
}
