package dao;

import domain.Book;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Author Admin
 * @Description // TODO
 * @Date 20:01 2021/4/21
 * @Param
 * @return
 */
@Repository
public interface BookDao {
	/**
	 * [获取全部图书]
	 * @return [返回图书列表]
	 */
	List<Book> getAllBooks();
	
	/**
	 * [根据图书id获取图书]
	 * @param bid [传入图书id]
	 * @return [返回图示实体类]
	 */
	Book getBookByBid(Integer bid);
	
	/**
	 * [添加图书]
	 * @param book [传入图书实体类]
	 * @return
	 */
	int addBook(Book book);
	
	/**
	 * [根据图书id删除图书]
	 * @param bid [传入图书id]
	 * @return
	 */
	int delBookByBid(Integer bid);
	
	/**
	 * [更新图书信息]
	 * @param book [传入图书实体类]
	 * @return
	 */
	int updateBook(Book book);
}
