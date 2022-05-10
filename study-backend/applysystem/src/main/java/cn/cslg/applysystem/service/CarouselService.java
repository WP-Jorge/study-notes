package cn.cslg.applysystem.service;

import cn.cslg.applysystem.pojo.entity.Carousel;
import cn.cslg.applysystem.pojo.vo.CarouselVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
public interface CarouselService extends IService<Carousel> {
	List<CarouselVO> getCarousels();
}
