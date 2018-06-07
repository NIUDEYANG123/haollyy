package com.haolyy.haolyy.ui.home.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.entity.Notice;
import com.haolyy.haolyy.entity.product.HomeBannerBean;
import com.haolyy.haolyy.entity.product.HomeListBean;
import com.haolyy.haolyy.model.ProductModel;
import com.haolyy.haolyy.ui.home.View.HomeView;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import rx.Subscriber;

import static com.haolyy.haolyy.config.Config.success;


/**
 * Created by niudeyang on 2017/8/8.
 */

public class HomePresenter extends BasePresenter<HomeView> {
    public HomePresenter(Context context) {
        super(context);
    }


    public void fetch(String pageNUm, String pageSize) {
        invokeMerge(
                new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        if (o instanceof HomeListBean) {
                            HomeListBean homeListBean = (HomeListBean) o;
                            if (success.equals(homeListBean.getCode())) {
                                getView().showHomeList(homeListBean);
                            } else {
                                UIUtils.showToastCommon(mContext, homeListBean.getMsg());
                            }
                        } else if (o instanceof HomeBannerBean) {
                            HomeBannerBean homeBannerBean = (HomeBannerBean) o;
                            if (success.equals(homeBannerBean.code)) {
                                getView().showBannerData(homeBannerBean);
                            } else {
                                UIUtils.showToastCommon(mContext, homeBannerBean.msg);
                            }
                        } else if (o instanceof Notice) {
                            Notice notice = (Notice) o;
                            if (success.equals(notice.getCode())) {
                                getView().showNotice(notice);
                            } else {
                                UIUtils.showToastCommon(mContext, notice.getMsg());
                            }
                        }
                    }
                }
                , ProductModel.getInstance().gethomebanner(), ProductModel.getInstance().queryList(), ProductModel.getInstance().getNotice(pageNUm, pageSize));
    }

}
