package com.virtualpairprogrammers.avalon.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.virtualpairprogrammers.avalon.domain.Book;

@Repository
public class BookDaoJpaImpl implements BookDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Book> allBooks()
	{
		return em.createQuery("select book from Book as book", Book.class).getResultList();
	}

	@Override
	public Book findByIsbn(String isbn) throws BookNotFoundException
	{
		try
		{
			return em.createQuery("Select book from Book as book where book.isbn=:isbn", Book.class)
					.setParameter("isbn", isbn).getSingleResult();
		} 
		catch (NoResultException e)
		{
			throw new BookNotFoundException();
		}
	}

	@Override
	public void create(Book newBook)
	{
		em.persist(newBook);
	}

	@Override
	public void delete(Book redundantBook)
	{
		Book book = em.find(Book.class, redundantBook.getId());
		em.remove(book);
	}

	@Override
	public List<Book> findBooksByAuthor(String author)
	{
		return em.createQuery("select book from Book as book where book.author = :author", Book.class).setParameter("author", author).getResultList();
	}

}
