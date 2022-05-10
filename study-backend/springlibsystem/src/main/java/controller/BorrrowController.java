package controller;

import domain.Book;
import domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.BookServiceImpl;
import service.BorrowServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author Admin
 * @Description // TODO
 * @Date 20:02 2021/4/21
 * @Param
 * @return
 */
@Controller
public class BorrrowController {
	
	@Autowired
	BorrowServiceImpl borrowService;
	@Autowired
	BookServiceImpl bookService;
	
	@RequestMapping("/borrow.do")
	public String borrow(@Param("bid") Integer bid, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Book book = bookService.getBookByBid(bid);
		if (book.getBorrowed() == 1) {
			return "library";
		} else {
			int i = borrowService.borrowBook(user.getUid(), bid);
			List<Book> bookList = bookService.getAllBooks();
			session.setAttribute("allBooks", bookList);
			return "library";
		}
	}
}
