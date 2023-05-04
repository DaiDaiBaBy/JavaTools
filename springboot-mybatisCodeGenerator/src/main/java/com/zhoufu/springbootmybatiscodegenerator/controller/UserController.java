package com.zhoufu.springbootmybatiscodegenerator.controller;

import com.zhoufu.springbootmybatiscodegenerator.entity.User;
import com.zhoufu.springbootmybatiscodegenerator.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
/**
 * <p>
 *  前端控制器
 * </p>
 * @author ZhouFu
 * @since 2023-04-28
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "根据用户ID获取用户信息", notes = "根据用户ID获取用户信息")
    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Integer id){
        if(id == null || id <= 0){
            return "参数错误！";
        }
        User user = userService.getById(id);
        if(user == null){
            return "当前用户不存在，参数错误";
        }
        return user.toString();
    }

    @ApiOperation(value = "用户信息保存", notes = "用户信息保存接口")
    @PostMapping("/save")
    public String saveUser(@RequestBody User user) {
        boolean save = false;
        if (user.getId() != null && user.getId() > 0) {
            save = userService.updateById(user);
        } else {
            save = userService.save(user);
        }
        if (save){
            return String.valueOf(user.getId());
        } else {
            return "保存失败！";
        }
    }
}

