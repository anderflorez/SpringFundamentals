package com.virtualpairprogrammers.motd;

public class MessageOfTheDayBasicImpl implements MessageOfTheDayService {
	
	private String message;
	
	// method to inject the message from the xml bean configuration
	public void setMessage(String message) 
	{
		this.message = message;
	}

	@Override
	public String getTodaysMessage()
	{		
		return this.message;
	}

}
