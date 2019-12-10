package com.freeze.spring.tx;

public interface BookShopDao {
	
	/**
	 * 根据书号获取书的单价
	 * @param isbn
	 * @return
	 */
	int findBookPriceByIsbn(String isbn);
	/**
	 * 更新书的库存，使书号对应的库存-1
	 * @param isbn
	 */
	void updateBookStock(String isbn);
	
	/**
	 * 更新用户的账户余额:使username
	 * @param username
	 * @param price
	 */
	void updateUserAccount(String username,int price);

}
