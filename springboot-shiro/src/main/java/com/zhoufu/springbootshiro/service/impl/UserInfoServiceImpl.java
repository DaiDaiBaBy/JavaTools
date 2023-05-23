package com.zhoufu.springbootshiro.service.impl;


import javax.annotation.Resource;
import com.zhoufu.springbootshiro.dao.UserInfoDao;
import com.zhoufu.springbootshiro.entity.UserInfo;
import com.zhoufu.springbootshiro.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 *  实现
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoDao dao;

    public UserInfo findByUsername(String username) {
        return dao.findByUsername(username);
    }

}
