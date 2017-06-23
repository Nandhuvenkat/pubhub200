package com.nandhu.dive.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nandhu.dive.model.Book;

@Repository

public class BookDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;


	public List<Book> findAll() {
		String sql = "select id,title,author_Name,description,price from book";

		List<Book> bookList = jdbcTemplate.query(sql, (rs, rowno) -> {
			Book book = new Book();
			book.setId(rs.getInt("id"));
			book.setTitle(rs.getString("title"));
			book.setAuthorName(rs.getString("author_Name"));
			book.setDescrption(rs.getString("description"));
			book.setPrice(rs.getInt("price"));
			return book;
		});
		return bookList;
	}
	
	public void save(Book book) {

		String sql = "insert into book (title,author_Name,description,price) values (?,?,?,?)";
		Object[] args = new Object[] {book.getTitle(),book.getAuthorName(),book.getDescrption(),book.getPrice()};
			int rows = jdbcTemplate.update(sql, args);
		System.out.println("No of rows inserted:" + rows);
	}

}
