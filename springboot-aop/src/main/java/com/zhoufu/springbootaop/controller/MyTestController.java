package com.zhoufu.springbootaop.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhoufu.springbootaop.common.LogTrack;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName : MyTestController
 * @Author : ZhouFu
 * @Date: 2023/5/10 14:31
 * @Description : 自定义注解测试
 */
@Controller
@RequestMapping("/test")
public class MyTestController {

    @ResponseBody
    @GetMapping("/testGet1")
    @LogTrack("testGet1 接口")
    public void testGet1(@RequestParam("userId") String userId, @RequestParam("toUserId") String toUserId) {

        System.out.println("已经进入GET测试接口,参数userId：" + userId+  "参数toUserId:"+toUserId);
    }

    @ResponseBody
    @GetMapping("/testGet2")
    @LogTrack("testGet2 接口")
    public void testGet2(@RequestParam Map map) {
        System.out.println("已经进入GET测试接口,参数：" + map.get("userId"));
    }

    @ResponseBody
    @PostMapping("/testPost1")
    @LogTrack("testPost1 接口")
    public void testPost1(@RequestBody Map map) {

        System.out.println("已经进入POST测试接口,参数：" + map.toString());
    }

    @ResponseBody
    @PostMapping("/testPost2")
    @LogTrack("testPost2 接口")
    public void testPost2(@RequestBody JSONObject jsonObject) {

        System.out.println("已经进入POST测试接口,参数：" + jsonObject.toString());
    }
}