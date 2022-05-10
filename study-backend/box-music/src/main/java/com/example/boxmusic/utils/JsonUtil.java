package com.example.boxmusic.utils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.vo.UserVO;
import com.example.boxmusic.utils.JwtTokenUtil;
import com.example.boxmusic.utils.Value;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

/**
 * 封装输出 JSON 格式的类
 */
@Component("jsonUtil")
public class JsonUtil {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	/**
	 * 输出JSON
	 *
	 * @param request
	 * @param response
	 * @param data
	 * @throws IOException
	 * @throws ServletException
	 */
	public void WriteJSON(HttpServletRequest request,
						  HttpServletResponse response,
						  Object data) throws IOException, ServletException {
		// 这里很重要，否则页面获取不到正常的 JSON 数据集
		response.setContentType(Value.CONTENT_TYPE_JSON_UTF8);
		response.setHeader(Value.ACCESS_CONTROL_ALLOW_ORIGIN, Value.WILDCARD_ALL);
		response.setHeader(Value.ACCESS_CONTROL_ALLOW_METHOD, Value.WILDCARD_ALL);
		// 输出 JSON
		PrintWriter out = response.getWriter();
		out.write(new ObjectMapper().writeValueAsString(data));
		out.flush();
		out.close();
	}
	
	public <T> ArrayList<Map<String, Object>> transformPages(Page<Map<String, Object>> pages, Class<T> clazz) {
		ArrayList<Map<String, Object>> list = new ArrayList<>();
		for (Map<String, Object> record : pages.getRecords()) {
			Object object = JSON.parseObject(JSON.toJSONString(record), clazz);
			list.add(JSON.parseObject(JSON.toJSONString(object), Map.class));
		}
		return list;
	}
}
