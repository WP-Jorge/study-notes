package cn.cslg.applysystem.service.impl;

import cn.cslg.applysystem.pojo.entity.Carousel;
import cn.cslg.applysystem.mapper.CarouselMapper;
import cn.cslg.applysystem.service.CarouselService;
import cn.cslg.applysystem.pojo.vo.CarouselVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {
	
	@Override
	public List<CarouselVO> getCarousels() {
		return baseMapper.getCarousels();
	}
}
