package com.zhoufu.springbootswagger2.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.zhoufu.springbootswagger2.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName : HelloWorldController
 * @Author : ZhouFu
 * @Date: 2023/4/25 14:47
 * @Description : 测试swagger
 */
@Api(tags = "swagger配置测试")
@RestController
@RequiredArgsConstructor
@RequestMapping("/swagger")
public class HelloWorldController {

    @PostMapping("/hello")
    @ApiOperation(value = "swagger测试接口", notes = "swagger测试接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",required = true, value = "用户名",dataType = "String"),
            @ApiImplicitParam(name = "password",required = true, value = "密码",dataType = "String")
    })
    public Result helloWorld(@RequestBody JSONObject params){
        return Result.success(params);
    }
}
