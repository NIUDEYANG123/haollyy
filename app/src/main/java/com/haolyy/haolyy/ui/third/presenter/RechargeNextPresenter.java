package com.haolyy.haolyy.ui.third.presenter;

import android.content.Context;
import android.widget.TextView;

import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.entity.bank.RechargeBean;
import com.haolyy.haolyy.entity.bank.RechargeResultBean;
import com.haolyy.haolyy.entity.bank.SmsCodeBean;
import com.haolyy.haolyy.model.HuifuModel;
import com.haolyy.haolyy.ui.third.view.RechargeView;
import com.haolyy.haolyy.utils.AppToast;
import com.haolyy.haolyy.utils.DateUtil;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import static com.haolyy.haolyy.config.Config.success;




public class RechargeNextPresenter extends BasePresenter<RechargeView> {
   private HuifuModel huifuModel;

    public RechargeNextPresenter(Context context) {
        super(context);
        huifuModel = HuifuModel.getInstance();
    }




    /**
     * 充值
     */
    public void recharge(String trans_amt_,String rechargeFee, String sms_seq_, String sms_code_,String bankno,String bankcode) {
        invoke(huifuModel.recharge(trans_amt_, rechargeFee, sms_seq_, sms_code_, bankno, bankcode),new ProgressSubscriber<RechargeBean>(new SubscriberOnNextListener<RechargeBean>() {
            @Override
            public void onNext(RechargeBean rechargeBean) {
                if(rechargeBean.code.equals(success)){
                    getView().pushActivity(1,"");
                }else if (rechargeBean.code.equals("processing")){
                    //充值结果查询
                    rechargeResult(rechargeBean.model.OutMap.orderNo);
                }else {
                    getView().pushActivity(2,rechargeBean.msg);
                }
            }
            @Override
            public void onError(Throwable e) {

            }


        },mContext));
    }
    /**
     * 充值结果查询
     */
    public void rechargeResult(String orderNo){
        invoke(huifuModel.queryRechargeResult(orderNo),new ProgressSubscriber<RechargeResultBean>(new SubscriberOnNextListener<RechargeResultBean>() {
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(RechargeResultBean baseBean) {
                if ("4".equals(baseBean.model.orderStatus)){
                    getView().qrResult(orderNo);
                }else if ("2".equals(baseBean.model.orderStatus)){
                    getView().pushActivity(1,"");
                }else {
                    getView().pushActivity(2,baseBean.model.orderRemark);
                }
            }

        },mContext));
    }
}
