package com.zhoufu.springbootshardingsphere.service;

import com.zhoufu.springbootshardingsphere.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zf
 * @since 2023-05-29
 */
public interface UserService extends IService<User> {

    public int addUser(User user);

    public List<User> getAllUsers();
}
