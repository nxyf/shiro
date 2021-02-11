package com.nxyf.springboot_thymeleaf_shiro.shiro.realms;



import com.nxyf.springboot_thymeleaf_shiro.entity.Pers;
import com.nxyf.springboot_thymeleaf_shiro.entity.User;
import com.nxyf.springboot_thymeleaf_shiro.service.UserService;
import com.nxyf.springboot_thymeleaf_shiro.shiro.salt.MyByteSource;
import com.nxyf.springboot_thymeleaf_shiro.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class CustomerRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        //获取身份信息
        String principal = (String) principalCollection.getPrimaryPrincipal();
        //根据主身份信息，获取角色 和权限信息
        UserService userService = (UserService) ApplicationContextUtils.getBean("userServiceImpl");
        User user = userService.findRolesByUserName(principal);
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //角色信息
            user.getRoles().forEach(role -> {
                info.addRole(role.getName());
                //权限信息
                List<Pers> pers = userService.findPermsByRoleId(role.getId().toString());
                if (!CollectionUtils.isEmpty(pers)) {
                    pers.forEach(per -> {
                        info.addStringPermission(per.getName());
                    });
                }
            });
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        System.out.println("认证");
        //从工厂获取service对象
        UserService userService = (UserService) ApplicationContextUtils.getBean("userServiceImpl");
        User user = userService.findByUserName(principal);
        if (!ObjectUtils.isEmpty(user)) {
          /*  return new SimpleAuthenticationInfo(principal, user.getPassword(),
                    ByteSource.Util.bytes(user.getSalt()), this.getName());*/
            //自定义盐实现序列化
            return new SimpleAuthenticationInfo(principal, user.getPassword(),
                  new MyByteSource(user.getSalt()), this.getName());
        }
        return null;
    }
}