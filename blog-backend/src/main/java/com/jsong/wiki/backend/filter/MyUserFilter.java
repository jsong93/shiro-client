package com.jsong.wiki.backend.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.web.filter.authc.UserFilter;
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
 * @Date: 2020/2/29 16:48
 * @Description:
 */
@Component
@Log4j2
public class MyUserFilter extends UserFilter {

    @Value("{shiro.loginUrl}")
    private String loginUrl;
    @Override
    public void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response){
//        Subject subject = SecurityUtils.getSubject();
//        Session session = subject.getSession();
//        HttpServletRequest httpRequest = WebUtils.toHttp(request);
//        SavedRequest savedRequest = new SavedRequest(httpRequest);
//        session.setAttribute();
        log.info("---------------------重定向登录----------------------");
        WebUtils.saveRequest(request);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = null;
        try {
            Map map = new HashMap();
            map.put("redirectUrl", loginUrl);
            printWriter = response.getWriter();
            printWriter.write(JSON.toJSONString(map));
        } catch (IOException e) {
            log.error("登录重定向失败");
            e.printStackTrace();
        }
    }
}
