package com.example.myblog.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author RaoShiqi
 * @Classname SessionFilter
 * @Description TODO
 * @Date 2020/8/12 20:29
 */
@Slf4j
@WebFilter(urlPatterns = "/secret/*")
public class SessionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //读取Cookie
        Cookie[] cookies = Optional.ofNullable(((HttpServletRequest) servletRequest).getCookies())
                .orElse(new Cookie[0]);
        boolean unauthorized = true;
        for (Cookie cookie : cookies) {
            if ("sessionId".equals(cookie.getName()) && "test-token".equals(cookie.getValue())) {
                unauthorized = false;
            }
        }
        if (unauthorized) {
            log.error("UNAUTHORIZED");
            unauthorizedResp(servletResponse);
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private void unauthorizedResp(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.getWriter().write("UNAUTHORIZED");
    }
}
