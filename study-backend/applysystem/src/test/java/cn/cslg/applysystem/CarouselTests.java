package cn.cslg.applysystem;

import cn.cslg.applysystem.service.CarouselService;
import cn.cslg.applysystem.pojo.vo.CarouselVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarouselTests {
	
	@Autowired
	private CarouselService carouselService;
	
	@Test
	void testGetCarousel() {
		List<CarouselVO> carousels = carouselService.getCarousels();
		System.out.println(carousels);
	}
}
