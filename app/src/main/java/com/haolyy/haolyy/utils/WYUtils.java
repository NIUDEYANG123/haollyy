package com.haolyy.haolyy.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.gesture.GestureUtils;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.blankj.utilcode.util.*;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.ActivityCollector;
import com.haolyy.haolyy.base.WebActivity;
import com.haolyy.haolyy.config.NetConstantValues;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;


/**
 * Created by wangyin on 2017/5/23.
 */

public class WYUtils {
    //手机号码正则0
    //private static String phoneRex = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
    private static String phoneRex = "^1\\d{10}";
    //密码正则
//    private static String passRex = "^(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[\\-\\/:;()$&@\"\\.,\\?\\!'\\[\\]#%\\^\\*\\+=_\\\\\\|~<>€£¥•：；（）¥@“”。，、？！【】｛｝—《》\\·]+$)[\\da-zA-Z\\-\\/:;()$&@\"\\.,\\?\\!'\\[\\]#%\\^\\*\\+=_\\\\\\|~<>€£¥•：；（）¥@“”。，、？！【】｛｝—《》\\·]{6,20}$";
    private static String passRex = "^[A-Za-z0-9]{6,16}$";
    //身份证正则
    private static String idRex = "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";
    //银行卡正则
    private static String cardRex = "^\\d{16,19}$";
    // 汉字
    private static String chn_characters = "([\\u4e00-\\u9fa5]{2,10})";
    // F码
    private static String fma = "^[a-zA-Z0-9]{6}$";//    /(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{2,})$/


    public static boolean checkPhone(String phone) {
        return phone.matches(phoneRex);
    }

    public static boolean checkPass(String password) {
        return password.matches(passRex);
    }

    public static boolean checkId(String id) {
        return id.matches(idRex);
    }

    public static boolean checkCard(String card) {
        return card.matches(cardRex);
    }

    public static boolean checkChnCharacters(String chnCharacters) {
        return chnCharacters.matches(chn_characters);
    }

    public static boolean checkFm(String fm) {
        return fm.matches(fma);
    }


    /**
     * 把金额字符串转化为数字
     *
     * @param string
     * @return
     */
    public static float processAmount(String string) {
        return Float.valueOf(string.replace(",", ""));
    }

    public static String processAmountString(String string) {
        return string.replace(",", "");
    }

    /**
     * 2      * 判断当前应用是否是debug状态
     * 3
     */
    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 修改tabLayoutIndicator长度
     */
    public static void setIndicator(Context context, TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout ll_tab = null;
        try {
            ll_tab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) (getDisplayMetrics(context).density * UIUtils.dp2px(leftDip, context));
        int right = (int) (getDisplayMetrics(context).density * UIUtils.dp2px(rightDip, context));

        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric;
    }


    /**
     * 保留两位小数 不进行四舍五入
     */
    public static String getTwoNumStr(double num) {
        DecimalFormat df = new DecimalFormat("######0.00");
//        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setGroupingSize(0);
        df.setRoundingMode(RoundingMode.FLOOR);
        return df.format(num);
    }

    /**
     * 保留两位小数 四舍五入
     */
    public static String getTwoNumStrUp(double num) {
        DecimalFormat df = new DecimalFormat("######0.00");
//        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setGroupingSize(0);
        df.setRoundingMode(RoundingMode.UP);
        return df.format(num);

    }

    /**
     * 保留两位小数 不进行四舍五入
     */
    public static String getSingleNumStr(double num) {
        DecimalFormat df = new DecimalFormat("######0.0");
//        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);
        df.setGroupingSize(0);
        df.setRoundingMode(RoundingMode.FLOOR);
        return df.format(num);

    }

    public static String getVersion(Context context)//获取版本号
    {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0";
        }
    }


    /**
     * 投资期限
     */
    public static String getInvestDeadline(int num) {
        String result = "";
        switch (num) {
            case 1:
                result = "天";
                break;
            case 2:
                result = "周";
                break;
            case 3:
                result = "月";
                break;
            case 4:
                result = "年";
                break;
        }
        return result;

    }

    /**
     * 收益处理方式  1：等额本息 2：等额本金 3：按期付息，到期还本 4：一次性还款 99：其他
     */
    public static String getProfitPlan(String profitPlan) {
        String result = "";
        switch (profitPlan) {
            case "1":
                result = "等额本息";
                break;
            case "2":
                result = "等额本金";
                break;
            case "3":
                result = "按期付息，到期还本";
                break;
            case "4":
                result = "一次性还款";
                break;
        }
        return result;
    }

    /**
     * webView加载
     */
    @SuppressLint("SetJavaScriptEnabled")
    public static void loadHtml(final String url, final WebView mWebView, final ProgressBar mProgressBar) {
        LogUtils.e("webViewUrl", url);
        WebSettings settings = mWebView.getSettings();
        /**
         * setAllowFileAccess 启用或禁止WebView访问文件数据 setBlockNetworkImage 是否显示网络图像
         * setBuiltInZoomControls 设置是否支持缩放 setCacheMode 设置缓冲的模式
         * setDefaultFontSize 设置默认的字体大小 setDefaultTextEncodingName 设置在解码时使用的默认编码
         * setFixedFontFamily 设置固定使用的字体 setJavaSciptEnabled 设置是否支持Javascript
         * setLayoutAlgorithm 设置布局方式 setLightTouchEnabled 设置用鼠标激活被选项
         * setSupportZoom 设置是否支持变焦
         * */

        //webview在安卓5.0之前默认允许其加载混合网络协议内容
        // 在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        }
        mWebView.setDrawingCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        // 取消滚动条
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        // 触摸焦点起作用
        mWebView.requestFocus();
        settings.setSavePassword(false);// 不弹窗浏览器是否保存密码
        settings.setDefaultTextEncodingName("utf-8");
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 自动适应屏幕尺寸
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //打开DOM储存
        settings.setDomStorageEnabled(true);
        mWebView.loadUrl(url);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                LogUtils.e("webViewUrl", url);
                view.loadUrl(url);
                return true;

            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //注意：super句话一定要删除，或者注释掉，否则又走handler.cancel()默认的不支持https的了。
                //super.onReceivedSslError(view, handler, error);
                //handler.cancel(); // Android默认的处理方式
                //handler.handleMessage(Message msg); // 进行其他处理

                handler.proceed(); // 接受所有网站的证书
            }
        });
    }

    /**
     * webView加载
     * 优化还款 h5不能显示问题
     */
    @SuppressLint("SetJavaScriptEnabled")
    public static void loadHtmlNew(Activity activity, final String url, final WebView mWebView, final ProgressBar mProgressBar) {
        WebSettings settings = mWebView.getSettings();
        /**
         * setAllowFileAccess 启用或禁止WebView访问文件数据 setBlockNetworkImage 是否显示网络图像
         * setBuiltInZoomControls 设置是否支持缩放 setCacheMode 设置缓冲的模式
         * setDefaultFontSize 设置默认的字体大小 setDefaultTextEncodingName 设置在解码时使用的默认编码
         * setFixedFontFamily 设置固定使用的字体 setJavaSciptEnabled 设置是否支持Javascript
         * setLayoutAlgorithm 设置布局方式 setLightTouchEnabled 设置用鼠标激活被选项
         * setSupportZoom 设置是否支持变焦
         * */

        /**
         * 在安卓5.0之前默认允许其加载混合网络协议内容;
         * 在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebView.setDrawingCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        // 取消滚动条
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        // 触摸焦点起作用
        mWebView.requestFocus();
        settings.setSavePassword(false);// 不弹窗浏览器是否保存密码
        settings.setDefaultTextEncodingName("utf-8");
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 自动适应屏幕尺寸
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //打开DOM储存
        settings.setDomStorageEnabled(true);
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //处理http和https开头的url
                if (url.equals(NetConstantValues.disclosure) || url.equals(NetConstantValues.about2_url)) {
                    activity.startActivity(WebActivity.getWebIntent(activity, "借款协议", url));
                } else {
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LogUtils.e("urlOnPageStart", url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (null != mProgressBar) {
                    mProgressBar.setVisibility(View.GONE);
                }
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed(); // 接受所有网站的证书
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    if (null != mProgressBar) {
                        mProgressBar.setVisibility(View.GONE);
                    }
                } else {
                    if (null != mProgressBar) {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        // 如何webview支持回退事件则可以处理
//        mWebView.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (event.getAction() == KeyEvent.ACTION_DOWN) {
//                    if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) { // 表示按返回键
//                        mWebView.goBack(); // 后退
//                        return true; // 已处理
//                    }
//                }
//                return false;
//            }
//        });
    }


    /**
     * webView加载(包含打电话)
     */
    @SuppressLint("SetJavaScriptEnabled")
    public static void loadHtmlIncludeCall(final String url, final WebView mWebView, final ProgressBar mProgressBar) {
        LogUtils.e("loadHtmlIncludeCall---webViewUrl", url);
        WebSettings settings = mWebView.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebView.setDrawingCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        // 取消滚动条
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        // 触摸焦点起作用
        mWebView.requestFocus();
        settings.setSavePassword(false);// 不弹窗浏览器是否保存密码
        settings.setDefaultTextEncodingName("utf-8");
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 自动适应屏幕尺寸
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //打开DOM储存
        settings.setDomStorageEnabled(true);
        mWebView.loadUrl(url);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    /**
     * 拨打客服电话
     *
     * @param context
     */
    public static void serviceTel(Context context, String phoneNum) {
        Uri uri;
        uri = Uri.parse("tel:" + phoneNum);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        //此处不判断就会报错
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            context.startActivity(intent);
        }
    }

    /**
     * 6.0以上拨打客服电话判断权限
     *
     * @param context
     */
    public static void callPhone(Context context, String phoneNum) {
        if (Build.VERSION.SDK_INT >= 23) {
            //判断有没有拨打电话权限
            if (PermissionChecker.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                //请求拨打电话权限
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 0x11);
            } else {
                serviceTel(context, phoneNum);
            }
        } else {
            serviceTel(context, phoneNum);
        }
    }

    /**
     * 判断软键盘是否显示
     *
     * @param activity
     * @return
     */
    public static boolean isSoftShowing(Activity activity) {
        //获取当前屏幕内容的高度
        int screenHeight = activity.getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

        return screenHeight - rect.bottom - getSoftButtonsBarHeight(activity) != 0;
    }

    /**
     * 底部虚拟按键栏的高度
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static int getSoftButtonsBarHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        //这个方法获取可能不是真实屏幕的高度
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        //获取当前屏幕的真实高度
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
        }
    }

    public static void showSoftPan(final EditText editText) {
        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputManager =
                        (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(editText, 0);
            }
        }, 500);

    }

    /**
     * 判断用户是否开启通知权限
     *
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean isNotificationEnabled(Context context) {

        String CHECK_OP_NO_THROW = "checkOpNoThrow";
        String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass = null;
     /* Context.APP_OPS_MANAGER */
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取当前应用的版本号
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    /**
     * 获取当前版本和服务器版本.如果服务器版本高于本地安装的版本.就更新
     *
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = pi.versionCode;

        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionCode;

    }

    /**
     * 判断按返回键是否退出本应用弹出对话框
     *
     * @param keyCode
     * @param event
     * @param context
     * @return
     */
    public static long exitTime = 0;//设置当前点击返回键的退出系统时间

    public static boolean clickBack(int keyCode, KeyEvent event, final Context context) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            // System.currentTimeMillis()无论何时调用，肯定大于2000
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                AppToast.makeShortToast(context, "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle("温馨提示").setMessage("您是否要退出本应用程序？").setNegativeButton("取消", null)
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Activity activity = (Activity) context;
//                                activity.finish();
                ActivityCollector.finishAll();
                System.exit(0);
//                            }
//                        });
                // 设置窗口外点击dialog不消失
//                Dialog dialog = builder.create();
//                dialog.setCanceledOnTouchOutside(false);
//                dialog.show();
            }
            return true;
        }
        return false;
    }

    public static boolean clickDialogBack(int keyCode, KeyEvent event, final Context context) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            return true;
        }
        return false;
    }

    /***
     * 获取url 指定name的value;
     * @param url
     * @param name
     * @return
     */
    public static String getValueByName(String url, String name) {
        String result = "";
        int index = url.indexOf("?");
        String temp = url.substring(index + 1);
        String[] keyValue = temp.split("&");
        for (String str : keyValue) {
            if (str.contains(name)) {
                result = str.replace(name + "=", "");
                break;
            }
        }
        return result;
    }

    /**
     * 遮盖页面不可编辑
     *
     * @param flag
     * @param llCover
     */
    public static void coverPage(boolean flag, LinearLayout llCover) {
        if (flag) {
            llCover.setVisibility(View.GONE);
        } else {
            llCover.setVisibility(View.VISIBLE);
            llCover.setOnClickListener(null);
        }
    }


    /**
     * 用户名隐藏
     *
     * @param string
     * @return
     */
    public static String nameSecret(String string) {
//        return string.replace(string.substring(0, 1), "*");
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        return string.substring(0, 1) + "*";
    }

    /**
     * 手机号******
     */
    public static String phoneSecret(String s) {
        String head = s.substring(0, 3);
        String end = s.substring(s.length() - 4, s.length());
        return head + "****" + end;
    }

    /**
     * 获取业务员待跟进状态
     */
    public static String getWaitFollowStatus(int step, int status) {
        String result = null;
        switch (step) {

            case 3:
                if (status == 0 || status == 2) {
                    result = "审核中";
                } else if (status == 9) {
                    result = "审批拒件";
                }
                break;
            case 4:
                if (status == 2) {
                    result = "领取金额";
                } else if (status == 9) {
                    result = "领取拒件";
                }
                break;
            case 5:
                if (status == 2 || status == 3) {
                    result = "放款中";
                } else if (status == 7) {
                    result = "放款失败";
                } else if (status == 9) {
                    result = "放款拒件";
                }
                break;

            case 7:
                if (status == 10 || status == 4) {
                    result = "受理中";
                } else if (status == 9) {
                    result = "审批拒件";
                }
                break;

        }
        return result;
    }

    /**
     * 我的借款金额类型
     */
    public static String getMyBorrowMoneyStatus(int step, int onlineType) {
        String result = null;
        switch (step) {
            case 3:
                if (onlineType == 1) {
                    //线下.申请金额
                    result = "申请金额";
                } else {
                    //线上.审批金额
                    result = "审批金额";
                }
                break;
            case 4:
                //审批金额
                result = "审批金额";
                break;
            case 5:
                //审批金额
                result = "审批金额";
                break;
            case 6:
                //审批金额
                result = "审批金额";
                break;
            case 7:
                //申请金额
                result = "申请金额";
                break;
        }
        return result;
    }

    /**
     * 我的借款状态
     */
    public static String getMyBorrowStatus(int step, int status) {
        String result = null;
        switch (step) {
            case 3:

                if (status == 10 || status == 0 || status == 2) {
                    result = "审核中";
                } else if (status == 9) {
                    result = "审批拒件";
                }
                break;
            case 4:

                if (status == 2) {
                    result = "领取金额";
                } else if (status == 9) {
                    result = "领取拒件";
                }
                break;
            case 5:

                if (status == 2 || status == 3) {
                    result = "放款中";
                } else if (status == 7) {
                    result = "放款失败";
                } else if (status == 9) {
                    result = "放款拒件";
                }
                break;
            case 6:
                if (status == 1) {
                    result = "还款中";
                } else if (status == 6) {
                    result = "已结清";
                }
                break;
            case 7:
                if (status == 10 || status == 4) {
                    result = "受理中";
                } else if (status == 9) {
                    result = "审批拒件";
                }
                break;
        }
        return result;
    }

    public static void showRequestError(LinearLayout llMain, LinearLayout llRetry) {
        llMain.setVisibility(View.GONE);
        llRetry.setVisibility(View.VISIBLE);
    }

    public static void showRequestSuccess(LinearLayout llMain, LinearLayout llRetry) {
        llMain.setVisibility(View.VISIBLE);
        llRetry.setVisibility(View.GONE);
    }

    public static String getPeriod(String loan_unit) {
        if ("1".equals(loan_unit)) {
            return "天";
        } else if ("2".equals(loan_unit)) {
            return "周";
        }
        return null;
    }


    //获取一个随机数
    public static int getOneRandom(int size) {
        Random random = new Random();
        return random.nextInt(size);
    }

    public static void showBankLogo(String code, ImageView ivBankLogo) {
        switch (code) {
            case "ABC":
                ivBankLogo.setImageResource(R.drawable.abc);
                break;
            case "CCB":
                ivBankLogo.setImageResource(R.drawable.ccb);
                break;
            case "BOC":
                ivBankLogo.setImageResource(R.drawable.boc);
                break;
            case "CEB":
                ivBankLogo.setImageResource(R.drawable.ceb);
                break;
            case "CIB":
                ivBankLogo.setImageResource(R.drawable.cib);
                break;
            case "PINGAN":
                ivBankLogo.setImageResource(R.drawable.pingan);
                break;
            case "PSBC":
                ivBankLogo.setImageResource(R.drawable.psbc);
                break;
            case "SPDB":
                ivBankLogo.setImageResource(R.drawable.spdb);
                break;
            case "ICBC":
                ivBankLogo.setImageResource(R.drawable.icbc);
                break;
            case "CMBC":
                ivBankLogo.setImageResource(R.drawable.cmbc);
                break;
            case "BOCOM":
                ivBankLogo.setImageResource(R.drawable.bocom);
                break;
            case "GDB":
                ivBankLogo.setImageResource(R.drawable.gdb);
                break;
            case "HXB":
                ivBankLogo.setImageResource(R.drawable.hxb);
                break;
            case "CMB":
                ivBankLogo.setImageResource(R.drawable.cmb);
                break;
            case "BCCB":
                ivBankLogo.setImageResource(R.drawable.bccb);
                break;
            case "BJRCB":
                ivBankLogo.setImageResource(R.drawable.bjrcb);
                break;
            case "BOS":
                ivBankLogo.setImageResource(R.drawable.bos);
                break;
            case "CBHB":
                ivBankLogo.setImageResource(R.drawable.cbhb);
                break;
            case "CITIC":
                ivBankLogo.setImageResource(R.drawable.citic);
                break;
            case "CZB":
                ivBankLogo.setImageResource(R.drawable.czb);
                break;
            case "HKBEA":
                ivBankLogo.setImageResource(R.drawable.hkbea);
                break;
            case "HZCB":
                ivBankLogo.setImageResource(R.drawable.hzcb);
                break;
            case "NJCB":
                ivBankLogo.setImageResource(R.drawable.njcb);
                break;
            case "SDB":
                ivBankLogo.setImageResource(R.drawable.sdb);
                break;
            case "SRCB":
                ivBankLogo.setImageResource(R.drawable.srcb);
                break;
        }
    }

    public static String bankCardProcess(String card) {
        String str1 = card.substring(0, 4);
        String str2 = card.substring(card.length() - 3, card.length());
        String str3 = " **** **** **** ";
        return str1 + str3 + str2;
    }

    public static String phoneProcess(String phone) {
        String str1 = phone.substring(0, 3);
        String str2 = phone.substring(phone.length() - 4, phone.length());
        String str3 = "****";
        return str1 + str3 + str2;
    }

    public static String periodUnit(String periodUnit) {
        String result = "";
        switch (periodUnit) {
            case "1":
                result = "天";
                break;
            case "2":
                result = "周";
                break;
            case "3":
                result = "月";
                break;
            case "4":
                result = "年";
                break;
        }
        return result;
    }

    public static String getWithdrawType(int type) {
        String result = "";
        switch (type) {
            case 1:
                result = "加息券";
                break;
            case 2:
                result = "返现券";
                break;
            case 3:
                result = "提现券";
                break;
        }
        return result;
    }

    public static String getRepayStatus(int status) {
        String s = "待回款";
        switch (status) {
            case 1:
                s = "待回款";
                break;
            case 2:
                s = "已回款";
                break;
            case 3:
                s = "回款中";
                break;
            case 4:
                s = "回款失败";
                break;
        }

        return s;
    }

    /**
     * 获取渠道号
     *
     * @param ctx
     * @param key
     * @return
     */
    public static String getAppMetaData(Context ctx, String key) {
        if (ctx == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String resultData = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return resultData;
    }

    /**
     * 清除信息
     */

    public static void clearData() {
        SPUtils.clear();
        LockPatternUtils.clear();
    }
}
