package com.jsong.wiki.backend.config;

import com.jsong.wiki.backend.filter.CookieFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Jsong
 * @Date: 2020/3/4 22:06
 * @Description:
 */

//@Configuration
public class webConfig  {

    @Bean
    public FilterRegistrationBean cookieConfig(CookieFilter cookieFilter){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(cookieFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;
    }
}
