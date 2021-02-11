package com.nxyf.springboot_thymeleaf_shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: shiro
 * @description:
 * @author: myj
 * @create: 2020-12-26 15:47
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("---------");
        return "index";
    }
}