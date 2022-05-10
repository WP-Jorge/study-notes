package cn.cslg.applysystem.mapper;

import cn.cslg.applysystem.cache.RedisCache;
import cn.cslg.applysystem.pojo.dto.AddApiDTO;
import cn.cslg.applysystem.pojo.dto.UpdateApiDTO;
import cn.cslg.applysystem.pojo.entity.Api;
import cn.cslg.applysystem.pojo.vo.ApiTreeVO;
import cn.cslg.applysystem.pojo.vo.ApiVO;
import cn.cslg.applysystem.pojo.vo.RoleWithApiVO;
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
public interface ApiMapper extends BaseMapper<Api> {
	List<ApiVO> getApisByUsername(String username);
	// IPage<ApiVO> getApisByUsernameWithPage(Page page, String username);
	IPage<ApiVO> getApisByUsernameWithPage(Page page, String username);
	IPage<ApiVO> getAllApisWithPage(Page page);
	List<RoleWithApiVO> getAllRolesApiTree();
	List<ApiTreeVO> getApiTree();
	List<RoleWithApiVO> getApiTreeByRoleId(Integer roleId);
	List<RoleWithApiVO> getApiTreeByUserId(Integer userId);
	List<RoleWithApiVO> getApiTreeByUsername(String username);
	List<ApiTreeVO> getApiTreeByApiId(Integer apiId);
	int addApi(AddApiDTO addApiDTO);
	int updateApi(UpdateApiDTO updateApiDTO);
	
	IPage<ApiVO> getApisByApiNameWithPage(Page page, String apiName);
	
}
