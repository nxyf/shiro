package com.nxyf.springboot_thymeleaf_shiro.service;



import com.nxyf.springboot_thymeleaf_shiro.entity.Pers;
import com.nxyf.springboot_thymeleaf_shiro.entity.User;

import java.util.List;

public interface UserService {

    void register(User user);

    User findByUserName(String userName);

    User findRolesByUserName(String userName);

    //根据角色id查询权限集合
    List<Pers> findPermsByRoleId(String id);
}