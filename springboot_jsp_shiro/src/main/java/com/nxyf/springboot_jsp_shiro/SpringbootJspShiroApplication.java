package com.nxyf.springboot_jsp_shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nxyf.springboot_jsp_shiro.dao")
public class SpringbootJspShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJspShiroApplication.class, args);
	}

}
