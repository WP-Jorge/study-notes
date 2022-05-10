package cn.cslg.applysystem.service;

import cn.cslg.applysystem.pojo.entity.AllApply;
import cn.cslg.applysystem.pojo.vo.AllApplyVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
public interface AllApplyService extends IService<AllApply> {
	int deleteAllApplyTrue(Integer allApplyId);
	
	IPage<AllApplyVO> getAllAppliesWithPage(Page<Map> page);
}
