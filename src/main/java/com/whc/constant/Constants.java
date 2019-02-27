package com.whc.constant;

public final class Constants {

    /** 开发模式 */
    public static final boolean debug = true;

    /** 微信浏览器标识*/
    public static final String MICROMESSENGER = "MICROMESSENGER";

    /** 公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token*/
//    public static String ACCESS_TOKEN = "";

    /** 调用微信JS接口的临时票据*/
//    public static String JSAPI_TICKET = "";

    /** 微信回调state*/
    public static String REDIRECT_STATE = "weixin";


    public static String SESSION_OPENID = "openId";

    public static final String MESSAGE = "errmsg";

    public static final String MES_OK = "ok";


    /**
     * 公众号ACCESS_TOKEN
     */
    public static String WX_ACCESS_TOKEN = "WX_ACCESS_TOKEN";

    public static String WX_JSAPI_TICKET = "WX_JSAPI_TICKET";


    public interface wxApiUrl{
        /** 获取access_token*/
        String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
        /** 获取jsapi_ticket*/
        String JSAPI_TOKEN = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
        /** 微信回调获取CODE*/
        String WEIXIN_REDIRECT = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=%s#wechat_redirect";
        /** 授权的ACCESS_TOKEN*/
        String AUTHORIZE_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
        /** 获取用户基本信息(UnionID机制)*/
        String USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
        /** 发送模板消息*/
        String SEND_MESSAGE_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";
    }

    public interface resultCode{
        String SUCCESS = "0";
        String FAIL = "1";
        String IS_NULL = "2";
        String COMPLETE = "5";
        String EXISTX = "6";
        String INV_EXPIRED = "7";
        /** 离线开票  不处理*/
        String INV_OFFLINE = "8";
        String CALC_RESULT = "failue";
        String NO_ORDER = "noorder";
    }

    public interface wxInfo{
        String openId = "openId";
        String subscribe = "subscribe";
    }
}
