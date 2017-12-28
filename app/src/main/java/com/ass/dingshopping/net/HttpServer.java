package com.ass.dingshopping.net;

import android.util.Log;

import com.ass.dingshopping.net.RequestManager.ReqCallBack;

import java.util.HashMap;

/**
 * 定义各种请求的方法 该类采用单利模式
 */

public class HttpServer {
    private static HttpServer instance;

    private HttpServer() {
    }

    public static HttpServer getInstance() {
        if (instance == null) {
            synchronized (HttpServer.class) {
                if (instance == null)
                    instance = new HttpServer();
            }
        }
        return instance;
    }





    /**
     * 登录
     */
    public <T> void Login(String action, String phone, String pwd, ReqCallBack<T> callBack) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("Mobile", phone);
        params.put("LoginPassword", pwd);
        requestPOST("", params, callBack);

    }

    /**
     * 发出请求 POST
     */
    private <T> void requestPOST(String action, HashMap<String, Object> params, ReqCallBack<T> callBack) {
        Log.i("----------------", "params:" + params.toString());
        RequestManager.getInstance().requestAsyn(action, RequestManager.TYPE_POST_FORM, params, callBack);
    }

    /**
     * 发出请求 GET
     */
    private <T> void requestGet(String action, HashMap<String, Object> params, ReqCallBack<T> callBack) {
        Log.i("----------------", "params:" + params.toString());
        RequestManager.getInstance().requestAsyn(action, RequestManager.TYPE_POST_JSON, params, callBack);
    }
}
