package com.virtualpairprogrammers.advice;

import org.aspectj.lang.ProceedingJoinPoint;

public class TimePerformanceAdvice
{
	public Object performanceMeasurement(ProceedingJoinPoint method) throws Throwable
	{
		long nanoSecondsInMilisecond = 1000000;
		long timebefore = System.nanoTime();
		
		try
		{
			Object result = method.proceed();
			return result;
		}
		finally
		{
			long timeafter = System.nanoTime();
			long totaltime = timeafter - timebefore;
			System.out.println("The method " + method.getSignature().getName() + " took " + totaltime / nanoSecondsInMilisecond + " miliseconds");
		}
	}
}
