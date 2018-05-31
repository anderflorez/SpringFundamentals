package com.virtualpairprogrammers.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.virtualpairprogrammers.motd.MessageOfTheDayService;

public class ClientApplication
{
	public static void main(String[] args)
	{
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
		
		//when there is only one bean in the xml file there is no need to specify the id here
		MessageOfTheDayService helloWorld = container.getBean("motdService3", MessageOfTheDayService.class);
		System.out.println(helloWorld.getTodaysMessage());
		
		container.close();
	}

}
