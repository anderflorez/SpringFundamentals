package com.virtualpairprogrammers.avalon.services;

import java.util.List;

import com.virtualpairprogrammers.avalon.data.BookNotFoundException;
import com.virtualpairprogrammers.avalon.domain.Book;

public class BookServiceTimingProxy implements BookService {
	
	private BookService originalBookService;
	
	public void setOriginalBookService(BookService original)
	{
		this.originalBookService = original;
	}

	@Override
	public List<Book> getAllBooksByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getAllRecommendedBooks(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBookByIsbn(String isbn) throws BookNotFoundException {
		long nanoSecondsInAMillisecond = 1000000;
		long timeThen = System.nanoTime();
		
		Book foundBook;
		try 
		{
			foundBook = originalBookService.getBookByIsbn(isbn);
			return foundBook;
		}
		finally
		{
			long timeNow = System.nanoTime();
			long timeTaken = timeNow - timeThen;
			System.out.println("getBookByIsbn took: " + timeTaken / nanoSecondsInAMillisecond + " milliseconds.");
		}
	}

	@Override
	public List<Book> getEntireCatalogue() {
		long nanoSecondsInAMillisecond = 1000000;
		long timeThen = System.nanoTime();
		
		List<Book> allBooks = originalBookService.getEntireCatalogue();
		
		long timeNow = System.nanoTime();
		long timeTaken = timeNow - timeThen;
		System.out.println("getEntireCatalogue took: " + timeTaken / nanoSecondsInAMillisecond + " milliseconds.");
		
		return allBooks;
	}

	@Override
	public void registerNewBook(Book newBook) {
		long nanoSecondsInAMillisecond = 1000000;
		long timeThen = System.nanoTime();
		
		originalBookService.registerNewBook(newBook);
		
		long timeNow = System.nanoTime();
		long timeTaken = timeNow - timeThen;
		System.out.println("registerNewBook took: " + timeTaken / nanoSecondsInAMillisecond + " milliseconds.");
	}

}
