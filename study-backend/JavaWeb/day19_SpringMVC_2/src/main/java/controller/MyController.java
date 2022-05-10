package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Reques tMapping;
 * value：所有请求地址的公共部分，叫做模块名称
 * 位置：放在类的上面
 */
@Controller
@RequestMapping(value = "/user")
public class MyController {
	
	/**
	 * @RequestMapping: 请求映射
	 * 属性：method，表示请求方式，值是RequestMethod类的枚举值
	 * 例如表示get请求方式，Reques tMethod.GET
	 * post方式，Reques tMethod.POST
	 */
	// @RequestMapping(value = "/receiveparam.do", params = {"username=heihei"}) 表示请求参数必须有username，参数值必须要为heihei
	// 指定some.do使用get请求方式
	@RequestMapping(value = "/some.do", method = RequestMethod.GET)
	public ModelAndView doSome() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "欢迎使用springmvc做web开发");
		mv.addObject("fun", "执行的是doSome方法");
		mv.setViewName("show");
		return mv;
	}
	
	// 指定other.do使用post请求方式
	@RequestMapping(value = "/other.do", method = RequestMethod.POST)
	public ModelAndView doOther() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "-------欢迎使用springmvc做web开发--------");
		mv.addObject("fun", "执行的是doOther方法");
		mv.setViewName("other");
		return mv;
	}
	
	// 不指定请求方式，没有限制
	@RequestMapping(value = "/first.do")
	public ModelAndView doFirst(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "--欢迎使用springmvc做web开发--" + request.getParameter("name"));
		mv.addObject("fun", "执行的是doFirstr方法");
		mv.setViewName("other");
		return mv;
	}
}
