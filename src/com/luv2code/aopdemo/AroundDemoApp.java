package com.luv2code.aopdemo;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.service.TrafficFortuneService;

public class AroundDemoApp {
	
	private static final Logger LOGGER = Logger.getLogger(AroundDemoApp.class.getName());

	public static void main(String[] args) {

		// read spring configuration class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		TrafficFortuneService trafficFortuneService = context.getBean(TrafficFortuneService.class);
		
		LOGGER.info("Calling getFortune");
		LOGGER.info("\nMy fortune is: " + trafficFortuneService.getFortune());
		LOGGER.info("Finished");

		// close the context
		context.close();
	}

}
