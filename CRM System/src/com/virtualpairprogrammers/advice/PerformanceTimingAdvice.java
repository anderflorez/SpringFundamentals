package com.virtualpairprogrammers.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component("performanceAdvice")
public class PerformanceTimingAdvice
{
	private static final int NANOSECONDS_IN_A_MILLISECOND = 1000000;

	public Object recordTiming(ProceedingJoinPoint jp) throws Throwable
	{
		double timeNow = System.nanoTime();
		
		try
		{
			Object returnValue = jp.proceed();
			return returnValue;
		}
		finally
		{
			double timeAfter = System.nanoTime();
			double timeTaken = timeAfter - timeNow;
			System.out.println("The method " + jp.getSignature().getName() + " took " + timeTaken / NANOSECONDS_IN_A_MILLISECOND + " miliseconds");
		}
	}
}
