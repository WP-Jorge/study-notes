package cn.cslg.applysystem.mapper;

import cn.cslg.applysystem.cache.RedisCache;
import cn.cslg.applysystem.pojo.entity.CompeteLev;
import cn.cslg.applysystem.pojo.vo.CompeteLevVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@CacheNamespace(implementation = RedisCache.class, eviction = RedisCache.class)
public interface CompeteLevMapper extends BaseMapper<CompeteLev> {
	IPage<CompeteLevVO> getAllCompeteLevsWithPage(Page page);
	IPage<CompeteLevVO> getCompeteLevsByCompeteLevsNameWithPage(Page page, String competeLevName);
}
