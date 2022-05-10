package cn.cslg.applysystem.mapper;

import cn.cslg.applysystem.cache.RedisCache;
import cn.cslg.applysystem.pojo.entity.Award;
import cn.cslg.applysystem.pojo.vo.AwardVO;
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
@CacheNamespace(implementation = RedisCache.class, eviction = RedisCache.class)
public interface AwardMapper extends BaseMapper<Award> {
	IPage<AwardVO> getAllAwardWithPage(Page page);
	IPage<AwardVO> getAwardByAwardNameWithPage(Page page, String awardName);
}
