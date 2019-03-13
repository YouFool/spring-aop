package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;

public class MainDemoApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get bean from spring container
		AccountDAO accountDAO = context.getBean(AccountDAO.class);

		// call the business method
		accountDAO.addAccount();
		
		// do it again!
		System.out.println("\n let's call it again!");
		accountDAO.addAccount();

		// close the context
		context.close();
	}

}
