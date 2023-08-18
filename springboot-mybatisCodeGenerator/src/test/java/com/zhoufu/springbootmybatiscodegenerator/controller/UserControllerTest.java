package com.zhoufu.springbootmybatiscodegenerator.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.zhoufu.springbootmybatiscodegenerator.entity.User;
import com.zhoufu.springbootmybatiscodegenerator.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName : UserControllerTest
 * @Author : ZhouFu
 * @Date: 2023/8/18 11:00
 * @Description :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserControllerTest {

    @Spy
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;
    @Test
    public void getUser() {
        User user = User.builder()
                .userName("周")
                .sex(11)
                .phone("1111")
                .password("1231")
                .build();
        Mockito.when(userService.getById(1)).thenReturn(user);
        String controllerUser = userController.getUser(1);
        Assert.assertNotNull(controllerUser);
    }

    @Test
    public void getUser1() {
        Mockito.when(userService.getById(1)).thenReturn(null);
        String controllerUser = userController.getUser(1);
        Assert.assertNotNull(controllerUser);
    }
    @Test
    public void getUser2() {
        Mockito.when(userService.getById(0)).thenReturn(null);
        String controllerUser = userController.getUser(0);
        Assert.assertNotNull(controllerUser);
    }

    @Test
    public void saveUser() {
        User user = User.builder()
                .id(123L)
                .userName("周")
                .sex(11)
                .phone("1111")
                .password("1231")
                .build();
        Mockito.when(userService.updateById(user)).thenReturn(true);
        String saveUser = userController.saveUser(user);
        System.out.println(saveUser);
    }

    @Test
    public void saveUser2() {
        User user = User.builder()
                .id(123L)
                .userName("周")
                .sex(11)
                .phone("1111")
                .password("1231")
                .build();
        Mockito.when(userService.updateById(user)).thenReturn(false);
        String saveUser = userController.saveUser(user);
        System.out.println(saveUser);
    }

    @Test
    public void saveUser1() {
        User user = User.builder()
                .userName("周")
                .sex(11)
                .phone("1111")
                .password("1231")
                .build();
        Mockito.when(userService.save(user)).thenReturn(true);
        String saveUser = userController.saveUser(user);
        System.out.println(saveUser);
    }

    @Test
    public void saveUser11() {
        User user = User.builder()
                .userName("周")
                .sex(11)
                .phone("1111")
                .password("1231")
                .build();
        Mockito.when(userService.save(user)).thenReturn(false);
        String saveUser = userController.saveUser(user);
    }
}