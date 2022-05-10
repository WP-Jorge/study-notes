package cn.cslg.applysystem.service;

import cn.cslg.applysystem.pojo.entity.Agency;
import cn.cslg.applysystem.pojo.vo.AgencyVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
public interface AgencyService extends IService<Agency> {
	IPage<AgencyVO> getAllAgenciesWithPage(Page page);
	IPage<AgencyVO> getAgenciesByAgencyNameWithPage(Page page, String agencyName);
}
