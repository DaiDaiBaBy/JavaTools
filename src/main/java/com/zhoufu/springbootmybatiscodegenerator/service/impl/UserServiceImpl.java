package com.zhoufu.springbootmybatiscodegenerator.service.impl;

import com.zhoufu.springbootmybatiscodegenerator.entity.User;
import com.zhoufu.springbootmybatiscodegenerator.mapper.UserMapper;
import com.zhoufu.springbootmybatiscodegenerator.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhouFu
 * @since 2023-04-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
