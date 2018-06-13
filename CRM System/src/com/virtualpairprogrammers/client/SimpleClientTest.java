package com.virtualpairprogrammers.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.virtualpairprogrammers.domain.Action;
import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;
import com.virtualpairprogrammers.services.calls.CallHandlingService;
import com.virtualpairprogrammers.services.customers.CustomerManagementService;
import com.virtualpairprogrammers.services.customers.CustomerNotFoundException;
import com.virtualpairprogrammers.services.diary.DiaryManagementService;

public class SimpleClientTest {

	public static void main(String[] args)
	{
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");

		//		CustomerManagementService customerService = container.getBean("customerService", CustomerManagementService.class);
		//		
		//		List<Customer> allCustomers = customerService.getAllCustomers();
		//		for (Customer next : allCustomers)
		//		{
		//			System.out.println(next);
		//		}

		try
		{
			CustomerManagementService customerService = container.getBean(CustomerManagementService.class);
			CallHandlingService callService = container.getBean(CallHandlingService.class);
			DiaryManagementService diaryService = container.getBean(DiaryManagementService.class);

			Customer newCustomer = new Customer("CS03939", "Acme", "Good Company");
			customerService.newCustomer(newCustomer);

			//try {
			//	Customer foundCustomer = customerService.findCustomerById("CS03939");
			//	foundCustomer.setTelephone("6323003");
			//	foundCustomer.setEmail("larry@acme.com");
			//	customerService.updateCustomer(foundCustomer);
			//	customerService.deleteCustomer(foundCustomer);
			//} catch (Exception e) {
			//	System.out.println("That customer could not be found");
			//}

			Call newCall = new Call("Larry will call from Acme Corp");
			Action action1 = new Action("Call back Larry to ask how things are going", new GregorianCalendar(2016, 0, 0), "rac");
			Action action2 = new Action("Check our sales dept to make sure Larry is being tracked", new GregorianCalendar(2016, 0, 0), "rac");

			List<Action> actions = new ArrayList<>();
			actions.add(action1);
			actions.add(action2);

			try
			{
				callService.recordCall("CS03939", newCall, actions);
			} 
			catch (CustomerNotFoundException e)
			{
				System.out.println("Customer does not exist");
			}

			System.out.println("Here are the outstanding actions: ");
			Collection<Action> incompleteActions = diaryService.getAllIncompleteActions("rac");
			for (Action next : incompleteActions)
			{
				System.out.println(next);
			}
		}
		finally
		{
			container.close();
		}	
	}

}
