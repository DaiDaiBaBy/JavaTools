package com.zhoufu.springbootsecurityoauth2.mapper;

import com.zhoufu.springbootsecurityoauth2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
