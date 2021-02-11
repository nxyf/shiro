package com.nxyf.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @description:
 * @author: myj
 * @create: 2020-12-22 19:42
 * @Version 1.0
 */
public class CustomerMD5Realm extends AuthorizingRealm{
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("primaryPrincipal = " + primaryPrincipal);
        //根据身份信息，获取当前用户的角色信息  权限信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //将数据库查询的角色信息赋值给权限对象
        info.addRole("admin");
        info.addRole("user");
        //将数据库查询的权限信息赋值给权限对象
        info.addStringPermission("user:*:01");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        if ("admin".equals(principal)) {
            SimpleAuthenticationInfo info =
                    new SimpleAuthenticationInfo(principal, "1e7767cf547d942fdb1c05595f8c9f12",
                            ByteSource.Util.bytes("nxyf"),this.getName());
            return info;
        }
        return null;
    }
}
