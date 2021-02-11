package com.nxyf;

import com.nxyf.realm.CustomerMD5Realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

/**
 * @description:
 * @author: myj
 * @create: 2020-12-22 19:43
 * @Version 1.0
 */
public class TestCustomerMD5Realm {

    public static void main(String[] args) {
        //创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //注入realm
        CustomerMD5Realm realm = new CustomerMD5Realm();
        //设置realm使用hash凭证匹配器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //使用的算法
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列次数
        hashedCredentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(hashedCredentialsMatcher);
        securityManager.setRealm(realm);
        //将安全管理器注入安全工具
        SecurityUtils.setSecurityManager(securityManager);
        //获取subject
        Subject subject = SecurityUtils.getSubject();
        //认证
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123");
       try {
           subject.login(token);
       }catch (Exception e){
           e.printStackTrace();
       }
       //授权
        if (subject.isAuthenticated()) {
            //基于角色控制
            boolean admin = subject.hasRole("admin");
            System.out.println("admin = " + admin);
            //基于多角色权限控制
            boolean b = subject.hasAllRoles(Arrays.asList("admin", "user"));
            System.out.println("b = " + b);
            //是否具有其中一个
            boolean[] roles = subject.hasRoles(Arrays.asList("admin", "user"));
            for (boolean role : roles) {
                System.out.println("role = " + role);
            }
            System.out.println("=======================");
            //基于权限字符串的的访问控制  资源标识符:操作:资源类型
            boolean permitted = subject.isPermitted("user:*:01");
            System.out.println("permitted = " + permitted);
            //分别具有那些权限
            subject.isPermitted("user:*:01", "order:update");
            //同时具有那些权限
            subject.isPermittedAll("user:*:01", "order:update");
        }
    }

}
