package com.nxyf.springboot_jsp_shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: shiro
 * @description:
 * @author: myj
 * @create: 2020-12-24 22:43
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Pers implements Serializable {

    private Integer id;

    private String name;

    private String url;
}