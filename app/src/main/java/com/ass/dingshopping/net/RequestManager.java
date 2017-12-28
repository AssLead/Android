package com.ass.dingshopping.net;

import android.os.Build;
import android.os.Handler;
import android.util.Log;


import com.ass.dingshopping.ShopApplication;
import com.ass.dingshopping.airutils.LogUtils;
import com.ass.dingshopping.airutils.ToastUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestManager {
    // mdiatype 需要和服务端保持一致
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    private static final String TAG = RequestManager.class.getSimpleName();
    private static final String BASE_URL = HttpConfig.URL_HOST;// 请求接口根地址
    private static volatile RequestManager mInstance;// 单利引用
    public static final int TYPE_GET = 0;// get请求
    public static final int TYPE_POST_JSON = 1;// post请求参数为json
    public static final int TYPE_POST_FORM = 2;// post请求参数为表单
    private OkHttpClient mOkHttpClient;// okHttpClient 实例
    private Handler okHttpHandler;// 全局处理子线程和M主线程通信
    HashMap<String, String> headersParams = new HashMap<String, String>();

    /**
     * 初始化RequestManager
     */
    public RequestManager() {
        // 初始化OkHttpClient
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.cookieJar(new CookieJar() {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        });

        mOkHttpClient = okBuilder.connectTimeout(30, TimeUnit.SECONDS)// 设置超时时间
                .readTimeout(30, TimeUnit.SECONDS)// 设置读取超时时间
                .writeTimeout(30, TimeUnit.SECONDS)// 设置写入超时时间
                .build();
        // 初始化Handlr
        okHttpHandler = new Handler(ShopApplication.getInstance().getMainLooper());
    }

    /**
     * 获取单例引用
     *
     * @return
     */
    public static RequestManager getInstance() {
        RequestManager inst = mInstance;
        if (inst == null) {
            synchronized (RequestManager.class) {
                inst = mInstance;
                if (inst == null) {
                    inst = new RequestManager();
                    mInstance = inst;
                }
            }
        }
        return inst;
    }

    /**
     * okHttp同步请求统一入口
     *
     * @param actionUrl   接口地址
     * @param requestType 请求类型 0:get请求 ,1:post请求参数为json, 2:post请求参数为表单
     * @param paramsMap   请求参数
     */
    public void requestSyn(String actionUrl, int requestType, HashMap<String, Object> paramsMap) {
        switch (requestType) {
            case TYPE_GET:
                requestGetBySyn(actionUrl, paramsMap);
                break;
            case TYPE_POST_JSON:
                requestPostBySyn(actionUrl, paramsMap);
                break;
            case TYPE_POST_FORM:
                requestPostBySynWithForm(actionUrl, paramsMap);
                break;
        }
    }

    /**
     * okHttp get同步请求
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     */
    private void requestGetBySyn(String actionUrl, HashMap<String, Object> paramsMap) {
        StringBuilder tempParams = new StringBuilder();
        try {
            // 处理参数
            int pos = 0;
            for (Object key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                // 对参数进行URLEncoder
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode((String) paramsMap.get(key), "utf-8")));
                pos++;
            }
            // 补全请求地址
            String requestUrl = String.format("%s/%s?%s", BASE_URL, "", tempParams.toString());
            // 创建一个请求
            Request request = addHeaders().url(requestUrl).build();
            // 创建一个Call
            final Call call = mOkHttpClient.newCall(request);
            // 执行请求
            final Response response = call.execute();
            response.body().string();
        } catch (Exception e) {
            LogUtils.e(TAG, e.toString());
        }
    }

    /**
     * okHttp post同步请求
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     */
    private void requestPostBySyn(String actionUrl, HashMap<String, Object> paramsMap) {
        try {
            // 处理参数
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode((String) paramsMap.get(key), "utf-8")));
                pos++;
            }
            // 补全请求地址
            String requestUrl = String.format("%s/%s", BASE_URL, "");
            // 生成参数
            String params = tempParams.toString();
            // 创建一个请求实体对象 RequestBody
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);
            // 创建一个请求
            final Request request = addHeaders().url(requestUrl).post(body).build();
            // 创建一个Call
            final Call call = mOkHttpClient.newCall(request);
            // 执行请求
            Response response = call.execute();
            // 请求执行成功
            if (response.isSuccessful()) {
                // 获取返回数据 可以是String，bytes ,byteStream
                LogUtils.e(TAG, "response ----->" + response.body().string());
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e.toString());
        }
    }

    /**
     * okHttp post同步请求表单提交
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     */
    private void requestPostBySynWithForm(String actionUrl, HashMap<String, Object> paramsMap) {
        try {
            // 创建一个FormBody.Builder
            FormBody.Builder builder = new FormBody.Builder();
            for (String key : paramsMap.keySet()) {
                // 追加表单信息
                builder.add(key, paramsMap.get(key).toString());
            }
            // 生成表单实体对象
            RequestBody formBody = builder.build();
            // 补全请求地址
            String requestUrl = String.format("%s/%s", BASE_URL, "");
            // 创建一个请求
            final Request request = addHeaders().url(requestUrl).post(formBody).build();
            // 创建一个Call
            final Call call = mOkHttpClient.newCall(request);
            // 执行请求
            Response response = call.execute();
            if (response.isSuccessful()) {
                LogUtils.e(TAG, "response ----->" + response.body().string());
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e.toString());
        }
    }

    /**
     * okHttp异步请求统一入口
     *
     * @param actionUrl   接口地址
     * @param requestType 请求类型 0:get请求 ,1:post请求参数为json, 2:post请求参数为表单
     * @param paramsMap   请求参数
     * @param callBack    请求返回数据回调
     * @param <T>         数据泛型
     **/
    public <T> Call requestAsyn(String actionUrl, int requestType, HashMap<String, Object> paramsMap, ReqCallBack<T> callBack) {
        Call call = null;
        switch (requestType) {
            case TYPE_GET:
                call = requestGetByAsyn(actionUrl, paramsMap, callBack);
                break;
            case TYPE_POST_JSON:
                call = requestPostByAsyn(actionUrl, paramsMap, callBack);
                break;
            case TYPE_POST_FORM:
                call = requestPostByAsynWithForm(actionUrl, paramsMap, callBack);
                break;
        }
        return call;
    }

    /**
     * okHttp get异步请求
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     * @param callBack  请求返回数据回调
     * @param <T>       数据泛型
     * @return
     */
    private <T> Call requestGetByAsyn(final String actionUrl, HashMap<String, Object> paramsMap, final ReqCallBack<T> callBack) {
        StringBuilder tempParams = new StringBuilder();
        try {
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key).toString(), "utf-8")));
                pos++;
            }
            String requestUrl = String.format("%s/%s?%s", BASE_URL, actionUrl, tempParams.toString());
            final Request request = addHeaders().url(requestUrl).build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    failedCallBack("访问失败", callBack);
                    LogUtils.e(TAG, e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        LogUtils.i(TAG, actionUrl + ": " + string);
                        successCallBack((T) string, callBack);
                    } else {
                        LogUtils.i(TAG, actionUrl + ": " + "服务器错误");
                        failedCallBack("服务器错误", callBack);
                    }
                }
            });
            return call;
        } catch (Exception e) {
            LogUtils.e(TAG, e.toString());
        }
        return null;
    }

    /**
     * okHttp post异步请求
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     * @param callBack  请求返回数据回调
     * @param <T>       数据泛型
     * @return
     */
    private <T> Call requestPostByAsyn(final String actionUrl, HashMap<String, Object> paramsMap, final ReqCallBack<T> callBack) {
        try {
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                //tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key).toString(), "utf-8")));
                tempParams.append(paramsMap.get(key).toString());
                pos++;
            }
            String params = tempParams.toString();
            Log.i("--------", "params:" + params);
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);

            String requestUrl = String.format("%s/%s", BASE_URL, actionUrl);
            final Request request = addHeaders().url(requestUrl).post(body).build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    failedCallBack("访问失败", callBack);
                    LogUtils.e(TAG, e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        LogUtils.i(TAG, actionUrl + ": " + string);
                        successCallBack((T) string, callBack);
                    } else {
                        LogUtils.i(TAG, actionUrl + ": " + "服务器错误");
                        failedCallBack("服务器错误", callBack);
                    }
                }
            });
            return call;
        } catch (Exception e) {
            LogUtils.e(TAG, e.toString());
        }
        return null;
    }

    /**
     * okHttp post异步请求表单提交
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     * @param callBack  请求返回数据回调
     * @param <T>       数据泛型
     * @return
     */
    private <T> Call requestPostByAsynWithForm(final String actionUrl, HashMap<String, Object> paramsMap, final ReqCallBack<T> callBack) {
        try {
            MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
            for (String key : paramsMap.keySet()) {
                Object object = paramsMap.get(key);
                if (object instanceof File[]) {
                    File[] file = (File[]) object;
                    for (int i = 0; i < file.length; i++) {
                        if (file[i] != null) {
                            requestBody.addFormDataPart(key, file[i].getName(), RequestBody.create(null, file[i]));
                        }
                    }
                    continue;
                }
                if (object instanceof File) {
                    File file = (File) object;
                    if (file != null) {
                        requestBody.addFormDataPart(key, file.getName(), RequestBody.create(null, file));
                    }
                    continue;
                }
                if (object != null) {
                    requestBody.addFormDataPart(key, object.toString());
                }
            }
            RequestBody formBody;
            if (paramsMap.size() == 0) {
                FormBody.Builder builder = new FormBody.Builder();
                formBody = builder.build();
            } else {
                formBody = requestBody.build();
            }
            String requestUrl = String.format("%s/%s", BASE_URL, actionUrl);
            LogUtils.i("------------", "requestUrl：" + requestUrl);
            final Request request = addHeaders().url(requestUrl).post(formBody).build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    failedCallBack("访问失败", callBack);
                    LogUtils.e(TAG, e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        LogUtils.i(TAG, actionUrl + ": " + string);
                        successCallBack((T) string, callBack);
                    } else {
                        String string = response.body().string();
                        LogUtils.i(TAG, actionUrl + ": " + "服务器错误:" + string);
                        failedCallBack("服务器错误", callBack);
                    }
                }
            });
            return call;
        } catch (Exception e) {
            LogUtils.e(TAG, e.toString());
        }
        return null;
    }

    /**
     * 上传图片
     *
     * @param actionUrl
     * @param callBack
     * @return
     */
    public <T> Call upLoadFile(final String actionUrl, HashMap<String, Object> paramsMap, final ReqCallBack<T> callBack) {
        try {
            MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
            for (String key : paramsMap.keySet()) {
                Object object = paramsMap.get(key);
                if (!(object instanceof File)) {
                    requestBody.addFormDataPart(key, object.toString());
                } else {
                    File file = (File) object;
                    RequestBody body = RequestBody.create(null, file);
                    requestBody.addFormDataPart(key, file.getName(), body);
                }
            }
            MultipartBody formBody = requestBody.build();
            String requestUrl = String.format("%s/%s", BASE_URL, actionUrl);
            Request request = addHeaders().url(requestUrl).post(formBody).build();
            Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    failedCallBack("访问失败", callBack);
                    LogUtils.e(TAG, e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        LogUtils.i(TAG, actionUrl + ": " + string);
                        successCallBack((T) string, callBack);
                    } else {
                        LogUtils.i(TAG, actionUrl + ": " + "服务器错误");
                        failedCallBack("服务器错误", callBack);
                    }
                }
            });
            return call;
        } catch (Exception e) {
            LogUtils.e(TAG, e.toString());
        }
        return null;

    }

    public interface ReqCallBack<T> {
        /**
         * 响应成功
         */
        void onReqSuccess(T result);

        /**
         * 响应失败
         */
        void onReqFailed(String errorMsg);
    }

    /**
     * 统一为请求添加头信息
     *
     * @return
     */
    private Request.Builder addHeaders() {
        Request.Builder builder = new Request.Builder().addHeader("Connection", "keep-alive").addHeader("platform", "2").addHeader("phoneModel", Build.MODEL)
                .addHeader("systemVersion", Build.VERSION.RELEASE).addHeader("appVersion", "3.2.0");
        if (getHeadersParams().size() > 0) {
            for (String key : getHeadersParams().keySet()) {
                builder.addHeader(key, getHeadersParams().get(key));
            }
        }
        return builder;
    }

    public HashMap<String, String> getHeadersParams() {
        return headersParams;
    }

    public void setHeadersParams(HashMap<String, String> headersParams) {
        this.headersParams = headersParams;
    }

    /**
     * 统一同意处理成功信息
     *
     * @param result
     * @param callBack
     * @param <T>
     */
    private <T> void successCallBack(final T result, final ReqCallBack<T> callBack) {
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onReqSuccess(result);
                }
            }
        });
    }

    /**
     * 统一处理失败信息
     *
     * @param errorMsg
     * @param callBack
     * @param <T>
     */
    private <T> void failedCallBack(final String errorMsg, final ReqCallBack<T> callBack) {
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    checkLoadFailReason();
                    callBack.onReqFailed(errorMsg);
                }
            }
        });
    }

    /**
     * 检测不成功的原因
     */
    private void checkLoadFailReason() {
        if (!HttpConfig.isNetworkAvailable(ShopApplication.getInstance())) {
            ToastUtils.toastshort("网络君出问题了,重启网络试试!");
        } else {
            ToastUtils.toastshort("服务器需要休息了,请稍后再试!");
        }
    }
}
