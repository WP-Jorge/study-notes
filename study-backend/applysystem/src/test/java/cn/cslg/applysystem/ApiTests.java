package cn.cslg.applysystem;

import cn.cslg.applysystem.pojo.dto.AddApiDTO;
import cn.cslg.applysystem.pojo.entity.Log;
import cn.cslg.applysystem.pojo.vo.ApiTreeVO;
import cn.cslg.applysystem.pojo.vo.RoleWithApiVO;
import cn.cslg.applysystem.service.ApiService;
import cn.cslg.applysystem.pojo.vo.ApiVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiTests {
	
	@Autowired
	private ApiService apiService;
	
	@Test
	void testGetApisByUsername() {
		List<ApiVO> apis = apiService.getApisByUsername("Z09418233");
		System.out.println(apis);
	}
	
	@Test
	void testGetAllApisWithPage() {
		IPage<ApiVO> apis = apiService.getAllApisWithPage(new Page(1, 10));
		System.out.println(apis);
	}
	
	@Test
	void testGetAllRolesApiTree() {
		List<RoleWithApiVO> apiTree = apiService.getAllRolesApiTree();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(apiTree);
	}
	
	@Test
	void testGetApiTree() {
		List<ApiTreeVO> apiTree = apiService.getApiTree();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(apiTree);
	}
	
	@Test
	void testGetApiTreeByRoleId() {
		List<RoleWithApiVO> apiTree = apiService.getApiTreeByRoleId(4);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(apiTree);
	}
	
	@Test
	void testGetApiTreeByUserId() {
		List<RoleWithApiVO> apiTree = apiService.getApiTreeByUserId(1);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(apiTree);
	}
	
	@Test
	void testGetApiTreeByUsername() {
		List<RoleWithApiVO> apiTree = apiService.getApiTreeByUsername("Z09418233");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(apiTree);
	}
	
	@Test
	void testGetApisFromApiTree() {
		ArrayList<String> apis = new ArrayList<>();
		List<RoleWithApiVO> apiTree = apiService.getApiTreeByUsername("Z09418233");
		for (RoleWithApiVO roleWithApiVO : apiTree) {
			this.getAPi(roleWithApiVO.getChildrenApi(), apis);
		}
		System.out.println(apis);
	}
	
	void getAPi(List<ApiTreeVO> apiTreeList, List<String> apis) {
		if (apiTreeList.size() == 0) {
			return;
		}
		for (ApiTreeVO apiTreeVO : apiTreeList) {
			if (apiTreeVO.getApiPath() != null && !"".equals(apiTreeVO.getApiPath())) {
				apis.add(apiTreeVO.getApiPath());
			}
			getAPi(apiTreeVO.getChildrenApi(), apis);
		}
	}
	
	@Test
	void testAddApi() {
		AddApiDTO addApiDTO = new AddApiDTO();
		addApiDTO.setApiName("添加用户");
		addApiDTO.setParentApiId(8);
		addApiDTO.setApiMethod("*");
		addApiDTO.setApiPath("/user/updateUser");
		addApiDTO.setApiType(1);
		// boolean save = apiService.save(addApiDTO);
		int i = apiService.addApi(addApiDTO);
		System.out.println(i);
		// System.out.println(save);
	}
	
	@Test
	void testGetApiTreeByApiId() {
		List<ApiTreeVO> tree = apiService.getApiTreeByApiId(7);
		System.out.println(tree);
	}
	
	@Test
	void  testGetApisByUsernameWithPage() {
		IPage<ApiVO> z09418233 = apiService.getApisByUsernameWithPage(new Page(1, 10), "Z09418233");
		System.out.println(z09418233);
	}
}
