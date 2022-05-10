package com.example.session.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/redis")
public class SessionController {
	// 使用redis的session管理 注意：当session中的数据发生变化时必须将session中变化的数据同步到redis中
	@RequestMapping("/test")
	public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<String> list = (List<String>) request.getSession().getAttribute("list");
		if (list == null) {
			list = new ArrayList<>();
		}
		list.add("xxxx");
		response.getWriter().println("sezi: " + list.size()); // 每次session变化都要同步session
		request.getSession().setAttribute("list", list);
		response.getWriter().println("sessionid: " + request.getSession().getId());
	}
	
	// 退出清除缓存
	@RequestMapping("logout")
	public void logout(HttpServletRequest request) {
		// 清除redis中的session
		request.getSession().invalidate();
	}
}
