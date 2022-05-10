package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyController {
	@RequestMapping(value = "/some.do")
	public ModelAndView doSome(HttpServletRequest request, String name, Integer age) {
		System.out.println("执行MyController");
		ModelAndView mv = new ModelAndView();
		mv.addObject("myname", name);
		mv.addObject("myage", age);
		mv.setViewName("show");
		return mv;
	}
}
