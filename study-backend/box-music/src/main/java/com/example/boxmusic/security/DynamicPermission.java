package com.example.boxmusic.security;

import com.example.boxmusic.exception.MyAccessDeniedException;
import com.example.boxmusic.pojo.vo.ApiVO;
import com.example.boxmusic.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class DynamicPermission {
	
	@Autowired
	ApiService apiService;
	
	// 判断有访问 API 的权限
	public Boolean checkPermission(HttpServletRequest request, Authentication authentication) throws MyAccessDeniedException {
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			// 得到当前用户名
			String username = userDetails.getUsername();
			// 通过用户名获取资源鉴权
			List<ApiVO> apis = apiService.getApisWithUsername(username);
			// ArrayList<String> apis = new ArrayList<>();
			// List<RoleWithApiVO> apiTree = apiService.getApiTreeByUsername(username);
			// for (RoleWithApiVO roleWithApiVO : apiTree) {
			// 	ApiTreeUtils.getApiPath(roleWithApiVO.getChildrenApi(), apis);
			// }
			AntPathMatcher antPathMatcher = new AntPathMatcher();
			// 当前访问路径
			String requestURI = request.getRequestURI();
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println(apis);
			// 判断当前路径中是否在资源鉴权中
			boolean rs = apis.stream().anyMatch(item -> {
				// 判断 url 是否匹配
				boolean hashAntPath = antPathMatcher.match(item.getApiPath(), requestURI);
				return hashAntPath;
			});
			// 返回
			if (rs) {
				return rs;
			}
			throw new MyAccessDeniedException("您没有访问该 api 的权限！");
		}
		throw new MyAccessDeniedException("不是 UserDetails 类型！");
	}
	
	/**
	 * 套娃获取权限树中的所有权限
	 * @param apiTreeList
	 * @param apis
	 */
	/*private void getAPi(List<ApiTreeVO> apiTreeList, List<String> apis) {
		if (apiTreeList.size() == 0) {
			return;
		}
		for (ApiTreeVO apiTreeVO : apiTreeList) {
			if (apiTreeVO.getApiPath() != null && !"".equals(apiTreeVO.getApiPath())) {
				apis.add(apiTreeVO.getApiPath());
			}
			getAPi(apiTreeVO.getChildrenApi(), apis);
		}
	}*/
}
