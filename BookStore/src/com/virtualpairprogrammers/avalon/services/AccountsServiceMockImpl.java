package com.virtualpairprogrammers.avalon.services;

import com.virtualpairprogrammers.avalon.domain.Book;

public class AccountsServiceMockImpl implements AccountsService
{
	@Override
	public void raiseInvoice(Book requiredBook)
	{
		System.out.println("Raised the invoice for " + requiredBook);
	}
}
