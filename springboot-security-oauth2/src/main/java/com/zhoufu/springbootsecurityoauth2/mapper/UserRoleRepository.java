package com.zhoufu.springbootsecurityoauth2.mapper;

import com.zhoufu.springbootsecurityoauth2.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    List<UserRole> findByuid(int uid);

    @Query(value = "select r.role_name from user_role ur left join role r on ur.rid=r.id where ur.uid = ?1",nativeQuery = true)
    List<String> findRoleName(int uid);
}
