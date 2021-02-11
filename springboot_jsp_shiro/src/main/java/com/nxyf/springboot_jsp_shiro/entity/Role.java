package com.nxyf.springboot_jsp_shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @program: shiro
 * @description:
 * @author: myj
 * @create: 2020-12-24 22:42
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    private Integer id;

    private String name;

    //定义权限集合
    private List<Pers> pers;
}