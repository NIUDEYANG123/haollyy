package com.haolyy.haolyy.ui.third.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.ActivityCollector;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.entity.bank.OpenAccountBean;
import com.haolyy.haolyy.entity.bank.QueryBankBean;
import com.haolyy.haolyy.entity.bank.SmsCodeBean;
import com.haolyy.haolyy.entity.userinfo.GetUserStatusBean;
import com.haolyy.haolyy.model.HuifuModel;
import com.haolyy.haolyy.ui.third.OpenBankActivity;
import com.haolyy.haolyy.ui.third.view.OpenBankView;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import static com.haolyy.haolyy.config.Config.success;


/**
 * Created by niudeyang on 2017/8/11.
 */

public class OpenBankPresenter extends BasePresenter<OpenBankView> {

    private final HuifuModel huifuModel;

    public OpenBankPresenter(Context context) {
        super(context);
        huifuModel = HuifuModel.getInstance();
    }

    @Override
    public void overwriteSelectUserState(GetUserStatusBean u) {
        super.overwriteSelectUserState(u);
        if (u.model.userStatus.openAccountFlag.equals("1")) {
            getView().pushSucessActivity(true);
        } else {
            getView().pushSucessActivity(false);
        }
    }



    public void open(String realName, String idno, String bankno, String bankCardNo, String smscode, String smsSeq, final String mobile) {
        invoke(huifuModel.openAccount(realName, idno, bankno, bankCardNo, smscode, smsSeq, mobile), new ProgressSubscriber<OpenAccountBean>(new SubscriberOnNextListener<OpenAccountBean>() {
            @Override
            public void onNext(OpenAccountBean openAccountBean) {
                if (openAccountBean.code.equals(success)) {
                    if (null == openAccountBean.model.InMap) {
                        UIUtils.showToastCommon(mContext, "您已开通存管!");
                    } else if(openAccountBean.msg.equals("用户开户请求汇付中")){
                        getView().showOpenWaitDialog();
                    }else {
                        getView().pushActivity(openAccountBean);
                    }
                } else if(openAccountBean.code.equals("user_already_in_account")){
                    getView().showOpenWaitDialog();
                }else {
                    UIUtils.showToastCommon(mContext, openAccountBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {

            }


        }, mContext));
    }


}
