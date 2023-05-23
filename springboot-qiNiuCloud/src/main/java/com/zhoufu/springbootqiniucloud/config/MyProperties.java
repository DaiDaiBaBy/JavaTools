package com.zhoufu.springbootqiniucloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName : MyProperties
 * @Author : ZhouFu
 * @Date: 2023/5/22 15:56
 * @Description : 自定义配置文件读取
 */
@Data
@ConfigurationProperties(prefix = "qiniucloud", ignoreUnknownFields = false)
public class MyProperties {
    /**
     * SWAGGER参数
     */
    private final Swagger swagger = new Swagger();

    /**
     * SWAGGER接口文档参数
     */
    @Data
    public static class Swagger {
        private String title;
        private String description;
        private String version;
        private String termsOfServiceUrl;
        private String contactName;
        private String contactUrl;
        private String contactEmail;
        private String license;
        private String licenseUrl;
    }
}
