package com.zhoufu.springbootdistributedxxxjob.annotation;

import java.lang.annotation.*;

/**
 * 定义分布式锁注解
 *
 * @author zf
 * @description Redis独享锁信息
 **/
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {

    /**
     * 锁的名称(唯一标识)，可选为""时候使用方法的名称
     */
    String name() default "";

    /**
     * 重试重获取锁的次数,默认0 不重试
     */
    int retry() default 0;

    /**
     * 占有锁的时间,避免程序宕机导致锁无法释放
     */
    int expired() default 60;

}
