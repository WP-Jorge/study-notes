package cn.cslg.applysystem.service;

import cn.cslg.applysystem.pojo.dto.AddApiDTO;
import cn.cslg.applysystem.pojo.dto.UpdateApiDTO;
import cn.cslg.applysystem.pojo.entity.Api;
import cn.cslg.applysystem.pojo.vo.ApiTreeVO;
import cn.cslg.applysystem.pojo.vo.ApiVO;
import cn.cslg.applysystem.pojo.vo.RoleWithApiVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
public interface ApiService extends IService<Api> {
	
	List<ApiVO> getApisByUsername(String username);
	
	IPage<ApiVO> getApisByUsernameWithPage(Page page, String username);
	
	IPage<ApiVO> getAllApisWithPage(Page page);
	
	List<RoleWithApiVO> getAllRolesApiTree();
	
	List<ApiTreeVO> getApiTree();
	
	List<RoleWithApiVO> getApiTreeByRoleId(Integer roleId);
	
	List<RoleWithApiVO> getApiTreeByUserId(Integer userId);
	
	List<RoleWithApiVO> getApiTreeByUsername(String username);
	
	int addApi(AddApiDTO addApiDTO);
	
	List<ApiTreeVO> getApiTreeByApiId(Integer apiId);
	
	int updateApi(UpdateApiDTO updateApiDTO);
	
	IPage<ApiVO> getApisByApiNameWithPage(Page page, String apiName);
	
}
