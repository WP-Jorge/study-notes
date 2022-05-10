package com.example.shirojwt.shiro;

import com.example.shirojwt.entity.Perms;
import com.example.shirojwt.entity.User;
import com.example.shirojwt.service.impl.UserServiceImpl;
import com.example.shirojwt.utils.JWTUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserServiceImpl userService;


    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // String username = JWTUtils.getUsername(principals.toString());
        // UserBean user = userService.getUser(username);
        // SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // simpleAuthorizationInfo.addRole(user.getRole());
        // Set<String> permission = new HashSet<>(Arrays.asList(user.getPermission().split(",")));
        // simpleAuthorizationInfo.addStringPermissions(permission);
    
    
        String username = JWTUtils.getUsername(principals.toString());
        // 获取身份信息
        // 根据主身份信息获取角色信息和权限信息
        User user = userService.findRolesByUsername(username);
        // User user = new User();
        // System.out.println(111);
        System.out.println(user.getRoles());
        // 授权角色
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role -> {
                System.out.println(role.getRoleName());
                simpleAuthorizationInfo.addRole(role.getRoleName());
            
                // 权限信息
                List<Perms> permsList = userService.findPermsByRoleId(role.getRoleId());
                if (!CollectionUtils.isEmpty(permsList)) {
                    permsList.forEach(perms -> {
                        System.out.println(perms.getPermsName());
                        simpleAuthorizationInfo.addStringPermission(perms.getPermsName());
                    });
                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        // String token = (String) auth.getCredentials();
        // // 解密获得username，用于和数据库进行对比
        // String username = JWTUtil.getUsername(token);
        // if (username == null) {
        //     throw new AuthenticationException("token invalid");
        // }
        //
        // UserBean userBean = userService.getUser(username);
        // if (userBean == null) {
        //     throw new AuthenticationException("User didn't existed!");
        // }
        //
        // if (! JWTUtil.verify(token, username, userBean.getPassword())) {
        //     throw new AuthenticationException("Username or password error");
        // }
    
        String token = (String) auth.getCredentials();
        Object credentials = auth.getCredentials();
        String username = JWTUtils.getUsername(token);
        System.out.println(credentials.toString());
        // 在工厂中获取service对象
        // UserService userService = (UserService) ApplicationContextUtils.getBean("userServiceImpl");
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }
        
        // 根据用户名获取用户对象
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        }
        if ((JWTUtils.verify(token)) == null) {
            throw new AuthenticationException("Username or password error");
        }
        if (!ObjectUtils.isEmpty(user)) {
            // return new SimpleAuthenticationInfo(token, token, "my_realm");
    
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), "my_realm");
        }

        return null;
        // return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
