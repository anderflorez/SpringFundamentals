package com.virtualpairprogrammers.avalon.services;

import com.virtualpairprogrammers.avalon.domain.Book;

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
	public void buyBook(String isbn)
	{
		Book requiredBook = books.getBookByIsbn(isbn);
		accounts.raiseInvoice(requiredBook);
	}
}