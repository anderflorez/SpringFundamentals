package com.virtualpairprogrammers.avalon.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.virtualpairprogrammers.avalon.data.BookDao;
import com.virtualpairprogrammers.avalon.data.BookNotFoundException;
import com.virtualpairprogrammers.avalon.domain.Book;
import com.virtualpairprogrammers.avalon.services.BookService;
import com.virtualpairprogrammers.avalon.services.PurchasingService;

public class Client 
{
	public static void main(String[] args)
	{
		//Testing BookService
//		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
//		BookService service = container.getBean("bookService", BookService.class);
//		
//		List<Book> allBooks = service.getEntireCatalogue();
//		for (Book next : allBooks)
//		{
//			System.out.println(next);
//		}
//		
//		container.close();
		
		
		//Testing PurchasingService
//		System.out.println("\nTesting buying a book...");
//		String requiredIsbn = "ISBN1";		//we know this isbn is pressent in the mock
//		
//		ClassPathXmlApplicationContext purchaseContainer = new ClassPathXmlApplicationContext("application.xml");
//		PurchasingService purchasing = purchaseContainer.getBean("purchasingService", PurchasingService.class);
//		
//		purchasing.buyBook(requiredIsbn);
//		
//		purchaseContainer.close();
		
		
		// Testing database connections
//		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
//		
//		try
//		{
//			BookService bookService = container.getBean("bookService", BookService.class);
//			bookService.registerNewBook(new Book("2384928389223", "War and Peace", "Leo Tolstoy", 10.99));
//			List<Book> allBooks = bookService.getEntireCatalogue();
//			for (Book next : allBooks)
//			{
//				System.out.println(next);
//			}
//			try
//			{
//				Book foundBook = bookService.getBookByIsbn("vlakifnvbolikejvsopli");
//			}
//			catch (BookNotFoundException e)
//			{
//				System.err.println("Sorry, that book does not exist");
//			}
//		}
//		finally
//		{
//			container.close();			
//		}
		
		
		// Testing Transactions
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
		
		try
		{
			PurchasingService purchasing = container.getBean(PurchasingService.class);
			BookService bookService = container.getBean(BookService.class);
			
			bookService.registerNewBook(new Book("10003993939", "Test Title", "Author", 10.99));
			
			try
			{
				purchasing.buyBook("10003993939");
			} 
			catch (BookNotFoundException e)
			{
				System.out.println("Sorry, that book doesn't exist");
			}			
		}
		finally
		{
			container.close();
		}
		
	}
}
