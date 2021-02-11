package com.nxyf.springboot_thymeleaf_shiro.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.nxyf.springboot_thymeleaf_shiro.shiro.cache.RedisCacheManage;
import com.nxyf.springboot_thymeleaf_shiro.shiro.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 用来整合shiro相关的配置类
 */
@Configuration
public class ShiroConfig {

    //1.创建shiroFilter  负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置 安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //配置系统受限资源
        //配置系统公共资源
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("/login.html", "anon");//公共资源，不需要认证
        hashMap.put("/user/getImage", "anon");
        hashMap.put("/user/register", "anon");
        hashMap.put("/user/registerView", "anon");
        hashMap.put("/user/login", "anon");
        hashMap.put("/**", "authc");//请求这个资源需要认证和授权
        //设置默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/user/loginview");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(hashMap);
        return shiroFilterFactoryBean;
    }

    //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置realm
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    //3.自定义realm
    @Bean
    public Realm getRealm(){
        CustomerRealm realm = new CustomerRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //修改凭证校验匹配器
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(credentialsMatcher);
        //开启缓存管理  缓存在在当前应用内存中  应用停止后缓存清除
//        realm.setCacheManager(new EhCacheManager());//使用Ehcache缓存
        realm.setCacheManager(new RedisCacheManage());//使用redis缓存
        realm.setCachingEnabled(true);//开启全局缓存
        realm.setAuthenticationCachingEnabled(true);
        realm.setAuthenticationCacheName("authenticationCache");//认证缓存名称
        realm.setAuthorizationCachingEnabled(true);
        realm.setAuthorizationCacheName("authorizationCache");//授权缓存名称
        return realm;
    }

    /**
     * 整合thymeleaf时  需要加入shiro方言
     */
    @Bean("shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}