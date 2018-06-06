package com.virtualpairprogrammers.avalon.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class SimpleLoggingAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] arguments, Object targetObject) throws Throwable
	{
		System.out.println("Now about to call " + method.getName() + " method");
	}

}
