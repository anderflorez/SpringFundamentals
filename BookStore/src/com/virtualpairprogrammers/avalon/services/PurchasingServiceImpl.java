package com.virtualpairprogrammers.avalon.services;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.virtualpairprogrammers.avalon.data.BookNotFoundException;
import com.virtualpairprogrammers.avalon.domain.Book;

@Transactional
public class PurchasingServiceImpl implements PurchasingService
{
	private AccountsService accounts;
	private BookService books;
	
	//Constructor needed for the constructor injection used in the xml file
	//This is used by the constructor-arg in the bean of the xml file
	public PurchasingServiceImpl(AccountsService accounts, BookService books)
	{
		this.accounts = accounts;
		this.books = books;
	}

	//Methods needed for the setter injection used in the xml file
	//These are used by the properties in the bean created in the xml file	
//	@Override
//	public void setAccountsService(AccountsService accounts)
//	{
//		this.accounts = accounts;
//	}
//	
//	@Override
//	public void setBookService(BookService books)
//	{
//		this.books = books;
//	}
	
	@Override
	//The transaction will rollback on either of the exceptions or after the 10 second timeout
	//it also sets isolation level to serializable for this method
	@Transactional(rollbackFor= {CustomerCreditExceededException.class, BookNotFoundException.class}, 
				timeout=10, isolation=Isolation.SERIALIZABLE)
	public void buyBook(String isbn) throws BookNotFoundException, CustomerCreditExceededException
	{
		//find the correct book
		Book requiredBook = books.getBookByIsbn(isbn);
		
		//delete the book from stock
		books.deleteFromStock(requiredBook);
		
		//now raise the invoice
		accounts.raiseInvoice(requiredBook);
	}
}
