package com.ass.dingshopping.airutils;

import android.text.TextUtils;
import android.widget.Toast;

import com.ass.dingshopping.ShopApplication;

/**
 * @ClassName: ToastUtils
 * @Description: Toast显示信息工具类
 */
public class ToastUtils {

    private ToastUtils()
    {
		/* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static Toast toast;

    static {
        toast = Toast.makeText(ShopApplication.getApplictionContext(), "", Toast.LENGTH_SHORT);
    }

    /**
     * Long Toast
     * @param value
     */
    public static void toastLong(String value){
        if(TextUtils.isEmpty(value)){
            return;
        }
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setText(value);
        toast.show();
    }

    /**
     * Long Toast
     * @param strRes
     */
    public static void toastLong(int strRes){
        toastLong(ShopApplication.getApplictionContext().getString(strRes));
    }

    /**
     * Short Toast
     * @param value
     */
    public static void toastshort(String value){
        if(TextUtils.isEmpty(value)){
            return;
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(value);
        toast.show();
    }

    /**
     * Short Toast
     * @param strRes
     */
    public static void toastShort(int strRes){
        toastLong(ShopApplication.getApplictionContext().getString(strRes));
    }

}
