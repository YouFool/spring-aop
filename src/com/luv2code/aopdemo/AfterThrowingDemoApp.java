package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;

public class AfterThrowingDemoApp {

	public static void main(String[] args) {

		// read spring configuration class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get accountDAO bean from spring container
		AccountDAO accountDAO = context.getBean(AccountDAO.class);
		
		// find the accounts
		List<Account> accounts = null;
		try {
			accountDAO.findAccounts(true);
		} catch (Exception e) {
			System.out.println("\n\nCaught excepton " + e);
		}
		
		// display the accounts
		System.out.println("\n\nMain Program: AfterThrowing");
		System.out.println("----");
		System.out.println(accounts);

		// close the context
		context.close();
	}

}
