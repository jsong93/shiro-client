package com.jsong.wiki.backend.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class ShiroCasRealm extends CasRealm {
    /**
     * 设置角色 权限信息
     * @param principals
     * @return
     */
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String account = (String) principals.getPrimaryPrincipal();
        System.out.println(account);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    /**
     * cas 认证
     * @param token
     * @return
     */
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token){
        AuthenticationInfo authc = super.doGetAuthenticationInfo(token);
        return authc;
    }
}
