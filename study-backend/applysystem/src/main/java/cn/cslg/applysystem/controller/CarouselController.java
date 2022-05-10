package cn.cslg.applysystem.controller;


import cn.cslg.applysystem.service.CarouselService;
import cn.cslg.applysystem.utils.R;
import cn.cslg.applysystem.pojo.vo.CarouselVO;
import cn.cslg.applysystem.pojo.vo.FileVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@ApiOperation("轮播图")
@RestController
@RequestMapping("/carousel")
public class CarouselController {
	
	@Autowired
	private CarouselService carouselService;
	
	@Autowired
	Environment environment;
	
	@Value("${host}")
	private String host;
	
	@ApiOperation("获取轮播图")
	@GetMapping("/getCarousels")
	public R getCarousels() {
		HashMap<String, Object> map = new HashMap<>();
		String property = environment.getProperty("local.server.port");
		String pre = "http://" + this.host + ":" + property;
		System.out.println(this.host + ":" + property);
		
		
		List<CarouselVO> carousels = carouselService.getCarousels();
		for (CarouselVO carousel : carousels) {
			FileVO file = carousel.getFile();
			file.setFilePath(pre + file.getFilePath());
			
		}
		map.put("carousels", carousels);
		return R.ok(map);
	}

}

