package com.jsong.wiki.backend.filter;

import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author: Jsong
 * @Date: 2020/2/27 21:58
 * @Description:
 */
@Component
public class MyAuthenticationFilter extends CasFilter {

    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("1111111111");
        WebUtils.redirectToSavedRequest(request, response, getSuccessUrl());
    }
}
