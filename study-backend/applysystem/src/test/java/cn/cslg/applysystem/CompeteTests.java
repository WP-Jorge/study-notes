package cn.cslg.applysystem;

import cn.cslg.applysystem.service.CompeteService;
import cn.cslg.applysystem.pojo.vo.CompeteVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompeteTests {
	
	@Autowired
	private CompeteService competeService;
	
	// @Test
	// void testGetAllCompetes() {
	// 	List<CompeteVO> allCompetes = competeService.getAllCompetes();
	// 	System.out.println(allCompetes);
	// }
}
