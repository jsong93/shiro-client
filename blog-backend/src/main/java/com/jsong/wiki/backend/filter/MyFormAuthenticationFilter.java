package com.jsong.wiki.backend.filter;

import com.jsong.wiki.backend.bean.CookieProperties;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * authc 的过滤器
 *
 * @Author: Jsong
 * @Date: 2020/3/2 22:24
 * @Description:
 */

//@Component
@Log4j2
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    @Value("${shiro.loginUrl}")
    private String loginUrl;
    @Value("${front.url}")
    private String frontUrl;

//    protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
//        log.info("-----------------redirect login --------------------");
//        Cookie cookie = new Cookie("front-url", ((HttpServletRequest) request).getHeader("front-url"));
//        ((HttpServletResponse) response).addCookie(cookie);
//        saveRequest(request);
//        redirectToLogin(request, response);
//    }
    @Autowired
    private CookieProperties cookieProperties;

    /***
     *重写登录重定向
     * 把请求内容存入cookie，重定向的时候带着cookie保存状态
     * @date 2020/3/3 14:46
     * @author Jsong
     * @param request
     * @param response
     * @return boolean
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }
            log.info("-----------------redirect login --------------------");


//            Cookie[] cookies = ((HttpServletRequest) request).getCookies();
//            for (Cookie cookie : cookies) {
//                cookie.setDomain(cookieProperties.getDomain());
//                cookie.setPath(cookieProperties.getPath());
//                cookie.setHttpOnly(cookieProperties.getHttpOnly());
//            }

//            Cookie cookie = new Cookie(frontUrl, ((HttpServletRequest) request).getHeader(frontUrl));
//            cookie.setDomain(cookieProperties.getDomain());
//            cookie.setPath(cookieProperties.getPath());
//            cookie.setHttpOnly(cookieProperties.getHttpOnly());
//            ((HttpServletResponse) response).addCookie(cookie);

//            saveRequestAndRedirectToLogin(request, response);
            saveRequest(request);
//            redirectToLogin(request, response);
            // 读取到的 /index.jsp 不知道为啥
//            String loginUrl = getLoginUrl();

//            shiro 会把请求内容保存起来，未登录为空
//            if(WebUtils.getSavedRequest(request)==null){
//                // 未授权，返回前端状态401 进行重定向
//            request里获取不到用户信息，不能通过request判断是否登录，不能用这个方法了
//            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return false;
//            }
            // ajax 重定向会返回html页面字符串
            WebUtils.issueRedirect(request, response, loginUrl);
            return false;
        }
    }
}
