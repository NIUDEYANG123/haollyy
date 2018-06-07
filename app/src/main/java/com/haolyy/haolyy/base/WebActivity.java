package com.haolyy.haolyy.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haolyy.haolyy.MainActivity;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.entity.my.InviteBean;
import com.haolyy.haolyy.inteface.MyUMShareListener;
import com.haolyy.haolyy.utils.LogUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liliang on 2017/9/27.
 */

public class WebActivity extends BaseActivity {
    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.web_sh)
    WebView webSh;
    @BindView(R.id.web_p)
    ProgressBar webP;
    @BindView(R.id.tv_menu)
    TextView tvMenu;

    private String title, html,flag;
    private String content;
    private UMShareAPI umShareAPI;
    public static Intent getWebIntent(Context context, String title, String html) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("html", html);
        return intent;
    }

    /**
     * 开户成功风险测评
     * @param context
     * @param title
     * @param html
     * @param flag
     * @return
     */
    public static Intent getWebIntentFlag(Context context, String title, String html, String flag) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("html", html);
        intent.putExtra("flag", flag);
        return intent;
    }
    public static Intent getWebIntentContent(Context context, String title, String html) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", html);
        return intent;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        initView();
        umShareAPI = UMShareAPI.get(this);
    }

    private void initView() {
        tvMenu.setOnClickListener(v->{//分享调用h5的方法
            LogUtils.e("javascript","javascript1");
             webSh.loadUrl("javascript:Submith5Date()");
            });
        ivFinish.setOnClickListener(view -> {
            if (webSh.canGoBack()) {
                // 返回上一页面
                webSh.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                webSh.goBack();
            } else {
                finish();
                if (!TextUtils.isEmpty(flag)) {
                    openActivity(MainActivity.getMainIntent(mContext, 3));
                }
            }
        });
        title = getIntent().getStringExtra("title");
        html = getIntent().getStringExtra("html");
        flag = getIntent().getStringExtra("flag");
        content = getIntent().getStringExtra("content");
        tvTitle.setText(title);
        if(title.equals("邀请好友")){
            tvMenu.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(html)) {
            initWebView(content, false);
        } else {
            initWebView(html, true);
        }
    }

    private void initWebView(final String urll, boolean b) {
        WebSettings settings = webSh.getSettings();
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
            webSh.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSh.setDrawingCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        // 取消滚动条
        webSh.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        // 触摸焦点起作用
        webSh.requestFocus();
        settings.setSavePassword(false);// 不弹窗浏览器是否保存密码
        settings.setDefaultTextEncodingName("utf-8");
        //settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 自动适应屏幕尺寸
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setSupportZoom(true);
        settings.setAllowContentAccess(true);
        webSh.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    webP.setVisibility(View.GONE);
                } else {
                    webP.setVisibility(View.VISIBLE);
                }
            }
        });
        webSh.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if(Build.VERSION.SDK_INT<Build.VERSION_CODES.O){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.loadUrl(request.getUrl().toString());
                    } else {
                        view.loadUrl(request.toString());
                    }
                    return true;
                }
                return false;

            }

            /**
             * 判断签约
             * @param view
             * @param url
             * @param favicon
             */
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LogUtils.e(tag, url);
                LogUtils.e(tag, "watch" + NetConstantValues.watch_old);
                if (url.startsWith(Config.returl)) {
                    //认为签约成功
                    BaseApplication.signstatus = true;
                    finish();
                    if (!TextUtils.isEmpty(flag)) {
                        openActivity(MainActivity.getMainIntent(mContext, 3));
                    }
                } else if (url.contains(NetConstantValues.watch_old)) {
                    finish();
                }
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
        webSh.addJavascriptInterface(new JavaScriptInterface(this), "Android");//MyBrowserAPI:自定义的js函数名
        if (b) {
            webSh.loadUrl(urll);
        } else {
            settings.setTextSize(WebSettings.TextSize.LARGEST);
            webSh.loadData(content, "text/html; charset=UTF-8", null);
        }
    }


    public class JavaScriptInterface {
        Context mContext;

        JavaScriptInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void invite(String json) {
            LogUtils.e("javascript",json);
            Gson gson=new Gson();
            InviteBean inviteBean = gson.fromJson(json, InviteBean.class);
            UMImage thumb = new UMImage(WebActivity.this,inviteBean.getImgUrl());
            final UMWeb web = new UMWeb(inviteBean.getLink());
            web.setTitle(inviteBean.getTitle());
            web.setThumb(thumb);
            web.setDescription(inviteBean.getDesc());
            new ShareAction(WebActivity.this).withMedia(web)
                    .setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                    .setCallback(new MyUMShareListener(WebActivity.this,umShareAPI)).open();

        }

        @JavascriptInterface
        public String inviteCallBack() {
            Map map = new HashMap();
            map.put("mobile", BaseApplication.mUserName);
            map.put("userCode", BaseApplication.juid);
            map.put("token",BaseApplication.accessToken);
            Gson gson = new Gson();
            String jsonStr = gson.toJson(map);
            LogUtils.e("invite", jsonStr);
            return jsonStr;
        }
        @JavascriptInterface
        public void skipList() {
            startActivity(MainActivity.getMainIntent(mContext, 1));
            finish();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webSh.clearCache(true);//清除缓存
        umShareAPI.release();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webSh.canGoBack()) {
            // 返回上一页面
            webSh.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            webSh.goBack();
            return true;
        } else {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
