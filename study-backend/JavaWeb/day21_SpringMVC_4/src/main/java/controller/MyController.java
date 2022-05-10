package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.AgeException;
import exception.MyUserException;
import exception.NameException;
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
	@RequestMapping(value = "/some.do")
	public ModelAndView doSome(HttpServletRequest request, String name, Integer age) throws MyUserException {
		ModelAndView mv = new ModelAndView();
		// 根据请求参数泡池异常
		if (!"张三".equals(name)) {
			throw new NameException("姓名不正确");
		}
		if (age == null || age > 80) {
			throw new AgeException("年龄不正确");
		}
		// int i = 1 / 0;
		
		mv.addObject("myname", name);
		mv.addObject("myage", age);
		mv.setViewName("show");
		return mv;
	}
}
