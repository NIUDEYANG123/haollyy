

package com.haolyy.haolyy;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.gyf.barlibrary.ImmersionBar;
import com.haolyy.haolyy.base.ActivityCollector;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.config.ConstantKey;
import com.haolyy.haolyy.entity.IsUpdateBean;
import com.haolyy.haolyy.guesture.GesturePwdLoginActivity;
import com.haolyy.haolyy.model.UserModel;
import com.haolyy.haolyy.ui.account.CheckUsernameActivity;
import com.haolyy.haolyy.ui.account.LoginActivity;
import com.haolyy.haolyy.utils.LockPatternUtils;
import com.haolyy.haolyy.utils.SPUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.UpdateService;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import rx.functions.Action1;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;
import static com.haolyy.haolyy.config.Config.success;


public class SplashActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = this;
        ActivityCollector.addActivity(this);
        ImmersionBar immersionBar = ImmersionBar.with(this);
        immersionBar.init();
        //isUpdate();
        nextActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 检测更新
     */
    public void isUpdate() {
        UserModel.getInstance().isUpdate(new ProgressSubscriber<IsUpdateBean>(new SubscriberOnNextListener<IsUpdateBean>() {
            @Override
            public void onNext(IsUpdateBean baseBean) {
                if (success.equals(baseBean.code)) {
                    if ("1".equals(baseBean.model.state)) {
                        updateData(baseBean);
                    } else {
                        nextActivity();
                    }
                } else {
                    nextActivity();
                }

            }

            @Override
            public void onError(Throwable e) {
                nextActivity();
                //UIUtils.showToastCommon(mContext, "获取最新版本失败，请检测网络是否正常");
            }


        }, mContext));
    }

    public void updateData(IsUpdateBean baseBean) {
        if ("1".equals(baseBean.model.verStatus) || "0".equals(baseBean.model.verStatus)) {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            //dialog.setTitle("版本更新");
            dialog.setMessage(baseBean.model.updateDesc);
            dialog.setCancelable(false);
            dialog.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //下载需要写SD卡权限, targetSdkVersion>=23 需要动态申请权限
                    new RxPermissions(SplashActivity.this)
                            // 申请权限
                            .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(granted -> {
                                if (granted) {
                                    // 请求成功，开启服务，下载文件
                                    Intent intent = new Intent(SplashActivity.this, UpdateService.class);
                                    intent.putExtra("url", baseBean.model.downloadUrl);
                                    startService(intent);
                                    // 普通更新，还是会进入主页
                                    if ("0".equals(baseBean.model.verStatus)) {
                                        nextActivity();
                                    }
                                } else {
                                    // 用户拒绝权限
                                    UIUtils.showToastCommon(mContext, "获取文件权限失败");
                                    dialog.show();
                                }
                            });
                }
            });
            if ("0".equals(baseBean.model.verStatus)) {
                dialog.setNegativeButton("取消", (dialogInterface, i) -> {
                    // 非强制更新，进入主页
                    BaseApplication.noUpdate = true;
                    nextActivity();
                });
            }
            dialog.setOnKeyListener((dialogInterface, i, keyEvent) -> {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    finish();
                }
                return false;
            });
            dialog.show();
        } else if ("2".equals(baseBean.model.verStatus)) {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage(baseBean.model.updateDesc);
            dialog.setCancelable(false);
            dialog.show();
        } else {
            nextActivity();
        }

    }

    public void nextActivity() {
        BaseApplication.noIsApp = true;
        new Handler().postDelayed(() -> {
            if (!TextUtils.isEmpty(LockPatternUtils.getLockPattern(mContext, ConstantKey.GESTURE_STATE_KEY))) {
                //设置过手势密码
                BaseApplication.mLoginState = SPUtils.getBoolean(mContext, ConstantKey.LOGIN_STATE, false);
                BaseApplication.exist = SPUtils.getBoolean(mContext, ConstantKey.EXISTS, false);
                BaseApplication.userId = SPUtils.getInt(mContext, ConstantKey.USER_ID, -1);
                BaseApplication.mUserName = SPUtils.getString(mContext, ConstantKey.USER_NAME, "");
                BaseApplication.juid = SPUtils.getString(mContext, ConstantKey.JUID, "");
                BaseApplication.juidMd5 = SPUtils.getString(mContext, ConstantKey.JUID_MD5, "");
                BaseApplication.accessToken = SPUtils.getString(mContext, ConstantKey.NEW_TOKEN_A, "");
                BaseApplication.refreshToken = SPUtils.getString(mContext, ConstantKey.NEW_TOKEN_R, "");
                startActivity(new Intent(mContext, GesturePwdLoginActivity.class));
            } else {
                //startActivity(new Intent(mContext, CheckUsernameActivity.class));

                if (SPUtils.getBoolean(mContext, ConstantKey.LOGIN_STATE, false)) {
                    BaseApplication.mLoginState = SPUtils.getBoolean(mContext, ConstantKey.LOGIN_STATE, false);
                    BaseApplication.exist = SPUtils.getBoolean(mContext, ConstantKey.EXISTS, false);
                    BaseApplication.userId = SPUtils.getInt(mContext, ConstantKey.USER_ID, -1);
                    BaseApplication.mUserName = SPUtils.getString(mContext, ConstantKey.USER_NAME, "");
                    BaseApplication.juid = SPUtils.getString(mContext, ConstantKey.JUID, "");
                    BaseApplication.juidMd5 = SPUtils.getString(mContext, ConstantKey.JUID_MD5, "");
                    BaseApplication.accessToken = SPUtils.getString(mContext, ConstantKey.NEW_TOKEN_A, "");
                    BaseApplication.refreshToken = SPUtils.getString(mContext, ConstantKey.NEW_TOKEN_R, "");
                }

                startActivity(new Intent(mContext, MainActivity.class));
            }
            finish();
        }, 1500);
    }
}

