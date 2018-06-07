package com.haolyy.haolyy.ui.third.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.entity.bank.QueryBankBean;
import com.haolyy.haolyy.entity.bank.SmsCodeBean;
import com.haolyy.haolyy.model.HuifuModel;
import com.haolyy.haolyy.ui.third.view.BindCardView;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import rx.Subscriber;

import static com.haolyy.haolyy.config.Config.success;


/**
 * Created by niudeyang on 2017/9/14.
 */

public class BindCardPresenter extends BasePresenter<BindCardView> {

    private final HuifuModel huifuModel;

    public BindCardPresenter(Context context) {
        super(context);
        huifuModel = HuifuModel.getInstance();
    }




    public void rebind(String phoneNum,String bankId,String openAcctId,String usrMp,String smsCode,String smsSeq,String orgSmsCode,String orgSmsSeq){
        invoke(huifuModel.rebind(phoneNum,bankId,openAcctId,usrMp,smsCode,smsSeq,orgSmsCode,orgSmsSeq),new ProgressSubscriber<BaseBean>(new SubscriberOnNextListener<BaseBean>() {
            @Override
            public void onNext(BaseBean bBaseBean) {
                 if(bBaseBean.code.equals(success)){
                     UIUtils.showToastCommon(mContext,bBaseBean.msg);
                     getView().sucess();
                 }else {
                     UIUtils.showToastCommon(mContext,bBaseBean.msg);
                 }
            }

            @Override
            public void onError(Throwable e) {

            }


        },mContext));
    }

}
