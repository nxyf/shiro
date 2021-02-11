package com.nxyf;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * @description:
 * @author: myj
 * @create: 2020-12-21 20:05
 * @Version 1.0
 */
public class TestAuthenticator {

    public static void main(String[] args) {
        //创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //给安全管理器设置realm
        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));
        //securityUtils 给全局安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        //创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123");
        //认证
        try {
            System.out.println("认证状态"+subject.isAuthenticated());
            subject.login(token);
            System.out.println("认证状态"+subject.isAuthenticated());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
