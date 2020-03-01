package com.jsong.wiki.backend.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jsong
 * @Date: 2020/2/27 21:58
 * @Description:
 */
@Component
@Log4j2
public class MyAuthenticationFilter extends CasFilter {

    @Value("${front.baseUrl}")
    private String frontBaseUrl;
    @Value("{shiro.loginUrl}")
    private String loginUrl;

    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        String originalUrl = ((ShiroHttpServletRequest)request).getHeader("original-url");
        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
        if(originalUrl==null){
            originalUrl = "/";
        }
        log.info(originalUrl);
        WebUtils.redirectToSavedRequest(request, response, originalUrl);
    }

}
