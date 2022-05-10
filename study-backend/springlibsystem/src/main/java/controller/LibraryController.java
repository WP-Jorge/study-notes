package controller;

import domain.Book;
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
public class LibraryController {
	
	@Autowired
	BookServiceImpl bookService;
	@Autowired
	BorrowServiceImpl borrowService;
	
	@RequestMapping("/library.do")
	public ModelAndView library(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		List<Book> allBooks = bookService.getAllBooks();
		mv.addObject("allBooks", allBooks);
		mv.setViewName("library");
		HttpSession session = request.getSession();
		session.setAttribute("allBooks", allBooks);
		return mv;
	}
	
	@RequestMapping("/search.do")
	public String search(Book book, HttpServletRequest request) {
		book.setBname(book.getBname().trim());
		book.setByear(book.getByear().trim());
		book.setBtype(book.getBtype().trim());
		List<Book> bookList = borrowService.getBorrowByLike(book);
		request.setAttribute("bname", book.getBname());
		request.setAttribute("btype", book.getBtype());
		request.setAttribute("byear", book.getByear());
		HttpSession session = request.getSession();
		session.setAttribute("allBooks", bookList);
		return "library";
	}
}
