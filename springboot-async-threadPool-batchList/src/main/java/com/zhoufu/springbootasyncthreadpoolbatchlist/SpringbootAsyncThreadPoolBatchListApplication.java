package com.zhoufu.springbootasyncthreadpoolbatchlist;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhoufu.springbootasyncthreadpoolbatchlist.mapper")
public class SpringbootAsyncThreadPoolBatchListApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAsyncThreadPoolBatchListApplication.class, args);
    }

}
