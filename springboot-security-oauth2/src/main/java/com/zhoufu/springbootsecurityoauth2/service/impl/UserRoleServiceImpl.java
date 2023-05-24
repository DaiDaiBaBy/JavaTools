package com.zhoufu.springbootsecurityoauth2.service.impl;


import com.zhoufu.springbootsecurityoauth2.mapper.UserRoleRepository;
import com.zhoufu.springbootsecurityoauth2.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Override
    public List<String> findRoles(int uid) {

        List<String> list = userRoleRepository.findRoleName(uid);

        return list;
    }
}
