package com.zhoufu.springbootsecurityjwt.controller;

import com.zhoufu.springbootsecurityjwt.annotation.IgnoreWebSecurity;
import org.springframework.web.bind.annotation.*;

/**
 * @author zf
 * @Description
 * @CreateTime 2022/12/22 15:18
 **/
@RestController
@RequestMapping("/test")
public class TestIgnoreController {

    @GetMapping("/image/{id}")
    public String image(@PathVariable String id) {
        return id;
    }

    @IgnoreWebSecurity("/css/*")
    @GetMapping("/css/{id}")
    public String css(@PathVariable String id) {
        return id;
    }

    @IgnoreWebSecurity
    @GetMapping("/login")
    public String login() {
        return "登录成功";
    }

}
