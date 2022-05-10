package cn.cslg.applysystem.service.impl;

import cn.cslg.applysystem.pojo.entity.CompeteLev;
import cn.cslg.applysystem.mapper.CompeteLevMapper;
import cn.cslg.applysystem.service.CompeteLevService;
import cn.cslg.applysystem.pojo.vo.CompeteLevVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@Service
public class CompeteLevServiceImpl extends ServiceImpl<CompeteLevMapper, CompeteLev> implements CompeteLevService {
	
	@Override
	public IPage<CompeteLevVO> getAllCompeteLevsWithPage(Page<Map> page) {
		return baseMapper.getAllCompeteLevsWithPage(page);
	}
	
	@Override
	public IPage<CompeteLevVO> getCompeteLevsByCompeteLevsNameWithPage(Page page, String competeLevName) {
		return baseMapper.getCompeteLevsByCompeteLevsNameWithPage(page, competeLevName);
	}
}
