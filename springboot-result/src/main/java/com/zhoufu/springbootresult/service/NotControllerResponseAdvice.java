package com.zhoufu.springbootresult.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @ClassName : NotControllerResponseAdvice
 * @Author : ZhouFu
 * @Date: 2023/5/5 13:51
 * @Description : 新增不进行封装注解: 所有不需要包装的就加上这个注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotControllerResponseAdvice {
}
