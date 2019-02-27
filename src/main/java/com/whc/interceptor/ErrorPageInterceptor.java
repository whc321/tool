package com.whc.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @author wang_haichun
 * @date 2018/11/12
 */
@Component
public class ErrorPageInterceptor extends HandlerInterceptorAdapter {

    private List<Integer> errorCodeList = Arrays.asList(400,403,404,500);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        if (errorCodeList.contains(response.getStatus())) {
            //捕获异常后进行重定向，controller对应的requestMapping为/error/{code}
            response.sendRedirect("/error/" + response.getStatus());
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
