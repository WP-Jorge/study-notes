package com.team001.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team001.common.lang.Result;
import com.team001.components.BCryptPasswordEncoderUtil;
import com.team001.entity.Role;
import com.team001.entity.User;
import com.team001.entity.UserLocked;
import com.team001.entity.UserLog;
import com.team001.mapper.UserMapper;
import com.team001.service.UserLockedService;
import com.team001.service.UserLogService;
import com.team001.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author team001
 * @since 2020-08-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserService userService;
    @Autowired
    UserLogService userLogService;

    @Autowired
    UserLockedService userLockedService;

    @Autowired
    BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;
    //用户激活状态
    private static final int USER_STATE = 1;
//    @Override
//    public List<Role> getUserRolesByName(String username) {
//        return baseMapper.getUserRolesByName(username);
//    }
    public User getUserByUserName(String username){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询条件：全匹配账号名，和状态为1的账号
        lambdaQueryWrapper
                .eq(User::getUsername,username);
//                .eq(User::getEnabled,USER_STATE);

        //用getOne查询一个对象出来
        User user = this.getOne(lambdaQueryWrapper);

        return  user;
    }

    @Override
    public User getUserDetail(String username) {
        return baseMapper.getUserDetail(username);
    }

    @Override
    public boolean checkLogin(String username, String rawPassword){
        User user = getUserByUserName(username);
        System.out.println("userEntity = " + user);
        if(user == null){
            throw new UsernameNotFoundException("账号不存在，请重新尝试");
        } else if(user.getEnabled()==0){
            throw new DisabledException("账号被禁用，请联系管理员");
        } else {

            //检测用户是否锁定
            UserLocked userLocked = userLockedService.getOne(new QueryWrapper<UserLocked>().eq("username", user.getUsername()).orderByDesc("lock_end").last("LIMIT 1"));
            System.out.println(userLocked);
            if(userLocked!=null && userLocked.getLockEnd().compareTo(LocalDateTime.now())>=0){
                throw new LockedException("用户被锁定，请稍后再试");
            }

            //日志文件添加
            UserLog userLog = new UserLog();
            userLog.setUsername(user.getUsername()).setIpaddress("localhost").setLoginTime(LocalDateTime.now());
            //密码校验
            String password = user.getPassword();
            System.out.println("#$#$#$%#$@#@@@@@@@@@@@@@@");
            System.out.println(bCryptPasswordEncoderUtil.matches(rawPassword,password));
            System.out.println("#$#$#$%#$@#@@@@@@@@@@@@@@");
            if(bCryptPasswordEncoderUtil.matches(rawPassword,password)){
                //密码校验通过
                userLog.setLogcontent("登陆成功");
                userLogService.save(userLog);
                user.setLogin(5);
                userService.update(user,new QueryWrapper<User>().eq("username",user.getUsername()));
                return true;
            } else {
                userLog.setLogcontent("登陆失败");
                userLogService.save(userLog);

                int count = user.getLogin();

                user.setLogin(count-1);
                userService.update(user,new QueryWrapper<User>().eq("username",user.getUsername()));

//                int count = userLogService.count(new QueryWrapper<UserLog>().like("logcontent","登陆失败"));
                //锁定用户
                if(count <= 0){
                    UserLocked locked = new UserLocked();
                    locked.setLockStart(LocalDateTime.now()).setLockEnd(LocalDateTime.now().plusMinutes(15)).setUsername(user.getUsername());
                    userLockedService.save(locked);
                    user.setLogin(5);
                    userService.update(user,new QueryWrapper<User>().eq("username",user.getUsername()));
                    throw new LockedException("用户被锁定，请稍后再试");
                }
                throw new BadCredentialsException("密码不正确");
            }
        }
    }
}
