package cn.cslg.applysystem.mapper;

import cn.cslg.applysystem.cache.RedisCache;
import cn.cslg.applysystem.pojo.entity.AllApply;
import cn.cslg.applysystem.pojo.vo.AllApplyVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
// @CacheNamespace(implementation = RedisCache.class, eviction = RedisCache.class)
public interface AllApplyMapper extends BaseMapper<AllApply> {
	
	// int addApply(AllApplyDTO allApplyDTO);
	int deleteAllApplyTrue(Integer allApplyId);
	
	IPage<AllApplyVO> getAllAppliesWithPage(Page<Map> page);

}
