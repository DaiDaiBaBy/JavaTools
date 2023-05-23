package com.zhoufu.springbootqiniucloud.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.zhoufu.springbootqiniucloud.utils.JacksonAnnotationIntrospectorEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @ClassName : SwaggerConfiguration
 * @Author : ZhouFu
 * @Date: 2023/5/22 16:01
 * @Description :
 */
@Configuration
@EnableSwagger2
@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    private final Logger LOG = LoggerFactory.getLogger(SwaggerConfiguration.class);

    public static final String DEFAULT_INCLUDE_PATTERN = "/.*";

    private final ApplicationContext applicationContext;

    @Autowired
    public SwaggerConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public ObjectMapper setObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        objectMapper.registerModule(module);
        objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospectorEx());
        return objectMapper;
    }

    /**
     * Swagger Springfox configuration.
     *
     * @param  the properties of the application
     * @return the Swagger Springfox configuration
     */
    @Bean
    public Docket swaggerSpringfoxDocket() {
        LOG.debug("Starting Swagger");
        StopWatch watch = new StopWatch();
        watch.start();
//        Contact contact = new Contact(
//                properties.getSwagger().getContactName(),
//                properties.getSwagger().getContactUrl(),
//                properties.getSwagger().getContactEmail());

        ApiInfo apiInfo = new ApiInfo("项目demo - 接口文档","项目demo - 接口文档","1.0.0",null,null,null, null);
//                properties.getSwagger().getTitle(),
//                properties.getSwagger().getDescription(),
//                properties.getSwagger().getVersion(),
//                properties.getSwagger().getTermsOfServiceUrl(),
//                properties.getSwagger().getContactName(),
//                properties.getSwagger().getLicense(),
//                properties.getSwagger().getLicenseUrl());

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .select()
                .paths(regex(DEFAULT_INCLUDE_PATTERN))
                .build();

        watch.stop();
        LOG.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
        return docket;
    }
}
