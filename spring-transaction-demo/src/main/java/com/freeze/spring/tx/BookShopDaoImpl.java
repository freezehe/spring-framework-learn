package com.freeze.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int findBookPriceByIsbn(String isbn) {
		String sql = "SELECT price FROM book WHERE isbn = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, isbn);
	}

	public void updateBookStock(String isbn) {
		// 检查书的库存是否足够,若不够则抛出异常
		String sql2 = "SELECT stock FROM book_stock WHERE isbn = ?";
		int stock = jdbcTemplate.queryForObject(sql2, Integer.class,isbn);
		if(0 == stock) {
			throw new BookStockException("库存不足!");
		}
		
		String sql = "UPDATE book_stock SET stock = stock -1 WHERE isbn = ?";
		jdbcTemplate.update(sql, isbn);
		
	}

	public void updateUserAccount(String username, int price) {
		
		// 检查余额是否足够,若不够则抛出异常
		String sql2 = "SELECT balance  FROM account WHERE username = ?";
		int balance = jdbcTemplate.queryForObject(sql2, Integer.class,username);
		if(balance < price) {
			throw new UserAccountException("余额不足!");
		}
		
		String sql = "UPDATE account SET balance = balance - ? WHERE username = ?";
		jdbcTemplate.update(sql, price,username);
	}

}
