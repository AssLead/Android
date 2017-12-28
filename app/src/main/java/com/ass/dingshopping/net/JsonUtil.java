package com.ass.dingshopping.net;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    public static final String STATE = "Code";
    public static final String MSG = "Message";
    public static final String DATA = "Results";
    public static final String ListS = "PageResults";




    /**
     * 在指定的json里查找key对应的值
     *
     * @param json
     * @param key
     * @return
     */
    public static String getValue(String json, String key) {
        if (json == null || key == null)
            return null;
        String value = "";
        try {
            JSONObject jsonObject = new JSONObject(json);
            value = jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获得状态码
     *
     * @param result
     * @return
     */
    public static int getStateCode(String result) {
        int stateCode = -1;
        try {
            JSONObject jsonObject = new JSONObject(result);
            stateCode = jsonObject.getInt(STATE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stateCode;
    }

    /**
     * 获得对应的信息
     *
     * @param result
     * @return
     */
    public static String getMessage(String result) {
        String message = "";
        try {
            JSONObject jsonObject = new JSONObject(result);
            message = jsonObject.getString(MSG);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return message;
    }


    /**
     * 获得data的内容
     *
     * @param result
     * @return
     */
    public static String getDataStr(String result) {
        String datastr = "";
        try {
            JSONObject jsonObject = new JSONObject(result);
            datastr = jsonObject.getString(DATA);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datastr;
    }

    /**
     * 获得data的内容
     *
     * @param result
     * @return
     */
    public static String getClassStr(String result,String name) {
        String datastr = "";
        try {
            JSONObject jsonObject = new JSONObject(result);
            datastr = jsonObject.getString(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datastr;
    }




    /**
     * 获得data的内容
     *
     * @param result
     * @return
     */
    public static String getDataStr1(String result) {
        String datastr = "";
        try {
            JSONObject jsonObject = new JSONObject(result);
            datastr = jsonObject.getString(ListS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datastr;
    }

    /**
     * 把jsonObject转换成指定对象
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getObjectList(String data, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        if (data.equals(""))
            return list;
        try {
            Gson gson = new Gson();
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(gson.fromJson(jsonArray.getString(i), clazz));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 将Json字符串转成具体对象
     *
     * @param object
     * @param t
     * @param <T>
     * @return
     */
    public static <T extends Model> T parseVo1(JSONObject object, Class<T> t) {
        return parseVo(object.toString(), t);
    }

    /**
     * 将Json字符串转成具体对象
     *
     * @param object
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T parseVo(String object, Class<T> t) {
        if (TextUtils.isEmpty(object)) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(object, t);
    }

}
