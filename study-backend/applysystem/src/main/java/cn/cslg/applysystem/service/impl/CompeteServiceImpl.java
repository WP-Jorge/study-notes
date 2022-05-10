package cn.cslg.applysystem.service.impl;

import cn.cslg.applysystem.pojo.entity.Compete;
import cn.cslg.applysystem.mapper.CompeteMapper;
import cn.cslg.applysystem.service.CompeteService;
import cn.cslg.applysystem.pojo.vo.CompeteVO;
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
public class CompeteServiceImpl extends ServiceImpl<CompeteMapper, Compete> implements CompeteService {
	
	// @Override
	// public List<CompeteVO> getAllCompetes() {
	// 	return baseMapper.getAllCompetes();
	// }
	
	@Override
	public IPage<CompeteVO> getAllCompetesWithPage(Page page) {
		return baseMapper.getAllCompetesWithPage(page);
	}
	
	@Override
	public IPage<CompeteVO> getCompetesByCompeteNameWithPage(Page page, String competeName) {
		return baseMapper.getCompetesByCompeteNameWithPage(page, competeName);
	}
}
