package controller;

import dao.UserDao;
import domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author Admin
 * @Description // TODO
 * @Date 20:02 2021/4/21
 * @Param
 * @return
 */
@Controller
public class LoginController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	UserServiceImpl userService;
	
	@RequestMapping("/login.do")
	public ModelAndView login(User user, HttpServletRequest request, HttpServletResponse response) {
		final ModelAndView mv = new ModelAndView();
		User user1 = userService.getUser(user);
		HttpSession session = request.getSession();
		session.setAttribute("user", user1);
		if (user1 == null) {
			mv.addObject("msg", "用户名或密码错误！");
			mv.setViewName("login");
			return mv;
		}
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "login";
	}
}
