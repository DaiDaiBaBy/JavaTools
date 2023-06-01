package com.zhoufu.springbootsecurityjwt.service;


import com.zhoufu.springbootsecurityjwt.security.UserDetailsFactory;
import com.zhoufu.springbootsecurityjwt.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务处理逻辑类
 *
 * passwordEncoder.encode() 对密码进行加密
 * passwordEncoder.matches() 用原始密码匹配和加密过的匹配进行匹配
 */
@Component
public class UserService {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    UserDetailsFactory userDetailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Map login(String username, String password) {
        Map map = null;
        UserDetails user = userDetailService.loadUserByUsername(username);
        if (!passwordEncoder.matches(/*原始密码*/password,/*加密过的密码*/user.getPassword())) {
            map = new HashMap();
            map.put("code", 303);
            map.put("msg", "密码错误");
        }
        List<String> authorites = new ArrayList<>(user.getAuthorities().size());
        for (GrantedAuthority authorite : user.getAuthorities()) {
            authorites.add(authorite.getAuthority());
        }
        map = jwtTokenUtils.getToken(username, authorites);
        map.put("code",200);
        return map;
    }
}
