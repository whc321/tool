package com.whc.util;

import com.whc.constant.Constants;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by wang_haichun on 2017/7/26.
 */
public class RequestUtil {
    private static final Logger logger = Logger.getLogger(RequestUtil.class);

    private static final int DEFULT_PORT = 80;


    public static boolean checkWeiXin(HttpServletRequest request){
        Enumeration e1 = request.getHeaderNames();
        while (e1.hasMoreElements()) {
            String headerName = (String) e1.nextElement();
            String headValue = request.getHeader(headerName);
            logger.debug(headerName + "=" + headValue);
            if(!StringUtils.isEmpty(headerName)
                && "user-agent".equalsIgnoreCase(headerName)
                && !StringUtils.isEmpty(headValue)){
                if(headValue.toUpperCase().contains(Constants.MICROMESSENGER)){
                    return true;
                }
            }
        }
        return false;
    }


    public static String getRequestUrl(HttpServletRequest request){
        StringBuilder url = new StringBuilder();
        url.append(request.getScheme()).append("://");
        url.append(request.getServerName());
        if(DEFULT_PORT != request.getServerPort()){
            url.append(":").append(request.getServerPort());
        }
        url.append(request.getRequestURI());
        if(!StringUtils.isEmpty(request.getQueryString())){
            url.append("?").append(request.getQueryString());
        }
        return url.toString();
    }


    public static String getCallUrl(HttpServletRequest request,String pageStr){
        StringBuilder url = new StringBuilder();
        url.append(request.getScheme()).append("://");
        url.append(request.getServerName());
        if(DEFULT_PORT != request.getServerPort()){
            url.append(":").append(request.getServerPort());
        }
        url.append(request.getContextPath());
        url.append("/");
        url.append(pageStr);
        return url.toString();
    }
}
