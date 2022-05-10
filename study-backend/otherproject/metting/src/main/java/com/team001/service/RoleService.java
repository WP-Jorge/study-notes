package com.team001.service;

import com.team001.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author team001
 * @since 2020-08-01
 */
public interface RoleService extends IService<Role> {

    List<String> getRolesByUserName(@Param("userName") String userName);

}
