package com.haolyy.haolyy.ui.my.presenter;

import android.content.Context;

import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.model.UserModel;
import com.haolyy.haolyy.ui.my.View.ResetPassView;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import static com.haolyy.haolyy.config.Config.success;

/**
 * Created by niudeyang on 2017/9/14.
 */

public class ResetPassPresenter extends BasePresenter<ResetPassView> {
    public ResetPassPresenter(Context context) {
        super(context);
    }

  public void changePass(String oldPassword,String newPassword,String newPasswordConfirm){
      invoke(UserModel.getInstance().changePass(oldPassword,newPassword,newPasswordConfirm),new ProgressSubscriber<BaseBean>(new SubscriberOnNextListener<BaseBean>() {
          @Override
          public void onNext(BaseBean baseBean) {
              if(baseBean.code.equals(success)){
                  UIUtils.showToastCommon(mContext,"密码修改成功");
                  getView().close();
              }else {
                  UIUtils.showToastCommon(mContext,baseBean.msg);
              }
          }

          @Override
          public void onError(Throwable e) {

          }
      },mContext));
  }
}
