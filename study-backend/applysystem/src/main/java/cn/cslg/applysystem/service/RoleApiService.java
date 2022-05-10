package cn.cslg.applysystem.service;

import cn.cslg.applysystem.pojo.entity.RoleApi;
import cn.cslg.applysystem.pojo.vo.ApiVO;
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
public interface RoleApiService extends IService<RoleApi> {
	List<ApiVO> getApisByRoleId(Integer roleId);
}
