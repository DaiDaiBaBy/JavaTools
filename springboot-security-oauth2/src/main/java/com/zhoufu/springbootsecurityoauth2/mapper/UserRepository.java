package com.zhoufu.springbootsecurityoauth2.mapper;

import com.zhoufu.springbootsecurityoauth2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String name);

}
