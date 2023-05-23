package com.zhoufu.springbootshiro.service;
import com.zhoufu.springbootshiro.entity.UserInfo;

public interface UserInfoService {
    UserInfo findByUsername(String username);
}
