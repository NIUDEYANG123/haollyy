package com.haolyy.haolyy.inteface;

import android.app.Activity;
import android.content.Context;

import com.haolyy.haolyy.base.WebActivity;
import com.haolyy.haolyy.utils.LogUtils;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by shanghai on 2018/4/17.
 */

public class MyUMShareListener implements UMShareListener {
    private Activity context;
    private UMShareAPI um;

    public MyUMShareListener(Activity context, UMShareAPI umShareAPI) {
        this.um=umShareAPI;
        this.context =context;
    }

    /**
     * @descrption 分享开始的回调
     * @param platform 平台类型
     */
    @Override
    public void onStart(SHARE_MEDIA platform) {
        LogUtils.e("shareStart", platform.getName());
    }

    /**
     * @descrption 分享成功的回调
     * @param platform 平台类型
     */
    @Override
    public void onResult(SHARE_MEDIA platform) {
        LogUtils.e("success", platform.getName());

    }

    /**
     * @descrption 分享失败的回调
     * @param platform 平台类型
     * @param t 错误原因
     */
    @Override
    public void onError(SHARE_MEDIA platform, Throwable t) {
        LogUtils.e(platform.getName(), t.toString());
        um.isInstall(context, SHARE_MEDIA.WEIXIN);//判断是否安装微信或者其他
    }

    /**
     * @descrption 分享取消的回调
     * @param platform 平台类型
     */
    @Override
    public void onCancel(SHARE_MEDIA platform) {
        LogUtils.e("shareCancel", platform.getName());
    }
}
