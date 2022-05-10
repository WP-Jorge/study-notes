package cn.cslg.applysystem.service.impl;

import cn.cslg.applysystem.pojo.entity.RoleApi;
import cn.cslg.applysystem.mapper.RoleApiMapper;
import cn.cslg.applysystem.pojo.vo.ApiVO;
import cn.cslg.applysystem.service.RoleApiService;
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
public class RoleApiServiceImpl extends ServiceImpl<RoleApiMapper, RoleApi> implements RoleApiService {
	
	@Override
	public List<ApiVO> getApisByRoleId(Integer roleId) {
		return baseMapper.getApisByRoleId(roleId);
	}
}
