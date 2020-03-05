package com.jsong.wiki.backend.filter;

import com.jsong.wiki.backend.bean.CookieProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: Jsong
 * @Date: 2020/3/4 21:53
 * @Description:
 */
//@Order(0)
//@WebFilter(urlPatterns = "/*")
//@Component
public class CookieFilter implements Filter {

    @Autowired
    private CookieProperties cookieProperties;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(111111111);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        for (Cookie cookie : cookies) {
            cookie.setPath(cookieProperties.getPath());
            cookie.setDomain(cookieProperties.getDomain());
            cookie.setHttpOnly(cookieProperties.getHttpOnly());
        }
        chain.doFilter(request, response);
    }
}
