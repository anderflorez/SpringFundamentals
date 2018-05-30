package com.virtualpairprogrammers.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.virtualpairprogrammers.motd.MessageOfTheDayService;

public class ClientApplication
{
	public static void main(String[] args)
	{
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
		
		MessageOfTheDayService helloWorld = container.getBean("motdService2", MessageOfTheDayService.class);
		System.out.println(helloWorld.getTodaysMessage());
		
		container.close();
	}

}
