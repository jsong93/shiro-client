package com.jsong.wiki.backend.filter;

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * casfilter过滤器
 *
 * @Author: Jsong
 * @Date: 2020/2/27 21:58
 * @Description:
 */
//@Component
@Log4j2
public class MyAuthenticationFilter extends CasFilter {

    //    @Value("${front.baseUrl}")
//    private String frontBaseUrl;
    @Value("{shiro.loginUrl}")
    private String loginUrl;
    @Value("${front.url}")
    private String frontUrl;
    @Value("${api.uri}")
    private String apiUri;

    private String test = "http://127.0.0.1:28080/#";

//    @Autowired
//    private MyFormAuthenticationFilter myFormAuthenticationFilter;

    /***
     *登录过的用户重定向到请求的controller
     * 第一次登录的用户重定向到前端地址
     * @date 2020/3/3 21:00
     * @author Jsong
     * @param request
     * @param response
     * @return void
     */
    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
//        String originalUrl = ((ShiroHttpServletRequest) request).getHeader(apiUri);

        // 不再重hearder中获取，改为重cookie中获取
        String originalUrl = null;
//        String api_uri = null;
//        String front_url = null;

        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
//                if (apiUri.equals(cookie.getName())) {
//                    api_uri = cookie.getValue();
//                } else if (frontUrl.equals((cookie.getName()))) {
//                    front_url = cookie.getValue();
//                }
                if (frontUrl.equals((cookie.getName()))) {
//                    front_url = cookie.getValue();
                    originalUrl = cookie.getValue();
                }
            }
            // 重定向到api 或者前端页面
//            originalUrl = api_uri != null ? api_uri : front_url;
        }

//        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
//        if (originalUrl == null || "".equals(originalUrl)) {
//            // 重cookie中获取上次请求的地址，登录成功后，重定向到前端
//            Cookie[] cookies = ((ShiroHttpServletRequest) request).getCookies();
//
//            if (cookies != null) {
//                for (Cookie cookie : cookies) {
//                    if (frontUrl.equals(cookie.getName())) {
//                        // 神奇的问题
////                    originalUrl = frontBaseUrl+ cookie.getValue();
////                    originalUrl = "http://127.0.0.1:28080/#"+ cookie.getValue();
////                    originalUrl = frontBaseUrl + "/blog-edit";
////                    originalUrl = test + cookie.getValue();
////                    originalUrl = "http://127.0.0.1:28080/#/";
//                        originalUrl = cookie.getValue();
//                        log.info("originalUrl:" + originalUrl);
//                    }
//                }
//            }

//        String requestUri = savedRequest.getRequestURI();
//        String redirectUrl = null;
//        String[] requestArray = requestUri.split("/blog-backend");
//        if(requestArray!=null&&requestArray.length>1){
//            redirectUrl = requestArray[1];
//        }
        log.info(originalUrl);
        try {
            WebUtils.redirectToSavedRequest(request, response, originalUrl);
        } catch (Exception e) {
            log.error(e.getStackTrace());
        }

//        }
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
