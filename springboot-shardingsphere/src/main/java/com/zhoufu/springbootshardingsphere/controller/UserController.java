package com.zhoufu.springbootshardingsphere.controller;

import com.zhoufu.springbootshardingsphere.entity.User;
import com.zhoufu.springbootshardingsphere.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zf
 * @since 2023-05-29
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public List<User> get() {
        return userService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/del/{id}")
    public String delete(@PathVariable Long id) {
        boolean b = userService.removeById(id);
        if (b) {
            return "SUCCESS";
        }
        return "FAILURE";
    }

    @GetMapping("/add/{count}")
    public String add(@PathVariable int count) {
        int result = 0;
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setUsername("username" + i);
            user.setPassword("password" + i);
            user.setAge(new Random().nextInt(100));
            result += userService.addUser(user);
        }

        return result == count ? "SUCCESS" : "FAILURE";
    }
}
