package com.zhoufu.springbootaop.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName : LogTrack
 * @Author : ZhouFu
 * @Date: 2023/5/10 11:51
 * @Description : 创建自定义注解: 记录接口调用的传入参数日志为场景,
 *  AOP切面方式去记录每个接口的传入参数以及可扩展的业务处理
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogTrack {
    String value() default "logTracking";
}
