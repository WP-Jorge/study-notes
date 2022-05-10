package cn.cslg.applysystem.service.impl;

import cn.cslg.applysystem.pojo.entity.Award;
import cn.cslg.applysystem.mapper.AwardMapper;
import cn.cslg.applysystem.pojo.vo.AwardVO;
import cn.cslg.applysystem.service.AwardService;
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
public class AwardServiceImpl extends ServiceImpl<AwardMapper, Award> implements AwardService {
	
	@Override
	public IPage<AwardVO> getAllAwardWithPage(Page page) {
		return baseMapper.getAllAwardWithPage(page);
	}
	
	@Override
	public IPage<AwardVO> getAwardByAwardNameWithPage(Page page, String awardName) {
		return baseMapper.getAwardByAwardNameWithPage(page, awardName);
	}
}
