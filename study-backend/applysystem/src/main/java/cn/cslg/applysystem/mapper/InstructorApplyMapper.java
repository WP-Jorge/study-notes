package cn.cslg.applysystem.mapper;

import cn.cslg.applysystem.cache.RedisCache;
import cn.cslg.applysystem.pojo.entity.InstructorApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Development team
 * @since 2021-03-22
 */
@CacheNamespace(implementation = RedisCache.class, eviction = RedisCache.class)
public interface InstructorApplyMapper extends BaseMapper<InstructorApply> {

}
