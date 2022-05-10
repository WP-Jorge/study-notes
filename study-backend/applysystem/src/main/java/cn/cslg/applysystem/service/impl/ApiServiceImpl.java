package cn.cslg.applysystem.service.impl;

import cn.cslg.applysystem.pojo.dto.AddApiDTO;
import cn.cslg.applysystem.pojo.dto.UpdateApiDTO;
import cn.cslg.applysystem.pojo.entity.Api;
import cn.cslg.applysystem.mapper.ApiMapper;
import cn.cslg.applysystem.pojo.vo.ApiTreeVO;
import cn.cslg.applysystem.pojo.vo.RoleWithApiVO;
import cn.cslg.applysystem.service.ApiService;
import cn.cslg.applysystem.pojo.vo.ApiVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper, Api> implements ApiService {
	
	@Override
	public List<ApiVO> getApisByUsername(String username) {
		List<ApiVO> api = baseMapper.getApisByUsername(username);
		return api;
	}
	
	@Override
	public IPage<ApiVO> getApisByUsernameWithPage(Page page, String username) {
		return baseMapper.getApisByUsernameWithPage(page, username);
	}
	
	@Override
	public IPage<ApiVO> getAllApisWithPage(Page page) {
		return baseMapper.getAllApisWithPage(page);
	}
	
	@Override
	public List<RoleWithApiVO> getAllRolesApiTree() {
		return baseMapper.getAllRolesApiTree();
	}
	
	@Override
	public List<ApiTreeVO> getApiTree() {
		return baseMapper.getApiTree();
	}
	
	@Override
	public List<RoleWithApiVO> getApiTreeByRoleId(Integer roleId) {
		return baseMapper.getApiTreeByRoleId(roleId);
	}
	
	@Override
	public List<RoleWithApiVO> getApiTreeByUserId(Integer userId) {
		return baseMapper.getApiTreeByUserId(userId);
	}
	
	@Override
	public List<RoleWithApiVO> getApiTreeByUsername(String username) {
		return baseMapper.getApiTreeByUsername(username);
	}
	
	@Override
	public int addApi(AddApiDTO addApiDTO) {
		return baseMapper.addApi(addApiDTO);
	}
	
	@Override
	public List<ApiTreeVO> getApiTreeByApiId(Integer apiId) {
		return baseMapper.getApiTreeByApiId(apiId);
	}
	
	@Override
	public int updateApi(UpdateApiDTO updateApiDTO) {
		
		return baseMapper.updateApi(updateApiDTO);
	}
	
	@Override
	public IPage<ApiVO> getApisByApiNameWithPage(Page page, String apiName) {
		return baseMapper.getApisByApiNameWithPage(page, apiName);
	}
}
