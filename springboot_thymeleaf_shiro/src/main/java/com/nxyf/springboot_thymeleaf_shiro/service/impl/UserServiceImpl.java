package com.nxyf.springboot_thymeleaf_shiro.service.impl;


import com.nxyf.springboot_thymeleaf_shiro.dao.UserDao;
import com.nxyf.springboot_thymeleaf_shiro.entity.Pers;
import com.nxyf.springboot_thymeleaf_shiro.entity.User;
import com.nxyf.springboot_thymeleaf_shiro.service.UserService;
import com.nxyf.springboot_thymeleaf_shiro.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {

        //生成随机盐
        String salt = SaltUtils.getSalt(4);
        //将盐保存到数据库
        user.setSalt(salt);
        //将明文密码进行加密
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        userDao.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public User findRolesByUserName(String userName) {
        return userDao.findRolesByUserName(userName);
    }

    @Override
    public List<Pers> findPermsByRoleId(String id) {
        return userDao.findPermsByRoleId(Integer.parseInt(id));
    }
}