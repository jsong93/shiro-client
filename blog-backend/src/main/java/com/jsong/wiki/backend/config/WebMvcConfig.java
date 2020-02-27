package com.jsong.wiki.backend.config;

import com.jsong.wiki.backend.interceptor.CommonInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 配置跨域拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        CommonInterceptor commonInterceptor = new CommonInterceptor();
        registry.addInterceptor(commonInterceptor).addPathPatterns("/**");
    }
}
