package cn.cslg.applysystem.service.impl;

import cn.cslg.applysystem.pojo.entity.AllApply;
import cn.cslg.applysystem.mapper.AllApplyMapper;
import cn.cslg.applysystem.pojo.vo.AllApplyVO;
import cn.cslg.applysystem.service.AllApplyService;
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
public class AllApplyServiceImpl extends ServiceImpl<AllApplyMapper, AllApply> implements AllApplyService {
	
	@Override
	public int deleteAllApplyTrue(Integer allApplyId) {
		return baseMapper.deleteAllApplyTrue(allApplyId);
	}
	
	@Override
	public IPage<AllApplyVO> getAllAppliesWithPage(Page<Map> page) {
		return baseMapper.getAllAppliesWithPage(page);
	}
}
