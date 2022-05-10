package cn.cslg.applysystem;

import cn.cslg.applysystem.service.MajorService;
import cn.cslg.applysystem.pojo.vo.MajorVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MajorTests {
	
	@Autowired
	private MajorService majorService;
	
	@Test
	void testGetAllMajorsWithPage() {
		Page<Map> page = new Page<>(1, 10);
		Page<MajorVO> allMajorsByPage = majorService.getAllMajorsWithPage(page);
		System.out.println(allMajorsByPage);
	}
	
	@Test
	void testGetAllMajors() {
		List<MajorVO> allMajors = majorService.getAllMajors();
		System.out.println(allMajors);
	}
}
