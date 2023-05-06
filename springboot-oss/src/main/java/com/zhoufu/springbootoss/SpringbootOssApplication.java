package com.zhoufu.springbootoss;

import cn.xuyanwu.spring.file.storage.EnableFileStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot启动类中增加注解@EnableFileStorage，显式的开启文件上传功能
 */
@EnableFileStorage // 文件上传工具
@SpringBootApplication
public class SpringbootOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootOssApplication.class, args);
    }

}
