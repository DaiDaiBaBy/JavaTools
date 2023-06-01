package com.zhoufu.springbootdatabaselock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootDatabaseLockApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDatabaseLockApplication.class, args);
	}

}
