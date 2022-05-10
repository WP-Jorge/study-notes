package cn.cslg.applysystem.utils;

import cn.cslg.applysystem.pojo.vo.ApiTreeVO;

import java.util.List;

public class ApiTreeUtils {
	
	public static void getApiPath(List<ApiTreeVO> apiTreeList, List<String> apis) {
		if (apiTreeList.size() == 0) {
			return;
		}
		for (ApiTreeVO apiTreeVO : apiTreeList) {
			if (apiTreeVO.getApiPath() != null && !"".equals(apiTreeVO.getApiPath())) {
				apis.add(apiTreeVO.getApiPath());
			}
			getApiPath(apiTreeVO.getChildrenApi(), apis);
		}
	}
	
	public static void getApiId(List<ApiTreeVO> apiTreeList, List<Integer> apis) {
		if (apiTreeList.size() == 0) {
			return;
		}
		for (ApiTreeVO apiTreeVO : apiTreeList) {
			apis.add(apiTreeVO.getApiId());
			getApiId(apiTreeVO.getChildrenApi(), apis);
		}
	}
}
