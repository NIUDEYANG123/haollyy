package com.haolyy.haolyy.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * 手势密码设置
 *
 * @author dalong
 */
public class LockPatternUtils {
    private static String SP_NAME = "gesture";
    private static SharedPreferences sp;

    /**
     * 保存设置的手势密码
     *
     * @param locks
     */
    public static void saveLockPattern(Context context, String key, String locks) {

        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, locks).apply();
    }

    /**
     * 读取手势密码
     *
     * @param context
     * @param key
     * @return
     */
    public static String getLockPattern(Context context, String key) {

        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getString(key, null);
    }

    /**
     * 清除
     */
    public static void clear() {
        sp.edit().clear().apply();
    }

}
