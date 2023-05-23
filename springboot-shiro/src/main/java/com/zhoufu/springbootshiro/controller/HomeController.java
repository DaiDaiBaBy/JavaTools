package com.zhoufu.springbootshiro.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  home入口
 */
@Controller
public class HomeController {

    @RequestMapping({"/","/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request,Map<String,Object> map) {
        System.out.println("HomeController->login");
        String exception = (String)request.getAttribute("shiroLoginFailure");
        System.out.println("exception="+exception);
        String msg = "";
        if(exception !=null) {
            if(UnknownAccountException.class.getName().equals(exception)) {
                msg = "UnknownAccountException->账号不存在";
            } else if(IncorrectCredentialsException.class.getName().equals(exception)) {
                msg = "IncorrectCredentialsException->密码不正确";
            } else if("kaptchaValidateFailed".equals(exception)) {
                msg = "验证码错误";
            } else {
                msg = " else ->"+exception;
            }
            map.put("msg", msg);
            return "login";
        }
        map.put("msg", "login success");
        return "index";
    }

    @RequestMapping("/403")
    public String unauthorizedRole() {
        System.out.println("HomeController->unauthorizedRole");
        return "403";
    }
}
