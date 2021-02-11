package com.nxyf;

import com.nxyf.realm.CustomerRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @description:自定义realm
 * @author: myj
 * @create: 2020-12-21 22:22
 * @Version 1.0
 */
public class TestCustomerRealmAuthorizing {

    public static void main(String[] args) {
        //1.创建securityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //2.设置realm
        securityManager.setRealm(new CustomerRealm());
        //3.安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        //4.获取subject
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123");
        //5.认证
        try {
            subject.login(token);
        }catch (UnknownAccountException unknownAccountException){
            unknownAccountException.printStackTrace();
            System.out.println("用户名不存在");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
