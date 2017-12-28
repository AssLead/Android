package com.ass.dingshopping.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 配置
 */
public class HttpConfig {


//    public static final String URL_HOST = "http://ltapi.desbang.com/newapi/index";

    //    public static final String URL_HOST = "http://ltapi.weizhang886.cn/newapi/index"; //开发
//    public static final String URL_HOST = "http://tapi.weizhang886.cn/newapi/index";  //测试外网
    public static final String URL_HOST = "http://api.weizhang886.com/newapi/index";    //生产URL
//    public static final String URL_HOST = "http://tapi.weizhang886.com/newapi/index";    //准生产URL


//    public final static String AppId = "desbang100001";
        public final static String AppId = "DSB636423033124241239";
    public final static int FromChannel = 20;
    //    public static final String QR_URL = "http://aapi.acarbang.com/"; //生产
    public static final String QR_URL = "http://api.weizhang886.com/";
    public static final String QR_CODE = QR_URL + "qrcode/create?w=240&h=240&cont=";
    public static String IMG_URL = "";
    public static String AcarbangImg1Host = "";
    public static String SHENG = "";
    public static String QrToken = "";
    public static String protocol_url = "http://m.desbang.com/wap/Passport/register_info.html";


        public final static String AppKey = "EEA29175C932424CA484DBD3E8B6ECDB";
//    public final static String AppKey = "00000000000000000000000000000020";


    public static String APP_ID_Pay;
    public static String APP_ID_Share = "wxbe5296df1a9c55e0";
    public static int MinCode = -112;
    public static int MaxCode = -105;


    public static String SAO_URL = "";

    /**
     * 判断是否有网络
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        if (context == null) return false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null == cm) {
            return false;
        } else {
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
