package com.zhoufu.springbootdistributedxxxjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootDistributedXxxJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDistributedXxxJobApplication.class, args);
    }

}
