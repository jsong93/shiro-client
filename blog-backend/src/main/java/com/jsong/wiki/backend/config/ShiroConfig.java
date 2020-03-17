package com.jsong.wiki.backend.config;

import com.jsong.wiki.backend.bean.ShiroUrl;
import com.jsong.wiki.backend.filter.MyAuthenticationFilter;
import com.jsong.wiki.backend.filter.MyFormAuthenticationFilter;
import com.jsong.wiki.backend.filter.MyUserFilter;
import com.jsong.wiki.backend.shiro.ShiroCasRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    // shiroFilter
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager,
                                              @Qualifier("casFilter") MyAuthenticationFilter casFilter,
//                                              @Qualifier("logoutFilter") LogoutFilter logoutFilter,
                                              @Qualifier("userFilter") MyUserFilter userFilter,
                                              MyFormAuthenticationFilter myFormAuthenticationFilter,
                                              ShiroUrl shiroUrl) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        设置登录URL,当用户未认证,但访问了需要认证后才能访问的页面,就会自动重定向到登录URL
        shiroFilterFactoryBean.setLoginUrl(shiroUrl.getLoginUrl());
        shiroFilterFactoryBean.setSuccessUrl(shiroUrl.getSuccessUrl());
//        设置没有权限的URL,当用户认证后,访问一个页面却没有权限时,就会自动重定向到没有权限的URL,若用户未认证访问一个需要权限的URL时,会跳转到登录URL
        shiroFilterFactoryBean.setUnauthorizedUrl(shiroUrl.getUnauthorizedUrl());

        Map<String, Filter> filters = new HashMap<>();

//        org.apache.shiro.web.filter.mgt.DefaultFilter 包含所有的过滤器
        filters.put("casFilter", casFilter);
        filters.put("userFilter", userFilter);
        filters.put("authc", myFormAuthenticationFilter);
//        filters.put("logoutFilter", logoutFilter);
//        将Filter添加到Shiro过滤器链中,用于对资源设置权限
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
        filterChainDefinitionMap.put(shiroUrl.getCasFilterUrlPattern(), "casFilter");
//        filterChainDefinitionMap.put(shiroUrl.getCasFilterUrlPattern(), "userFilter");
//        filterChainDefinitionMap.put(shiroUrl.getLogoutUrlPattern(), "logoutFilter");
        filterChainDefinitionMap.putAll(shiroUrl.getAuthUrlPatternMap());
        // 配置哪些请求需要受保护,以及访问这些页面需要的权限
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    // 认证filter
    @Bean
    public MyAuthenticationFilter casFilter(ShiroUrl shiroUrl) {
        MyAuthenticationFilter casFilter = new MyAuthenticationFilter();
        // 登录成功url
        casFilter.setSuccessUrl(shiroUrl.getSuccessUrl());
        // 登录失败url
        casFilter.setFailureUrl(shiroUrl.getFailureUrl());
        return casFilter;
    }

  /***
   * 配置登录后重定向filter
   * @date 2020/2/29 20:41
   * @author Jsong
   * @param
   * @return com.jsong.wiki.backend.filter.MyUserFilter
   */
    @Bean
    public MyUserFilter userFilter(){
        return new MyUserFilter();
    }

    @Bean
    public MyFormAuthenticationFilter myFormAuthenticationFilter(){
        return new MyFormAuthenticationFilter();
    }

    // 自定义 casRealm
    @Bean
    public ShiroCasRealm casRealm(ShiroUrl shiroUrl, EhCacheManager ehCacheManager
    ) {
        ShiroCasRealm casRealm = new ShiroCasRealm();
        // cas服务器
        casRealm.setCasServerUrlPrefix(shiroUrl.getCas().getServerUrlPrefix());
        // 客户端地址，用于接收tiket
        casRealm.setCasService(shiroUrl.getCas().getService());
        casRealm.setCacheManager(ehCacheManager);
        return casRealm;
    }

    /**
     * 问题？？？？？
     * 把这个bean注入后，登录后就会重定向到登出，为什么呢？？？？？
     *
     * @param shiroUrl
     * @return
     */
//    @Bean
    public LogoutFilter logoutFilter(ShiroUrl shiroUrl) {
        LogoutFilter logoutFilter = new LogoutFilter();
        // 登出后重定向地址
        logoutFilter.setRedirectUrl(shiroUrl.getLogoutUrl());
        return logoutFilter;
    }

    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return ehCacheManager;
    }

    //    配置securityManager SecurityManager,Shiro的安全管理，主要是身份认证的管理，缓存管理，cookie管理
    @Bean
    public SecurityManager securityManager(ShiroCasRealm casRealm, EhCacheManager ehCacheManager
    ) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSubjectFactory(new CasSubjectFactory());
        securityManager.setCacheManager(ehCacheManager);
        securityManager.setRealm(casRealm);
        return securityManager;
    }

    //    配置lifecycleBeanPostProcessor,shiro bean的生命周期管理器,可以自动调用Spring IOC容器中shiro bean的生命周期方法(初始化/销毁)
//    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /*    为了支持Shiro的注解需要定义DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor两个bean
        配置DefaultAdvisorAutoProxyCreator,必须配置了lifecycleBeanPostProcessor才能使用*/
//    @DependsOn("lifecycleBeanPostProcessor")
//    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        return defaultAdvisorAutoProxyCreator;
    }

    //    配置AuthorizationAttributeSourceAdvisor
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
           return authorizationAttributeSourceAdvisor;
    }
}
