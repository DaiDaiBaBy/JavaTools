package com.zhoufu.springbootqiniucloud;

import com.zhoufu.springbootqiniucloud.config.MyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableConfigurationProperties({MyProperties.class})
@EnableTransactionManagement
@SpringBootApplication
public class SpringbootQiNiuCloudApplication {

	public static void main(String[] args) {
		System.setProperty("es.set.netty.runtime.available.processors","false");
		SpringApplication.run(SpringbootQiNiuCloudApplication.class, args);
	}

}
