package com.zhoufu.springbootsecurityoauth2.service.impl;


import com.zhoufu.springbootsecurityoauth2.entity.User;
import com.zhoufu.springbootsecurityoauth2.mapper.UserRepository;
import com.zhoufu.springbootsecurityoauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
