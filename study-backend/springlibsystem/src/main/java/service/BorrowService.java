package service;

import domain.Book;
import domain.Borrow;

import java.util.List;

/**
 * @Author Admin
 * @Description // TODO 
 * @Date 20:03 2021/4/21
 * @Param 
 * @return 
 */
public interface BorrowService {
	/**
	 * [获取所有借阅的书籍]
	 * @param uid
	 * @return
	 */
	List<Borrow> getBorrow(Integer uid);
	
	/**
	 * [借阅图书]
	 * @param uid
	 * @param bid
	 * @return
	 */
	int borrowBook(Integer uid, Integer bid);
	
	/**
	 * [归还图书]
	 * @param uid
	 * @param bid
	 * @return
	 */
	int restituteBook(Integer uid, Integer bid);
	
	/**
	 * [模糊查询借阅图书]
	 * @param book
	 * @return
	 */
	List<Book> getBorrowByLike(Book book);
	
	/**
	 * [根据借阅id查询借阅图书]
	 * @param bid
	 * @return
	 */
	Book getBorrowedBookByBid(Integer bid);
}
