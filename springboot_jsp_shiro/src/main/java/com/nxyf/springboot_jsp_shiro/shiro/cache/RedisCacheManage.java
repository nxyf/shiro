package com.nxyf.springboot_jsp_shiro.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @program: shiro
 * @description:自定义shiro缓存管理器
 * @author: myj
 * @create: 2020-12-26 10:07
 */
public class RedisCacheManage implements CacheManager {
    //参数 认证或者授权缓存的统一名称  不知道是啥时打印输出  具体实现由Cache<K, V>，自定义实现Cache<K, V>
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        System.out.println("s = " + cacheName);
        return new RedisCache<K, V>(cacheName);
    }
}