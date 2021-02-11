package com.nxyf.springboot_jsp_shiro.dao;

import com.nxyf.springboot_jsp_shiro.entity.Pers;
import com.nxyf.springboot_jsp_shiro.entity.Role;
import com.nxyf.springboot_jsp_shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    void save(User user);

    User findByUserName(String userName);

   User findRolesByUserName(String userName);

    //根据角色id查询权限集合
    List<Pers> findPermsByRoleId(Integer id);
}