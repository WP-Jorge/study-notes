package cn.cslg.applysystem.mapper;

import cn.cslg.applysystem.cache.RedisCache;
import cn.cslg.applysystem.pojo.entity.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
// @CacheNamespace(implementation = RedisCache.class, eviction = RedisCache.class)
public interface FileMapper extends BaseMapper<File> {
	int updateFIleRefCountByFileIdSub(Integer fileId);
	int updateFIleRefCountByFileIdPlus(Integer fileId);
}
