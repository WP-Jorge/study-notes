package service;

import domain.Book;
import java.util.List;

/**
 * @Author Admin
 * @Description // TODO 
 * @Date 20:02 2021/4/21
 * @Param 
 * @return 
 */
public interface BookService {
	/**
	 * [获取全部图书]
	 * @return
	 */
	List<Book> getAllBooks();
	
	/**
	 * 根据图书id获取图书
	 * @param bid
	 * @return
	 */
	Book getBookByBid(Integer bid);
	
	/**
	 * 添加图书
	 * @param book
	 * @return
	 */
	int addBook(Book book);
	
	/**
	 * 根据图书id删除图书
	 * @param bid
	 * @return
	 */
	int delBookByBid(Integer bid);
	
	/**
	 * 更新图书信息
	 * @param book
	 * @return
	 */
	int updateBook(Book book);
}
