package com.zhoufu.springbootswagger2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName : SwaggerConfiguration
 * @Author : ZhouFu
 * @Date: 2023/4/25 14:28
 * @Description : swagger2集成配置类
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        String groupName="swagger集成接口-1.0版本";
        Docket docket=new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("swagger集成接口文档")
                        .description("展示swagger界面")
                        .termsOfServiceUrl("http://localhost:8080")
                        .contact(new Contact("zhoufu","http://localhost:8080/doc.html", "1515349947@qq.com"))
                        .version("3.0")
                        .build())
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zhoufu.springbootswagger2.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
