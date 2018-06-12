package com.virtualpairprogrammers.avalon.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class SimpleLoggingAdvice2 implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] arguments, Object targetObject) throws Throwable
	{
		System.out.println("Now finished calling the " + method.getName() + " method");
		System.out.println("The target method returned the value: " + returnValue);
	}

}
