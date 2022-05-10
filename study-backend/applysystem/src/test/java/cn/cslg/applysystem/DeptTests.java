package cn.cslg.applysystem;

import cn.cslg.applysystem.service.DeptService;
import cn.cslg.applysystem.pojo.vo.DeptVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeptTests {
	
	@Autowired
	private DeptService deptService;
	
	@Test
	void testGetAllDeptsWithPage() {
		Page<Map> page = new Page<>(1, 10);
		Page<DeptVO> depts = deptService.getAllDeptsWithPage(page);
		System.out.println(depts);
	}
	
	@Test
	void testGetAllDepts() {
		List<DeptVO> depts = deptService.getAllDepts();
		System.out.println(depts);
	}
}
