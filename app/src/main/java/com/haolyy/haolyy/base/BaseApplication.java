package com.haolyy.haolyy.base;


import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.haolyy.haolyy.BuildConfig;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.WYUtils;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.PlatformConfig;

import java.util.Map;


/**
 * 主工程
 * Created by ndy on 2016/05/19.
 */
public class BaseApplication extends Application {
    /**
     * 全局Context，原理是因为Application类是应用最先运行的，所以在我们的代码调用时，该值已经被赋值过了
     */
    public static BaseApplication mInstance;


    public static boolean mLoginState;//是否登录
    public static int userId = -1;
    public static String mUserName = "";
    public static String phone = "";

    public static Context context;
    public static String accessToken = "";
    public static String refreshToken = "";
    public static String version;//版本号
    public static String juid = "";//平台客户号  //userCode
    public static String orderNo = "";//修改预留手机订单号
    public static String juidMd5 = "";//加密后的平台客户号
    public static String userCustId = "";//第三方客户号
    public static int step;//开户状态
    public static boolean signstatus;//签约状态
    public static boolean noUpdate;//更新取消按钮
    public static boolean noIsApp;//更新是否是app再次进入
    public static String sms_seq_;
    public static int riskCheck;//风险测评是否 1 0
    public static boolean exist;//是否新用户 true老用户  false新用户
    public static long remainTime;

    //5950d938310c935ca0000c81
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        mInstance = this;
        //获取版本号
        version = WYUtils.getVersion(getApplicationContext());

        //是否打印日志
        LogUtils.isDebug = BuildConfig.IS_SHOW_LOG;
        com.umeng.socialize.Config.DEBUG = BuildConfig.IS_SHOW_LOG;
        initUmeng();
        //SophixManager.getInstance().queryAndLoadNewPatch(); // 加载新的补丁包
    }


    public static Context getContext() {
        return context;
    }

    private void initUmeng() {
        UMConfigure.init(this, "5950d938310c935ca0000c81", "UMENG", UMConfigure.DEVICE_TYPE_PHONE, "a7de11990631404cddb40157434134fa");
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setCatchUncaughtExceptions(true);
        //MobclickAgent.setDebugMode(true);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

        PlatformConfig.setWeixin("wxda4611becd3fe120", "d4624c36b6795d1d99dcf0547af5443d");
        // PlatformConfig.setQQZone("1103450099", "x2V4mgHbVgz0gnvp");
        PlatformConfig.setQQZone("1105466293", "oQSginV468YM1yg5");
        PlatformConfig.setSinaWeibo("321833617", "24f2719b230d6bcd95399dcc763d9cf4", Config.returl);

        initPush();
    }

    private void initPush() {
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //sdk开启通知声音
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
        // sdk关闭通知声音
//		mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
        // 通知声音由服务端控制
//		mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER);

        mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
        mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);


        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            /**
             * 自定义消息的回调方法,是指发送后不会在系统通知栏展现
             */
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {
                for (Map.Entry<String, String> entry : msg.extra.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                }
                LogUtils.e("BaseApplication", "不在通知栏里展现");
            }

            /**
             * 自定义通知栏样式的回调方法
             */
            @Override
            public Notification getNotification(Context context, UMessage msg) {
                switch (msg.builder_id) {//通知栏样式编号
                    case 1:
                        Notification.Builder builder = new Notification.Builder(context);
                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(), R.layout.notification_view);
                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
                        //myNotificationView.setImageViewResource(R.id.notification_small_icon, getSmallIconId(context, msg));
                        builder.setContent(myNotificationView)
                                .setSmallIcon(getSmallIconId(context, msg))
                                .setTicker(msg.ticker)
                                .setAutoCancel(true);

                        return builder.getNotification();
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }
        };
        mPushAgent.setMessageHandler(messageHandler);

        /**
         * 在umeng的后台设置
         * 自定义行为的回调处理，参考文档：高级功能-通知的展示及提醒-自定义通知打开动作
         * UmengNotificationClickHandler是在BroadcastReceiver中被调用，故
         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
         * */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
            }
        };
        //使用自定义的NotificationHandler，来结合友盟统计处理消息通知，参考http://bbs.umeng.com/thread-11112-1-1.html
        //CustomNotificationHandler notificationClickHandler = new CustomNotificationHandler();
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
        //注册推送服务 每次调用register都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                LogUtils.e("BaseApplication", "device token: " + deviceToken);
                //AofFtTLM6BO4UOvljBDcHJhshDCovYluiEY8SVqcq7WP
            }

            @Override
            public void onFailure(String s, String s1) {
                LogUtils.e("BaseApplication", "register failed: " + s + " " + s1);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //initSopfix();//与pushSdk冲突导致收不到推送
    }

    /**
     * initialize在attachBaseContext中，query在onCreate中，试试
      Android技术支持_自助提问@云博士
      cleanPatches注释掉
     */
    private void initSopfix() {
        String appVersion = "";
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            appVersion = "4.0.1";
        }
        SophixManager.getInstance().setContext(this).setAppVersion(appVersion).setAesKey(null)
                .setEnableDebug(false)//上线续改为false
                .setPatchLoadStatusStub((mode, code, info, handlePatchVersion) -> {
                    String msg = new StringBuilder("")
                            .append(" Mode:")
                            .append(mode)
                            .append(" Code:")
                            .append(code)
                            .append(" Info:")
                            .append(info)
                            .append(" HandlePatchVersion:")
                            .append(handlePatchVersion).toString();
                    Log.e("SophixManager", "加载补丁msg：" + msg);
                    if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                        // 表明补丁加载ok
                        Log.e("SophixManager", "加载补丁:" + PatchStatus.CODE_LOAD_SUCCESS);
                    } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                        // 表明新补丁生效需要重启，开发者课题室用户或者强制重启
                        // 建议:用户可以监听进入后台事件,然后应用自杀
                        Log.e("SophixManager", "加载补丁:" + PatchStatus.CODE_LOAD_RELAUNCH);
                    } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                        // 内部引擎异常，推荐此时清空本地补丁，防止失败补丁重复加载
                        //SophixManager.getInstance().cleanPatches();
                        Log.e("SophixManager", "加载补丁:" + PatchStatus.CODE_LOAD_FAIL);
                    } else {
                        // 其他错误信息
                        Log.e("SophixManager", "加载补丁:其他");
                    }
                }).initialize();
        //SophixManager.getInstance().queryAndLoadNewPatch(); // 加载新的补丁包
    }

    public static BaseApplication getApplication() {
        return mInstance;
    }


}
