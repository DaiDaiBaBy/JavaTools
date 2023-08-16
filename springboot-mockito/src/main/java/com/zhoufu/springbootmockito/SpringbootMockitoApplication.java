package com.zhoufu.springbootmockito;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.zhoufu.springbootmockito.*")
@MapperScan(basePackages = "com.zhoufu.springbootmockito.**.dao")
public class SpringbootMockitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMockitoApplication.class, args);
	}

}
