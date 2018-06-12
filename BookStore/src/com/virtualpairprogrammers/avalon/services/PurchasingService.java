package com.virtualpairprogrammers.avalon.services;

import com.virtualpairprogrammers.avalon.data.BookNotFoundException;

public interface PurchasingService
{	
//	void setAccountsService(AccountsService accounts);
//
//	void setBookService(BookService books);

	void buyBook(String isbn) throws BookNotFoundException, CustomerCreditExceededException;
}