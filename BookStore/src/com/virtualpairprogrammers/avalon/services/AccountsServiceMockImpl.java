package com.virtualpairprogrammers.avalon.services;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.virtualpairprogrammers.avalon.domain.Book;

// propagation "supports" will join a parent transaction when called from another transactional service 
// but will not create a transaction if called directly
@Transactional(propagation=Propagation.SUPPORTS)
public class AccountsServiceMockImpl implements AccountsService
{
	@Override
	public void raiseInvoice(Book requiredBook)
	{
		System.out.println("Raised the invoice for " + requiredBook);
	}
}
