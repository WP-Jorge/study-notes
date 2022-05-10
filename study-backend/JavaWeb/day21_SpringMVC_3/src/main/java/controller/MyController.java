package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vo.Student;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyController {
	/**
	 * 处理器方法返@Mode LAndView,实现转发forward
	 * 语法: setViewName ( "forward :视图文件完整路径" )
	 * forward特点:不和视图解析器 -同使用,就当项目中没有视图解析器
	 */
	
	@RequestMapping(value = "/doForward.do")
	public ModelAndView doSome(HttpServletRequest request, String name, Integer age) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("myname", name);
		mv.addObject("myage", age);
		
		// 使用forward可以转发到不是WEB-INF下的文件，比如webapp目录下的index.jsp
		// forward是以webapp为根目录
		mv.setViewName("forward:/WEB-INF/view/show.jsp");
		return mv;
	}
	
	/**
	 * 处理器方法返@Mode LAndView,实现转发redirect
	 * 语法: setViewName ( "redirect :视图文件完整路径" )
	 * redirect特点:不和视图解析器 -同使用,就当项目中没有视图解析器
	 * 框架对重定向的操作:
	 * 1.框架会把Model中的简单类型的数据,转为string使用,作为hello. jsp的get请求参数使用。
	 * 目的是在doRedirect.do 和hello. jsp两次请求之间传递数据
	 * 2.在目标hello. jsp页面可以使用参数集合对象${param} 获取请求参数值${param.mynamer}
	 * 3.重定向不能访问WEB-INF中内容
	 */
	
	@RequestMapping(value = "/doRedirect.do")
	public ModelAndView doRedirect(HttpServletRequest request, String name, Integer age) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("myname", name);
		mv.addObject("myage", age);
		
		// 使用redirect可以重定向到不是WEB-INF下的文件，比如webapp目录下的index.jsp
		// redirect是以webapp为根目录
		// 由于是重定向，导致request不共享，数据无法携带，但是框架会将数据拼接在url后面，要使用param.参数名获取参数
		// 重定向不能访问WEB-INF中内容
		mv.setViewName("redirect:/show.jsp");
		return mv;
	}
}
