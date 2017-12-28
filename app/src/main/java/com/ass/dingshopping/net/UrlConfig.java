package com.ass.dingshopping.net;

/**
 * Created by Air on 2017/8/7 0007.
 */

public class UrlConfig {



    public final static String Icon = "desbang100002";
    public final static String ActionBaseConfig = "Configer.GetConfig";
    public final static String ActionCheckUpdate = "Configer.CheckVersion";
    public final static String ActionVerify = "Verify.Send";
    public final static String ActionCkCode = "Verify.Check";
    public final static String ActionRegister = "Passport.Register";
    public final static String ActionLogin = "Passport.Login";
    public final static String ActionResetPwd = "UserCenter.ResetLoginPassword";
    public final static String ActionChangPwd = "UserCenter.UpdateLoginPassword";  //修改密码
    public final static String ActionWxJs = "Weixin.GetJsapiSign";//微信JSAPI签名
    public final static String ActionWxLogin = "Weixin.WcOAuth2";//微信授权登录
    public final static String ActionIputFa = "Violation.GetInputRule";//查询发动机输入规则
    public final static String ActionCarContent = "Violation.GetInputInfo"; //获取车牌信息
    public final static String ActionRules = "Violation.Query";  //违章查询
    public final static String ActionCarMoney = "Violation.QueryPrice";  //最新报价


    public final static String ActionGetUser = "UserCenter.GetUserInfo";  //获取个人信息
    public final static String ActionGetPay = "Payment.GetPayChannelList";  //获取支付通道
    public final static String ActionUpOrder = "Violation.CreateOrder";
    public final static String ActionGetRulesList = "Violation.GetQueryHistory";
    public final static String ActionQueryOrderList = "Violation.GetOrderList";
    public final static String ActionOrderDetails = "Violation.GetOrderDetailList";
    public final static String ActionPays = "Payment.GetPayInfo";//订单支付
    public final static String ActionGetImage = "/qrcode/createshare";
    public final static String UpImage = "Support.UploadImage";
    public final static String ActionGetBankCardList = "UserCenter.GetBankCardList";
    public final static String ActionGetCityList = "Support.GetAreaList";
    public final static String ActionGetCityListAll = "Support.GetAllAreaList";
    public final static String ActionBranchList = "Support.GetBranchList";  //开户分行列表
    public final static String ActionDelBank = "UserCenter.RemoveBankCard";



}
