package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	
	private static final Logger LOGGER = Logger.getLogger(DemoLoggingAspect.class.getName());
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		
		// print out method we are advising
		LOGGER.info("\n====>>> Executing @Around on method: " + proceedingJoinPoint.getSignature().toShortString());
		
		// get begin timestamp
		long begin = System.currentTimeMillis();
		
		// exec method
		Object result = null;
		try {
			result = proceedingJoinPoint.proceed();
		} catch (Exception e) {
			// log the expection
			LOGGER.warning(e.getMessage());
			
			// rethow the exception
			throw e;
		}
		
		// get end timestamp
		long end = System.currentTimeMillis();
		LOGGER.info("\n ===> Duration: " + (end - begin)/ 1000.0 + " seconds");
		
		return result;
	}
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
		LOGGER.info("\n====>>> Executing @After (finally) on method: " + joinPoint.getSignature().toLongString());
	}
	
	@AfterThrowing(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", throwing = "throwable")
	public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable throwable) {
		LOGGER.info("\n====>>> Executing @AfterThrowing on method: " + joinPoint.getSignature().toLongString());
		LOGGER.info("\n====>>> The expection is: " + throwable);
	}

	@AfterReturning(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
		
		// print out method wich we are advising on
		LOGGER.info("\n====>>> Executing @AfterReturning on method: " + joinPoint.getSignature().toLongString());
		
		// print out the results
		LOGGER.info("\n====>>> result is: " + result);
		
		// post-process the data
		
		// convert the acount names to uppercase
		result.stream().forEach(account -> account.setName(account.getName().toUpperCase()));
	}

	@Before("com.luv2code.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAcountAdvice(JoinPoint joinpoint) {
		LOGGER.info("\n=====>>> Executing @Before advice on method");

		// display method signature
		MethodSignature methodSignature = (MethodSignature) joinpoint.getSignature();
		LOGGER.info("Method: " + methodSignature);

		// display method arguments

		// get args
		Object[] args = joinpoint.getArgs();

		// loop through args
		for (Object arg : args) {
			LOGGER.info(arg.toString());
			if (arg instanceof Account) {
				// downcast and print account specific fields
				Account account = (Account) arg;
				LOGGER.info("Account name: " + account.getName());
				LOGGER.info("Account level: " + account.getLevel());
			}
		}

	}

}
