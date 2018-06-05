package com.virtualpairprogrammers.avalon.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.virtualpairprogrammers.avalon.domain.Book;

public class BookDaoCleanerJdbcImpl implements BookDao
{
	private JdbcTemplate jdbcTemplate;
	
	// SQL
	private static final String INSERT_BOOK_SQL = "insert into BOOK (ISBN, TITLE, AUTHOR,PRICE) values (?, ?, ?, ?) ";
	private static final String CREATE_TABLE_SQL = "create table BOOK(ISBN VARCHAR(20), TITLE VARCHAR(50), AUTHOR VARCHAR(50), PRICE DOUBLE)";
	private static final String GET_ALL_BOOKS_SQL = "select * from BOOK";
	
	public BookDaoCleanerJdbcImpl (JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private void createTables()
	{
		try
		{
			jdbcTemplate.update(CREATE_TABLE_SQL);
		} 
		catch (BadSqlGrammarException e)
		{
			System.out.println("Assuming that the table already exists");
		}
		catch (DataAccessException e)
		{
			System.err.println(e);
		}
	}

	@Override
	public List<Book> allBooks()
	{
		return jdbcTemplate.query(GET_ALL_BOOKS_SQL, new BookMapper());
	}

	@Override
	public Book findByIsbn(String isbn) throws BookNotFoundException
	{
		// return jdbcTemplate.queryForObject("SELECT * FROM BOOK WHERE ISBN = ?", new BookMapper(), isbn);
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM BOOK WHERE ISBN = ?", Book.class, isbn);
		}
		catch (EmptyResultDataAccessException e)
		{
			throw new BookNotFoundException();
		}
	}

	@Override
	public void create(Book newBook)
	{
		//jdbcTemplate.update(INSERT_BOOK_SQL, new Object[] {newBook.getIsbn(), newBook.getTitle(), newBook.getAuthor(), newBook.getPrice()});
		jdbcTemplate.update(INSERT_BOOK_SQL, newBook.getIsbn(), newBook.getTitle(), newBook.getAuthor(), newBook.getPrice());
	}

	@Override
	public void delete(Book redundantBook)
	{
		jdbcTemplate.update("DELETE FROM BOOK WHERE ISBN = ?", redundantBook.getIsbn());
	}

	@Override
	public List<Book> findBooksByAuthor(String author) {
		return jdbcTemplate.query("SELECT * FROM BOOK WHERE AUTHOR = ?", new BookMapper(), author);
	}

}

class BookMapper implements RowMapper<Book>
{
	@Override
	public Book mapRow(ResultSet rs, int rowNumber) throws SQLException
	{
		String isbn = rs.getString("ISBN");
		String title = rs.getString("TITLE");
		String author = rs.getString("AUTHOR");
		double price = rs.getDouble("PRICE");
		Book theBook = new Book(isbn, title, author, price);
		return theBook;
	}
	
}
