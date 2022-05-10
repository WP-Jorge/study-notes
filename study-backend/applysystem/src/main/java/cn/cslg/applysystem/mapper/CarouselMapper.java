package cn.cslg.applysystem.mapper;

import cn.cslg.applysystem.cache.RedisCache;
import cn.cslg.applysystem.pojo.entity.Carousel;
import cn.cslg.applysystem.pojo.vo.CarouselVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface CarouselMapper extends BaseMapper<Carousel> {
	List<CarouselVO> getCarousels();
}
