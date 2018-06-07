package com.haolyy.haolyy.ui.home.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.base.BaseView;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.entity.Notice;
import com.haolyy.haolyy.entity.my.MessageBean;
import com.haolyy.haolyy.model.ProductModel;
import com.haolyy.haolyy.model.WymanModel;
import com.haolyy.haolyy.ui.home.View.MessageView;
import com.haolyy.haolyy.ui.home.View.NoticeView;
import com.haolyy.haolyy.utils.AppToast;
import com.haolyy.haolyy.utils.ToastAlone;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import static com.haolyy.haolyy.config.Config.success;


/**
 * @author wangyin
 * @date 2017/10/27.
 * @description :
 */

public class NoticePresenter extends BasePresenter<NoticeView> {
    public NoticePresenter(Context context) {
        super(context);
    }

    public void getNotice(String pageNUm,String pageSize,boolean isLoadMore) {
        invoke(ProductModel.getInstance().getNotice(pageNUm,pageSize),new ProgressSubscriber<>(new SubscriberOnNextListener<Notice>() {
            @Override
            public void onNext(Notice notice) {
                if (success.equals(notice.getCode())) {
                    if (isLoadMore) {
                        getView().getMessageMore(notice);
                    } else {
                        getView().getMessage(notice);
                    }

                }else {
                    AppToast.showShortText(mContext, notice.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastAlone.showShortToast(mContext, Config.TIP_NET_ERROR);
            }
        },mContext));
    }
}
