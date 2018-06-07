package com.haolyy.haolyy.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.haolyy.haolyy.MainActivity;
import com.haolyy.haolyy.base.ActivityCollector;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.config.ConstantKey;


//实现标记的写入与读取
public class SPUtils {
    private static final String SP_NAME = "Haolly";
    private static SharedPreferences sp;

    //保存布尔值
    public static void saveBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }

    //保存字符串
    public static void saveString(Context context, String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key, String defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getString(key, defValue);
    }

    //保存Long---token
    public static void saveLong(Context context, String key, long value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().putLong(key, value).apply();
    }

    public static long getLong(Context context, String key, long defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getLong(key, defValue);
    }

    //保存int
    public static void saveInt(Context context, String key, int value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, String key, int defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getInt(key, defValue);
    }

    public static void clear() {
        sp.edit().clear().apply();
        BaseApplication.mLoginState=false;
        BaseApplication.mUserName="";
        BaseApplication.userId=-1;
        BaseApplication.juid="";//usercode是明码 juid是加密后的 世纪是一个东西
        BaseApplication.juidMd5="";
        BaseApplication.accessToken="";
        BaseApplication.refreshToken="";
        BaseApplication.exist=false;
    }
}
