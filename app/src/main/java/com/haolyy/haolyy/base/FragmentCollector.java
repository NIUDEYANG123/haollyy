package com.haolyy.haolyy.base;

import android.app.Activity;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import static com.haolyy.haolyy.base.ActivityCollector.activities;

/**
 * Created by wy on 2016/5/19.
 *
 */
public class FragmentCollector {
    public static List<Fragment> fragments = new ArrayList<Fragment>();

    public static void addFragment(Fragment activity) {
        fragments.add(activity);
    }

    public static void removeFragment(Fragment activity) {
        fragments.remove(activity);
    }

    public static void finishAll() {
        for (Fragment activity : fragments) {
                activity.onDestroy();

        }
    }

    public static void finishActivity(Class tClass) {
        for (Activity activity : activities) {
            if (activity.getClass().equals(tClass)) {
                activity.finish();
            }
        }
    }

}
