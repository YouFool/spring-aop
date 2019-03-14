package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	// here we add all services related to logging

	@Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	public void beforeAddAcountAdvice() {
		System.out.println("\n=====>>> Executing @Before advice on method");
	}

}
