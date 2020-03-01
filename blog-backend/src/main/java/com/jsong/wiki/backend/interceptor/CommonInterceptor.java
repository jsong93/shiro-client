package com.jsong.wiki.backend.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Component
@Log4j2
public class CommonInterceptor implements HandlerInterceptor {
    /**
     * 跨域拦截器
     *
     * @param request
     * @param response
     * @param handler
     * @return boolean
     * @date 2020/2/27 21:56
     * @author Jsong
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("拦截器");
        response.setHeader("Access-Control-Allow-Origin", "null");
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            cookies[i].setPath("/");
            cookies[i].setDomain("127.0.0.1");
//            response.addCookie(cookies[i]);
        }
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:28080");
        if (request.getMethod().equals("OPTIONS")) {
            response.addHeader("Access-Control-Allow-Methods", "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type,Accept,Authorization");
        }
        return true;
    }
}
