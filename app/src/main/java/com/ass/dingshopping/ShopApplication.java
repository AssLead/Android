package com.ass.dingshopping;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Air on 2017/12/25.
 */

public class ShopApplication extends Application {

    protected static final String TAG = "ShopApplication";

    private static ShopApplication instance;
    private static Context context;


    public static ShopApplication getInstance() {
        return instance;
    }

    public static Context getApplictionContext() {
        return context;
    }

    /**
     * 维护Activity的集合
     */
    private static List<SoftReference<Activity>> activities = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = this;


    }

    /**
     * 缓存Activity引用
     *
     * @param activity
     */
    public static void putActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        SoftReference<Activity> reference = new SoftReference<Activity>(activity);
        activities.add(reference);
    }

    /**
     * 移除Activity引用
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        for (SoftReference<Activity> reference : activities) {
            Activity cacheActivity = reference.get();
            if (cacheActivity != null && cacheActivity.equals(activity)) {
                reference.clear();
                activities.remove(reference);
                break;
            }
        }
    }

    /**
     * 清除Activity引用集合
     */
    public static void clearActivity() {
        for (SoftReference<Activity> reference : activities) {
            Activity cacheActivity = reference.get();
            if (cacheActivity != null) {
                cacheActivity.finish();
                reference.clear();
            }
        }
        activities.clear();
    }

    /**
     * 退出程序
     */
    public static void exitApp() {
        clearActivity();
    }


}
