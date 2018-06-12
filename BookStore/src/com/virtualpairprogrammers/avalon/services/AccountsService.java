package com.virtualpairprogrammers.avalon.services;

import com.virtualpairprogrammers.avalon.domain.Book;

public interface AccountsService
{
	void raiseInvoice(Book requiredBook) throws CustomerCreditExceededException;
}