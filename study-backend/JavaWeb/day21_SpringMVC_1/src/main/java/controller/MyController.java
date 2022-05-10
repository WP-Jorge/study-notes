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
	@RequestMapping(value = "/user/some")
	public ModelAndView doSome(HttpServletRequest request, String name, Integer age) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("myname", name);
		mv.addObject("myage", age);
		mv.setViewName("/index.jsp");
		return mv;
	}
	
}
