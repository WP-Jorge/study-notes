package cn.cslg.applysystem.service.impl;

import cn.cslg.applysystem.pojo.entity.Agency;
import cn.cslg.applysystem.mapper.AgencyMapper;
import cn.cslg.applysystem.pojo.vo.AgencyVO;
import cn.cslg.applysystem.service.AgencyService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@Service
public class AgencyServiceImpl extends ServiceImpl<AgencyMapper, Agency> implements AgencyService {
	
	@Override
	public IPage<AgencyVO> getAllAgenciesWithPage(Page page) {
		return baseMapper.getAllAgenciesWithPage(page);
	}
	
	@Override
	public IPage<AgencyVO> getAgenciesByAgencyNameWithPage(Page page, String agencyName) {
		return baseMapper.getAgenciesByAgencyNameWithPage(page, agencyName);
	}
}
