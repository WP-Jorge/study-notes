package service;

import dao.BorrowDao;
import domain.Book;
import domain.Borrow;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Admin
 * @Description // TODO 
 * @Date 20:03 2021/4/21
 * @Param 
 * @return 
 */
@Service
public class BorrowServiceImpl implements BorrowService {
	@Autowired
	BorrowDao borrowDao;
	
	@Override
	public List<Borrow> getBorrow(Integer uid) {
		return borrowDao.getBorrow(uid);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int borrowBook(Integer uid, Integer bid) {
		int i1 = borrowDao.updateBorrowStatusByBid(bid, 1);
		int i2 = borrowDao.addBorrow(uid, bid);
		if (i1 > 0 && i2 > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int restituteBook(Integer uid, Integer bid) {
		int i1 = borrowDao.updateBorrowStatusByBid(bid, 0);
		int i2 = borrowDao.delBorrow(uid, bid);
		if (i1 > 0 && i2 > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public List<Book> getBorrowByLike(Book book) {
		return borrowDao.getBorrowByLike(book);
	}
	
	@Override
	public Book getBorrowedBookByBid(Integer bid) {
		return borrowDao.getBorrowedBookByBid(bid);
	}
	
}
