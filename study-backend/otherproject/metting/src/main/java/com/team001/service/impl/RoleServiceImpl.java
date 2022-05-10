package com.team001.service.impl;

import com.team001.entity.Role;
import com.team001.mapper.RoleMapper;
import com.team001.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> getRolesByUserName(String userName) {
        return baseMapper.getRolesByUserName(userName);
    }
}
