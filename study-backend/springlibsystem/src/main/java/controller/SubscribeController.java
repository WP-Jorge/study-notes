package controller;

import dao.BookDao;
import dao.BorrowDao;
import domain.Book;
import domain.Borrow;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.BookServiceImpl;
import service.BorrowServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Author Admin
 * @Description // TODO
 * @Date 20:02 2021/4/21
 * @Param
 * @return
 */
@Controller
public class SubscribeController {
	@Autowired
	BookServiceImpl bookService;
	@Autowired
	BorrowServiceImpl borrowService;
	
	@RequestMapping("/subscribe.do")
	public String subscribe(HttpServletRequest request, Map map) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Borrow> borrowList = borrowService.getBorrow(user.getUid());
		map.put("borrowList", borrowList);
		return "subscribe";
	}
	
	@RequestMapping("/restitute.do")
	public String restituteBook(Integer bid, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Book book = bookService.getBookByBid(bid);
		if (book.getBorrowed() == 0) {
			return "subscribe";
		} else {
			borrowService.restituteBook(user.getUid(), bid);
			List<Borrow> bookList = borrowService.getBorrow(user.getUid());
			session.setAttribute("allBooks", bookList);
			return "subscribe";
		}
	}
}
