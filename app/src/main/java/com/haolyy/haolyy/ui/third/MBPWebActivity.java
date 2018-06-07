package com.haolyy.haolyy.ui.third;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.ActivityCollector;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.ui.my.ModifyBankPhoneActivity;
import com.haolyy.haolyy.utils.LogUtils;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 修改银行预留手机号
 */
public class MBPWebActivity extends AppCompatActivity {

    @BindView(R.id.ll_progress)
    LinearLayout llProgress;
    @BindView(R.id.web_sh)
    WebView webSh;
    @BindView(R.id.topBar)
    TopBar topBar;
    private String urldata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sh_web);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        ImmersionBar immersionBar = ImmersionBar.with(this);
        immersionBar.statusBarDarkFont(true).init();

        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                ActivityCollector.finishActivity(ModifyBankPhoneActivity.class);
                finish();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });
        urldata = getIntent().getAction();

        WebViewClient webViewClient = new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                } else {
                    view.loadUrl(request.toString());
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LogUtils.e("ndyGoUrl222", url);
                if (url.contains(Config.returl)) {


                    ActivityCollector.finishActivity(ModifyBankPhoneActivity.class);
                    finish();
                    startActivity(new Intent(MBPWebActivity.this, GuideSkipActivity.class));
                }
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed(); // 接受所有证书
            }
        };
        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    llProgress.setVisibility(View.GONE);
                } else {
                    llProgress.setVisibility(View.VISIBLE);
                }
                super.onProgressChanged(view, newProgress);

            }
        };

        webSh.getSettings().setDomStorageEnabled(true);//支持所有标签
        webSh.getSettings().setSupportZoom(true);
        webSh.getSettings().setJavaScriptEnabled(true);
        webSh.getSettings().setLoadWithOverviewMode(true);
        webSh.getSettings().setUseWideViewPort(true);
        webSh.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSh.setDrawingCacheEnabled(true);
        // 取消滚动条
        webSh.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webSh.setWebChromeClient(webChromeClient);
        webSh.setWebViewClient(webViewClient);
        webSh.addJavascriptInterface(new JavaScriptInterface(MBPWebActivity.this), "Android");//MyBrowserAPI:自定义的js函数名
        webSh.loadUrl("file:///android_asset/register.html");
    }


    public class JavaScriptInterface {
        Context mContext;

        JavaScriptInterface(Context c) {
            mContext = c;
        }

        /**
         * 采用此方法
         * 传递字符串网页里解析成对象
         *
         * @param message
         * @return
         */
        @JavascriptInterface
        public String show(String message) {
            LogUtils.e("shbankwebActivity", message);
            return urldata;
        }

        /**
         * 直接传json网页上里解析不成功
         *
         * @return
         */
        @JavascriptInterface
        public JSONObject sendJson() {
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webSh.clearCache(true);//清除缓存
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
