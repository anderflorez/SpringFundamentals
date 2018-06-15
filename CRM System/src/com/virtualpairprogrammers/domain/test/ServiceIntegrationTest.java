package com.virtualpairprogrammers.domain.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.virtualpairprogrammers.domain.Action;
import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;
import com.virtualpairprogrammers.services.calls.CallHandlingService;
import com.virtualpairprogrammers.services.customers.CustomerManagementService;
import com.virtualpairprogrammers.services.customers.CustomerNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/application-JPA-annotations.xml", "/test-datasource.xml"})
@Transactional
public class ServiceIntegrationTest {
	
	@Autowired
	private CustomerManagementService customerService;
	
	@Autowired
	private CallHandlingService callService;
	
//	@Autowired
//	private DiaryManagementService diaryService;

	@Test
	public void testSaveAndFindACustomer()
	{
		Customer customer = new Customer("000123", "Test Company Name", "Company for Testing");
		customerService.newCustomer(customer);
		
		List<Customer> allCustomers = customerService.getAllCustomers();
		
		assertEquals(1, allCustomers.size());
	}
	
	@Test
	public void testFindingACustomer()
	{
		Customer customer = new Customer("000123", "Test Company Name", "Company for Testing");
		customerService.newCustomer(customer);
		
		try
		{
			customerService.findCustomerById("000123");
		} 
		catch (CustomerNotFoundException e)
		{
			fail("The saved customer could not be found");
		}
	}
	
	@Test(expected = CustomerNotFoundException.class)
	public void testCustomerNotFound() throws CustomerNotFoundException
	{
		customerService.findCustomerById("000121");
	}
	
	@Test
	public void testAddingACallToACustomer()
	{
		Customer customer = new Customer("000123", "Test Company Name", "Company for Testing");
		customerService.newCustomer(customer);
		
		Call testCall = new Call("Just A Call");
		List<Action> actions = new ArrayList<>();
		
		try
		{
			callService.recordCall("000123", testCall, actions);
			
			Customer foundCustomer = customerService.getFullCustomerDetail("000123");
			Call foundCall = foundCustomer.getCalls().get(0);
			
			assertEquals(testCall, foundCall);
		}
		catch (CustomerNotFoundException e)
		{
			fail("Customer was not found");
		}
	}

}
