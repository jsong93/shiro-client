package com.jsong.wiki.backend.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * casfilter过滤器
 * @Author: Jsong
 * @Date: 2020/2/27 21:58
 * @Description:
 */
//@Component
@Log4j2
public class MyAuthenticationFilter extends CasFilter {

    @Value("${front.baseUrl}")
    private String frontBaseUrl;
    @Value("{shiro.loginUrl}")
    private String loginUrl;
    @Value("${front.uri}")
    private String frontUri;
    @Value("${api.uri}")
    private  String apiUri;

//    @Autowired
//    private MyFormAuthenticationFilter myFormAuthenticationFilter;

    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        String originalUrl = ((ShiroHttpServletRequest) request).getHeader(apiUri);
//        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
        if (originalUrl == null || "".equals(originalUrl)) {
            // 重cookie中获取上次请求的地址，并且重定向到前端
            Cookie[] cookies = ((ShiroHttpServletRequest) request).getCookies();
            for (Cookie cookie : cookies) {
                if(frontUri.equals(cookie.getName())){
                    originalUrl = frontBaseUrl + cookie.getValue();
                }
            }
        }
//        String requestUri = savedRequest.getRequestURI();
//        String redirectUrl = null;
//        String[] requestArray = requestUri.split("/blog-backend");
//        if(requestArray!=null&&requestArray.length>1){
//            redirectUrl = requestArray[1];
//        }
        log.info(originalUrl);
        WebUtils.redirectToSavedRequest(request, response, originalUrl);

    }

//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
//
//        return myOnAccessDenied(request, response);
//    }
//
//    protected boolean myOnAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        return myFormAuthenticationFilter.onAccessDenied(request, response);
//    }

}
