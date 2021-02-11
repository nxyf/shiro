package com.nxyf.springboot_jsp_shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: shiro
 * @description: 订单服务
 * @author: myj
 * @create: 2020-12-24 20:33
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("save")
    @RequiresRoles(value = {"admin","user"})//判断角色，同时具有
    @RequiresPermissions("user:update:01")
    public String save() {
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        //代码形式
        if (subject.hasRole("admin")) {
            System.out.println("保存订单");
        }else {
            System.out.println("无权访问");
        }
        //基于权限字符串
        return "redirect:/index.jsp";
    }
}