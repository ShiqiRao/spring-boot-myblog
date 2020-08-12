package com.example.myblog.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author RaoShiqi
 * @Classname SessionFilter
 * @Description TODO
 * @Date 2020/8/12 20:29
 */
@Slf4j
@WebFilter(urlPatterns = "/file/*")
public class SessionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //读取Cookie
        Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();
        for (Cookie cookie : cookies) {
            if ("sessionId".equals(cookie.getName()) && "test-info".equals(cookie.getValue())) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
        log.error("UNAUTHORIZED");
    }

}
