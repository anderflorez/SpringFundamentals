package com.virtualpairprogrammers.avalon.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class PerformanceTimingAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation method) throws Throwable
	{
		//before
		long nanoSecondsInAMillisecond = 1000000;
		long timeThen = System.nanoTime();
		
		try
		{
			//proceed to target
			Object returnValue = method.proceed();
			
			return returnValue;
		}
		finally
		{
			//after
			long timeNow = System.nanoTime();
			long timeTaken = timeNow - timeThen;
			System.out.println("The method " + method.getMethod().getName() + " took " + timeTaken / nanoSecondsInAMillisecond + " milliseconds.");
		}
	}

}
