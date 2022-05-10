package dao;

import domain.Book;
import domain.Borrow;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Admin
 * @Description // TODO
 * @Date 20:03 2021/4/21
 * @Param
 * @return
 */
@Repository
public interface BorrowDao {
	/**
	 * [获取所有借阅的书籍]
	 * @param uid
	 * @return
	 */
	List<Borrow> getBorrow(Integer uid);
	
	/**
	 * [模糊查询借阅图书]
	 * @param book
	 * @return
	 */
	List<Book> getBorrowByLike(Book book);
	
	/**
	 * [根据图书id查询借阅图书]
	 * @param bid
	 * @return
	 */
	Book getBorrowedBookByBid(Integer bid);
	
	/**
	 * [更新借阅图书状态]
	 * @param bid
	 * @param status
	 * @return
	 */
	int updateBorrowStatusByBid(@Param("bid") Integer bid, @Param("status") Integer status);
	
	/**
	 * [添加借阅图书]
	 * @param uid
	 * @param bid
	 * @return
	 */
	int addBorrow(@Param("uid") Integer uid, @Param("bid") Integer bid);
	
	/**
	 * [删除借阅图书]
	 * @param uid
	 * @param bid
	 * @return
	 */
	int delBorrow(@Param("uid") Integer uid, @Param("bid") Integer bid);
	
}


