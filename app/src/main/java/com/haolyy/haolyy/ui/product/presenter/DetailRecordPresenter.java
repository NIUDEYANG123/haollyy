package com.haolyy.haolyy.ui.product.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.entity.product.InvestRecordBean;
import com.haolyy.haolyy.entity.product.RepayPlanBean;
import com.haolyy.haolyy.entity.product.SanRepayPlanBean;
import com.haolyy.haolyy.model.ProductModel;
import com.haolyy.haolyy.ui.product.View.DetailActivityView;
import com.haolyy.haolyy.ui.product.View.DetailRecordView;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.UIUtils;

import rx.Observable;
import rx.Subscriber;

import static com.haolyy.haolyy.config.Config.success;

/**
 * Created by shanghai on 2018/3/1.
 */

public class DetailRecordPresenter extends BasePresenter<DetailRecordView> {
    public DetailRecordPresenter(Context context) {
        super(context);
    }

    public void getRepayPlan(String borrowNo, String pageIndex, String pageSize){
      invoke(ProductModel.getInstance().repayPlan(borrowNo, pageIndex, pageSize), new Subscriber<RepayPlanBean>() {
          @Override
          public void onCompleted() {

          }

          @Override
          public void onError(Throwable e) {
              LogUtils.e("RepayFragment",e.toString());
          }

          @Override
          public void onNext(RepayPlanBean repayPlanBean) {
              if(repayPlanBean.getCode().equals(success)){
                  getView().showRepayPlan(repayPlanBean);
              }else {
                  UIUtils.showToastCommon(mContext,repayPlanBean.getMsg());
              }
          }
      });
    }
    /*public void getRepayPlan(String borrowNo){
        invoke(ProductModel.getInstance().getSanrepayPlan(borrowNo), new Subscriber<SanRepayPlanBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("RepayFragment",e.toString());
            }

            @Override
            public void onNext(SanRepayPlanBean repayPlanBean) {
                if(repayPlanBean.getCode().equals(success)){
                    getView().showRepayPlan(repayPlanBean);
                }else {
                    UIUtils.showToastCommon(mContext,repayPlanBean.getMsg());
                }

            }
        });
    }*/
    public void getInvestRecord(boolean isRefresh,String borrowNo, String pageIndex){
        invoke(ProductModel.getInstance().getInvestRecord(borrowNo, pageIndex), new Subscriber<InvestRecordBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(tag,e.toString());
            }

            @Override
            public void onNext(InvestRecordBean investRecordBean) {
                if(investRecordBean.getCode().equals(success)){
                    if(isRefresh){
                        getView().getInvestRecord(investRecordBean);
                    }else {
                        getView().getMoreInvestRecord(investRecordBean);
                    }
                }else {
                    UIUtils.showToastCommon(mContext,investRecordBean.getMsg());
                }


            }
        });
    }

}
