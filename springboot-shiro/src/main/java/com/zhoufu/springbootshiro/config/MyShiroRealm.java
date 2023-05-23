package com.zhoufu.springbootshiro.config;

import javax.annotation.Resource;
import com.zhoufu.springbootshiro.entity.SysPermission;
import com.zhoufu.springbootshiro.entity.SysRole;
import com.zhoufu.springbootshiro.entity.UserInfo;
import com.zhoufu.springbootshiro.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 *  shiro 配置Realm   授权用户访问
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserInfoService userInfoService;

    /**
     * 权限配置   获取当前用户的授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("MyShiroRealm->doGetAuthorizationInfo");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
        for (SysRole role : userInfo.getRoles()) {
            authorizationInfo.addRole(role.getRole());
            for (SysPermission p : role.getPermissions()) {
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     * 完成用户身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("MyShiroRealm->doGetAuthenticationInfo");
        //用户名
        String username = (String) token.getPrincipal();
        System.out.println("username:" + username);

        //获取用户信息
        UserInfo userInfo = userInfoService.findByUsername(username);

        if (userInfo == null) {
            return null;
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo,
                userInfo.getPassword(), ByteSource.Util.bytes(userInfo.getCredentialsSalt()),
                getName());
        return authenticationInfo;
    }

}
