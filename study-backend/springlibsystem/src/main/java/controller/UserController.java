package controller;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author Admin
 * @Description // TODO
 * @Date 20:03 2021/4/21
 * @Param
 * @return
 */
@Controller
public class UserController {
	@Autowired
	UserServiceImpl userService;
	
	@RequestMapping("/profile.do")
	public String borrowBooksCount(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int booksCount = userService.borrowBooksCount(user.getUid());
		session.setAttribute("borrowCount", booksCount);
		return "profile";
	}
	
	@RequestMapping("regist.do")
	public String regist(User user, HttpServletRequest request) {
		User user1 = userService.getUser(user);
		String msg = null;
		if (user1 != null) {
			msg = "该用户名已被注册，请重新输入！";
		} else {
			int i = userService.addUser(user);
			if (i != 0) {
				msg = "恭喜你注册成功！请使用该账户进行登录！";
			} else {
				msg = "注册失败！";
			}
		}
		request.setAttribute("msg", msg);
		return "regist";
	}
}
