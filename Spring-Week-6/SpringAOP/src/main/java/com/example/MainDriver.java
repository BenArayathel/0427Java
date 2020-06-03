package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainDriver {
	
	public static void main(String[] args) {
		
		ApplicationContext appCon = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		MyAppProxy app = appCon.getBean("appProxy", MyAppProxy.class);
		
		app.randomMethod();
		
		app.anotherMethod(2);
		
		app.doingBJJ();
		app.doingDiscGold(7);
//		app.doingJudo('a', 3);
		
	}

}
