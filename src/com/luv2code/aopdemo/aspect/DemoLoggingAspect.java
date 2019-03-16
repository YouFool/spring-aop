package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class DemoLoggingAspect {

	@AfterReturning(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts())", returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
		
		// print out method wich we are advising on
		System.out.println("\n====>>> Executing @AfterReturning on method: " + joinPoint.getSignature().toLongString());
		
		// print out the results
		System.out.println("\n====>>> result is: " + result);
		
		// post-process the data
		
		// convert the acount names to uppercase
		result.stream().forEach(account -> account.setName(account.getName().toUpperCase()));
	}

	@Before("com.luv2code.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAcountAdvice(JoinPoint joinpoint) {
		System.out.println("\n=====>>> Executing @Before advice on method");

		// display method signature
		MethodSignature methodSignature = (MethodSignature) joinpoint.getSignature();
		System.out.println("Method: " + methodSignature);

		// display method arguments

		// get args
		Object[] args = joinpoint.getArgs();

		// loop through args
		for (Object arg : args) {
			System.out.println(arg);
			if (arg instanceof Account) {
				// downcast and print account specific fields
				Account account = (Account) arg;
				System.out.println("Account name: " + account.getName());
				System.out.println("Account level: " + account.getLevel());
			}
		}

	}

}
