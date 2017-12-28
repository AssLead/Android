package com.ass.dingshopping.airutils;

import android.text.TextUtils;
import android.util.Log;

/**
 * @ClassName:
 * @Description: 日志工具类
 */
public class LogUtils {

    private static boolean isShow = true;

    /**
     * 打印debug日志
     *
     * @param tag
     * @param content
     */
    public static void d(String tag, String content) {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        if (isShow) {
            Log.d(tag, content);
        }
    }

    /**
     * 打印error日志
     *
     * @param tag
     * @param content
     */
    public static void e(String tag, String content) {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        if (isShow) {
            Log.e(tag, content);
        }
    }


    public static void i(String tag, String content) {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        if (isShow) {
            Log.i(tag, content);
        }
    }
}
