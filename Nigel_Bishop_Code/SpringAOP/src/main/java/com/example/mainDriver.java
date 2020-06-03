package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class mainDriver {
	
	public static void main(String[] args) {
		
		ApplicationContext appCon = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		MyAppProxy app = appCon.getBean("appProxy", MyAppProxy.class);
		
		app.randomMethod();
		
		app.anotherMethod();
		
//		app.doingJudo('a', 3);
		app.doingStuffAgain();
//		app.doingDisco(5);
	}
}
