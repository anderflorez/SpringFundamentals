package com.virtualpairprogrammers.avalon.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PerformanceTimingAdvice2 
{
	@Pointcut("execution (* com.virtualpairprogrammers.avalon.services.*.* (..))")
	public void allServiceMethods() {}
	
	@Around("allServiceMethods()")
	public Object performTimingMeasurement(ProceedingJoinPoint method) throws Throwable
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
			System.out.println("The method " + method.getSignature().getName() + " took " + timeTaken / nanoSecondsInAMillisecond + " milliseconds.");
		}
	}
	
	@Before("allServiceMethods()")
	public void beforeAdviceTesting(JoinPoint jp)
	{
		System.out.println("Now entering a method..." + jp.getSignature().getName());
	}

}
