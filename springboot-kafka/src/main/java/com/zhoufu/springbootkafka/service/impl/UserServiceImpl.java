package com.zhoufu.springbootkafka.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhoufu.springbootkafka.mapper.UserMapper;
import com.zhoufu.springbootkafka.model.entity.UserEntity;
import com.zhoufu.springbootkafka.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author zf
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
