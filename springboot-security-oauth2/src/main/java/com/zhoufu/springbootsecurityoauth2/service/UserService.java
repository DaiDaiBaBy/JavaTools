package com.zhoufu.springbootsecurityoauth2.service;


import com.zhoufu.springbootsecurityoauth2.entity.User;

import java.util.List;

public interface UserService {

    User findByUsername(String name);

    List<User> findAll();

}
