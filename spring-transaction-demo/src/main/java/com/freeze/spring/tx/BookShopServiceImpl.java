package com.freeze.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {
	
	@Autowired
	private BookShopDao bookShopDao;

	@Transactional(propagation = Propagation.REQUIRES_NEW,
			isolation = Isolation.READ_COMMITTED)
	public void purchase(String username, String isbn) {
		
		// 1.获取书的单价
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		
		// 2.更新书的库存
		bookShopDao.updateBookStock(isbn);
		
		// 3.更新用户余额
		bookShopDao.updateUserAccount(username, price);
	}

}
