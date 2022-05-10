package com.team001.mapper;

import com.team001.entity.Role;
import com.team001.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author team001
 * @since 2020-08-01
 */
public interface UserMapper extends BaseMapper<User> {

//    List<Role> getUserRolesByName(String username);

    boolean checkLogin(String username,String rawPassword) throws Exception;
    User getUserDetail(String username);
}
