package service;


import dao.BookDao;
import domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Admin
 * @Description // TODO 
 * @Date 20:02 2021/4/21
 * @Param 
 * @return 
 */
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookDao bookDao;
	
	@Override
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}
	
	@Override
	public Book getBookByBid(Integer bid) {
		return bookDao.getBookByBid(bid);
	}
	
	@Override
	public int addBook(Book book) {
		return bookDao.addBook(book);
	}
	
	@Override
	public int delBookByBid(Integer bid) {
		return bookDao.delBookByBid(bid);
	}
	
	@Override
	public int updateBook(Book book) {
		return bookDao.updateBook(book);
	}
}
