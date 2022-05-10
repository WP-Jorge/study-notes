package cn.cslg.applysystem.mapper;

import cn.cslg.applysystem.cache.RedisCache;
import cn.cslg.applysystem.pojo.entity.Major;
import cn.cslg.applysystem.pojo.vo.MajorVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@CacheNamespace(implementation = RedisCache.class, eviction = RedisCache.class)
public interface MajorMapper extends BaseMapper<Major> {
	Page<MajorVO> getAllMajorsWithPage(Page page);
	List<MajorVO> getAllMajors();
}
