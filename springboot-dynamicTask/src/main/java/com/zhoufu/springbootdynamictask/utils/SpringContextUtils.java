package com.zhoufu.springbootdynamictask.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @ClassName : SpringContextUtils
 * @Author : ZhouFu
 * @Date: 2023/5/18 15:52
 * @Description : 工具类
 */
@Component
@Lazy(false)
public class SpringContextUtils implements ApplicationContextAware {

    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     *
     * @param applicationContext
     */
    public void setApplicationContext(ApplicationContext applicationContext) {

        SpringContextUtils.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {

        return applicationContext;
    }

    /**
     * 获取对象
     * 这里重写了bean方法，起主要作用
     *
     * @param name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws
     */
    public static <T> T getBean(String name) {
        try {
            return (T) applicationContext.getBean(name);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        try {
            return applicationContext.getBean(clazz);
        } catch (Exception e) {
            return null;
        }
    }

}