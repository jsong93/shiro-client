package com.jsong.wiki.backend.controller;

import com.jsong.wiki.backend.bean.ShiroUrl;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/shiro")
public class ShiroController {

    @Autowired
    private ShiroUrl shiroUrl;

    @ResponseBody
    @RequestMapping("/hello")
    public String getHello() {
        return "hello world";
    }

    @ResponseBody
    @RequiresRoles("admin")
    @RequestMapping("/role")
    public String getRole(){
        return "admin";
    }

    /**
     * 在ShiroConfig中配置logoutFilter登录会直接重定向到logout，不知道为什么？？？？？
     * 求解答
     * @return
     */
    @RequestMapping("/logout")
    public  String logout(){
        return "redirect:"+shiroUrl.getLogoutUrl();
    }
}
