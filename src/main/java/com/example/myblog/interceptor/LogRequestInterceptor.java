package com.example.myblog.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Slf4j
@Component
public class LogRequestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(String.format("[preHandle][%s][%s]%s%s", request, request.getMethod(), request.getRequestURI(), getParameters(request)));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("[postHandle]");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            ex.printStackTrace();
        }
        log.info(String.format("[afterCompletion][%s][exception:%s]", request, ex));
    }

    //提取请求中的参数
    private String getParameters(HttpServletRequest request) {
        StringBuilder parameterBuilder = new StringBuilder();
        Enumeration<String> names = request.getParameterNames();
        if (names != null) {
            parameterBuilder.append("?");
            while (names.hasMoreElements()) {
                if (parameterBuilder.length() > 1) {
                    parameterBuilder.append("&");
                }
                String pointer = names.nextElement();
                parameterBuilder.append(pointer).append("=").append(request.getParameter(pointer));
            }
        }
        return parameterBuilder.toString();
    }

}
