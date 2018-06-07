package com.haolyy.haolyy.base;


import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;

import com.google.gson.Gson;
import com.haolyy.haolyy.MainActivity;
import com.haolyy.haolyy.config.ConstantKey;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.inteface.MainLooper;
import com.haolyy.haolyy.ui.account.CheckUsernameActivity;
import com.haolyy.haolyy.ui.account.LoginActivity;
import com.haolyy.haolyy.utils.JsonUtils;
import com.haolyy.haolyy.utils.LockPatternUtils;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.SPUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.haolyy.haolyy.base.BaseApplication.accessToken;
import static com.haolyy.haolyy.base.BaseApplication.context;
import static com.haolyy.haolyy.base.BaseApplication.mInstance;
import static com.haolyy.haolyy.base.BaseApplication.mLoginState;
import static com.haolyy.haolyy.base.BaseApplication.version;
import static com.haolyy.haolyy.config.Config.client;
import static com.haolyy.haolyy.config.Config.platform;

/**
 * 网络请求基类2.0版本
 * Created by wy on 2016/11/17.
 */

public class BaseModel {

    private static final int DEFAULT_TIMEOUT = 60;
    protected Retrofit retrofit;
    private OkHttpClient.Builder httpClientBuilder;

    /**
     * 有公共参数
     */
    protected BaseModel() {
        //手动创建一个OkHttpClient并设置超时时间
        httpClientBuilder = new OkHttpClient.Builder();
        if (WYUtils.isApkInDebug(BaseApplication.getContext())) {
            httpClientBuilder.retryOnConnectionFailure(true)
                    .addInterceptor(new AddBodyInterceptor())
                    //.addInterceptor(new ResponseInterceptor())
                    .addInterceptor(new LogInterceptor());
        } else {
            httpClientBuilder.addInterceptor(new AddBodyInterceptor())
                    //.addInterceptor(new ResponseInterceptor())
                    .addInterceptor(new LogInterceptor());
        }
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(NetConstantValues.HOST_URL)
                .build();
    }

    /**
     * 无公共参数
     *
     * @param url
     */
    public BaseModel(String url) {
        //手动创建一个OkHttpClient并设置超时时间
        httpClientBuilder = new OkHttpClient.Builder();
        if (WYUtils.isApkInDebug(BaseApplication.getContext())) {
            httpClientBuilder.retryOnConnectionFailure(true)
                    .addInterceptor(new LogInterceptor())
                    .addInterceptor(new ResponseInterceptor());
        } else {
            httpClientBuilder
                    .addInterceptor(new ResponseInterceptor()).addInterceptor(new LogInterceptor());
        }
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();

    }


    /**
     * 响应拦截,当终端登录设备发生变更后,跳转到登录页面
     */
    private class ResponseInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            MediaType mediaType = response.body().contentType();
            ResponseBody originalBody = response.body();
            String content = "";
            if (null != originalBody) {
                content = originalBody.string();
            }
            try {
                BaseBean loginOutBean = JsonUtils.toObject(content, BaseBean.class);
                LogUtils.e(loginOutBean.code);
                if (mLoginState) {
                    if (loginOutBean.code.equals("P-1011") || loginOutBean.code.equals("user_not_login")) {
                        new Thread(() -> {
                            Looper.prepare();
                            BaseApplication.mLoginState = false;
                            SPUtils.saveBoolean(context, ConstantKey.GESTURE_STATE_KEY, false);
                            SPUtils.saveBoolean(context, ConstantKey.USER_INFO_KEY, false);
                            Intent intent = new Intent(BaseApplication.context, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            Bundle bundle = new Bundle();
                            bundle.putInt("index", 0);
                            intent.putExtras(bundle);
                            BaseApplication.context.startActivity(intent);
                            Looper.loop();
                        }).start();
                    }
                }

            } catch (Exception e) {
                LogUtils.e("Exception", e.getMessage());
                e.printStackTrace();
            }
            return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
        }
    }

    /**
     * 公共参数，get请求添加
     */
    private class addQueryParameterInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request request;
            HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                    .addQueryParameter("platform", platform)
                    .addQueryParameter("client", client)
                    .addQueryParameter("version", "V" + version)
                    .build();
            if (mLoginState && accessToken != null) {
                request = originalRequest.newBuilder()
                        .header("accessToken", accessToken)
                        .url(modifiedUrl)
                        .build();
            } else {
                request = originalRequest.newBuilder()
                        .url(modifiedUrl)
                        .build();
            }
            return chain.proceed(request);
        }
    }

    /**
     * 请求体定制：统一添加定制参数
     */
    private class AddBodyInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder();
            if (original.body() instanceof FormBody) {
                HashMap<String, Object> rootMap = new HashMap<>();
                FormBody.Builder newFormBody = new FormBody.Builder();
                FormBody oidFormBody = (FormBody) original.body();
                for (int i = 0; i < oidFormBody.size(); i++) {
                    //rootMap.put(oidFormBody.encodedName(i), oidFormBody.encodedValue(i));
                    newFormBody.addEncoded(oidFormBody.encodedName(i), oidFormBody.encodedValue(i));
                }
                newFormBody.add("platform", platform);
                newFormBody.add("client", client);
                newFormBody.add("version", "V" + version);
                requestBuilder.method(original.method(), newFormBody.build());
                //LogUtils.e("addBodyInterceptor_params",rootMap.toString());
            }
            if (mLoginState && accessToken != null) {
                requestBuilder.addHeader("accessToken", accessToken);
            }
            Request request = requestBuilder.build();

            return chain.proceed(request);
        }
    }

    /**
     * 日志
     */
    private class LogInterceptor implements Interceptor {
        String TAG = "LoggerInterceptor";
        private String content;

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            LogUtils.e(TAG, "request:" + request.url());
            Headers headers = request.headers();
            for (int i = 0; i < headers.size(); i++) {
                String headerName = headers.name(i);
                String headerValue = headers.get(headerName);
                LogUtils.e(TAG, "Header----------->Name:" + headerName + "------------>Value:" + headerValue + "\n");
            }
            RequestBody requestBody = request.body();
            if (requestBody instanceof FormBody) {
                HashMap<String, Object> rootMap = new HashMap<>();
                for (int i = 0; i < ((FormBody) requestBody).size(); i++) {
                    rootMap.put(((FormBody) requestBody).encodedName(i), getValueDecode(((FormBody) requestBody).encodedValue(i)));
                }
                LogUtils.e(TAG, "params : " + new Gson().toJson(rootMap));
            }
            long t1 = System.nanoTime();
            okhttp3.Response response = chain.proceed(chain.request());
            okhttp3.MediaType mediaType = response.body().contentType();
            ResponseBody originalBody = response.body();
            if (null != originalBody) {
                content = originalBody.string();
            }
            LogUtils.e(TAG, "response body:" + content);

            long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - t1);
            LogUtils.e(TAG, "time : " + " (" + tookMs + "ms" + ')');
            BaseBean loginOutBean = JsonUtils.toObject(content, BaseBean.class);
            if (mLoginState) {
                if (loginOutBean.code.equals("P-1011") || loginOutBean.code.equals("user_not_login")) {
                  /*  new Thread(() -> {
                        Looper.prepare();
                        nextLogin(loginOutBean.msg);
                        Looper.loop();
                    }).start();*/
                    MainLooper.runOnUiThread(() -> nextLogin(loginOutBean.msg));
                }
            }

            return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
        }

        /**
         * 去登录
         */
        public void nextLogin(String str) {
            UIUtils.showToastCommon(context, str);
            BaseApplication.mLoginState = false;
            LockPatternUtils.saveLockPattern(context, ConstantKey.GESTURE_STATE_KEY, null);
            SPUtils.saveBoolean(context, ConstantKey.USER_INFO_KEY, false);
            SPUtils.saveBoolean(context, ConstantKey.GESTURE_STATE_KEY, false);
            //ActivityCollector.finishAll();
            context.startActivity(CheckUsernameActivity.getReturnIntent(context, "logout").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        }

        /**
         * 解决中文乱码结果集
         *
         * @param value
         * @return
         */
        private String getValueDecode(String value) {
            try {
                return URLDecoder.decode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return value;
        }
    }

    public static <T> void invoke(LifeSubscription lifeSubscription, Observable<T> observable, Subscriber<T> subscriber) {
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        lifeSubscription.bindSubscription(subscription);
    }

    //添加线程管理并订阅
    protected void toSubscribe(Observable observable, Subscriber subscriber) {
        observable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    static <T> void invokeMerge(LifeSubscription lifeSubscription, Subscriber<T> subscriber, Observable... observables) {
        Observable observable = null;
        switch (observables.length) {
            case 1:
                observable = observables[0];
                break;
            case 2:
                observable = Observable.merge(observables[0], observables[1]);
                break;
            case 3:
                observable = Observable.merge(observables[0], observables[1], observables[2]);
                break;
            case 4:
                observable = Observable.merge(observables[0], observables[1], observables[2], observables[3]);
                break;
        }
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        lifeSubscription.bindSubscription(subscription);
    }
}
