package com.nxyf.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @description:自定义realm
 * @author: myj
 * @create: 2020-12-21 22:19
 * @Version 1.0
 */
public class CustomerRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取用户名
        String userName = (String) authenticationToken.getPrincipal();
        //2.根据用户名进行数据库查询
        if ("admin".equals(userName)) {
            //参数1 数据库返回的用户名  参数2 数据库返回的密码  参数3 当前realm名字
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName,"123",this.getName());
            return info;
        }
        return null;
    }
}
