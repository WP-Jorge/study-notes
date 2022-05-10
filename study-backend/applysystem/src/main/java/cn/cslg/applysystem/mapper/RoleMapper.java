package cn.cslg.applysystem.mapper;

import cn.cslg.applysystem.cache.RedisCache;
import cn.cslg.applysystem.pojo.dto.AddRoleDTO;
import cn.cslg.applysystem.pojo.entity.Role;
import cn.cslg.applysystem.pojo.vo.RoleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@CacheNamespace(implementation = RedisCache.class, eviction = RedisCache.class)
public interface RoleMapper extends BaseMapper<Role> {
	List<String> getRolesByUsername(String username);
	
	int addRole(AddRoleDTO addRoleDTO);
	
	List<RoleVO> getRolesByUsernameVO(String username);
	
	IPage<RoleVO> getAllRolesWithPage(Page page);
	
	
	IPage<RoleVO> getRolesByRoleNameWithPage(Page page, String roleName);
	
}
