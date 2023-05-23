package com.zhoufu.springbootshiro.dao;

import com.zhoufu.springbootshiro.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * CRUD
 */
public interface UserInfoDao extends CrudRepository<UserInfo, Integer> {

    UserInfo findByUsername(String username);
}
