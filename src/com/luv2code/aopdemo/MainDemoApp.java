package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {

		// read spring configuration class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get accountDAO bean from spring container
		AccountDAO accountDAO = context.getBean(AccountDAO.class);

		// get membershipDAO bean from spring container
		MembershipDAO membershipDAO = context.getBean(MembershipDAO.class);

		// call accountDAO business methods
		accountDAO.addAccount(new Account("John", "Platinum"), true);
		accountDAO.doWork();

		// call teh account DAO getter/setter methods
		accountDAO.setName("name");
		accountDAO.setServiceCode("code");
		String name = accountDAO.getName();
		String code = accountDAO.getServiceCode();

		// call membership dao business methods
		membershipDAO.addSillyMember();
		membershipDAO.goToSleep();

		// close the context
		context.close();
	}

}
