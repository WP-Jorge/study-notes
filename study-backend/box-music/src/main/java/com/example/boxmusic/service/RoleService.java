package com.example.boxmusic.service;

import com.example.boxmusic.pojo.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boxmusic.pojo.vo.RoleVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
public interface RoleService extends IService<Role> {
	List<RoleVO> getRolesWithUsername(String username);
}
