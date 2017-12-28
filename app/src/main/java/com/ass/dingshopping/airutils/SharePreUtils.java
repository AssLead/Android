package com.ass.dingshopping.airutils;

import android.content.Context;
import android.content.SharedPreferences;

import com.ass.dingshopping.ShopApplication;


/**
 * @ClassName: SharePreItils
 * @Description: SharePreference 工具类
 */
public final class SharePreUtils {

    private final static String NAME = "jcly_sharePre";
    private static SharedPreferences preferences;
    static {
        if(preferences == null){
            preferences = ShopApplication.getApplictionContext().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
    }



    /**
     * 保存String类型数据
     * @param key 键
     * @param value 值
     * @return
     */
    public static boolean putString(String key, String value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * 获得String类型数据
     * @param key 键
     * @return
     */
    public static String getString(String key){
        return preferences.getString(key,"");
    }

    /**
     * 保存Integer数据
     * @param key 键
     * @param value 值
     * @return
     */
    public static boolean putInt(String key, Integer value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * 获得Integer类型数据
     * @param key
     * @return
     */
    public static Integer getInt(String key){
        return preferences.getInt(key, -1);
    }

    /**
     * 保存Float数据
     * @param key
     * @param value
     * @return
     */
    public static boolean putfloat(String key, Float value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * 获得Float数据
     * @param key
     * @return
     */
    public static Float getfloat(String key){
        return preferences.getFloat(key, -1.0f);
    }


    /**
     * 保存Boolean 数据
     * @param key
     * @param value
     * @return
     */
    public static boolean putBoolean(String key, Boolean value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * 获得Boolean数据
     * @param key
     * @return
     */
    public static Boolean getBoolean(String key){
        return preferences.getBoolean(key, false);
    }


    /**
     * 保存Long数据
     * @param key
     * @param value
     * @return
     */
    public static boolean putLong(String key, Long value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * 获得Long数据
     * @param key
     * @return
     */
    public static Long getLong(String key){
        return preferences.getLong(key, -1L);
    }



}
