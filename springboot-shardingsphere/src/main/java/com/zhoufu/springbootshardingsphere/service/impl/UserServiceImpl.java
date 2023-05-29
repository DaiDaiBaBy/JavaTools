package com.zhoufu.springbootshardingsphere.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhoufu.springbootshardingsphere.entity.User;
import com.zhoufu.springbootshardingsphere.mapper.UserMapper;
import com.zhoufu.springbootshardingsphere.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zf
 * @since 2023-05-29
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addUser(User user) {
        user.setCreateBy("admin");
        user.setUpdateBy("admin");
        log.info("user: {}", user);
        return userMapper.insert(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectList(Wrappers.lambdaQuery());
    }
}
