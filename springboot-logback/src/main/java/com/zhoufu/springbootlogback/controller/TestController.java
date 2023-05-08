package com.zhoufu.springbootlogback.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : TestController
 * @Author : ZhouFu
 * @Date: 2023/5/6 16:44
 * @Description : 测试
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("/logback")
    public void test01() {
        log.error("debug日志收集");
//        log.info("info日志收集测试");
    }
}
